package com.github.ciifm.personal.admin.share.dto.system.request;

import lombok.Data;
import java.util.List;
import javax.validation.constraints.NotBlank;
import com.github.ciifm.personal.admin.share.dto.system.PermissionDTO;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/25 0025 11:31
 */
@Data
public class AddSystemRoleRequest {

    @NotBlank(message = "角色名称不能为空")
    private String name;

    private String remark;

    private List<PermissionDTO> permissionList;

}
