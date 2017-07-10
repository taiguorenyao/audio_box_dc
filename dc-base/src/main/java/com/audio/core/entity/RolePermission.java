package com.audio.core.entity;

import java.io.Serializable;

/**
 * Created by gx on 2017/1/9.
 * e-mail: ooxx@Live.cn
 */
public class RolePermission implements Serializable {

    private String id;
    private String roleId;
    private String permissionId;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getRoleId()
    {
        return roleId;
    }

    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }

    public String getPermissionId()
    {
        return permissionId;
    }

    public void setPermissionId(String permissionId)
    {
        this.permissionId = permissionId;
    }
}
