package com.github.ciifm.personal.admin.share.dto.request;

import lombok.Data;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/22 0022 16:20
 */
@Data
public class UserInfoRequest {
    private String userName;
    private String nickName;
    private String tel;
    private String email;
    private String roles;
    private String remark;
}
