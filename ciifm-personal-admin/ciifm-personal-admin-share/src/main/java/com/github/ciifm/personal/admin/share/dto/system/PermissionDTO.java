package com.github.ciifm.personal.admin.share.dto.system;

import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/30 0030 15:23
 */
@Data
public class PermissionDTO {
    private Long id;
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
    private List<PermissionDTO> children;
    private boolean hasPermission = false;
    private String creator;
    private Date gmtCreated;
    private String modifier;
    private Date gmtModified;
    private String isDeleted = "N";
    private String remark;
    private String extraInfo;
}
