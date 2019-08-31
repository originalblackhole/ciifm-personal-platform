package com.github.ciifm.personal.admin.provider.support.mapper;

import com.github.ciifm.personal.admin.dao.dataobject.PermissionDO;
import com.github.ciifm.personal.admin.share.dto.MenuDTO;
import com.github.ciifm.personal.admin.share.dto.system.PermissionDTO;
import com.github.ciifm.personal.admin.share.dto.system.request.AddSystemPermissionRequest;
import com.github.ciifm.personal.admin.share.dto.system.request.EditSystemPermissionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionDO doTOPermissionDO(AddSystemPermissionRequest addSystemPermissionRequest);

    PermissionDO editDoTOPermissionDO(EditSystemPermissionRequest editSystemPermissionRequest);

    @Mappings({
            @Mapping(source = "parentId", target = "pid"),
            @Mapping(source = "children", target = "childrens"),
            @Mapping(source = "name", target = "title")
    })
    MenuDTO doToMenuDTO(PermissionDO permissionDO);

    List<MenuDTO> doToMenuDTOList(List<PermissionDO> allMenu);

    List<PermissionDTO> changeDTOList(List<PermissionDO> rootList);

    PermissionDTO doToPermissionDTO(PermissionDO permissionDO);

    //List<PermissionDTO> changeToPermissionDTOList(List<PermissionDO> toTreePermission);
}
