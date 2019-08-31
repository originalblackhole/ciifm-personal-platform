package com.github.ciifm.personal.admin.share.service;

import com.github.ciifm.handy.model.ResponseData;
import com.github.ciifm.personal.admin.share.dto.MenuDTO;
import com.github.ciifm.personal.admin.share.dto.request.UserInfoRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

public interface IUserShareService {

    /**
     * 网页首页
     * @return
     */
    @RequestMapping(value = "index",method = RequestMethod.GET)
    ModelAndView index();

    /**
     * 后台首页
     * @return
     */
    @RequestMapping(value = "home",method = RequestMethod.GET)
    ModelAndView home();

    /**
     * 个人资料页
     * @return
     */
    @RequestMapping(value = "/user/userInfo",method = RequestMethod.GET)
    ModelAndView userInfo();

    /**
     * 修改密码页
     * @return
     */
    @RequestMapping(value = "/user/password",method = RequestMethod.GET)
    ModelAndView password();

    /**
     * 修改个人资料
     * @param userInfoRequest
     * @return
     */
    @RequestMapping(value = "/user/updateUserInfo",method = RequestMethod.POST)
    ResponseData<Void> updateUserInfo(UserInfoRequest userInfoRequest);

    /**
     * 修改密码
     * @param oldPwd
     * @param newPwd
     * @param confirmPwd
     * @return
     */
    @RequestMapping(value = "/user/changePassword",method = RequestMethod.POST)
    ResponseData<Void> updatePassword(String oldPwd, String newPwd, String confirmPwd);

    /**
     * 查询用户菜单
     * @return
     */
    @RequestMapping(value = "/user/getUserMenu",method = RequestMethod.GET)
    List<MenuDTO> getUserMenu();


}
