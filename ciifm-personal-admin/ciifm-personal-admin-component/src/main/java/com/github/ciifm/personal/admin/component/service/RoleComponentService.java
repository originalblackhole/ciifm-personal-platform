package com.github.ciifm.personal.admin.component.service;

import com.github.ciifm.personal.admin.dao.dataobject.RoleDO;
import com.github.ciifm.personal.admin.dao.dataobject.UserRoleDO;

import java.util.List;

public interface RoleComponentService {

    RoleDO findByName(String name);

    void deleteRolePerssionByRid(Long id);

    void deleteByUserId(Long id);

    void insertBatchUserRole(List<UserRoleDO> list);
}
