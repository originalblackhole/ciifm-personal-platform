package com.github.ciifm.personal.admin.dao;

import com.github.ciifm.handy.jdbc.mapper.GenericMapper;
import com.github.ciifm.personal.admin.dao.dataobject.RolePermissionDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
  * <p>
  * 角色权限信息数据访问接口
  * </p>
  *
  * @author rui.zhou
  * date: 2019-8-19 12:50:10
  */
@Mapper
public interface RolePermissionDAO extends GenericMapper<RolePermissionDO, Long> {

    Set<Long> findPermissions(List<Long> roleIds);

    void deleteBacth(Long[] permissionIds);

    void deleteBacthByRid(Long[] roleIds);

    List<RolePermissionDO> findByRoleId(Long roleId);

    void deleteByRid(Long id);
}