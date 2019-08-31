package com.github.ciifm.personal.admin.dao;

import com.github.ciifm.handy.jdbc.mapper.GenericMapper;
import com.github.ciifm.personal.admin.dao.dataobject.PermissionDO;
import com.github.ciifm.personal.admin.dao.dataobject.RoleDO;
import com.github.ciifm.personal.admin.dao.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
  * <p>
  * 用户信息数据访问接口
  * </p>
  *
  * @author rui.zhou
  * date: 2019-8-19 12:50:05
  */
@Mapper
public interface UserDAO extends GenericMapper<UserDO, Long> {

    Long findId(String username);

    UserDO findByUsername(String username);

    List<UserDO> findByCondition(String condition);

    List<RoleDO> findRoles(Long id);

    List<PermissionDO> findPerssions(Long id);
}