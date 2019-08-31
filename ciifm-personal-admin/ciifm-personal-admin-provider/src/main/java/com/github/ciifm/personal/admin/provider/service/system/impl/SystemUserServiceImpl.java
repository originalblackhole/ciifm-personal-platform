package com.github.ciifm.personal.admin.provider.service.system.impl;

import com.github.ciifm.handy.model.CommonMessageCode;
import com.github.ciifm.handy.model.PageCondition;
import com.github.ciifm.handy.model.Pagination;
import com.github.ciifm.handy.model.ResponseData;
import com.github.ciifm.personal.admin.component.service.impl.RoleComponentServiceImpl;
import com.github.ciifm.personal.admin.component.service.impl.UserComponentServiceImpl;
import com.github.ciifm.personal.admin.dao.dataobject.RoleDO;
import com.github.ciifm.personal.admin.dao.dataobject.UserDO;
import com.github.ciifm.personal.admin.dao.dataobject.UserRoleDO;
import com.github.ciifm.personal.admin.provider.component.CurrentContext;
import com.github.ciifm.personal.admin.provider.service.system.SystemUserService;
import com.github.ciifm.personal.admin.provider.support.mapper.UserMapper;
import com.github.ciifm.personal.admin.provider.util.ShiroUtil;
import com.github.ciifm.personal.admin.share.dto.UserDTO;
import com.github.ciifm.personal.admin.share.dto.system.RoleDTO;
import com.github.ciifm.personal.admin.share.dto.system.request.AddSystemUserRequest;
import com.github.ciifm.personal.admin.share.dto.system.request.EditSystemUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/30 0030 17:52
 */
