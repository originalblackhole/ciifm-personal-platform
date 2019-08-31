package com.github.ciifm.personal.admin.share.dto.system;

import lombok.Data;
import java.util.List;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/27 0027 15:37
 */
@Data
public class RolePermissionResponse {

    private List<PermissionDTO> permissionList;

    private RoleDTO role;

}
