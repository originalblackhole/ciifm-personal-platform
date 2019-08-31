package com.github.ciifm.personal.admin.provider.service.system.impl;

import com.github.ciifm.handy.model.CommonMessageCode;
import com.github.ciifm.handy.model.PageCondition;
import com.github.ciifm.handy.model.Pagination;
import com.github.ciifm.handy.model.ResponseData;
import com.github.ciifm.personal.admin.component.service.impl.PermissionComponentServiceImpl;
import com.github.ciifm.personal.admin.component.service.impl.RoleComponentServiceImpl;
import com.github.ciifm.personal.admin.dao.dataobject.PermissionDO;
import com.github.ciifm.personal.admin.dao.dataobject.RoleDO;
import com.github.ciifm.personal.admin.dao.dataobject.RolePermissionDO;
import com.github.ciifm.personal.admin.provider.component.CurrentContext;
import com.github.ciifm.personal.admin.provider.service.system.SystemRoleService;
import com.github.ciifm.personal.admin.provider.support.mapper.PermissionMapper;
import com.github.ciifm.personal.admin.provider.support.mapper.RoleMapper;
import com.github.ciifm.personal.admin.provider.util.TreeUtil;
import com.github.ciifm.personal.admin.share.dto.system.PermissionDTO;
import com.github.ciifm.personal.admin.share.dto.system.RoleDTO;
import com.github.ciifm.personal.admin.share.dto.system.RolePermissionResponse;
import com.github.ciifm.personal.admin.share.dto.system.request.AddSystemRoleRequest;
import com.github.ciifm.personal.admin.share.dto.system.request.DeleteSystemRoleRequest;
import com.github.ciifm.personal.admin.share.dto.system.request.EditSystemRoleRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/30 0030 16:24
 */
