package com.github.ciifm.personal.admin.provider.util;

import com.github.ciifm.personal.admin.share.dto.MenuDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/21 0021 23:05
 */
public class MenuUtils {

    /**
     * 转换成树型菜单
     * @param menuList
     * @return
     */
    public static List<MenuDTO> toTreeMenu(List<MenuDTO> menuList){
        //1. 先找到所有的一级菜单
        List<MenuDTO> rootMenu = new ArrayList<>();
        for (MenuDTO menuDO: menuList) {
            if(null == menuDO.getPid()){
                rootMenu.add(menuDO);
            }
        }
        //2. 为一级菜单设置子菜单，getChild是递归调用的
        for (MenuDTO menu : rootMenu) {
            menu.setChildrens(MenuUtils.getChild(menu.getId(), menuList));
        }
        return rootMenu;
    }

    /**
     * 递归查找子菜单
     * @param id 当前菜单id
     * @param menuList 要查找的列表
     * @return
     */
    public static List<MenuDTO> getChild(Long id, List<MenuDTO> menuList) {
        // 子菜单
        List<MenuDTO> childList = new ArrayList<>();
        for (MenuDTO menu : menuList) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (null != menu.getPid()) {
                if (menu.getPid().equals(id)) {
                    childList.add(menu);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (MenuDTO menu : childList) {// 没有url子菜单还有子菜单
            if (null != menu.getPid()) {
                menu.setChildrens(getChild(menu.getId(), menuList));
            }
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
