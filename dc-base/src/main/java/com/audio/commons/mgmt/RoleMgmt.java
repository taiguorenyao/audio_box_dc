package com.audio.commons.mgmt;

import com.audio.commons.exception.PortalException;
import com.audio.core.cfg.Cfg;
import com.audio.core.entity.Permission;
import com.audio.core.entity.Role;
import com.audio.core.entity.RolePermission;
import com.audio.core.manager.Manager;
import com.audio.util.ConfigUtil;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

public class RoleMgmt extends BaseMgmt
{

    /**
     * singlton instance
     */
    private static RoleMgmt instance = null;

    /**
     * 配置对象
     */
    private Cfg config;

    /**
     * 构造函数
     */
    public RoleMgmt()
    {
        if (instance == null)
        {
            instance = this;
        }
    }

    public void init(ServletContext servletContext) throws PortalException
    {
        if (config == null)
        {
            config = new Cfg();
        }

        // 加载数据
        loadConfig(servletContext);
    }

    /**
     * 加载全部配置
     *
     * @throws PortalException
     */
    private void loadConfig(ServletContext servletContext)
            throws PortalException
    {
        List<Role> roleList = Manager.invoke()
                .roleManager(servletContext)
                .getAllRole();

        //初始化角色缓存
        for (Role role : roleList)
        {
            List<RolePermission> rolePermissionsList = role.getPermissionIds();

            List<Permission> permissionList = new ArrayList<Permission>();

            for (RolePermission rolepermission : rolePermissionsList)
            {
                Permission permission = ConfigUtil.getConfig().permissionCfg.getPermission(
                        rolepermission.getPermissionId());
                if (permission != null)
                {
                    permissionList.add(permission);
                }
            }

            role.setPermissionList(permissionList);

            ConfigUtil.getConfig().roleCfg.setAllRoleCache(role);
        }

    }

    public static RoleMgmt getInstance()
    {
        if (instance == null)
        {
            new RoleMgmt();
        }
        return instance;
    }

    /**
     * 封装Cfg对象
     *
     * @return Cfg
     */
    public Cfg getCfg()
    {
        return config;
    }
}
