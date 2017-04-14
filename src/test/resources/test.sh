#!/bin/bash

# set base var
CODE_PATH=/home/adolphor/workspace/test
PROGRAM_NAME=jy_rms_quartz
DEPLOY_NAME=rmsJobs

BACKUP_PATH=/home/adolphor/temp

TOMCAT_HOME=/home/adolphor/workspace/tomcat-8.0.37

echo "start"

date=`date +%F`
BAK_PATH=${BACKUP_PATH}/${DEPLOY_NAME}/${date}

inputBranchName(){
    read -p '请输入分支名称：' branchName
    echo "branch name: $branchName"
    if [ ${#branchName} -lt 2 ]; then
      echo "名称太短，请重新输入！\n"
      inputBranchName
    else
      git branch $branchName;
      if [ $? -ne 0 ]; then
        echo "分支已经存在，请重新命名"
        inputBranchName
      else
        echo "$branchName"
      fi
    fi
}

cd $CODE_PATH;

inputBranchName

git checkout $branchName;
echo "切换分支完成"
sleep 3;

git push -u origin $branchName;
echo "提交远程分支完成"
sleep 3;

git checkout master;
echo "切回master分支"
sleep 3;

echo "done"