package com.github.ciifm.personal.admin.share.dto.system.request;

import com.github.ciifm.personal.admin.share.dto.system.RoleDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/30 0030 17:50
 */
@Data
public class AddSystemUserRequest {

    private String id;
    @NotBlank(message = "用户名不能为空")
    private String userName;
    private String password;
    private String nickName;
    private String email;
    private String tel;
    private String state;
    private String slat;
    //private Set<String> roles;
    private List<RoleDTO> roleList;
}
