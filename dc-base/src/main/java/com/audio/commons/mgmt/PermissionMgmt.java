package com.audio.commons.mgmt;

import com.audio.commons.exception.PortalException;
import com.audio.core.cfg.Cfg;
import com.audio.core.entity.Permission;
import com.audio.core.manager.Manager;
import org.apache.commons.collections.CollectionUtils;

import javax.servlet.ServletContext;
import java.util.List;

public class PermissionMgmt extends BaseMgmt
{

    /**
     * singlton instance
     */
    private static PermissionMgmt instance = null;

    /**
     * 配置对象
     */
    private Cfg config;

    /**
     * 构造函数
     */
    public PermissionMgmt()
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
        List<Permission> permissionList = Manager.invoke().permissionManager(servletContext).getAllPermission();
        if(CollectionUtils.isNotEmpty(permissionList)){
            for(Permission p : permissionList){
                getCfg().permissionCfg.setAllPermissionCache(p);
            }
        }
    }

    public static PermissionMgmt getInstance()
    {
        if (instance == null)
        {
            new PermissionMgmt();
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
