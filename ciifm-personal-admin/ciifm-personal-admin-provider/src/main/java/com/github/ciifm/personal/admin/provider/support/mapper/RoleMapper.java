package com.github.ciifm.personal.admin.provider.support.mapper;

import com.github.ciifm.personal.admin.dao.dataobject.RoleDO;
import com.github.ciifm.personal.admin.share.dto.system.RoleDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO doToRoleDTO(RoleDO roleDO);

    List<RoleDTO> doToRoleDTOList(List<RoleDO> rows);
}
