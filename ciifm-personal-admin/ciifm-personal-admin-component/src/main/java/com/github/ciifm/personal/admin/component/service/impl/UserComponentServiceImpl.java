package com.github.ciifm.personal.admin.component.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.github.ciifm.personal.admin.dao.UserDAO;
import com.github.ciifm.handy.service.GenericService;
import com.github.ciifm.personal.admin.dao.dataobject.RoleDO;
import com.github.ciifm.personal.admin.dao.dataobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.ciifm.personal.admin.dao.dataobject.PermissionDO;
import com.github.ciifm.personal.admin.component.service.UserComponentService;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/30 0030 15:01
 */
@Service
public class UserComponentServiceImpl extends GenericService<UserDO, Long> implements UserComponentService {

    private UserDAO userDAO;

    public UserComponentServiceImpl(@Autowired UserDAO userDAO) {
        super(userDAO);
        this.userDAO = userDAO;
    }

    @Override
    public UserDO findByUsername(String userName) {
        return userDAO.findByUsername(userName);
    }

    @Override
    public void addUser(UserDO userDO) {
        userDAO.insertSelective(userDO);
    }

    @Override
    public List<RoleDO> findRoles(Long id) {
        return userDAO.findRoles(id);
    }

    @Override
    public List<PermissionDO> findPerssions(Long id) {
        return userDAO.findPerssions(id);
    }

    public List<UserDO> getUserByCondition(String condition) {
        return userDAO.findByCondition(condition);
    }
}
