package com.audio.core.cfg;

import com.audio.core.entity.Config;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统配置
 *
 * @author daigai
 * @version [ 2011-12-19]
 */
public class SysCfg
{
    /**
     * 系统配置缓存
     */
    private static Map<String, Config> configMapCache = new HashMap<String, Config>();

    /**
     * 根据KEY获取配置对象
     */
    public static Config getConfig(String key)
    {
        return configMapCache.get(key);
    }



}
