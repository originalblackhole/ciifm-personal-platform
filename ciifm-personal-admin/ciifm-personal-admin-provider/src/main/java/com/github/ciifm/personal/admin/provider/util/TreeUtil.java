package com.github.ciifm.personal.admin.provider.util;

import com.github.ciifm.personal.admin.dao.dataobject.PermissionDO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/27 0027 13:56
 */
public class TreeUtil {

    private static List<Long> returnList = new ArrayList<>();

    /**
     * 转换成树型菜单
     * @param permissionList
     * @return
     */
    public static List<PermissionDO> toTreePermission(List<PermissionDO> permissionList){
        //1. 先找到所有的一级菜单
        List<PermissionDO> rootMenu = new ArrayList<>();
        for (PermissionDO permission: permissionList) {
            if(null == permission.getParentId()){
                rootMenu.add(permission);
            }
        }
        //2. 为一级菜单设置子菜单，getChild是递归调用的
        for (PermissionDO permission : rootMenu) {
            permission.setChildren(TreeUtil.getChild(permission.getId(), permissionList));
        }
        return rootMenu;
    }

    /**
     * 递归查找子菜单
     * @param id 当前菜单id
     * @param permissionList 要查找的列表
     * @return
     */
    private static List<PermissionDO> getChild(Long id, List<PermissionDO> permissionList) {
        // 子菜单
        List<PermissionDO> childList = new ArrayList<>();
        for (PermissionDO permission : permissionList) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (null != permission.getParentId()) {
                if (permission.getParentId().equals(id)) {
                    childList.add(permission);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (PermissionDO permission : childList) {// 没有url子菜单还有子菜单
            if (null != permission.getParentId()) {
                permission.setChildren(getChild(permission.getId(), permissionList));
            }
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

    /**
     * 根据父节点的ID获取所有子节点
     * @param list 分类表
     * @param typeId 传入的父节点ID
     * @return String
     */
    public static List<Long> getChildNodes(List<PermissionDO> list, Long typeId) {
        List<Long> returnList = new ArrayList<>();
        if(list == null && typeId == null) return new ArrayList<>();
        Iterator<PermissionDO> iterator = list.iterator();
        while (iterator.hasNext()) {
            PermissionDO node = iterator.next();
            // 、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getParentId() == null && typeId == node.getId()) {
                returnList = TreeUtil.recursionFn(list, node);
            }
        }
        return returnList;
    }

    private static List<Long> recursionFn(List<PermissionDO> list, PermissionDO node) {
        //List<Long> returnList = new ArrayList<>();
        List<PermissionDO> childList = getChildList(list, node);// 得到子节点列表
        if (hasChild(list, node)) {// 判断是否有子节点
            returnList.add(node.getId());
            Iterator<PermissionDO> it = childList.iterator();
            while (it.hasNext()) {
                PermissionDO n = it.next();
                recursionFn(list, n);
            }
        } else {
            returnList.add(node.getId());
        }
        return returnList;
    }

    // 得到子节点列表
    private static List<PermissionDO> getChildList(List<PermissionDO> list, PermissionDO node) {
        List<PermissionDO> nodeList = new ArrayList<>();
        Iterator<PermissionDO> it = list.iterator();
        while (it.hasNext()) {
            PermissionDO n =  it.next();
            if (n.getParentId() == node.getId()) {
                nodeList.add(n);
            }
        }
        return nodeList;
    }

    // 判断是否有子节点
    private static boolean hasChild(List<PermissionDO> list, PermissionDO node) {
        return getChildList(list, node).size() > 0 ? true : false;
    }


    // 本地模拟数据测试
    /*public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<PermissionDO> nodeList = new ArrayList<>();
        PermissionDO node1 = new PermissionDO();
        node1.setId(1L);
        node1.setParentId(null);
        node1.setName("根菜单一");
        PermissionDO node2 = new PermissionDO();
        node2.setId(2L);
        node2.setParentId(1L);
        node2.setName("子菜单1");
        PermissionDO node3 = new PermissionDO();
        node3.setId(3L);
        node3.setParentId(1L);
        node3.setName("子菜单2");
        PermissionDO node4 = new PermissionDO();
        node4.setId(4L);
        node4.setParentId(2L);
        node4.setName("孙菜单1");
        PermissionDO node5 = new PermissionDO();
        node5.setId(5L);
        node5.setParentId(2L);
        node5.setName("孙菜单2");
        PermissionDO node6 = new PermissionDO();
        node6.setId(6L);
        node6.setParentId(null);
        node6.setName("根菜单二");
        PermissionDO node7 = new PermissionDO();
        node7.setId(7L);
        node7.setParentId(6L);
        node7.setName("子菜单1");
        PermissionDO node8 = new PermissionDO();
        node8.setId(8L);
        node8.setParentId(6L);
        node8.setName("子菜单1");
        PermissionDO node9 = new PermissionDO();
        node9.setId(9L);
        node9.setParentId(8L);
        node9.setName("孙菜单1");
        PermissionDO node10 = new PermissionDO();
        node10.setId(10L);
        node10.setParentId(9L);
        node10.setName("孙孙菜单1");
        PermissionDO node11 = new PermissionDO();
        node11.setId(11L);
        node11.setParentId(10L);
        node11.setName("孙孙孙菜单1");

        nodeList.add(node1);
        nodeList.add(node2);
        nodeList.add(node3);
        nodeList.add(node4);
        nodeList.add(node5);
        nodeList.add(node6);
        nodeList.add(node7);
        nodeList.add(node8);
        nodeList.add(node9);
        nodeList.add(node10);
        nodeList.add(node11);

        TreeUtil mt = new TreeUtil();
        System.out.println(JsonUtil.toJson(TreeUtil.toTreePermission(nodeList)));
        System.out.println(mt.getChildNodes(nodeList, 1L));
        long end = System.currentTimeMillis();
        System.out.println("用时:" + (end - start) + "ms");
    }*/
}
