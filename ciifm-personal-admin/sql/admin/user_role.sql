DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `uid` bigint(11) NOT NULL COMMENT '用户ID',
  `rid` bigint(11) NOT NULL COMMENT '角色ID',
  `role_name` varchar(64) COMMENT '用户角色名称',
  `remark` varchar(1000) COMMENT '',
  `creator` varchar(32) DEFAULT 'SYS',
  `modifier` varchar(32) DEFAULT 'SYS',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP,
  `is_deleted` char(1) DEFAULT 'N',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户角色信息';

INSERT INTO user_role (`uid`, `rid`,`role_name`) VALUES ( '1', '1','超级管理员');
