package com.github.ciifm.personal.admin.share.service.system;

import com.github.ciifm.handy.model.PageCondition;
import com.github.ciifm.handy.model.ResponseData;
import com.github.ciifm.personal.admin.share.dto.system.RoleDTO;
import com.github.ciifm.personal.admin.share.dto.system.request.DeleteSystemRoleRequest;
import com.github.ciifm.personal.admin.share.dto.system.request.AddSystemRoleRequest;
import com.github.ciifm.personal.admin.share.dto.system.request.EditSystemRoleRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface ISystemRoleShareService {

    /**
     * 添加页
     * @return
     */
    @RequestMapping(value = "/admin/role/add",method = RequestMethod.GET)
    ModelAndView add();

    /**
     * 修改页
     * @return
     */
    @RequestMapping(value = "/admin/role/edit",method = RequestMethod.GET)
    ModelAndView edit(Long id);

    /**
     * 查询列表页
     * @return
     */
    @RequestMapping(value = "/admin/role/list",method = RequestMethod.GET)
    ModelAndView list();

    /**
     * 添加角色
     * @param addSystemRoleRequest
     * @return
     */
    @RequestMapping(value = "/admin/role/add",method = RequestMethod.POST)
    ResponseData<Void> add(AddSystemRoleRequest addSystemRoleRequest);

    /**
     * 删除角色
     * @param deleteSystemRoleRequest
     * @return
     */
    @RequestMapping(value = "/admin/role/deleteBatch",method = RequestMethod.POST)
    ResponseData<Void> del(DeleteSystemRoleRequest deleteSystemRoleRequest);

    /**
     * 修改角色
     * @param editSystemRoleRequest
     * @return
     */
    @RequestMapping(value = "/admin/role/edit",method = RequestMethod.POST)
    ResponseData<Void> edit(EditSystemRoleRequest editSystemRoleRequest);

    /**
     * 角色搜索
     * @param pageCondition
     * @return
     */
    @RequestMapping(value = "/admin/role/list",method = RequestMethod.POST)
    ResponseData<List<RoleDTO>> search(PageCondition<String> pageCondition);

}
