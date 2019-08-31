package com.github.ciifm.personal.admin.dao.dataobject;

import com.github.ciifm.handy.model.GenericModel;
import lombok.Data;

import java.util.List;

/**
  * <p>
  * 权限信息实体类
  * 数据库表名称：permission
  * </p>
  *
  * @author rui.zhou
  * @date: 2019-8-19 12:50:07
  */
@Data
public class PermissionDO extends GenericModel<Long> {
    private static final long serialVersionUID = 1L;

    private String name;
    private String resourceType;
    private String href;
    private Long parentId;
    private String parentName;
    private String parentIds;
    private String permission;
    private Boolean isShow;
    private String bgColor;
    private String icon;
    private Integer level;
    private Integer sort;
    private List<PermissionDO> children;
    private boolean hasPermission = false;


}