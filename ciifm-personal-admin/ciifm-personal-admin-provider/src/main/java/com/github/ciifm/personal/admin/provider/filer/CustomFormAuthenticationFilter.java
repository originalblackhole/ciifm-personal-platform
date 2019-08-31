package com.github.ciifm.personal.admin.provider.filer;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/20 0020 14:51
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        /*if (!StringUtils.isEmpty(getSuccessUrl())) {
            // getSession(false)：如果当前session为null,则返回null,而不是创建一个新的session
            Session session = subject.getSession(false);
            if (session != null) {
                session.removeAttribute("shiroSavedRequest");
            }
        }
        return super.onLoginSuccess(token, subject, request, response);*/
        //String successUrl = "/index";//我是直接写死了跳转链接
        //WebUtils.issueRedirect(request,response,successUrl);
//        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
//        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + this.getSuccessUrl());
        String successUrl="/index";
        WebUtils.issueRedirect(request,response,successUrl);
        System.out.println("登录首页拦截");
        return false;
    }

    /*@Bean
    public FilterRegistrationBean registration(CustomFormAuthenticationFilter customFormAuthenticationFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(customFormAuthenticationFilter);
        registration.setEnabled(false);
        return registration;
    }*/
}
