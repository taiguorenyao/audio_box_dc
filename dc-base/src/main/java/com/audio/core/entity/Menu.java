package com.audio.core.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by taiguorenyao on 2017/1/9.
 * e-mail: ooxx@Live.cn
 */
public class Menu implements Serializable {
    private String id;
    private String menuName;
    private String menuUrl;
    private String menuLogo;
    private String whichPortal;
    private String parentId;
    private List<Permission> permissionList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuLogo() {
        return menuLogo;
    }

    public void setMenuLogo(String menuLogo) {
        this.menuLogo = menuLogo;
    }

    public String getWhichPortal() {
        return whichPortal;
    }

    public void setWhichPortal(String whichPortal) {
        this.whichPortal = whichPortal;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
