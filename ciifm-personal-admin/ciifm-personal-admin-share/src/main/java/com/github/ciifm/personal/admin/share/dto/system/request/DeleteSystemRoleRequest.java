package com.github.ciifm.personal.admin.share.dto.system.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/27 0027 16:39
 */
@Data
public class DeleteSystemRoleRequest {

    @NotNull(message = "URL权限不能为空")
    private Long [] ids;
}
