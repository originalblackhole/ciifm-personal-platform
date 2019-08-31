package com.github.ciifm.personal.admin.provider.service.system.impl;

import com.github.ciifm.handy.model.CommonMessageCode;
import com.github.ciifm.handy.model.ResponseData;
import com.github.ciifm.personal.admin.component.service.impl.PermissionComponentServiceImpl;
import com.github.ciifm.personal.admin.dao.dataobject.PermissionDO;
import com.github.ciifm.personal.admin.provider.component.CurrentContext;
import com.github.ciifm.personal.admin.provider.component.ResourceTypeEnum;
import com.github.ciifm.personal.admin.provider.service.system.SystemPermissionService;
import com.github.ciifm.personal.admin.provider.support.mapper.PermissionMapper;
import com.github.ciifm.personal.admin.provider.util.TreeUtil;
import com.github.ciifm.personal.admin.share.dto.system.PermissionDTO;
import com.github.ciifm.personal.admin.share.dto.system.request.AddSystemPermissionRequest;
import com.github.ciifm.personal.admin.share.dto.system.request.EditSystemPermissionRequest;
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
 * @date 2019/8/30 0030 15:13
 */
@Slf4j
@Service
public class SystemPermissionServiceImpl implements SystemPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private PermissionComponentServiceImpl permissionComponentService;

    @Override
    public ModelAndView add(Long parentId, String resourceType) {
        PermissionDO parent = permissionComponentService.get(parentId);
        PermissionDO permission = new PermissionDO();
        if(parent != null){
            permission.setParentId(parentId);
            permission.setParentName(parent.getName());
            permission.setResourceType(resourceType);
        }
        return new ModelAndView("admin/permission/add").addObject("permission",permission);
    }

    @Override
    public ModelAndView addRoot() {
        return new ModelAndView("admin/permission/root");
    }

    @Override
    public ModelAndView edit(Long id) {
        PermissionDO permission = permissionComponentService.get(id);
        PermissionDO parent = permissionComponentService.get(permission.getParentId());
        if(parent != null){
            permission.setParentId(parent.getId());
            permission.setParentName(parent.getName());
        }
        return new ModelAndView("admin/permission/edit").addObject("permission",permission);
    }

    @Override
    public ModelAndView list() {
        return new ModelAndView("admin/permission/list");
    }

    @Override
    public ResponseData<Void> add(AddSystemPermissionRequest addSystemPermissionRequest) {
        PermissionDO permissionDO = permissionComponentService.findByName(addSystemPermissionRequest.getName());
        if(permissionDO != null){
            return new ResponseData<>(1200,"权限名称已经存在");
        }
        permissionDO = permissionMapper.doTOPermissionDO(addSystemPermissionRequest);
        permissionDO.setLevel(Integer.parseInt(addSystemPermissionRequest.getResourceType()));
        permissionDO.setCreator(CurrentContext.userName());
        permissionDO.setModifier(CurrentContext.userName());
        permissionComponentService.insertSelective(permissionDO);

        permissionDO = permissionComponentService.findByName(addSystemPermissionRequest.getName());
        PermissionDO parent = permissionComponentService.get(permissionDO.getParentId());
        permissionDO.setParentIds(parent.getParentIds() + "|" + permissionDO.getId());
        //permissionDO.setSort();//TODO 排序字段待处理
        permissionComponentService.updateSelective(permissionDO);
        return new ResponseData<>(CommonMessageCode.SUCCESS);
    }

    @Override
    public ResponseData<Void> addRoot(AddSystemPermissionRequest addSystemPermissionRequest) {
        PermissionDO permissionDO = permissionComponentService.findByName(addSystemPermissionRequest.getName());
        if(permissionDO != null){
            return new ResponseData<>(1200,"权限名称已经存在");
        }
        permissionDO = permissionMapper.doTOPermissionDO(addSystemPermissionRequest);
        permissionDO.setLevel(1);
        permissionDO.setCreator(CurrentContext.userName());
        permissionDO.setModifier(CurrentContext.userName());
        permissionDO.setResourceType(ResourceTypeEnum.TOP_MENU.getCode());
        permissionComponentService.insertSelective(permissionDO);

        permissionDO = permissionComponentService.findByName(addSystemPermissionRequest.getName());
        permissionDO.setParentIds(String.valueOf(permissionDO.getId()));
        //permissionDO.setSort();//TODO 排序字段待处理
        permissionComponentService.updateSelective(permissionDO);
        return new ResponseData<>(CommonMessageCode.SUCCESS);
    }

    @Override
    public ResponseData<Void> edit(EditSystemPermissionRequest editSystemPermissionRequest) {
        PermissionDO permissionDO = permissionComponentService.findByName(editSystemPermissionRequest.getName());
        if(permissionDO != null && editSystemPermissionRequest.getId() != permissionDO.getId()){
            return new ResponseData<>(1200,"权限名称已经存在");
        }
        permissionDO = permissionMapper.editDoTOPermissionDO(editSystemPermissionRequest);
        PermissionDO parent = permissionComponentService.get(permissionDO.getParentId());
        permissionDO.setParentIds(parent.getParentIds() + "|" + permissionDO.getId());
        permissionComponentService.updateSelective(permissionDO);
        return new ResponseData<>(CommonMessageCode.SUCCESS);
    }

    @Override
    public ResponseData<Void> del(Long id) {
        if(id == null){
            return new ResponseData<>(1201,"权限ID不能为空");
        }
        //1、获取所有权限列表
        List<PermissionDO> permissionList = permissionComponentService.selectAll();

        //2. 获取所有子孙节点
        List<Long> childs = TreeUtil.getChildNodes(permissionList,id);
        Long [] ids = new Long[childs.size()];
        if(ids != null && ids.length > 0){
            permissionComponentService.delete(childs.toArray(ids));

            //3、删除(角色-权限)表数据
            permissionComponentService.deleteBacthPermission(ids);
        }
        return new ResponseData<>(CommonMessageCode.SUCCESS);
    }

    @Override
    public ResponseData<List<PermissionDTO>> treelist() {
        //1、 查询所有权限
        List<PermissionDO> permissionList = permissionComponentService.selectAll();
        List<PermissionDO> rootList = new ArrayList<>();
        for(PermissionDO permissionDO : permissionList){
            if(null == permissionDO.getParentId()){
                rootList.add(permissionDO);
            }
        }
        //2、获取孩子节点
        for(PermissionDO root : rootList){
            root.setChildren(this.getChildrenList(root.getId(),permissionList));
        }
        return new ResponseData (CommonMessageCode.SUCCESS,permissionMapper.changeDTOList(rootList));
    }

    /**
     * 获取子数据集合
     * @param id
     * @param entityList
     * @return
     */
    private List<PermissionDO> getChildrenList(Long id, List<PermissionDO> entityList) {
        List<PermissionDO> childList = new ArrayList<>();

        //子集的直接子对象
        for (PermissionDO entity : entityList) {
            if (id == entity.getParentId()) {
                childList.add(entity);
            }
        }

        //子集的间接子对象
        for (PermissionDO entity : childList) {
            entity.setChildren(this.getChildrenList(entity.getId(),entityList));
        }

        //递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

}
