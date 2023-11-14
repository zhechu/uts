-- 如果用户标签系统数据库不存在则创建
CREATE database if NOT EXISTS `uts` default character set utf8mb4 collate utf8mb4_unicode_ci;
use `uts`;

-- 用户表
CREATE TABLE `uts_user`
(
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `user_name` varchar(64) NOT NULL COMMENT '用户名',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- 标签表
CREATE TABLE `uts_tag`
(
    `id`       int(11)     NOT NULL AUTO_INCREMENT COMMENT '标签id',
    `tag_name` varchar(64) NOT NULL COMMENT '标签名',
    PRIMARY KEY (`id`),
    UNIQUE KEY `un_tag_name` (`tag_name`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


-- 用户标签表
CREATE TABLE `uts_user_tag`
(
    `user_id` int(11) NOT NULL COMMENT '用户id',
    `tag_id`  int(11) NOT NULL COMMENT '标签id',
    PRIMARY KEY (`user_id`, `tag_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;