DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) NOT NULL COMMENT '用户名',
  `pass_word` varchar(128) NOT NULL COMMENT '密码',
  `nick_name` varchar(40) COMMENT '昵称',
  `icon` varchar(500) COMMENT '头像',
  `salt` varchar(40) COMMENT 'shiro加密盐',
  `tel` varchar(11) COMMENT '手机号码',
  `email` varchar(200) COMMENT '邮箱地址',
  `locked` tinyint(2) COMMENT '是否锁定',
  `state` tinyint(4) COMMENT '用户状态',
  `remark` varchar(1000) COMMENT '评价',
  `creator` varchar(32) DEFAULT 'SYS',
  `modifier` varchar(32) DEFAULT 'SYS',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP,
  `is_deleted` char(1) DEFAULT 'N',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户信息';

INSERT INTO user (`user_name`, `pass_word`, `icon`, `salt`, `locked`, `state`) VALUES ('admin', '928bfd2577490322a6e19b793691467e', 'https://static.mysiteforme.com/3c5b69f4-2e39-4f88-b302-a6eb48e4c43a.jpg','admin','0','0');
