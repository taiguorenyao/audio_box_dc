package com.audio.core.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gx on 2017/1/5.
 * e-mail: object@live.com
 */
public class Role implements Serializable
{

    /**
     * id
     */
    private String id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色类型
     */
    private String roleType;

    /**
     * 角色备注
     */
    private String remark;

    /**
     * 角色权限列表
     */
    private List<RolePermission> permissionIds;

    /**
     * 权限列表
     */
    private List<Permission> permissionList;


    public List<Permission> getPermissionList()
    {
        return permissionList;
    }

    public void setPermissionList(
            List<Permission> permissionList)
    {
        this.permissionList = permissionList;
    }

    public List<RolePermission> getPermissionIds()
    {
        return permissionIds;
    }

    public void setPermissionIds(
            List<RolePermission> permissionIds)
    {
        this.permissionIds = permissionIds;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getRoleType()
    {
        return roleType;
    }

    public void setRoleType(String roleType)
    {
        this.roleType = roleType;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }
}
