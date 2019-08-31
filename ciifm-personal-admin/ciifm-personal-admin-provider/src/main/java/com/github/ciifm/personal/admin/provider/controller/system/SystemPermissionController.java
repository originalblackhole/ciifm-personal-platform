package com.github.ciifm.personal.admin.provider.controller.system;

import com.github.ciifm.handy.model.ResponseData;
import com.github.ciifm.personal.admin.provider.service.system.impl.SystemPermissionServiceImpl;
import com.github.ciifm.personal.admin.share.dto.system.PermissionDTO;
import com.github.ciifm.personal.admin.share.dto.system.request.AddSystemPermissionRequest;
import com.github.ciifm.personal.admin.share.dto.system.request.EditSystemPermissionRequest;
import com.github.ciifm.personal.admin.share.service.system.ISystemPermissionShareService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/30 0030 14:29
 */
@Slf4j
@RestController
public class SystemPermissionController implements ISystemPermissionShareService {

    @Autowired
    private SystemPermissionServiceImpl systemPermissionService;

    @Override
    public ModelAndView add(Long parentId, String resourceType) {
        return systemPermissionService.add(parentId,resourceType);
    }

    @Override
    public ModelAndView addRoot() {
        return systemPermissionService.addRoot();
    }

    @Override
    public ModelAndView edit(Long id) {
        return systemPermissionService.edit(id);
    }

    @Override
    @RequiresPermissions("admin:permission:list")
    public ModelAndView list() {
        return systemPermissionService.list();
    }

    @Override
    @RequiresPermissions("admin:permission:add")
    public ResponseData<Void> add(AddSystemPermissionRequest addSystemPermissionRequest) {
        return systemPermissionService.add(addSystemPermissionRequest);
    }

    @Override
    @RequiresPermissions("admin:permission:addRoot")
    public ResponseData<Void> addRoot(AddSystemPermissionRequest addSystemPermissionRequest) {
        return systemPermissionService.addRoot(addSystemPermissionRequest);
    }

    @Override
    @RequiresPermissions("admin:permission:edit")
    public ResponseData<Void> edit(EditSystemPermissionRequest editSystemPermissionRequest) {
        return systemPermissionService.edit(editSystemPermissionRequest);
    }

    @Override
    @RequiresPermissions("admin:permission:delete")
    public ResponseData<Void> del(Long id) {
        return systemPermissionService.del(id);
    }

    @Override
    @RequiresPermissions("admin:permission:list")
    public ResponseData<List<PermissionDTO>> treelist() {
        return systemPermissionService.treelist();
    }

}
