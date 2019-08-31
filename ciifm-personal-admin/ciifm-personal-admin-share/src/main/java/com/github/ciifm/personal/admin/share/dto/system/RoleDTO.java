package com.github.ciifm.personal.admin.share.dto.system;

import lombok.Data;

import java.util.Date;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/25 0025 11:29
 */
@Data
public class RoleDTO {

    private Long id;
    private String roleName;
    private String remark;
    private String creator;
    private String modifier;
    private Date gmtCreated;
    private Date gmtModified;

}
