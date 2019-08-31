package com.github.ciifm.personal.admin.dao;

import com.github.ciifm.handy.jdbc.mapper.GenericMapper;
import com.github.ciifm.personal.admin.dao.dataobject.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
  * <p>
  * 用户角色信息数据访问接口
  * </p>
  *
  * @author rui.zhou
  * date: 2019-8-19 12:50:09
  */
@Mapper
public interface UserRoleDAO extends GenericMapper<UserRoleDO, Long> {
    Set<String> findRoles(Long uid);

    Set<Long> findRoleIds(Long uid);

    int deleteByUserId(Long id);

}