#myBlog

* D:/java/eclipseHT/workspace/myBlog

# 参考资料

## 密码加密
* [使用typeHandler进行加密](http://www.thespringriver.com/simple-example-of-mybatis-java-maven-implementation-8-customized-type-handler/ "使用typeHandler处理密码")


ALTER TABLE `myBlog`.`parameter`
  ADD COLUMN `create_time` DATETIME NOT NULL   COMMENT '创建时间' AFTER `category_id`,
  ADD COLUMN `create_user` VARCHAR(64) NOT NULL   COMMENT '创建人' AFTER `create_time`,
  ADD COLUMN `update_time` DATETIME NULL   COMMENT '修改时间' AFTER `create_user`,
  ADD COLUMN `update_user` VARCHAR(64) NULL   COMMENT '修改人' AFTER `update_time`;
  
  
  
