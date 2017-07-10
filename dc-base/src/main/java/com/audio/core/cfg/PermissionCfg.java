package com.audio.core.cfg;

import com.audio.core.entity.Permission;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 权限配置
 *
 * @author wangjing
 * @version [ 2011-12-19]
 */
public class PermissionCfg
{
    /**
     *  权限缓存
     */
    private static Map<String, Permission> permissionMapCache = new HashMap<String, Permission>();

    /**
     * 根据KEY获取权限对象
     */
    public static Permission getPermission(String key)
    {
        return permissionMapCache.get(key);
    }

    public static Collection<Permission> getAllPermission(){
        return permissionMapCache.values();
    }

    /**
     * 设置权限缓存对象
     * @param permission
     */
    public static void setAllPermissionCache(Permission permission)
    {
        permissionMapCache.put(permission.getId(),permission);
    }

}
