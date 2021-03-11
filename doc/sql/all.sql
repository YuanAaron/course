drop table if exists `test`;
create table `test` (
    `id` char(8) not null default '' comment '主键',
    `name` varchar(50) comment '名称',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='测试';

insert into `test` (id, name) values (1,'测试');
insert into `test` (id, name) values (2,'测试');

# idea关联数据库后，所有的脚本都可以在idea直接执行，相当于把开发和运维关联起来了