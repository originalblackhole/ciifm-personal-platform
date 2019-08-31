package com.github.ciifm.personal.admin.provider.support.mapper;

import com.github.ciifm.personal.admin.dao.dataobject.PermissionDO;
import com.github.ciifm.personal.admin.dao.dataobject.UserDO;
import com.github.ciifm.personal.admin.share.dto.MenuDTO;
import com.github.ciifm.personal.admin.share.dto.UserDTO;
import com.github.ciifm.personal.admin.share.dto.system.request.AddSystemUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    /*@Mappings({
            @Mapping(source = "nickname", target = "userName"),
            @Mapping(source = "headimgurl", target = "hpUrl"),
            @Mapping(source = "openid", target = "openId")
    })
    PassengerDO doToPassengerDO(UserInfoResponseDTO data);*/


    UserDTO userDOToUserDTO(UserDO userDO);

    List<UserDTO> doList(List<UserDO> userList);

    @Mapping(source = "password", target = "passWord")
    UserDO doToUserDO(AddSystemUserRequest addUserRequestDTO);


    @Mappings({
            @Mapping(source = "parentId", target = "pid"),
            @Mapping(source = "name", target = "title"),
            @Mapping(source = "children", target = "childrens")
    })
    MenuDTO changeMenuDTO(PermissionDO permissionDO);

    List<MenuDTO> changeMenuDTOList(List<PermissionDO> toTreePermission);
}
