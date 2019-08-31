package com.github.ciifm.personal.admin.dao.dataobject;

import com.github.ciifm.handy.model.GenericModel;
import lombok.Data;

/**
  * <p>
  * 用户角色信息实体类
  * 数据库表名称：user_role
  * </p>
  *
  * @author rui.zhou
  * @date: 2019-8-19 12:50:08
  */
@Data
public class UserRoleDO extends GenericModel<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 字段名称：用户ID
     *
     * 数据库字段信息:uid BIGINT(19)
     */
    private Long uid;

    /**
     * 字段名称：角色ID
     *
     * 数据库字段信息:rid BIGINT(19)
     */
    private Long rid;

    private String roleName;
}