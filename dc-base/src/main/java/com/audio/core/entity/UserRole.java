package com.audio.core.entity;

import java.io.Serializable;

/**
 * Created by taiguorenyao on 2017/1/13.
 * e-mail: ooxx@Live.cn
 */
public class UserRole implements Serializable {
    private String id;
    private String userId;
    private String roleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
