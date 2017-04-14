#! /bin/sh

echo "============================ start ============================"

# set base var
CODE_PATH=/home/adolphor/workspace/test
PROGRAM_NAME=jy_rms_quartz
DEPLOY_NAME=rmsJobs
BACKUP_PATH=/home/adolphor/temp
TOMCAT_HOME=/home/adolphor/workspace/tomcat-8.0.37

echo "此脚本执行之前请先执行SQL升级脚本\n"
read -p '是否继续（默认 n ）？ [y/n]' isContinue
if [ "$isContinue" != "y" ]; then
  echo "正常退出"
  exit 1
fi

compileFun(){
  # workspacce
  cd ${CODE_PATH}
  if [ $? -ne 0 ]; then
    echo "'CODE_PATH' 参数设置错误"
    exit 1
  fi
  # update origin code
  git pull origin master
  if [ $? -ne 0 ]; then
    exit 1
  else
    echo "\n源码更新完成\n"
    sleep 1
  fi
  # compile
  mvn clean package -D maven.test.skip -P test
  if [ $? -ne 0 ]; then
    exit 1
  else
    echo "\n源码编译成功\n"
    sleep 1
  fi
  # rename
  mv ./target/${PROGRAM_NAME} ./target/${DEPLOY_NAME}
}

backupFun(){
  # ssh
  date=`date +%F`
  time=`date +%T`
  BAK_PATH=${BACKUP_PATH}/${DEPLOY_NAME}

  if [ ! -d "${BAK_PATH}" ]; then
    mkdir -p ${BAK_PATH}
    echo "create dir"
  fi

    if [ ! -f "${BAK_PATH}/${DEPLOY_NAME}-${date}-${time}.tar.gz" ]; then
      tar -C ${TOMCAT_HOME}/webapps -zcvf ${BAK_PATH}/${DEPLOY_NAME}-${date}-${time}.tar.gz ${DEPLOY_NAME}
      echo "原代码备份完成\n"
      sleep 1
    else
      echo "备份已经存在，不再备份\n"
      sleep 1
    fi
  # logout
}

deployFun(){
  # scp program to tomcat
  cp -r ${CODE_PATH}/target/${DEPLOY_NAME} ${TOMCAT_HOME}/webapps
  if [ $? -ne 0 ]; then
    exit 1
  else
    echo "新代码远程拷贝完成\n"
    sleep 1
  fi
}

reStartFun(){
  # reStart tomcat
  # ssh
  sh ${TOMCAT_HOME}/bin/shutdown.sh
  if [ $? -ne 0 ]; then
    exit 1
  else
    echo "\n关闭TOMCAT完成\n"
    sleep 1
  fi
  sh ${TOMCAT_HOME}/bin/startup.sh
  if [ $? -ne 0 ]; then
    exit 1
  else
    echo "\n启动TOMCAT完成\n"
    sleep 1
  fi
  # tail -f ${TOMCAT_HOME}/logs/catalina.out
  read -p '是否查看tomcat启动日志？(默认 y ) [y/n]：' viewLog
  if [ "$viewLog" != "n" ]; then
    tail -f ${TOMCAT_HOME}/logs/catalina.out
  else
    echo "可以使用如下命令手动查看tomcat启动日志："
    echo "ssh IP && tail -f ${TOMCAT_HOME}/logs/catalina.out"
  fi
}

inputBranchName(){
  cd $CODE_PATH;
  read -p '请输入分支名称：' branchName
  echo "branch name: $branchName"
  if [ ${#branchName} -lt 2 ]; then
    echo "名称太短，请重新输入！\n"
    inputBranchName
  else
    git branch $branchName;
    if [ $? -ne 0 ]; then
      echo "分支已经存在，请重新命名\n"
      inputBranchName
    else
      echo "创建分支完成\n"
      echo "$branchName"
    fi
  fi
}

gitBack(){
  # 获取分支名称
  inputBranchName

  git checkout $branchName;
  echo "\n切换分支完成\n"
  sleep 2;

  git push -u origin $branchName;
  echo "\n提交远程分支完成\n"
  sleep 2;

  git checkout master;
  echo "\n切回master分支\n"
  sleep 2;
}

inputRollVersion(){
  BAK_PATH=${BACKUP_PATH}/${DEPLOY_NAME}
  cd ${BAK_PATH}
  if [ $? -ne 0 ]; then
    echo "未找到项目备份，请确认备份是否存在"
    exit 1
  else
    echo "此项目所有备份如下：\n"
    echo `ls`
    echo "\n"
  fi

  read -p "请输入需要回滚的版本名称：" rollVersion
  if [ ! -f "${BAK_PATH}/${rollVersion}" ]; then
    echo "未找到项目备份，请确认后重新输入\n"
    inputRollVersion
  else
    echo ${rollVersion}
  fi
}

rollback(){
  inputRollVersion

  tar -zxvf ${BAK_PATH}/${rollVersion} -C ${TOMCAT_HOME}/webapps
  if [ $? -ne 0 ]; then
    exit 1
  else
    echo "\n代码回滚成功\n"
    read -p '是否重启 TOMCAT？(默认 y ) [y/n]：' restart
    if [ "$restart" != "n" ]; then
      reStartFun
    fi
    sleep 1
  fi
}

allFun(){
  compileFun
  backupFun
  deployFun
  reStartFun
}

echo "\n\n可选操作:\n"
echo "1) 编译"
echo "2) 备份"
echo "3) 部署"
echo "4) 重启"
echo "5) 回滚"
echo "6) 打GIT分支"
echo "9) 编译->备份->部署->重启"
echo "\n"

read -p '输入对应数字并回车：' actionNum

case "$actionNum" in
  '1')
     compileFun
     ;;
  '2')
     backupFun
     ;;
  '3')
     deployFun
     ;;
  '4')
     reStartFun
     ;;
  '5')
     rollback
     ;;
  '6')
     gitBack
     ;;
  '9')
     allFun
     ;;
  *)
     echo "输入错误，请输入操作对应数字"
esac

echo "============================ successed ============================"

exit 0


