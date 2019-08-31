package com.github.ciifm.personal.admin.dao;

import com.github.ciifm.handy.jdbc.mapper.GenericMapper;
import com.github.ciifm.personal.admin.dao.dataobject.PermissionDO;
import com.github.ciifm.personal.admin.dao.dataobject.RolePermissionDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
  * <p>
  * 权限信息数据访问接口
  * </p>
  *
  * @author rui.zhou
  * date: 2019-8-19 12:50:07
  */
@Mapper
public interface PermissionDAO extends GenericMapper<PermissionDO, Long> {

    Set<String> findPermissionsURL(List<Long> ids);

    PermissionDO findByName(String name);

    List<PermissionDO> search();

    List<PermissionDO> findAllMenu();

    void deleteBacthPermission(Long[] permissionIds);

    List<PermissionDO> findByRoleId(Long id);

    void insertBatchRolePermission(List<RolePermissionDO> rolePermissionList);

    void deleteBacthByRids(Long[] ids);
}