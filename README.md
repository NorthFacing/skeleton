# skeleton
* Spring MVC + myBatis
* jqGrid
* jQuery
* zTree
* 多环境配置


# 项目结构

##


# 命名规则

## 新增
* save()
* insert()
* addList()

## 查询
* getById()
* getVoById()
* getList()
* getVoList()
* getPage()
* getVoPage()

## 修改
* save()
* update()
* upadteList()

## 删除
* delById()
* delByIds()

## 页面跳转
* list
* edit
* view



# 参考资料

## 密码加密
* [使用typeHandler进行加密](http://www.thespringriver.com/simple-example-of-mybatis-java-maven-implementation-8-customized-type-handler/ "使用typeHandler处理密码")

## 谷歌代码规范
* [java规范 - 官方](https://google.github.io/styleguide/javaguide.html)
* [Java规范 - 中文版](http://www.cnblogs.com/lanxuezaipiao/p/3534447.html)
* [JavaScript规范-翻译](http://alloyteam.github.io/JX/doc/specification/google-javascript.xml)
* [Java规范 - xml配置文件](https://github.com/codeset/google-java-styleguide)


# TODO
* 资源文件和java文件分开，约定大于配置，职责分明
* index.html 和 error.html 的跳转
* shiro 对于 admin 账号权限的放行，目前通过查询所有roles和所有permissions的方法类实现放行

# 碎碎念
* 所有的设计模式，都是对语言缺陷的补救措施


Preference:
* [CGLIB](http://www.inspire-software.com/en/index/view/spring-dilema-jdk-proxy-or-cglib-proxy.html)
* [spring aop proxing](http://docs.spring.io/spring/docs/2.5.x/reference/aop.html#aop-proxying)
* [CGLIB 和 JDK proxy区别](https://www.google.com/#newwindow=1&q=cglib+jdk+%E5%8C%BA%E5%88%AB)

* [myBatis 源码研究](http://blog.csdn.net/column/details/mybatiscode.html)
* [myBatis 官网](http://mybatis.org/mybatis-3/index.html)

* [给 Android 开发者的 RxJava 详解](http://gank.io/post/560e15be2dca930e00da1083)

* [2015前端组件化框架之路](https://github.com/xufei/blog/issues/19)

* [redis集成](http://blog.csdn.net/zhu_tianwei/article/details/44923001)
