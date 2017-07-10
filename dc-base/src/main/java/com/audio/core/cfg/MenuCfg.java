package com.audio.core.cfg;

import com.audio.core.entity.Menu;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * 菜单配置
 *
 * @author wangjing
 * @version [ 2011-12-19]
 */
public class MenuCfg
{
    /**
     *  菜单缓存
     */
    private static Map<String, Menu> menuMapCache = new HashMap<String, Menu>();

    /**
     * 根据KEY获取菜单对象
     */
    public static Menu getMenu(String key)
    {
        return menuMapCache.get(key);
    }

    public static Collection<Menu> getAllMenu(){
        return menuMapCache.values();
    }

    /**
     * 根据 菜单id 获得子菜单对象
     * @param menuId 菜单id
     * @return List<Menu>
     */
    public static List<Menu> getMenuByParentId(String menuId){
        List<Menu> subMenuList = new ArrayList<Menu>();
        Collection<Menu> allMenu = getAllMenu();
        if(CollectionUtils.isNotEmpty(allMenu)){
            for(Menu menu : allMenu){
                if(menu.getId().equals(menuId)){
                    subMenuList.add(menu);
                }
            }
        }

        return subMenuList;
    }

    /**
     * 设置菜单缓存对象
     * @param menu
     */
    public static void setAllMenuCache(Menu menu)
    {
        menuMapCache.put(menu.getId(),menu);
    }

}
