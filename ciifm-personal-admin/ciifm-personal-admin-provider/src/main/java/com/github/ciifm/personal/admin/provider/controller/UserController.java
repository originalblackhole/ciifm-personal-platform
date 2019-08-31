package com.github.ciifm.personal.admin.provider.controller;

import com.github.ciifm.handy.model.ResponseData;
import com.github.ciifm.personal.admin.provider.service.impl.UserServiceImpl;
import com.github.ciifm.personal.admin.share.dto.MenuDTO;
import com.github.ciifm.personal.admin.share.dto.request.UserInfoRequest;
import com.github.ciifm.personal.admin.share.service.IUserShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/30 0030 14:26
 */
@RestController
public class UserController implements IUserShareService {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public ModelAndView index() {
        return userService.index();
    }

    @Override
    public ModelAndView home() {
        return userService.home();
    }

    @Override
    public ModelAndView userInfo() {
        return userService.userInfo();
    }

    @Override
    public ModelAndView password() {
        return userService.password();
    }

    @Override
    public ResponseData<Void> updateUserInfo(UserInfoRequest userInfoRequest) {
        return userService.updateUserInfo(userInfoRequest);
    }

    @Override
    public ResponseData<Void> updatePassword(@RequestParam(value = "oldPwd",required = false)String oldPwd,
                                             @RequestParam(value = "newPwd",required = false)String newPwd,
                                             @RequestParam(value = "confirmPwd",required = false)String confirmPwd) {
        return userService.updatePassword(oldPwd,newPwd,confirmPwd);
    }

    @Override
    public List<MenuDTO> getUserMenu() {
        return userService.getUserMenu();
    }
}
