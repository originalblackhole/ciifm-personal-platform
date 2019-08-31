package com.github.ciifm.personal.admin.provider.controller.system;

import com.github.ciifm.handy.model.PageCondition;
import com.github.ciifm.handy.model.ResponseData;
import com.github.ciifm.personal.admin.provider.service.system.impl.SystemRoleServiceImpl;
import com.github.ciifm.personal.admin.share.dto.system.RoleDTO;
import com.github.ciifm.personal.admin.share.dto.system.request.AddSystemRoleRequest;
import com.github.ciifm.personal.admin.share.dto.system.request.DeleteSystemRoleRequest;
import com.github.ciifm.personal.admin.share.dto.system.request.EditSystemRoleRequest;
import com.github.ciifm.personal.admin.share.service.system.ISystemRoleShareService;
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
public class SystemRoleController implements ISystemRoleShareService {

    @Autowired
    private SystemRoleServiceImpl systemRoleService;

    @Override
    public ModelAndView add() {
        return systemRoleService.add();
    }

    @Override
    public ModelAndView edit(Long id) {
        return systemRoleService.edit(id);
    }

    @Override
    @RequiresPermissions("admin:role:list")
    public ModelAndView list() {
        return systemRoleService.list();
    }

    @Override
    @RequiresPermissions("admin:role:add")
    public ResponseData<Void> add(@RequestBody @Validated AddSystemRoleRequest addSystemRoleRequest) {
        return systemRoleService.add(addSystemRoleRequest);
    }

    @Override
    @RequiresPermissions("admin:role:deleteBatch")
    public ResponseData<Void> del(@RequestBody @Validated DeleteSystemRoleRequest deleteSystemRoleRequest) {
        return systemRoleService.del(deleteSystemRoleRequest);
    }

    @Override
    @RequiresPermissions("admin:role:edit")
    public ResponseData<Void> edit(@RequestBody @Validated EditSystemRoleRequest editSystemRoleRequest) {
        return systemRoleService.edit(editSystemRoleRequest);
    }

    @Override
    @RequiresPermissions("admin:role:list")
    public ResponseData<List<RoleDTO>> search(PageCondition<String> pageCondition) {
        return systemRoleService.search(pageCondition);
    }
}
