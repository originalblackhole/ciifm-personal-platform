package com.github.ciifm.personal.admin.share.service.system;

import com.github.ciifm.handy.model.ResponseData;
import com.github.ciifm.personal.admin.share.dto.system.PermissionDTO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.ciifm.personal.admin.share.dto.system.request.AddSystemPermissionRequest;
import com.github.ciifm.personal.admin.share.dto.system.request.EditSystemPermissionRequest;
import java.util.List;

public interface ISystemPermissionShareService {

    /**
     * 添加子权限页
     * @return
     */
    @RequestMapping(value = "/admin/permission/add",method = RequestMethod.GET)
    ModelAndView add(Long parentId, String resourceType);

    /**
     * 添加根权限页
     * @return
     */
    @RequestMapping(value = "/admin/permission/addRoot",method = RequestMethod.GET)
    ModelAndView addRoot();

    /**
     * 编辑权限页
     * @return
     */
    @RequestMapping(value = "/admin/permission/edit",method = RequestMethod.GET)
    ModelAndView edit(Long id);

    /**
     * 权限列表页
     * @return
     */
    @RequestMapping(value = "/admin/permission/list",method = RequestMethod.GET)
    ModelAndView list();

    /**
     *  添加子权限
     * @param addSystemPermissionRequest
     * @return
     */
    @RequestMapping(value = "/admin/permission/add",method = RequestMethod.POST)
    ResponseData<Void> add(AddSystemPermissionRequest addSystemPermissionRequest);

    /**
     *  添加根权限
     * @param addSystemPermissionRequest
     * @return
     */
    @RequestMapping(value = "/admin/permission/addRoot",method = RequestMethod.POST)
    ResponseData<Void> addRoot(AddSystemPermissionRequest addSystemPermissionRequest);

    /**
     *  编辑权限
     * @param editSystemPermissionRequest
     * @return
     */
    @RequestMapping(value = "/admin/permission/edit",method = RequestMethod.POST)
    ResponseData<Void> edit(EditSystemPermissionRequest editSystemPermissionRequest);

    /**
     *  删除权限
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/permission/delete",method = RequestMethod.POST)
    ResponseData<Void> del(Long id);

    /**
     *  权限列表
     * @return
     */
    @RequestMapping(value = "/admin/permission/list",method = RequestMethod.POST)
    ResponseData<List<PermissionDTO>> treelist();

}
