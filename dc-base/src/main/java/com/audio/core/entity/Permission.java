package com.audio.core.entity;

import java.io.Serializable;

/**
 * Created by taiguorenyao on 2017/1/9.
 * e-mail: ooxx@Live.cn
 */
public class Permission implements Serializable {
    private String id;
    private String permissionName;
    private String menuId;
    private String permissionUrl;
    private String permissionStatus;
    private String permissionType;
    private String remark;

    public String getPermissionType()
    {
        return permissionType;
    }

    public void setPermissionType(String permissionType)
    {
        this.permissionType = permissionType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public String getPermissionStatus() {
        return permissionStatus;
    }

    public void setPermissionStatus(String permissionStatus) {
        this.permissionStatus = permissionStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
