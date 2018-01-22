create database ssh charset utf8;

use ssh;

/**学生基本信息表*/
create table t_stu (
  id bigint primary key auto_increment comment '编号',
  num varchar(20) unique not null comment '学号',
  real_name varchar(10) not null comment '真实姓名',
  class_id bigint not null comment '班级编号，t_class表的id字段'
) engine = InnoDB default charset = utf8;

/**学生详情表*/
create table t_stu_detail (
  id bigint primary key comment '学生的id,t_stu表的id字段',
  identity_no char(18) unique not null comment '身份证号' ,
  gender tinyint comment '性别，0表示男，1表示女',
  phone char(11) not null comment '手机号'
) engine = InnoDB default charset = utf8;

/**班级表*/
create table t_class (
  id bigint primary key auto_increment comment '编号',
  title varchar(20) unique not null comment '班级名称'
) engine = InnoDB default charset = utf8;

/**联系人表*/
create table t_contract (
  id bigint primary key auto_increment comment '编号',
  real_name varchar(10) not null comment '真实姓名',
  phone char(11) not null comment '手机号'
) engine = InnoDB default charset = utf8;

/**学生与联系人的关联表*/
create table t_stu_contract (
  id bigint primary key auto_increment comment '编号',
  stu_id bigint not null comment '学生编号，t_stu表的id字段',
  contract_id bigint not null comment '联系人编号，t_contract表的id字段'
) engine = InnoDB default charset = utf8;

/**老师表*/
create table t_teacher (
  id bigint primary key auto_increment comment '编号',
  real_name varchar(10) not null comment '真实姓名',
  phone char(11) not null comment '手机号'
) engine = InnoDB default charset = utf8;

/**班级与老师的关联表*/
create table t_class_teacher (
  id bigint primary key auto_increment comment '编号',
  class_id bigint not null comment '班级编号，t_class表的id字段',
  teacher_id bigint not null comment '老师编号，t_teacher表的id字段'
) engine = InnoDB default charset = utf8;

