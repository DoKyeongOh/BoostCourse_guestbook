CREATE TABLE `guestbook` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'guestbook id',
  `name` TEXT NOT NULL COMMENT 'guestbook name',
  `content` TEXT NOT NULL COMMENT 'guestbook content',
  `regdate` DATETIME NULL DEFAULT NULL COMMENT '등록일',
  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;