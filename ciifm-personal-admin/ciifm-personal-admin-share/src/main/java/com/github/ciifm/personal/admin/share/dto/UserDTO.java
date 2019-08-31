package com.github.ciifm.personal.admin.share.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/23 0023 10:06
 */
@Data
public class UserDTO {
    private Long id;
    private String userName;
    private String nickName;
    private String email;
    private String tel;
    private String state;
    private Set<String> roles;
    private String creator;
    private String modifier;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date gmtCreated;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;
}
