package com.github.ciifm.personal.admin.dao.dataobject;

import com.github.ciifm.handy.model.GenericModel;
import lombok.Data;

import java.util.Set;

/**
  * <p>
  * 用户信息实体类
  * 数据库表名称：user
  * </p>
  *
  * @author rui.zhou
  * @date: 2019-8-19 12:50:04
  */
@Data
public class UserDO extends GenericModel<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 字段名称：用户名
     *
     * 数据库字段信息:user_name VARCHAR(64)
     */
    private String userName;

    /**
     * 字段名称：密码
     *
     * 数据库字段信息:pass_word VARCHAR(128)
     */
    private String passWord;

    /**
     * 字段名称：昵称
     *
     * 数据库字段信息:nick_name VARCHAR(40)
     */
    private String nickName;

    /**
     * 字段名称：头像
     *
     * 数据库字段信息:icon VARCHAR(500)
     */
    private String icon;

    /**
     * 字段名称：shiro加密盐
     *
     * 数据库字段信息:salt VARCHAR(40)
     */
    private String salt;

    /**
     * 字段名称：手机号码
     *
     * 数据库字段信息:tel VARCHAR(11)
     */
    private String tel;

    /**
     * 字段名称：邮箱地址
     *
     * 数据库字段信息:email VARCHAR(200)
     */
    private String email;

    /**
     * 字段名称：是否锁定
     *
     * 数据库字段信息:locked TINYINT(3)
     */
    private int locked;

    /**
     * 字段名称：用户状态
     *
     * 数据库字段信息:state TINYINT(3)
     */
    private int state;

    private Set<String> roles;

    //private List<RoleDO> roleList;

    public UserDO() {
    }

    public UserDO(String userName) {
        this.userName = userName;
    }
}