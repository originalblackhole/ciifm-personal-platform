package com.github.ciifm.personal.admin.share.dto.system.request;

import com.github.ciifm.personal.admin.share.dto.system.RoleDTO;
import lombok.Data;

import java.util.List;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/30 0030 17:48
 */
@Data
public class EditSystemUserRequest {
    private Long id;
    private String userName;
    private String nickName;
    private String email;
    private String tel;
    private int state;
    private Long [] ids;
    private List<RoleDTO> roleList;
}
