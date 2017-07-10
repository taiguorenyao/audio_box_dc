package com.audio.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 属性加载类
 *
 * @author gaoxiang
 */
public final class PropertiesUtil
{
    private static Map<String, List<String>> map = new HashMap<String, List<String>>();

    private static Map<String, String> propertiesMap = new HashMap<String, String>();

    public static void invoke(Properties p)
    {
        try
        {
            Set<Object> paramNames = p.keySet();
            Iterator<Object> it = paramNames.iterator();
            while (it.hasNext())
            {
                String key = (String) it.next();
                String value = p.getProperty(key);
                if (null != value && !"".equals(value))
                {
                    propertiesMap.put(key, value);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 根据KEY 获取资源文件中的值，并且把{Value}替换成value
     */
    public static List<String> getRecordedList(String key, String value)
    {
        List<String> list = map.get(key);
        List<String> newList = new ArrayList<String>();
        for (String str : list)
        {
            newList.add(str.replace("{Value}", value));
        }
        return newList;
    }

    /**
     * 根据KEY 获取资源文件中的value
     */
    public static List<String> getRecordedList(String key)
    {
        return map.get(key);
    }

    /**
     * 根据KEY 获取资源文件中的value
     */
    public static String getPropertiesMap(String key)
    {
        return propertiesMap.get(key);
    }

}
