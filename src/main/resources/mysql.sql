drop database ssw;
create database ssw default charset utf8 COLLATE utf8_general_ci;
grant all on ssw.* to 'sswuser'@'localhost' identified by 'pwd4ssw';
grant all on ssw.* to 'sswuser'@'%' identified by 'pwd4ssw';