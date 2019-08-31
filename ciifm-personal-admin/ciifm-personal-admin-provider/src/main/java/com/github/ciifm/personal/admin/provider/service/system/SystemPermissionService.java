package com.github.ciifm.personal.admin.provider.service.system;

import com.github.ciifm.handy.model.ResponseData;
import com.github.ciifm.personal.admin.share.dto.system.PermissionDTO;
import com.github.ciifm.personal.admin.share.dto.system.request.AddSystemPermissionRequest;
import com.github.ciifm.personal.admin.share.dto.system.request.EditSystemPermissionRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/30 0030 15:13
 */
public interface SystemPermissionService {

    ModelAndView add(Long parentId, String resourceType);

    ModelAndView addRoot();

    ModelAndView edit(Long id);

    ModelAndView list();

    ResponseData<Void> add(AddSystemPermissionRequest addSystemPermissionRequest);

    ResponseData<Void> addRoot(AddSystemPermissionRequest addSystemPermissionRequest);

    ResponseData<Void> edit(EditSystemPermissionRequest editSystemPermissionRequest);

    ResponseData<Void> del(Long id);

    ResponseData<List<PermissionDTO>> treelist();
}
