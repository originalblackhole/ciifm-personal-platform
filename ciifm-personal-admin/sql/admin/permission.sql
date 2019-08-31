DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '名称',
  `parent_id` bigint(11)  COMMENT '父编号',
  `level` bigint(11)  COMMENT '菜单层级',
  `parent_ids` varchar(64)  COMMENT '父编号列表',
  `resource_type` varchar(64)  COMMENT '资源类型',
  `href` varchar(64) COMMENT '资源路径',
  `sort` smallint(6)  COMMENT '排序',
  `permission` varchar(64)  COMMENT '资源路径',
  `bg_color` varchar(32)  COMMENT '菜单背景颜色',
  `icon` varchar(64)  COMMENT '菜单图标',
  `is_show` tinyint(2)  COMMENT '是否显示',
  `creator` varchar(32) DEFAULT 'SYS',
  `modifier` varchar(32) DEFAULT 'SYS',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP,
  `is_deleted` char(1) DEFAULT 'N',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='权限信息';


INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('系统管理', NULL, '1', '1', '1', '', NULL, '', '#009688', '', '1', 'SYS', 'SYS');
INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('系统用户管理', '1', '2', '1|2', '2', '/admin/user/list', NULL, 'admin:user:list', '#009688', '', '1', 'SYS', 'SYS');
INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('新增用户', '2', '3', '1|2|3', '3', NULL, NULL, 'admin:user:add', '#009688', NULL, '0', 'SYS', 'SYS');
INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('修改用户', '2', '3', '1|2|4', '3', NULL, NULL, 'admin:user:edit', '#009688', NULL, '0', 'SYS', 'SYS');
INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('修改用户状态', '2', '3', '1|2|5', '3', NULL, NULL, 'admin:user:changeState', '#009688', NULL, '0', 'SYS', 'SYS');
INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('删除用户', '2', '3', '1|2|6', '3', NULL, NULL, 'admin:user:delete', '#009688', NULL, '0', 'SYS', 'SYS');
INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('批量删除用户', '2', '3', '1|2|7', '3', NULL, NULL, 'admin:user:deleteBatch', '#009688', NULL, '0', 'SYS', 'SYS');
INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('系统角色管理', '1', '2', '1|8', '2', '/admin/role/list', NULL, 'admin:role:list', '#009688', '', '1', 'SYS', 'SYS');
INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('新增角色', '8', '3', '1|8|9', '3', NULL, NULL, 'admin:role:add', '#009688', NULL, '0', 'SYS', 'SYS');
INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('修改角色', '8', '3', '1|8|10', '3', NULL, NULL, 'admin:role:edit', '#009688', NULL, '0', 'SYS', 'SYS');
INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('删除角色', '8', '3', '1|8|11', '3', NULL, NULL, 'admin:role:delete', '#009688', NULL, '0', 'SYS', 'SYS');
INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('批量删除角色', '8', '3', '1|8|12', '3', NULL, NULL, 'admin:role:deleteBatch', '#009688', NULL, '0', 'SYS', 'SYS');
INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('系统权限管理', '1', '2', '1|13', '2', '/admin/permission/list', NULL, 'admin:permission:list', '#009688', '', '1', 'SYS', 'SYS');
INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('新增根权限', '13', '3', '1|13|14', '3', NULL, NULL, 'admin:permission:addRoot', '#009688', NULL, '0', 'SYS', 'SYS');
INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('新增子权限', '13', '3', '1|13|15', '3', NULL, NULL, 'admin:permission:add', '#009688', NULL, '0', 'SYS', 'SYS');
INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('修改权限', '13', '3', '1|13|16', '3', NULL, NULL, 'admin:permission:edit', '#009688', NULL, '0', 'SYS', 'SYS');
INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('删除权限', '13', '3', '1|13|17', '3', NULL, NULL, 'admin:permission:delete', '#009688', NULL, '0', 'SYS', 'SYS');
INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('系统资源管理', '1', '2', '1|18', '2', '', NULL, '', '#009688', '', '1', 'SYS', 'SYS');
INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('系统配置', '18', '3', '1|18|19|', '2', '/admin/config/list', NULL, 'admin:config:list', '#009688', '', '1', 'SYS', 'SYS');
INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('按钮样式配置', '19', '4', '1|18|19|20|', '2', '/admin/config/style/list', NULL, 'admin:config:style:list', '#009688', '', '1', 'SYS', 'SYS');
INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('新增样式', '20', '5', '1|18|19|20|21', '3', NULL, NULL, 'admin:config:style:add', '#009688', NULL, '0', 'SYS', 'SYS');
INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('修改样式', '20', '5', '1|18|19|20|22', '3', NULL, NULL, 'admin:config:style:edit', '#009688', NULL, '0', 'SYS', 'SYS');
INSERT INTO permission ( `name`, `parent_id`, `level`, `parent_ids`, `resource_type`, `href`, `sort`, `permission`, `bg_color`, `icon`, `is_show`, `creator`, `modifier`) VALUES ('删除样式', '20', '5', '1|18|19|20|23', '3', NULL, NULL, 'admin:config:style:delete', '#009688', NULL, '0', 'SYS', 'SYS');
