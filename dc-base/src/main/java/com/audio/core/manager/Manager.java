package com.audio.core.manager;

import com.audio.commons.config.ContextServiceConfig;
import com.audio.core.service.data.DataService;
import com.audio.core.service.menu.MenuService;
import com.audio.core.service.permission.PermissionService;
import com.audio.core.service.role.RoleService;
import com.audio.core.service.system.SystemService;
import com.audio.core.service.user.UserService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

/**
 * Manager整体对象
 *
 * @author heyukun
 * @version [版本号, 2013-8-15]
 */
public class Manager
{

    private static Manager manager;

    /**
     * Manager实例化
     *
     * @return Manager
     */
    public static Manager invoke()
    {
        if (null == manager)
        {
            manager = new Manager();
        }
        return manager;
    }

    /***
     * system manager
     * @param context
     * @return DataService
     */
    public SystemService systemManager(ServletContext context)
    {
        return (SystemService) getBean(ContextServiceConfig.SYSTEM_SERVICE,
                context);
    }

    /***
     * system manager
     * @param context
     * @return DataService
     */
    public RoleService roleManager(ServletContext context)
    {
        return (RoleService) getBean(ContextServiceConfig.ROLE_SERVICE,
                context);
    }

    public PermissionService permissionManager(ServletContext context)
    {
        return (PermissionService) getBean(ContextServiceConfig.PERMISSION_SERVICE,
                context);
    }

    public MenuService menuManager(ServletContext context) {
        return (MenuService) getBean(ContextServiceConfig.MENU_SERVICE,
                context);
    }

    public UserService userManager(ServletContext context) {
        return (UserService) getBean(ContextServiceConfig.USER_SERVICE,
                context);
    }

    public DataService dataManager(ServletContext context) {
        return (DataService) getBean(ContextServiceConfig.DATA_SERVICE,
                context);
    }



    /**
     * get bean
     *
     * @param beanName service values
     * @param context  ServletContext
     * @return Object
     */
    public static Object getBean(String beanName, ServletContext context)
    {

        try
        {
            WebApplicationContext webCtx = WebApplicationContextUtils.getWebApplicationContext(
                    context);
            return webCtx.getBean(beanName);
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
