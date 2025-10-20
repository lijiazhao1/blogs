-- 创建数据库
CREATE DATABASE IF NOT EXISTS blog_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE blog_db;

-- 用户表
CREATE TABLE `user` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `username` varchar(50) NOT NULL COMMENT '登录用户名',
                        `password` varchar(100) NOT NULL COMMENT '加密密码',
                        `nickname` varchar(50) NOT NULL COMMENT '显示昵称',
                        `avatar` varchar(255) DEFAULT NULL COMMENT '头像URL',
                        `intro` varchar(500) DEFAULT NULL COMMENT '个人简介',
                        `email` varchar(100) DEFAULT NULL COMMENT '联系邮箱',
                        `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态：0-禁用，1-正常',
                        `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `uk_username` (`username`),
                        UNIQUE KEY `uk_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 分类表
CREATE TABLE `category` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `name` varchar(50) NOT NULL COMMENT '分类名称',
                            `alias` varchar(50) NOT NULL COMMENT '分类别名',
                            `description` varchar(200) DEFAULT NULL COMMENT '分类描述',
                            `sort_order` int NOT NULL DEFAULT '0' COMMENT '排序优先级',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uk_name` (`name`),
                            UNIQUE KEY `uk_alias` (`alias`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分类表';

-- 标签表
CREATE TABLE `tag` (
                       `id` bigint NOT NULL AUTO_INCREMENT,
                       `name` varchar(50) NOT NULL COMMENT '标签名称',
                       `alias` varchar(50) NOT NULL COMMENT '标签别名',
                       `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       PRIMARY KEY (`id`),
                       UNIQUE KEY `uk_name` (`name`),
                       UNIQUE KEY `uk_alias` (`alias`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='标签表';

-- 文章表
CREATE TABLE `article` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `title` varchar(200) NOT NULL COMMENT '文章标题',
                           `content` longtext NOT NULL COMMENT '文章内容（Markdown）',
                           `summary` varchar(500) DEFAULT NULL COMMENT '文章摘要',
                           `cover_image` varchar(255) DEFAULT NULL COMMENT '封面图URL',
                           `category_id` bigint NOT NULL COMMENT '所属分类ID',
                           `user_id` bigint NOT NULL COMMENT '作者ID',
                           `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态：0-草稿，1-已发布',
                           `view_count` int NOT NULL DEFAULT '0' COMMENT '阅读量',
                           `like_count` int NOT NULL DEFAULT '0' COMMENT '点赞数',
                           `is_top` tinyint NOT NULL DEFAULT '0' COMMENT '是否置顶：0-否，1-是',
                           `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`),
                           KEY `idx_title` (`title`),
                           KEY `idx_category_id` (`category_id`),
                           KEY `idx_create_time` (`create_time`),
                           KEY `idx_user_status` (`user_id`,`status`),
                           CONSTRAINT `fk_article_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
                           CONSTRAINT `fk_article_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

-- 文章-标签关联表
CREATE TABLE `article_tag` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `article_id` bigint NOT NULL COMMENT '文章ID',
                               `tag_id` bigint NOT NULL COMMENT '标签ID',
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `uk_article_tag` (`article_id`,`tag_id`),
                               KEY `idx_tag_id` (`tag_id`),
                               CONSTRAINT `fk_article_tag_article` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE,
                               CONSTRAINT `fk_article_tag_tag` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章-标签关联表';

-- 评论表
CREATE TABLE `comment` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `content` varchar(500) NOT NULL COMMENT '评论内容',
                           `article_id` bigint NOT NULL COMMENT '关联文章ID',
                           `user_id` bigint NOT NULL COMMENT '评论作者ID',
                           `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '父评论ID（0为顶级）',
                           `is_approved` tinyint NOT NULL DEFAULT '1' COMMENT '是否审核通过：0-待审，1-通过',
                           `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`),
                           KEY `idx_article_approved` (`article_id`,`is_approved`),
                           KEY `idx_parent_id` (`parent_id`),
                           KEY `idx_create_time` (`create_time`),
                           CONSTRAINT `fk_comment_article` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE,
                           CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论表';