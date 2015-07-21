/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/7/15 17:39:49                           */
/*==============================================================*/


drop table if exists nmall.brand;

drop table if exists nmall.category;

drop table if exists nmall.customer;

drop table if exists nmall.hotel;

drop table if exists nmall.item;

drop table if exists nmall.item_parameter;

drop table if exists nmall.order_detail;

drop table if exists nmall.organization;

drop table if exists nmall.parameter;

drop table if exists nmall.user;

/*==============================================================*/
/* Table: brand                                                 */
/*==============================================================*/
create table nmall.brand
(
   id                   varchar(32) not null comment 'ID',
   name                 varchar(128) comment '品牌名称',
   description          varchar(512) comment '品牌描述',
   create_time          datetime not null comment '创建时间',
   create_user          varchar(64) not null comment '创建人',
   update_time          datetime comment '修改时间',
   update_user          varchar(64) comment '修改人',
   primary key (id)
);

/*==============================================================*/
/* Table: category                                              */
/*==============================================================*/
create table nmall.category
(
   id                   varchar(32) not null comment 'ID',
   name                 varchar(128) comment '类目名称',
   description          varchar(512) comment '类目描述',
   create_time          datetime not null comment '创建时间',
   create_user          varchar(64) not null comment '创建人',
   update_time          datetime comment '修改时间',
   update_user          varchar(64) comment '修改人',
   primary key (id)
);

/*==============================================================*/
/* Table: customer                                              */
/*==============================================================*/
create table nmall.customer
(
   id                   varchar(32),
   name                 varchar(64) comment '消费者姓名',
   telephone            varchar(24) comment '消费者电话',
   mobile_phone         varchar(11) comment '消费者手机',
   province_id          varchar(32) comment '送货地址-省',
   city_id              varchar(32) comment '送货地址-市',
   area_id              varchar(32) comment '送货地址-区',
   address              varchar(256) comment '送货地址-详细地址',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '修改时间'
);

/*==============================================================*/
/* Table: hotel                                                 */
/*==============================================================*/
create table nmall.hotel
(
   id                   varchar(32) not null comment 'ID',
   name                 varchar(128) comment '酒店名称',
   manager_id           varchar(32) comment '酒店经理',
   org_id               varchar(32) comment '组织ID',
   org_code             varchar(32) comment '组织CODE',
   create_time          datetime not null comment '创建时间',
   create_user          varchar(64) not null comment '创建人',
   update_time          datetime comment '修改时间',
   update_user          varchar(64) comment '修改人',
   primary key (id)
);

/*==============================================================*/
/* Table: item                                                  */
/*==============================================================*/
create table nmall.item
(
   id                   varchar(32) not null comment 'ID',
   name                 varchar(128) comment '名称',
   category_id          varchar(32) comment '类目',
   brand_id             varchar(32) comment '品牌',
   description          varchar(512) comment '描述',
   counter_price        int(10) comment '专柜价格(单位：分)',
   price                int(10) comment '价格(单位：分)',
   promotional_price    int(10) comment '促销价格(单位：分)',
   is_license           tinyint(1) comment '是否具有生产许可证编号',
   create_time          datetime not null comment '创建时间',
   create_user          varchar(64) not null comment '创建人',
   update_time          datetime comment '修改时间',
   update_user          varchar(64) comment '修改人',
   primary key (id)
);

/*==============================================================*/
/* Table: item_parameter                                        */
/*==============================================================*/
create table nmall.item_parameter
(
   id                   varchar(32) not null,
   item_id              varchar(32) comment '商品ID',
   parameter_id         varchar(32),
   parameter_name       varchar(128) comment '参数名称',
   order_sequese        tinyint(2) comment '展示顺序',
   create_time          datetime not null comment '创建时间',
   create_user          varchar(64) not null comment '创建人',
   update_time          datetime comment '修改时间',
   update_user          varchar(64) comment '修改人',
   primary key (id)
);

