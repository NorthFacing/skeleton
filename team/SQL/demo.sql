/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.21-log : Database - nmall
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/nmall /*!40100 DEFAULT CHARACTER SET utf8 */;

USE nmall;

/*Table structure for table demo */

DROP TABLE IF EXISTS demo;

CREATE TABLE demo (
  id varchar(32) NOT NULL,
  name varchar(32) DEFAULT NULL,
  age int(3) DEFAULT NULL,
  birth_day datetime DEFAULT NULL,
  score double DEFAULT NULL,
  is_graduated tinyint(1) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  create_user varchar(32) DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  update_user varchar(32) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table demo */

insert  into demo(id,name,age,birth_day,score,is_graduated,create_time,create_user,update_time,update_user) 
values ('03D6AE490CA849A2A5DCA371ED0B3D28','Name55',55,'2015-07-05 00:12:52',55,1,NULL,NULL,NULL,NULL);
insert  into demo(id,name,age,birth_day,score,is_graduated,create_time,create_user,update_time,update_user) 
values ('068FDF717B4440949B243FD5811BFA8F','Name40Name40',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into demo(id,name,age,birth_day,score,is_graduated,create_time,create_user,update_time,update_user) 
values ('095421220EDC4D488156CDD99120718C','Name0',0,'2015-07-05 09:02:49',0,0,NULL,NULL,NULL,NULL);
insert  into demo(id,name,age,birth_day,score,is_graduated,create_time,create_user,update_time,update_user) 
values ('2FB11ED17BE74908B96491EBB74B70F0','Name44',44,'2015-07-05 00:12:40',44,1,NULL,NULL,NULL,NULL);
insert  into demo(id,name,age,birth_day,score,is_graduated,create_time,create_user,update_time,update_user) 
values ('2FE77B6307164934BCAA134F410C2E56','Name4',4,'2015-07-05 00:12:37',4,0,NULL,NULL,NULL,NULL);
insert  into demo(id,name,age,birth_day,score,is_graduated,create_time,create_user,update_time,update_user) 
values ('4A4E0029FFCD48F0A517F3EA23364A3E','Name55Name55',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into demo(id,name,age,birth_day,score,is_graduated,create_time,create_user,update_time,update_user) 
values ('9C4807DAAFBA4AF1847375AB83BA816C','Name29Name29',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
insert  into demo(id,name,age,birth_day,score,is_graduated,create_time,create_user,update_time,update_user) 
values ('BA19E2DA5F754949868A86D83E8DA8CC','Name29',29,'2015-07-05 09:02:48',29,0,NULL,NULL,NULL,NULL);
insert  into demo(id,name,age,birth_day,score,is_graduated,create_time,create_user,update_time,update_user) 
values ('CC2F4A91AD56420B92DDF193CE23DF4A','Name41',41,'2015-07-05 09:02:49',41,1,NULL,NULL,NULL,NULL);
insert  into demo(id,name,age,birth_day,score,is_graduated,create_time,create_user,update_time,update_user) 
values ('F034693C68EB433C812385AD84A7D7A8','Name52Name52',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
