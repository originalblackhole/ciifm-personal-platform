package com.github.ciifm.personal.admin.component.service;

import com.github.ciifm.personal.admin.dao.dataobject.PermissionDO;
import com.github.ciifm.personal.admin.dao.dataobject.RolePermissionDO;

import java.util.List;

public interface PermissionComponentService {

    PermissionDO findByName(String name);

    List<PermissionDO> selectAll();

    void deleteBacthPermission(Long[] permissionIds);

    List<PermissionDO> findByRoleId(Long id);

    void insertBatchRolePermission(List<RolePermissionDO> rolePermissionList);

    void deleteBacthByRids(Long[] ids);
}
