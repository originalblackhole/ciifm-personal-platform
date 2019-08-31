package com.github.ciifm.personal.admin.share.service;

import com.github.ciifm.handy.model.ResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IloginShareService {

    /**
     * 登录页
     * @param request
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    ModelAndView login(HttpServletRequest request);

    /**
     * 登录验证
     * @param request
     * @return
     */
    @RequestMapping(value = "/login/main",method = RequestMethod.POST)
    ResponseData<Void> auth(HttpServletRequest request, HttpServletResponse response);


    /**
     * 生成验证码
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login/createImgCode",method = RequestMethod.GET)
    void createImgCode(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
