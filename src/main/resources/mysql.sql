drop database ssw;
create database ssw default charset utf8 COLLATE utf8_general_ci;
grant all on ssw.* to 'sswuser'@'localhost' identified by 'pwd4ssw';
grant all on ssw.* to 'sswuser'@'%' identified by 'pwd4ssw';


 CREATE TABLE `dr_entity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `channel_id` varchar(20) DEFAULT NULL,
  `creation_time` datetime NOT NULL,
  `dr_time` varchar(30) DEFAULT NULL,
  `fee` int(11) DEFAULT NULL,
  `forward_status` int(3) DEFAULT NULL,
  `forward_time` datetime DEFAULT NULL,
  `link_id` varchar(16) NOT NULL,
  `msg` varchar(50) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `recv_time` datetime NOT NULL,
  `sp_id` varchar(20) NOT NULL,
  `sp_no` varchar(21) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `deduct_flag` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 

/*通道禁发黑名单*/
CREATE TABLE special_phone (
 id  integer(11)   primary key not null AUTO_INCREMENT,
 stype  char(1)  null,/*W:白名单，B：黑名单*/
 clevel char(1)  null,/*黑名单级别,O：全局，C：渠道,S:EC/SI*/
 phone  varchar(11)  not null,
 relate_id varchar(11) null,/*关联id*/
 relate_object  varchar(50)  null,
 admin_id integer(11)  null,/*操作员*/
 ctime timestamp not null
);
CREATE INDEX idx_special_phone ON special_phone(phone);

/*手机号段表*/
CREATE TABLE phone_head(
 id  integer(11)   primary key not null AUTO_INCREMENT,
 province VARCHAR(6)   NOT NULL,
 city   VARCHAR(10)   NOT NULL,
 postcode VARCHAR(3)   NOT NULL,
 head   VARCHAR(7) UNIQUE NOT NULL
);
CREATE INDEX idx_phone_head ON phone_head(head);

