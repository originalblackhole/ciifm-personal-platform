package com.github.ciifm.personal.admin.provider.service;

import com.github.ciifm.handy.model.ResponseData;
import com.github.ciifm.personal.admin.share.dto.MenuDTO;
import com.github.ciifm.personal.admin.share.dto.request.UserInfoRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface UserService {
    ModelAndView index();

    ModelAndView home();

    ModelAndView userInfo();

    ModelAndView password();

    ResponseData<Void> updateUserInfo(UserInfoRequest userInfoRequest);

    ResponseData<Void> updatePassword(String oldPwd, String newPwd, String confirmPwd);

    List<MenuDTO> getUserMenu();
}
