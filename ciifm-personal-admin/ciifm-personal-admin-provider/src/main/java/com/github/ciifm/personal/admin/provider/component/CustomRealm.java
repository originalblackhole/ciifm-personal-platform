package com.github.ciifm.personal.admin.provider.component;

import com.github.ciifm.handy.util.Context;
import com.github.ciifm.personal.admin.component.service.impl.UserComponentServiceImpl;
import com.github.ciifm.personal.admin.dao.dataobject.PermissionDO;
import com.github.ciifm.personal.admin.dao.dataobject.RoleDO;
import com.github.ciifm.personal.admin.dao.dataobject.UserDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/19 0019 11:58
 */
@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserComponentServiceImpl userComponentService;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(shiroUser.getRoles());
        authorizationInfo.setStringPermissions(shiroUser.getPermissions());
        return authorizationInfo;
    }

    /**
     * 验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        UserDO user = userComponentService.findByUsername(username);
        if(user == null){
            throw new UnknownAccountException(); //没找到账号
        }
        if(Boolean.TRUE.equals(user.getLocked())){
            throw new LockedAccountException(); //账号被锁定
        }

        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();

        for(RoleDO roleDO: userComponentService.findRoles(user.getId())){
            if(!StringUtils.isBlank(roleDO.getRoleName())){
                roles.add(roleDO.getRoleName());
            }
        }

        for(PermissionDO permissionDO: userComponentService.findPerssions(user.getId())){
            if(!StringUtils.isBlank(permissionDO.getPermission())){
                permissions.add(permissionDO.getPermission());
            }
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                new ShiroUser(user.getId(),user.getUserName(),user.getNickName(), user.getIcon(),roles,permissions),
                user.getPassWord(), //密码
                ByteSource.Util.bytes(user.getSalt()),
                getName()  //realm name
        );
        return authenticationInfo;
    }

    /**
     * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
     */
    public static class ShiroUser implements Serializable {
        private static final long serialVersionUID = -1373760761780840081L;
        public Long id;
        public String userName;
        public String nickName;
        public String icon;
        public Set<String> roles;
        public Set<String> permissions;

        public ShiroUser(Long id, String userName, String nickName,String icon,Set<String> roles,Set<String> permissions) {
            this.id = id;
            this.userName = userName;
            this.nickName = nickName;
            this.icon=icon;
            this.roles=roles;
            this.permissions=permissions;
        }

        public String getUserName() {
            return userName;
        }
        public String getNickName() {
            return nickName;
        }
        public String getIcon() {
            return icon;
        }
        public Long getId() {
            return id;
        }
        public Set<String> getRoles() {
            return roles;
        }
        public Set<String> getPermissions() {
            return permissions;
        }

        /**
         * 本函数输出将作为默认的<shiro:principal/>输出.
         */
        @Override
        public String toString() {
            return nickName;
        }

        /**
         * 重载hashCode,只计算loginName;
         */
        @Override
        public int hashCode() {
            return Objects.hashCode(userName);
        }

        /**
         * 重载equals,只计算loginName;
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            ShiroUser other = (ShiroUser) obj;
            if (userName == null) {
                return other.userName == null;
            } else return userName.equals(other.userName);
        }
    }



}
