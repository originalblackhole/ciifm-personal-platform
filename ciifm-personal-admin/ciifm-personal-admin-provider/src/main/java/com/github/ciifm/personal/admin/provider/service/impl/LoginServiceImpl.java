package com.github.ciifm.personal.admin.provider.service.impl;

import com.github.ciifm.handy.model.CommonMessageCode;
import com.github.ciifm.handy.model.ResponseData;
import com.github.ciifm.personal.admin.component.service.impl.UserComponentServiceImpl;
import com.github.ciifm.personal.admin.dao.dataobject.UserDO;
import com.github.ciifm.personal.admin.provider.service.LoginService;
import com.github.ciifm.personal.admin.provider.util.VerifyCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/24 0024 21:03
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserComponentServiceImpl userComponentService;

    @Override
    public void login(HttpServletRequest request) {
        log.info("跳转过来URL | {} ",request.getRequestURI());
        Subject subject = SecurityUtils.getSubject();
        log.info("记住登录 | {} , 是否有登录权限 | {}",subject.isRemembered(),subject.isAuthenticated());
        //TODO 待优化
        if(null != request.getCookies()){
            for (Cookie cookie : request.getCookies()) {
                if("icon".equals(cookie.getName())){
                    request.setAttribute("icon",cookie.getValue());
                }
            }
        }
    }

    @Override
    public ResponseData<Void> auth(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        String code = request.getParameter("code");

        HttpSession session = request.getSession();
        if(session == null){
            return new ResponseData<>(1103,"会话失效");
        }
        String trueCode =  (String)session.getAttribute("validateCode");

        if(StringUtils.isBlank(trueCode) || !trueCode.toLowerCase().equals(code.toLowerCase())){
            return new ResponseData<>(1104,"验证码错误");
        }

        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password,Boolean.valueOf(rememberMe));
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            return new ResponseData<>(1102,"登录密码错误");
        }

        UserDO userDO = userComponentService.findByUsername(username);
        Cookie cookie = new Cookie("icon", userDO.getIcon() );
        cookie.setPath("/");
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
        return new ResponseData<>(CommonMessageCode.SUCCESS);
    }

    @Override
    public void createImgCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置页面不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_ALL_MIXED, 4, null);
        //将验证码放到HttpSession里面
        request.getSession().setAttribute("validateCode", verifyCode);
        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 116, 36, 5, true, new Color(249,205,173), null, null);
        //写给浏览器
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
    }
}
