package com.github.ciifm.personal.admin.provider.service.impl;

import com.github.ciifm.handy.model.CommonMessageCode;
import com.github.ciifm.handy.model.ResponseData;
import com.github.ciifm.handy.util.Context;
import com.github.ciifm.personal.admin.component.service.impl.UserComponentServiceImpl;
import com.github.ciifm.personal.admin.dao.dataobject.PermissionDO;
import com.github.ciifm.personal.admin.dao.dataobject.UserDO;
import com.github.ciifm.personal.admin.provider.component.CurrentContext;
import com.github.ciifm.personal.admin.provider.service.UserService;
import com.github.ciifm.personal.admin.provider.service.system.impl.SystemUserServiceImpl;
import com.github.ciifm.personal.admin.provider.support.mapper.UserMapper;
import com.github.ciifm.personal.admin.provider.util.ShiroUtil;
import com.github.ciifm.personal.admin.provider.util.TreeUtil;
import com.github.ciifm.personal.admin.share.dto.MenuDTO;
import com.github.ciifm.personal.admin.share.dto.request.UserInfoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/30 0030 20:16
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserComponentServiceImpl userComponentService;

    @Autowired
    private SystemUserServiceImpl systemUserService;

    @Override
    public ModelAndView index() {
        log.info("username = " + Context.getCurrentContext().get("username"));
        UserDO user = userComponentService.findByUsername(CurrentContext.userName());
        return new ModelAndView("index").addObject("currentUser", user);
    }

    @Override
    public ModelAndView home() {
        return new ModelAndView("main");
    }

    @Override
    public ModelAndView userInfo() {
        UserDO user = userComponentService.findByUsername(CurrentContext.userName());
        user.setRoles(CurrentContext.roles());
        return new ModelAndView("user/user_info").addObject("currentUser",user);
    }

    @Override
    public ModelAndView password() {
        return new ModelAndView("user/change_password").addObject("username",CurrentContext.userName());
    }

    @Override
    public ResponseData<Void> updateUserInfo(UserInfoRequest userInfoRequest) {
        if(systemUserService.hasEmail(CurrentContext.userName(),userInfoRequest.getEmail())){
            return new ResponseData<>(1110,"邮箱已经存在");
        }
        if(systemUserService.hasPhone(CurrentContext.userName(),userInfoRequest.getTel())){
            return new ResponseData<>(1111,"手机号已经存在");
        }
        UserDO user = userComponentService.findByUsername(CurrentContext.userName());
        user.setNickName(userInfoRequest.getNickName());
        user.setTel(userInfoRequest.getTel());
        user.setEmail(userInfoRequest.getEmail());
        user.setRemark(userInfoRequest.getRemark());
        user.setModifier(CurrentContext.userName());
        userComponentService.update(user);
        return new ResponseData<>(CommonMessageCode.SUCCESS);
    }

    @Override
    public ResponseData<Void> updatePassword(String oldPwd, String newPwd, String confirmPwd) {
        log.info("oldPwd | {},newPwd | {} ,confirmPwd | {}",oldPwd,newPwd,confirmPwd);
        UserDO user = userComponentService.findByUsername(CurrentContext.userName());
        String password = ShiroUtil.encrypt(oldPwd,"MD5",user.getSalt(),2);
        if(!password.equals(user.getPassWord())){
            return new ResponseData<>(1105,"输入旧密码不正确");
        }
        if(!confirmPwd.equals(newPwd)){
            return new ResponseData<>(1106,"输入密码不一致");
        }
        String newPassword = ShiroUtil.encrypt(newPwd,"MD5",user.getSalt(),2);
        if(password.equals(newPassword)){
            return new ResponseData<>(1107,"新密码不能与旧密码相同");
        }
        user.setPassWord(newPassword);
        userComponentService.update(user);
        return new ResponseData<>(CommonMessageCode.SUCCESS);
    }

    @Override
    public List<MenuDTO> getUserMenu() {

        List<PermissionDO> showList = new ArrayList<>();
        List<PermissionDO> permissionList = userComponentService.findPerssions(CurrentContext.id());
        if(permissionList.size() <= 0){
            return new ArrayList<>();
        }
        for(PermissionDO permission : permissionList){
            if(permission.getIsShow()){
                showList.add(permission);
            }
        }
        return userMapper.changeMenuDTOList(TreeUtil.toTreePermission(showList));
    }
}
