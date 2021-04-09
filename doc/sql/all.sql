-- 课程
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`
(
    `id`         CHAR(8)     NOT NULL DEFAULT '' COMMENT 'id',
    `name`       VARCHAR(50) NOT NULL COMMENT '名称',
    `summary`    VARCHAR(2000)        DEFAULT NULL COMMENT '概述',
    `time`       INT(11)              DEFAULT '0' COMMENT '时长|单位秒',
    `price`      DECIMAL(8, 2)        DEFAULT '0.00' COMMENT '价格（元）',
    `image`      VARCHAR(100)         DEFAULT NULL COMMENT '封面',
    `level`      CHAR(1) COMMENT '级别|ONE("1","初级"),TWO("2","中级"),THREE("3","高级")',
    `charge`     CHAR(1)              DEFAULT NULL COMMENT '收费|CHARGE("C","收费"),FREE("F","免费")',
    `status`     CHAR(1)              DEFAULT NULL COMMENT '状态|PUBLISH("P","发布"),DRAFT("D","草稿")',
    `enroll`     INT(11)              DEFAULT '0' COMMENT '报名数',
    `sort`       INT(11)              DEFAULT NULL COMMENT '顺序',
    `created_at` datetime(3)          DEFAULT NULL COMMENT '创建时间', -- datetime(3)表示精确到毫秒，界面显示可以只到秒，但落库要精确
    `updated_at` datetime(3)          DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4 COMMENT = '课程表';

INSERT INTO `course` VALUES ('00000001', '测试课程01', '这是一门测试课程', 1200, 19.90, NULL, '2', 'C', 'D', 100, 0, now(), now());

-- 小节
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section`
(
    `id`         CHAR(8)     NOT NULL DEFAULT '' COMMENT 'ID',
    `title`      VARCHAR(50) NOT NULL COMMENT '标题',
    `course_id`  CHAR(8)              DEFAULT NULL COMMENT '课程|course.id',
    `chapter_id` CHAR(8)              DEFAULT NULL COMMENT '大章|chapter.id',
    `video`      VARCHAR(200)         DEFAULT NULL COMMENT '视频',
    `time`       INT(11)              DEFAULT NULL COMMENT '时长|单位秒',
    `charge`     CHAR(1)              DEFAULT NULL COMMENT '收费|C 收费；F 免费',
    `sort`       INT(11)              DEFAULT NULL COMMENT '顺序',
    `created_at` datetime(3)          DEFAULT NULL COMMENT '创建时间',
    `updated_at` datetime(3)          DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4 COMMENT = '小节';

INSERT INTO  `section` (id, title, course_id, chapter_id, video, time, charge, sort, created_at, updated_at)
VALUES ('00000001', '测试小节01', '00000001', '00000000', '', 500, 'F', 1, now(), now());

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