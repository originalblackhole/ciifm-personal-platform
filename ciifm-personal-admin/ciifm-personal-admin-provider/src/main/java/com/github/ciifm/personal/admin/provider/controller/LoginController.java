package com.github.ciifm.personal.admin.provider.controller;

import com.github.ciifm.handy.model.ResponseData;
import com.github.ciifm.personal.admin.provider.service.impl.LoginServiceImpl;
import com.github.ciifm.personal.admin.share.service.IloginShareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/24 0024 20:56
 */
@Slf4j
@RestController
public class LoginController implements IloginShareService {

    @Autowired
    private LoginServiceImpl loginService;

    @Override
    public ModelAndView login(HttpServletRequest request) {
        loginService.login(request);
        return new ModelAndView("login/login");
    }

    @Override
    public ResponseData<Void> auth(HttpServletRequest request, HttpServletResponse response) {
        return loginService.auth(request,response);
    }

    @Override
    public void createImgCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        loginService.createImgCode(request,response);
    }
}
