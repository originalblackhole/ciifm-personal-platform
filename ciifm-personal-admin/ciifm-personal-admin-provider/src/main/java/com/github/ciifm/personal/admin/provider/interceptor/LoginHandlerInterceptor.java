package com.github.ciifm.personal.admin.provider.interceptor;

import com.github.ciifm.handy.util.Context;
import com.github.ciifm.personal.admin.provider.component.CurrentContext;
import com.github.ciifm.personal.admin.provider.component.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/24 0024 14:26
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*if(null == SecurityUtils.getSubject().getPrincipal()){
            response.sendRedirect("/login");
            return false;
        }*/
        CustomRealm.ShiroUser shiroUser = (CustomRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        Context.getCurrentContext().set("username", shiroUser.userName);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
