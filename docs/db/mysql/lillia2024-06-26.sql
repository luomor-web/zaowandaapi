SET NAMES utf8mb4;
use lillia;

alter table question_menu
add column `chapter_id` int(11) NOT NULL DEFAULT '0' comment 'chapter_id',
add column `chapter_ratio` int(11) NOT NULL DEFAULT '0' comment 'chapter_ratio';
