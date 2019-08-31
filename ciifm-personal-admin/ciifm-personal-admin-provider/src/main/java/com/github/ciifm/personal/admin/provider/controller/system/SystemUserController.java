package com.github.ciifm.personal.admin.provider.controller.system;

import com.github.ciifm.handy.model.ResponseData;
import com.github.ciifm.personal.admin.provider.service.system.impl.SystemUserServiceImpl;
import com.github.ciifm.personal.admin.share.dto.UserDTO;
import com.github.ciifm.personal.admin.share.dto.system.request.AddSystemUserRequest;
import com.github.ciifm.personal.admin.share.dto.system.request.EditSystemUserRequest;
import com.github.ciifm.personal.admin.share.service.system.ISystemUserShareService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/30 0030 14:28
 */
@Slf4j
@RestController
public class SystemUserController implements ISystemUserShareService {

    @Autowired
    private SystemUserServiceImpl systemUserService;

    @Override
    @RequiresPermissions("admin:user:list")
    public ModelAndView list() {
        return systemUserService.list();
    }

    @Override
    @RequiresPermissions("admin:user:list")
    public ResponseData<List<UserDTO>> list(Integer page, Integer limit, String condition) {
        return systemUserService.list(page,limit,condition);
    }

    @Override
    @RequiresPermissions("admin:user:delete")
    public ResponseData<Void> delete(EditSystemUserRequest editUserRequest) {
        return systemUserService.delete(editUserRequest);
    }

    @Override
    @RequiresPermissions("admin:user:deleteBatch")
    public ResponseData<Void> deleteBatch(@RequestBody EditSystemUserRequest editUserRequest) {
        return systemUserService.deleteBatch(editUserRequest.getIds());
    }

    @Override
    public ModelAndView addUser() {
        return systemUserService.addUser();
    }

    @Override
    @RequiresPermissions("admin:user:add")
    public ResponseData<Void> addUser(@RequestBody @Validated AddSystemUserRequest addUserRequestDTO) {
        return systemUserService.addUser(addUserRequestDTO);
    }

    @Override
    public ModelAndView editUser(Long id) {
        return systemUserService.editUser(id);
    }

    @Override
    @RequiresPermissions("admin:user:edit")
    public ResponseData<Void> editUser(@RequestBody EditSystemUserRequest editUserRequest) {
        return systemUserService.editUser(editUserRequest);
    }

    @Override
    @RequiresPermissions("admin:user:changeState")
    public ResponseData<Void> changeUserState(EditSystemUserRequest editUserRequest) {
        return systemUserService.changeUserState(editUserRequest);
    }
}
