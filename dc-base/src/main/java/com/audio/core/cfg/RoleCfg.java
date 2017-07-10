package com.audio.core.cfg;

import com.audio.core.entity.Config;
import com.audio.core.entity.Role;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统配置
 *
 * @author daigai
 * @version [ 2011-12-19]
 */
public class RoleCfg
{
    /**
     * 角色缓存
     */
    private static Map<String, Role> RoleMapCache = new HashMap<String, Role>();

    /**
     * 根据KEY获取配置对象
     */
    public static Config getConfig(String key)
    {
        //return configMapCache.get(key);
        return null;
    }

    public static Collection<Role> getAllRoleCache(){
        return RoleMapCache.values();
    }

    public static Role getRoleCacheByRoleId(String roleId){
        return RoleMapCache.get(roleId);
    }

    /**
     * 设置配置缓存对象
     *
     * @param role
     */
    public static void setAllRoleCache(Role role)
    {

        RoleMapCache.put(role.getId(), role);

    }

}
