DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter`
(
    `id`        char(8) NOT NULL COMMENT 'ID',
    `course_id` char(8)     DEFAULT NULL COMMENT '课程ID',
    `name`      varchar(50) DEFAULT NULL COMMENT '名称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '大章';

insert into `chapter` (id,course_id,name) values ('00000001','00000000','测试大章01');
insert into `chapter` (id,course_id,name) values ('00000002','00000000','测试大章02');
insert into `chapter` (id,course_id,name) values ('00000003','00000000','测试大章03');
insert into `chapter` (id,course_id,name) values ('00000004','00000000','测试大章04');
insert into `chapter` (id,course_id,name) values ('00000005','00000000','测试大章05');
insert into `chapter` (id,course_id,name) values ('00000006','00000000','测试大章06');
insert into `chapter` (id,course_id,name) values ('00000007','00000000','测试大章07');
insert into `chapter` (id,course_id,name) values ('00000008','00000000','测试大章08');
insert into `chapter` (id,course_id,name) values ('00000009','00000000','测试大章09');
insert into `chapter` (id,course_id,name) values ('00000010','00000000','测试大章10');
insert into `chapter` (id,course_id,name) values ('00000011','00000000','测试大章11');
insert into `chapter` (id,course_id,name) values ('00000012','00000000','测试大章12');
insert into `chapter` (id,course_id,name) values ('00000013','00000000','测试大章13');
insert into `chapter` (id,course_id,name) values ('00000014','00000000','测试大章14');
insert into `chapter` (id,course_id,name) values ('00000015','00000000','测试大章15');
insert into `chapter` (id,course_id,name) values ('00000016','00000000','测试大章16');


-- 测试

drop table if exists `test`;
create table `test` (
    `id` char(8) not null default '' comment '主键',
    `name` varchar(50) comment '名称',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='测试';

insert into `test` (id, name) values (1,'测试');
insert into `test` (id, name) values (2,'测试');

# idea关联数据库后，所有的脚本都可以在idea直接执行，相当于把开发和运维关联起来了