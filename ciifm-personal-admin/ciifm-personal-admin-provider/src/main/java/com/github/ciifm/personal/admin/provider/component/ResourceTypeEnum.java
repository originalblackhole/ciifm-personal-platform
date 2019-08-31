package com.github.ciifm.personal.admin.provider.component;

public enum ResourceTypeEnum {

    TOP_MENU("1","根菜单"),
    SUB_MENU("2","子菜单"),
    BUTTON("3","页面按钮");

    /*ROOT_MENU("1","根菜单"),
    TOP_MENU("2","父菜单"),
    SUB_MENU("4","子菜单"),
    BUTTON("4","页面按钮");*/

    private String code;
    private String name;

    ResourceTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
