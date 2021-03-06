-- 分类
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`
(
    `id`     char(8)     NOT NULL DEFAULT '' COMMENT 'id',
    `parent` char(8)     NOT NULL DEFAULT '' COMMENT '父id',
    `name`   varchar(50) NOT NULL COMMENT '名称',
    `sort`   int(11) DEFAULT NULL COMMENT '顺序',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分类';

insert into `category` (id, parent, name, sort) values ('00000100', '00000000', '前端技术', 100);
insert into `category` (id, parent, name, sort) values ('00000101', '00000100', 'html/css', 101);
insert into `category` (id, parent, name, sort) values ('00000102', '00000100', 'javascript', 102);
insert into `category` (id, parent, name, sort) values ('00000103', '00000100', 'vue.js', 103);
insert into `category` (id, parent, name, sort) values ('00000104', '00000100', 'react.js', 104);
insert into `category` (id, parent, name, sort) values ('00000105', '00000100', 'angular', 105);
insert into `category` (id, parent, name, sort) values ('00000106', '00000100', 'node.js', 106);
insert into `category` (id, parent, name, sort) values ('00000107', '00000100', 'jquery', 107);
insert into `category` (id, parent, name, sort) values ('00000108', '00000100', '小程序', 108);
insert into `category` (id, parent, name, sort) values ('00000200', '00000000', '后端技术', 200);
insert into `category` (id, parent, name, sort) values ('00000201', '00000200', 'java', 201);
insert into `category` (id, parent, name, sort) values ('00000202', '00000200', 'springboot', 202);
insert into `category` (id, parent, name, sort) values ('00000203', '00000200', 'spring cloud', 203);
insert into `category` (id, parent, name, sort) values ('00000204', '00000200', 'ssm', 204);
insert into `category` (id, parent, name, sort) values ('00000205', '00000200', 'python', 205);
insert into `category` (id, parent, name, sort) values ('00000206', '00000200', '爬虫', 206);
insert into `category` (id, parent, name, sort) values ('00000300', '00000000', '移动开发', 300);
insert into `category` (id, parent, name, sort) values ('00000301', '00000300', 'android', 301);
insert into `category` (id, parent, name, sort) values ('00000302', '00000300', 'ios', 302);
insert into `category` (id, parent, name, sort) values ('00000303', '00000300', 'react native', 303);
insert into `category` (id, parent, name, sort) values ('00000304', '00000300', 'ionic', 304);
insert into `category` (id, parent, name, sort) values ('00000400', '00000000', '前沿技术', 400);
insert into `category` (id, parent, name, sort) values ('00000401', '00000400', '微服务', 401);
insert into `category` (id, parent, name, sort) values ('00000402', '00000400', '区块链', 402);
insert into `category` (id, parent, name, sort) values ('00000403', '00000400', '机器学习', 403);
insert into `category` (id, parent, name, sort) values ('00000404', '00000400', '深度学习', 404);
insert into `category` (id, parent, name, sort) values ('00000405', '00000400', '数据分析&挖掘', 405);
insert into `category` (id, parent, name, sort) values ('00000500', '00000000', '云计算&大数据', 500);
insert into `category` (id, parent, name, sort) values ('00000501', '00000500', '大数据', 501);
insert into `category` (id, parent, name, sort) values ('00000502', '00000500', 'hadoop', 502);
insert into `category` (id, parent, name, sort) values ('00000503', '00000500', 'spark', 503);
insert into `category` (id, parent, name, sort) values ('00000504', '00000500', 'hbase', 504);
insert into `category` (id, parent, name, sort) values ('00000505', '00000500', '阿里云', 505);
insert into `category` (id, parent, name, sort) values ('00000506', '00000500', 'docker', 506);
insert into `category` (id, parent, name, sort) values ('00000507', '00000500', 'kubernetes', 507);
insert into `category` (id, parent, name, sort) values ('00000600', '00000000', '运维&测试', 600);
insert into `category` (id, parent, name, sort) values ('00000601', '00000600', '运维', 601);
insert into `category` (id, parent, name, sort) values ('00000602', '00000600', '自动化运维', 602);
insert into `category` (id, parent, name, sort) values ('00000603', '00000600', '中间件', 603);
insert into `category` (id, parent, name, sort) values ('00000604', '00000600', 'linux', 604);
insert into `category` (id, parent, name, sort) values ('00000605', '00000600', '测试', 605);
insert into `category` (id, parent, name, sort) values ('00000606', '00000600', '自动化测试', 606);
insert into `category` (id, parent, name, sort) values ('00000700', '00000000', '数据库', 700);
insert into `category` (id, parent, name, sort) values ('00000701', '00000700', 'mysql', 701);
insert into `category` (id, parent, name, sort) values ('00000702', '00000700', 'redis', 702);
insert into `category` (id, parent, name, sort) values ('00000703', '00000700', 'mongodb', 703);

-- 课程
-- 约定：枚举类型的字段，增加”枚举“中文，并且将对应的枚举类写在[]中
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`
(
    `id`         CHAR(8)     NOT NULL DEFAULT '' COMMENT 'id',
    `name`       VARCHAR(50) NOT NULL COMMENT '名称',
    `summary`    VARCHAR(2000)        DEFAULT NULL COMMENT '概述',
    `time`       INT(11)              DEFAULT '0' COMMENT '时长|单位秒',
    `price`      DECIMAL(8, 2)        DEFAULT '0.00' COMMENT '价格（元）',
    `image`      VARCHAR(100)         DEFAULT NULL COMMENT '封面',
    `level`      CHAR(1) COMMENT '级别|枚举[CourseLevelEnum]:ONE("1","初级"),TWO("2","中级"),THREE("3","高级")',
    `charge`     CHAR(1)              DEFAULT NULL COMMENT '收费|枚举[CourseChargeEnum]:CHARGE("C","收费"),FREE("F","免费")',
    `status`     CHAR(1)              DEFAULT NULL COMMENT '状态|枚举[CourseStatusEnum]:PUBLISH("P","发布"),DRAFT("D","草稿")',
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