@Slf4j
@Service
public class SystemRoleServiceImpl implements SystemRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RoleComponentServiceImpl roleComponentService;

    @Autowired
    private PermissionComponentServiceImpl permissionComponentService;

    @Override
    public ModelAndView add() {
        List<PermissionDO> permissionList =  permissionComponentService.selectAll();
        return new ModelAndView("admin/role/add").addObject("permissionList", TreeUtil.toTreePermission(permissionList));
    }

    @Override
    public ModelAndView edit(Long id) {
        List<PermissionDO> permissionList =  permissionComponentService.selectAll();
        List<PermissionDO> rolePermissions = permissionComponentService.findByRoleId(id);
        for(PermissionDO permission : permissionList){
            for(PermissionDO rolePermission : rolePermissions){
                if(permission.getId() == rolePermission.getId()){
                    permission.setHasPermission(true);
                }
            }
        }
        RolePermissionResponse rolePermissionResponse = new RolePermissionResponse();
        rolePermissionResponse.setRole(roleMapper.doToRoleDTO(roleComponentService.get(id)));
        rolePermissionResponse.setPermissionList(permissionMapper.changeDTOList(TreeUtil.toTreePermission(permissionList)));
        //log.info("rolePermission | {}" , rolePermissionResponse.getPermissionList());
        return new ModelAndView("admin/role/edit").addObject("rolePermission",rolePermissionResponse);
    }

    @Override
    public ModelAndView list() {
        return new ModelAndView("admin/role/list");
    }

    @Override
    public ResponseData<Void> add(AddSystemRoleRequest addSystemRoleRequest) {
        //1、 检查角色名
        RoleDO roleDO = roleComponentService.findByName(addSystemRoleRequest.getName());
        if(roleDO != null){
            return new ResponseData<>(1200,"角色名称已经存在");
        }

        //2、 添加角色
        roleDO = new RoleDO();
        roleDO.setRoleName(addSystemRoleRequest.getName());
        roleDO.setRemark(addSystemRoleRequest.getRemark());
        roleDO.setCreator(CurrentContext.userName());
        roleDO.setModifier(CurrentContext.userName());
        roleComponentService.insertSelective(roleDO);

        //3、 批量添加角色相关权限
        RoleDO role = roleComponentService.findByName(addSystemRoleRequest.getName());
        List<RolePermissionDO> rolePermissionList = new ArrayList<>();
        for(PermissionDTO permissionDTO : addSystemRoleRequest.getPermissionList()){
            RolePermissionDO rolePermissionDO = new RolePermissionDO();
            rolePermissionDO.setRid(role.getId());
            rolePermissionDO.setPid(permissionDTO.getId());
            rolePermissionDO.setCreator(CurrentContext.userName());
            rolePermissionDO.setModifier(CurrentContext.userName());
            rolePermissionList.add(rolePermissionDO);
        }
        if(rolePermissionList.size() > 0){
            permissionComponentService.insertBatchRolePermission(rolePermissionList);
        }

        return new ResponseData<>(CommonMessageCode.SUCCESS);
    }

    @Override
    public ResponseData<Void> del(DeleteSystemRoleRequest deleteSystemRoleRequest) {
        //1、检查角色名
        for(Long id : deleteSystemRoleRequest.getIds()){
            if(id == 1){
                return new ResponseData<>(1000,"不能删除超级管理员角色");
            }
        }
        //2、批量删除角色
        roleComponentService.delete(deleteSystemRoleRequest.getIds());
        //3、批量删除角色相关的权限
        if(null != deleteSystemRoleRequest.getIds() && deleteSystemRoleRequest.getIds().length > 0){
            permissionComponentService.deleteBacthByRids(deleteSystemRoleRequest.getIds());
        }
        return new ResponseData<>(CommonMessageCode.SUCCESS);
    }

    @Override
    public ResponseData<Void> edit(EditSystemRoleRequest editSystemRoleRequest) {
        //1、 修改角色信息
        RoleDO roleDO = roleComponentService.findByName(editSystemRoleRequest.getName());
        if(roleDO != null && roleDO.getId() != editSystemRoleRequest.getId()){
            return new ResponseData<>(1200,"角色名称已经存在");
        }
        roleDO.setRoleName(editSystemRoleRequest.getName());
        roleDO.setRemark(editSystemRoleRequest.getRemark());
        roleDO.setCreator(CurrentContext.userName());
        roleDO.setModifier(CurrentContext.userName());
        roleComponentService.updateSelective(roleDO);

        //2、 删除角色相关权限
        roleComponentService.deleteRolePerssionByRid(editSystemRoleRequest.getId());

        //3、 重新添加权限
        List<RolePermissionDO> rolePermissionList = new ArrayList<>();
        for(PermissionDTO permission:editSystemRoleRequest.getPermissionList()){
            RolePermissionDO rolePermissionDO = new RolePermissionDO();
            rolePermissionDO.setRid(editSystemRoleRequest.getId());
            rolePermissionDO.setPid(permission.getId());
            rolePermissionDO.setPermissionDesc(editSystemRoleRequest.getName());
            rolePermissionDO.setCreator(CurrentContext.userName());
            rolePermissionDO.setModifier(CurrentContext.userName());
            rolePermissionList.add(rolePermissionDO);
        }
        if(rolePermissionList.size() > 0){
            permissionComponentService.insertBatchRolePermission(rolePermissionList);
        }
        return new ResponseData<>(CommonMessageCode.SUCCESS);
    }

    @Override
    public ResponseData<List<RoleDTO>> search(PageCondition<String> pageCondition) {
        List<RoleDO> roleDOList;
        ResponseData<List<RoleDTO>> response = new ResponseData<>(CommonMessageCode.SUCCESS);
        RoleDO roleDO = new RoleDO();
        roleDO.setRoleName(pageCondition.getCondition());
        if(StringUtils.isBlank(pageCondition.getCondition())){
            PageCondition<RoleDO> roleCondition = new PageCondition<>();
            roleCondition.setCondition(roleDO);
            roleCondition.setPageIndex(pageCondition.getPageIndex());
            roleCondition.setPageSize(pageCondition.getPageSize());
            Pagination<RoleDO> pagination = roleComponentService.search(roleCondition);
            roleDOList = pagination.getRows();
            response.setCount(pagination.getRowTotal());
        }else{
            roleDOList = roleComponentService.selectAll(roleDO);
            response.setCount(roleDOList.size());
        }
        response.setData(roleMapper.doToRoleDTOList(roleDOList));
        return response;
    }
}
