SET NAMES utf8mb4;
use lillia;

alter table lillia_file_batch
add column `question_menu_id` int NOT NULL DEFAULT '0';
