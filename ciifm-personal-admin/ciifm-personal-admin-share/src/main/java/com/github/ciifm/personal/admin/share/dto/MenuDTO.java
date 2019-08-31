package com.github.ciifm.personal.admin.share.dto;

import lombok.Data;

import java.util.List;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/21 0021 17:39
 */
@Data
public class MenuDTO {
    private Long id;
    private Long pid;
    private String title;
    private String icon;
    private String href;
    private Boolean spread = false;
    private List<MenuDTO> childrens;
}