/*==============================================================*/
/* Table: order_detail                                          */
/*==============================================================*/
create table nmall.order_detail
(
   id                   varchar(32) not null comment 'ID',
   item_id              varchar(32) comment '商品ID',
   hotel_id             varchar(32) comment '酒店ID',
   customer_id          varchar(32) comment '消费者ID',
   item_name            varchar(128) comment '商品名称',
   item_num             tinyint(3) comment '购买数量',
   paid_price           int(10) comment '实付单价',
   paid_amount          int(10) comment '实付金额',
   paid_type            enum('alipay','wxpay','cash','pos') comment '付款方式',
   is_paid              tinyint(1) comment '是否已付款',
   paid_time            datetime comment '付款时间',
   customer_name        varchar(32) comment '消费者姓名',
   customer_mobile_phone char(10) comment '消费者手机',
   customer_telephone   char(10) comment '消费者电话',
   delivery_to_hotel    tinyint(1) comment '是否送货到酒店',
   delivery_province    char(10) comment '送货地址-省',
   delivery_city        char(10) comment '送货地址-市',
   delivery_area        char(10) comment '送货地址-区',
   delivery_address     char(10) comment '送货地址-详细地址',
   create_time          datetime comment '创建时间',
   create_user          varchar(64) comment '创建人',
   update_time          datetime comment '修改时间',
   update_user          varchar(64) comment '修改人',
   primary key (id)
);

/*==============================================================*/
/* Table: organization                                          */
/*==============================================================*/
create table nmall.organization
(
   id                   varchar(32) not null comment 'ID',
   code                 varchar(64) comment '组织CODE',
   p_id                 varchar(32) comment '父级ID',
   name                 varchar(128) comment '组织名称',
   full_name            varchar(512) comment '组织全称',
   create_time          datetime not null comment '创建时间',
   create_user          varchar(64) not null comment '创建人',
   update_time          datetime comment '修改时间',
   update_user          varchar(64) comment '修改人',
   primary key (id)
);

/*==============================================================*/
/* Table: parameter                                             */
/*==============================================================*/
create table nmall.parameter
(
   id                   varchar(32) not null,
   name                 varchar(128),
   category_id          varchar(32),
   create_time          datetime not null comment '创建时间',
   create_user          varchar(64) not null comment '创建人',
   update_time          datetime comment '修改时间',
   update_user          varchar(64) comment '修改人',
   primary key (id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table nmall.user
(
   id                   varchar(32) not null,
   user_name            varchar(64),
   pass_word            varchar(64),
   user_role            enum('admin','regional_manager','deliveryman','hotel_manager'),
   is_delete            tinyint(1),
   create_time          datetime,
   create_user          varchar(64),
   update_time          datetime,
   update_user          varchar(64),
   primary key (id)
);

alter table nmall.hotel add constraint FK_hotel_manager foreign key (manager_id)
      references nmall.user (id) on delete restrict on update restrict;

alter table nmall.hotel add constraint FK_hotel_org foreign key (org_id)
      references nmall.organization (id) on delete restrict on update restrict;

alter table nmall.item add constraint FK_item_brand foreign key (brand_id)
      references nmall.brand (id) on delete restrict on update restrict;

alter table nmall.item add constraint FK_item_category foreign key (category_id)
      references nmall.category (id) on delete restrict on update restrict;

alter table nmall.item_parameter add constraint FK_parameter_item foreign key (item_id)
      references nmall.item (id) on delete restrict on update restrict;

alter table nmall.item_parameter add constraint FK_parameter_parameter foreign key (parameter_id)
      references nmall.parameter (id) on delete restrict on update restrict;

alter table nmall.order_detail add constraint FK_order_customer foreign key (customer_id)
      references nmall.customer (id) on delete restrict on update restrict;

alter table nmall.order_detail add constraint FK_order_hotel foreign key (hotel_id)
      references nmall.hotel (id) on delete restrict on update restrict;

alter table nmall.order_detail add constraint FK_order_item foreign key (item_id)
      references nmall.item (id) on delete restrict on update restrict;

alter table nmall.parameter add constraint FK_parameter_category foreign key (category_id)
      references nmall.category (id) on delete restrict on update restrict;

