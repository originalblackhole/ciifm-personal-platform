package com.github.ciifm.personal.admin.dao;

import com.github.ciifm.handy.jdbc.mapper.GenericMapper;
import com.github.ciifm.personal.admin.dao.dataobject.RoleDO;
import com.github.ciifm.personal.admin.dao.dataobject.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
  * <p>
  * 角色信息数据访问接口
  * </p>
  *
  * @author rui.zhou
  * date: 2019-8-19 12:50:06
  */
@Mapper
public interface RoleDAO extends GenericMapper<RoleDO, Long> {

    RoleDO findByName(String name);

    void deleteRolePerssionByRid(Long id);

    void deleteByUserId(Long id);

    void insertBatchUserRole(List<UserRoleDO> list);
}