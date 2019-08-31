package com.github.ciifm.personal.admin.dao.dataobject;

import com.github.ciifm.handy.model.GenericModel;
import lombok.Data;

/**
  * <p>
  * 角色权限信息实体类
  * 数据库表名称：role_permission
  * </p>
  *
  * @author rui.zhou
  * @date: 2019-8-19 12:50:10
  */
@Data
public class RolePermissionDO extends GenericModel<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 字段名称：角色ID
     *
     * 数据库字段信息:rid BIGINT(19)
     */
    private Long rid;

    /**
     * 字段名称：权限
     *
     * 数据库字段信息:url VARCHAR(64)
     */
    private Long pid;

    private String permissionDesc;
}