@Slf4j
@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserComponentServiceImpl userComponentService;

    @Autowired
    private RoleComponentServiceImpl roleComponentService;


    @Override
    public ModelAndView list() {
        return new ModelAndView("admin/user/list");
    }

    @Override
    public ResponseData<List<UserDTO>> list(Integer page, Integer limit, String condition) {
        ResponseData responseData = new ResponseData(CommonMessageCode.SUCCESS);
        if(StringUtils.isBlank(condition)){
            PageCondition<UserDO> pageCondition = new PageCondition<>();
            pageCondition.setPageIndex(page);
            pageCondition.setPageSize(limit);
            pageCondition.setCondition(new UserDO());
            Pagination<UserDO> pagination = userComponentService.search(pageCondition);
            responseData.setData(userMapper.doList(pagination.getRows()));
            responseData.setCount(pagination.getRowTotal());
        } else {
            List<UserDO> userDOList = userComponentService.getUserByCondition(condition);
            responseData.setData(userMapper.doList(userDOList));
            responseData.setCount(userDOList.size());
        }
        return responseData;
    }

    @Override
    public ResponseData<Void> delete(EditSystemUserRequest editUserRequest) {
        if("admin".equals(editUserRequest.getUserName())){
            return new ResponseData<>(1111,"不能删除admin管理员");
        }
        roleComponentService.deleteByUserId(editUserRequest.getId());
        userComponentService.delete(editUserRequest.getId());
        return new ResponseData<>(CommonMessageCode.SUCCESS);
    }

    @Override
    public ResponseData<Void> deleteBatch(Long[] ids) {
        for(Long id : ids){
            if(id == 1){
                return new ResponseData<>(1111,"不能删除admin管理员");
            }
            roleComponentService.deleteByUserId(id);
        }
        userComponentService.delete(ids);
        return new ResponseData<>(CommonMessageCode.SUCCESS);
    }

    @Override
    public ModelAndView addUser() {
        return new ModelAndView("admin/user/add").addObject("roles",roleComponentService.selectAll(new RoleDO()));
    }

    @Override
    public ResponseData<Void> addUser(AddSystemUserRequest addUserRequestDTO) {
        UserDO userDO = userComponentService.findByUsername(addUserRequestDTO.getUserName());
        if(null != userDO){
            return new ResponseData<>(1109,"用户名已经存在");
        }
        if(this.hasEmail(addUserRequestDTO.getUserName(),addUserRequestDTO.getEmail())){
            return new ResponseData<>(1110,"邮箱已经存在");
        }
        if(this.hasPhone(addUserRequestDTO.getUserName(),addUserRequestDTO.getTel())){
            return new ResponseData<>(1111,"手机号已经存在");
        }

        if(StringUtils.isBlank(addUserRequestDTO.getPassword())){
            String password = ShiroUtil.encrypt("123456","MD5",addUserRequestDTO.getUserName(),2);
            addUserRequestDTO.setPassword(password);
        }
        userDO = userMapper.doToUserDO(addUserRequestDTO);
        userDO.setSalt(addUserRequestDTO.getUserName());
        userDO.setCreator(CurrentContext.userName());
        userDO.setModifier(CurrentContext.userName());
        userComponentService.addUser(userDO);

        userDO = userComponentService.findByUsername(addUserRequestDTO.getUserName());
        List<UserRoleDO> list = this.changeList(addUserRequestDTO.getRoleList(),userDO.getId());
        if(list.size() > 0){
            roleComponentService.insertBatchUserRole(list);
        }

        return new ResponseData<>(CommonMessageCode.SUCCESS);
    }

    @Override
    public ModelAndView editUser(Long id) {
        UserDO userDO = userComponentService.get(id);
        List<RoleDO> roleList = userComponentService.findRoles(id);
        List<RoleDO> roleDOList = roleComponentService.selectAll(new RoleDO());
        for(RoleDO roleDO : roleDOList){
            if(this.contains(roleList,roleDO.getId())){
                roleDO.setHasRole(true);
            }
        }
        return new ModelAndView("admin/user/edit").addObject("user",userDO).addObject("roles",roleDOList);
    }

    @Override
    public ResponseData<Void> editUser(EditSystemUserRequest editUserRequest) {
        if(this.hasUser(editUserRequest.getUserName(),editUserRequest.getId())){
            return new ResponseData<>(1109,"用户名已经存在");
        }
        if(this.hasEmail(editUserRequest.getUserName(),editUserRequest.getEmail())){
            return new ResponseData<>(1110,"邮箱已经存在");
        }
        if(this.hasPhone(editUserRequest.getUserName(),editUserRequest.getTel())){
            return new ResponseData<>(1111,"手机号已经存在");
        }

        //1. 修改用户信息
        UserDO userDO = new UserDO();
        userDO.setId(editUserRequest.getId());
        userDO.setUserName(editUserRequest.getUserName());
        userDO.setNickName(editUserRequest.getNickName());
        userDO.setEmail(editUserRequest.getEmail());
        userDO.setTel(editUserRequest.getTel());
        userDO.setState(editUserRequest.getState());
        userDO.setModifier(CurrentContext.userName());
        userComponentService.updateSelective(userDO);

        //2、修改用户角色信息
        roleComponentService.deleteByUserId(editUserRequest.getId());
        List<UserRoleDO> list = new ArrayList<>();
        for (RoleDTO roleDTO:editUserRequest.getRoleList()) {
            UserRoleDO userRoleDO = new UserRoleDO();
            userRoleDO.setUid(editUserRequest.getId());
            userRoleDO.setRid(roleDTO.getId());
            userRoleDO.setRoleName(roleDTO.getRoleName());
            userRoleDO.setCreator(CurrentContext.userName());
            userRoleDO.setModifier(CurrentContext.userName());
            list.add(userRoleDO);
        }
        if(list.size() > 0){
            roleComponentService.insertBatchUserRole(list);
        }
        return new ResponseData<>(CommonMessageCode.SUCCESS);
    }

    @Override
    public ResponseData<Void> changeUserState(EditSystemUserRequest editUserRequest) {
        UserDO userDO = userComponentService.get(editUserRequest.getId());
        userDO.setState(editUserRequest.getState());
        userDO.setModifier(CurrentContext.userName());
        userComponentService.update(userDO);
        return new ResponseData<>(CommonMessageCode.SUCCESS);
    }

    public boolean contains (List<RoleDO> roleList, Long id) {
        for (RoleDO role : roleList){
            if(id == role.getId()){
                return true;
            }
        }
        return false;
    }

    public List<UserRoleDO> changeList(List<RoleDTO> roleList, Long id){
        List<UserRoleDO> result = new ArrayList<>();
        for (RoleDTO roleDTO:roleList) {
            UserRoleDO userRoleDO = new UserRoleDO();
            userRoleDO.setUid(id);
            userRoleDO.setRid(roleDTO.getId());
            userRoleDO.setRoleName(roleDTO.getRoleName());
            userRoleDO.setCreator(CurrentContext.userName());
            userRoleDO.setModifier(CurrentContext.userName());
            result.add(userRoleDO);
        }
        return result;
    }

    public boolean hasEmail(String username,String email) {
        UserDO userDO = new UserDO();
        userDO.setEmail(email);
        List<UserDO> userList = userComponentService.selectAll(userDO);
        if(userList.size() > 0){
            for(UserDO user : userList){
                if(!username.equals(user.getUserName())){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasPhone(String username,String tel) {
        UserDO userDO = new UserDO();
        userDO.setTel(tel);
        List<UserDO> userList = userComponentService.selectAll(userDO);
        if(userList.size() > 0){
            for(UserDO user : userList){
                if(!username.equals(user.getUserName())){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasUser(String username,Long id) {
        UserDO userDO = userComponentService.findByUsername(username);
        if(userDO != null && id != userDO.getId()){
            return true;
        }
        return false;
    }
}
