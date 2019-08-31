DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `rid` bigint(11) NOT NULL COMMENT '角色ID',
  `pid` bigint(11) NOT NULL COMMENT '权限ID',
  `permission_desc` varchar(64) COMMENT '权限说明',
  `remark` varchar(1000) COMMENT '',
  `creator` varchar(32) DEFAULT 'SYS',
  `modifier` varchar(32) DEFAULT 'SYS',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP,
  `is_deleted` char(1) DEFAULT 'N',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色权限信息';
