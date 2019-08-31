package com.github.ciifm.personal.admin.component.service.impl;

import com.github.ciifm.handy.service.GenericService;
import com.github.ciifm.personal.admin.component.service.RoleComponentService;
import com.github.ciifm.personal.admin.dao.RoleDAO;
import com.github.ciifm.personal.admin.dao.dataobject.RoleDO;
import com.github.ciifm.personal.admin.dao.dataobject.UserRoleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/30 0030 15:01
 */
@Component
public class RoleComponentServiceImpl extends GenericService<RoleDO, Long> implements RoleComponentService {

    private RoleDAO roleDAO;

    public RoleComponentServiceImpl(@Autowired RoleDAO roleDAO) {
        super(roleDAO);
        this.roleDAO = roleDAO;
    }

    @Override
    public RoleDO findByName(String name) {
        return roleDAO.findByName(name);
    }

    @Override
    public void deleteRolePerssionByRid(Long id) {
        roleDAO.deleteRolePerssionByRid(id);
    }

    @Override
    public void deleteByUserId(Long id) {
        roleDAO.deleteByUserId(id);
    }

    @Override
    public void insertBatchUserRole(List<UserRoleDO> list) {
        roleDAO.insertBatchUserRole(list);
    }

}
