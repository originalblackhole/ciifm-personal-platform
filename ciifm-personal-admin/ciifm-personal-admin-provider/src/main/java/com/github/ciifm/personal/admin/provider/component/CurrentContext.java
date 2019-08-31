package com.github.ciifm.personal.admin.provider.component;

import org.apache.shiro.SecurityUtils;

import java.util.Set;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/22 0022 01:21
 */
public class CurrentContext {

    public static String icon() {
        return ShiroUser().getIcon();
    }

    public static Long id() {
        return ShiroUser().getId();
    }

    public static String userName() {
        return ShiroUser().getUserName();
    }

    public static String nickName(){
        return ShiroUser().getNickName();
    }

    public static Set<String> roles(){
        return ShiroUser().getRoles();
    }

    public static CustomRealm.ShiroUser ShiroUser() {
        CustomRealm.ShiroUser user = (CustomRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return user;
    }
}
