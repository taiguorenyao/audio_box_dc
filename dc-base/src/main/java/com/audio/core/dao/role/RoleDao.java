package com.audio.core.dao.role;

import com.audio.core.entity.Role;

import java.util.List;

/**
 * Created by taiguorenyao on 2017/1/5.
 * e-mail: ooxx@Live.cn
 */
public interface RoleDao
{

    /**
     * 获取所有角色
     * @return
     */
     List<Role> getAllRole();
}
