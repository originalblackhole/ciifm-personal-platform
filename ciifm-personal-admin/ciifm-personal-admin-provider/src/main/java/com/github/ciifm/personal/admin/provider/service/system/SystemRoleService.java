package com.github.ciifm.personal.admin.provider.service.system;

import com.github.ciifm.handy.model.PageCondition;
import com.github.ciifm.handy.model.ResponseData;
import com.github.ciifm.personal.admin.share.dto.system.RoleDTO;
import com.github.ciifm.personal.admin.share.dto.system.request.AddSystemRoleRequest;
import com.github.ciifm.personal.admin.share.dto.system.request.DeleteSystemRoleRequest;
import com.github.ciifm.personal.admin.share.dto.system.request.EditSystemRoleRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface SystemRoleService {
    ModelAndView add();

    ModelAndView edit(Long id);

    ModelAndView list();

    ResponseData<Void> add(AddSystemRoleRequest addSystemRoleRequest);

    ResponseData<Void> del(DeleteSystemRoleRequest deleteSystemRoleRequest);

    ResponseData<Void> edit(EditSystemRoleRequest editSystemRoleRequest);

    ResponseData<List<RoleDTO>> search(PageCondition<String> pageCondition);
}
