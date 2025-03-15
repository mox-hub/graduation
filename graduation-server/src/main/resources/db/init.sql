-- 创建数据库
CREATE DATABASE IF NOT EXISTS graduation DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE graduation;

-- 用户表
CREATE TABLE IF NOT EXISTS `user` (
    `user_id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
    `user_name` VARCHAR(50) NOT NULL COMMENT '用户名',
    `user_password` VARCHAR(100) NOT NULL COMMENT '密码',
    `user_avatar` VARCHAR(255) DEFAULT NULL COMMENT '头像URL',
    `user_email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
    `user_role` VARCHAR(20) DEFAULT 'user' COMMENT '用户角色'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 图片表
CREATE TABLE IF NOT EXISTS `image` (
    `image_id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '图片ID',
    `image_url` VARCHAR(255) NOT NULL COMMENT '图片URL'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图片表';

-- 提示词表
CREATE TABLE IF NOT EXISTS `prompt` (
    `prompt_id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '提示词ID',
    `prompt` TEXT NOT NULL COMMENT '正向提示词',
    `negative_prompt` TEXT COMMENT '反向提示词',
    `steps` BIGINT DEFAULT 20 COMMENT '生成步数',
    `batch_size` BIGINT DEFAULT 1 COMMENT '生成数量',
    `height` BIGINT DEFAULT 256 COMMENT '图片高度',
    `width` BIGINT DEFAULT 256 COMMENT '图片宽度',
    `cfg_scale` DOUBLE DEFAULT 7.0 COMMENT '提示词相关程度',
    `sampler_index` VARCHAR(50) DEFAULT 'Euler' COMMENT '采样方法',
    `seed` BIGINT DEFAULT -1 COMMENT '随机数种子',
    `style` VARCHAR(100) DEFAULT NULL COMMENT '样式'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='提示词表';

-- 历史记录表
CREATE TABLE IF NOT EXISTS `history` (
    `history_id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '历史记录ID',
    `user_id` INT NOT NULL COMMENT '用户ID',
    `image_id` INT NOT NULL COMMENT '图片ID',
    `prompt_id` INT NOT NULL COMMENT '提示词ID',
    `image_num` INT DEFAULT 1 COMMENT '图片数量',
    `history_type` VARCHAR(20) NOT NULL COMMENT '历史记录类型',
    `generate_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (image_id) REFERENCES image(image_id),
    FOREIGN KEY (prompt_id) REFERENCES prompt(prompt_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='历史记录表';

-- 收藏表
CREATE TABLE IF NOT EXISTS `collect` (
    `collect_id` INT AUTO_INCREMENT PRIMARY KEY COMMENT '收藏ID',
    `image_id` INT NOT NULL COMMENT '图片ID',
    `user_id` INT NOT NULL COMMENT '用户ID',
    `prompt_id` INT NOT NULL COMMENT '提示词ID',
    `is_shared` INT DEFAULT 0 COMMENT '是否已分享',
    `rate` INT DEFAULT NULL COMMENT '评分',
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (image_id) REFERENCES image(image_id),
    FOREIGN KEY (prompt_id) REFERENCES prompt(prompt_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收藏表';

-- 分享表
CREATE TABLE IF NOT EXISTS `share` (
    `share_id` VARCHAR(100) PRIMARY KEY COMMENT '分享ID',
    `user_id` INT NOT NULL COMMENT '用户ID',
    `image_id` INT NOT NULL COMMENT '图片ID',
    `prompt_id` INT NOT NULL COMMENT '提示词ID',
    `start_time` DATE NOT NULL COMMENT '开始时间',
    `end_time` DATE NOT NULL COMMENT '结束时间',
    `is_valid` INT DEFAULT 1 COMMENT '是否有效',
    `share_url` VARCHAR(255) NOT NULL COMMENT '分享URL',
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (image_id) REFERENCES image(image_id),
    FOREIGN KEY (prompt_id) REFERENCES prompt(prompt_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分享表';