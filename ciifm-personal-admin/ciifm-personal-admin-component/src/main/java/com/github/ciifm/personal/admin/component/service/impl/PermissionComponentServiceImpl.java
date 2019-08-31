package com.github.ciifm.personal.admin.component.service.impl;

import com.github.ciifm.handy.service.GenericService;
import com.github.ciifm.personal.admin.dao.PermissionDAO;
import com.github.ciifm.personal.admin.dao.dataobject.PermissionDO;
import com.github.ciifm.personal.admin.dao.dataobject.RolePermissionDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.github.ciifm.personal.admin.component.service.PermissionComponentService;
import java.util.List;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/30 0030 14:52
 */
@Component
public class PermissionComponentServiceImpl extends GenericService<PermissionDO, Long> implements PermissionComponentService {

    private PermissionDAO permissionDAO;

    public PermissionComponentServiceImpl(@Autowired PermissionDAO permissionDAO) {
        super(permissionDAO);
        this.permissionDAO = permissionDAO;
    }

    @Override
    public PermissionDO findByName(String name) {
        return permissionDAO.findByName(name);
    }

    @Override
    public List<PermissionDO> selectAll() {
        return permissionDAO.search();
    }

    @Override
    public void deleteBacthPermission(Long[] permissionIds) {
        permissionDAO.deleteBacthPermission(permissionIds);
    }

    @Override
    public List<PermissionDO> findByRoleId(Long id) {
        return permissionDAO.findByRoleId(id);
    }

    @Override
    public void insertBatchRolePermission(List<RolePermissionDO> rolePermissionList) {
        permissionDAO.insertBatchRolePermission(rolePermissionList);
    }

    @Override
    public void deleteBacthByRids(Long[] ids) {
        permissionDAO.deleteBacthByRids(ids);
    }


}
