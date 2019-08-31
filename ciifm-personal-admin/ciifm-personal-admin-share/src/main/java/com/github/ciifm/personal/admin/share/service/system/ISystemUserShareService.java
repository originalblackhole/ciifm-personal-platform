package com.github.ciifm.personal.admin.share.service.system;

import com.github.ciifm.handy.model.ResponseData;
import com.github.ciifm.personal.admin.share.dto.UserDTO;
import com.github.ciifm.personal.admin.share.dto.system.request.AddSystemUserRequest;
import com.github.ciifm.personal.admin.share.dto.system.request.EditSystemUserRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/24 0024 20:27
 */
public interface ISystemUserShareService {

    @RequestMapping(value = "/admin/user/list",method = RequestMethod.GET)
    ModelAndView list();

    /**
     * 查询用户列表
     * @param page
     * @param limit
     * @param condition
     * @return
     */
    @RequestMapping(value = "/admin/user/list",method = RequestMethod.POST)
    ResponseData<List<UserDTO>> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                     String condition);

    /**
     * 删除用户
     * @param editUserRequest
     * @return
     */
    @RequestMapping(value = "/admin/user/delete",method = RequestMethod.POST)
    ResponseData<Void> delete(EditSystemUserRequest editUserRequest);

    /**
     * 批量删除用户
     * @param editUserRequest
     * @return
     */
    @RequestMapping(value = "/admin/user/deleteBatch",method = RequestMethod.POST)
    ResponseData<Void> deleteBatch(EditSystemUserRequest editUserRequest);

    /**
     * 添加用户页
     * @return
     */
    @RequestMapping(value = "/admin/user/add",method = RequestMethod.GET)
    ModelAndView addUser();

    /**
     * 添加用户
     * @param addUserRequestDTO
     * @return
     */
    @RequestMapping(value = "/admin/user/add",method = RequestMethod.POST)
    ResponseData<Void> addUser(AddSystemUserRequest addUserRequestDTO);

    /**
     * 修改用户页
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/user/edit",method = RequestMethod.GET)
    ModelAndView editUser(Long id);

    /**
     * 修改用户
     * @param editUserRequest
     * @return
     */
    @RequestMapping(value = "/admin/user/edit",method = RequestMethod.POST)
    ResponseData<Void> editUser(EditSystemUserRequest editUserRequest);

    /**
     * 修改用户状态
     * @param editUserRequest
     * @return
     */
    @RequestMapping(value = "/admin/user/changeState",method = RequestMethod.POST)
    ResponseData<Void> changeUserState(EditSystemUserRequest editUserRequest);

    /**
     * 查询用户菜单
     * @return
     */
    //@RequestMapping(value = "/admin/user/getUserMenu",method = RequestMethod.GET)
    //List<MenuDTO> getUserMenu();

    /**
     * 批量增加测试用户
     */
//    @RequestMapping(value = "/admin/user/addBatch",method = RequestMethod.GET)
//    ResponseData<Void> addBatch();
}
