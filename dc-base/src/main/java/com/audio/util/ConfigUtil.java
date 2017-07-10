package com.audio.util;

import com.audio.commons.mgmt.ConfigMgmt;
import com.audio.core.cfg.Cfg;

public final class ConfigUtil
{
    /**
     * singlton instance
     */
    private static ConfigMgmt instance = ConfigMgmt.getInstance();

    /**
     * 构造器
     */
    private ConfigUtil()
    {
    }

    /**
     * 得到配置对象
     *
     * @return Cfg
     */
    public static Cfg getConfig()
    {
        return instance.getCfg();
    }

}
