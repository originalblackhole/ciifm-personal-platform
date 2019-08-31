package com.github.ciifm.personal.admin.dao.dataobject;

import com.github.ciifm.handy.model.GenericModel;
import lombok.Data;

import java.util.List;

/**
  * <p>
  * 角色信息实体类
  * 数据库表名称：role
  * </p>
  *
  * @author rui.zhou
  * @date: 2019-8-19 12:50:06
  */
@Data
public class RoleDO extends GenericModel<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 字段名称：用户角色名称
     *
     * 数据库字段信息:role_name VARCHAR(64)
     */
    private String roleName;

    private boolean hasRole = false;

    private List<PermissionDO> permissionList;
}