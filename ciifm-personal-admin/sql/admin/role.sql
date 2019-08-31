DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) NOT NULL COMMENT '用户角色名称',
  `remark` varchar(1000) COMMENT '',
  `creator` varchar(32) DEFAULT 'SYS',
  `modifier` varchar(32) DEFAULT 'SYS',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP,
  `is_deleted` char(1) DEFAULT 'N',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色信息';

INSERT INTO role (`role_name`,`remark`) VALUES ('超级管理员','具有所有操作权限');
