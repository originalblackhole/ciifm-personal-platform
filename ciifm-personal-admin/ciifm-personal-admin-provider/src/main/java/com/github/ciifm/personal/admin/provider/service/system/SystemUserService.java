package com.github.ciifm.personal.admin.provider.service.system;

import com.github.ciifm.handy.model.ResponseData;
import com.github.ciifm.personal.admin.share.dto.UserDTO;
import com.github.ciifm.personal.admin.share.dto.system.request.AddSystemUserRequest;
import com.github.ciifm.personal.admin.share.dto.system.request.EditSystemUserRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface SystemUserService {

    ModelAndView list();

    ResponseData<List<UserDTO>> list(Integer page, Integer limit, String condition);

    ResponseData<Void> delete(EditSystemUserRequest editUserRequest);

    ResponseData<Void> deleteBatch(Long[] ids);

    ModelAndView addUser();

    ResponseData<Void> addUser(AddSystemUserRequest addUserRequestDTO);

    ModelAndView editUser(Long id);

    ResponseData<Void> editUser(EditSystemUserRequest editUserRequest);

    ResponseData<Void> changeUserState(EditSystemUserRequest editUserRequest);
}
