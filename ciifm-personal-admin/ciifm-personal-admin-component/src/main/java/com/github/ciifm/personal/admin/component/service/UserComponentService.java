package com.github.ciifm.personal.admin.component.service;

import com.github.ciifm.personal.admin.dao.dataobject.PermissionDO;
import com.github.ciifm.personal.admin.dao.dataobject.RoleDO;
import com.github.ciifm.personal.admin.dao.dataobject.UserDO;

import java.util.List;

public interface UserComponentService {

    UserDO findByUsername(String userName);

    void addUser(UserDO userDO);

    List<RoleDO> findRoles(Long id);

    List<PermissionDO> findPerssions(Long id);

    List<UserDO> getUserByCondition(String condition);

}
