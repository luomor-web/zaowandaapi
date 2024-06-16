SET NAMES utf8mb4;
use lillia;

CREATE TABLE `lillia_file_batch` (
  `lillia_file_batch_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `upload_name` varchar(32) NOT NULL DEFAULT '',
  `upload_type` tinyint NOT NULL DEFAULT '0' COMMENT ' 1- 2-',
  `num` int NOT NULL DEFAULT '0',
  `upload_total_num` int NOT NULL DEFAULT '0',
  `upload_success_num` int NOT NULL DEFAULT '0',
  `upload_fail_num` int NOT NULL DEFAULT '0',
  `upload_remove_num` int NOT NULL DEFAULT '0',
  `un_upload_num` int NOT NULL DEFAULT '0',
  `read_num` int NOT NULL DEFAULT '0',
  `read_ret` varchar(100) NOT NULL DEFAULT '',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT ' -1- 1-',
  `read_status` tinyint NOT NULL DEFAULT '0' COMMENT ' 1-',
  `operator_id` int NOT NULL DEFAULT '0' COMMENT 'ID',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT ' system',
  `comment` varchar(100) NOT NULL DEFAULT '' COMMENT '()',
  `create_time` int NOT NULL DEFAULT '0',
  `update_time` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`lillia_file_batch_id`),
  KEY `idx_upload_name` (`upload_name`),
  KEY `idx_upload_type` (`upload_type`),
  KEY `idx_st_ct` (`status`,`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `lillia_file` (
  `lillia_file_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `lillia_file_batch_id` bigint NOT NULL DEFAULT '0' COMMENT 'ID',
  `file_name` varchar(128) NOT NULL DEFAULT '',
  `file_type` tinyint NOT NULL DEFAULT '0' COMMENT ' 1- 2-',
  `file_path` varchar(256) NOT NULL DEFAULT '',
  `local_path` varchar(256) NOT NULL DEFAULT '',
  `file_size` varchar(20) NOT NULL DEFAULT '',
  `file_ctime` int NOT NULL DEFAULT '0',
  `file_cdate` int NOT NULL DEFAULT '0',
  `file_utime` int NOT NULL DEFAULT '0',
  `read_ret` varchar(32) NOT NULL DEFAULT '',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT ' -1- 1-',
  `read_status` tinyint NOT NULL DEFAULT '0' COMMENT ' 1-',
  `flag` bigint NOT NULL DEFAULT '0' COMMENT 'flag',
  `operator_id` int NOT NULL DEFAULT '0' COMMENT 'ID',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT ' system',
  `comment` varchar(100) NOT NULL DEFAULT '' COMMENT '()',
  `create_time` int NOT NULL DEFAULT '0',
  `update_time` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`lillia_file_id`),
  KEY `idx_lillia_file_batch_id` (`lillia_file_batch_id`),
  KEY `idx_ic` (`file_cdate`),
  KEY `idx_st_ct` (`status`,`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
