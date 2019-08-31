package com.github.ciifm.personal.admin.share.dto.system.request;

import lombok.Data;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/25 0025 19:26
 */
@Data
public class EditSystemPermissionRequest {

    private Long id;
    private String name;
    private String resourceType;
    private String href;
    private String permission;
    private Long parentId;
    private String icon;
    private String bgColor;
    private String isShow;
    private String sort;
    //private String parentIds;

}
