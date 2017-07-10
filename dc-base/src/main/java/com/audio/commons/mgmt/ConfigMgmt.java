package com.audio.commons.mgmt;

import com.audio.cache.CacheConfig;
import com.audio.cache.CacheInit;
import com.audio.commons.exception.PortalException;
import com.audio.core.cfg.Cfg;
import com.audio.core.cfg.SysCfg;
import com.audio.core.entity.*;
import com.audio.core.manager.Manager;
import com.audio.util.RedisUtils;
import com.audio.util.StringUtil;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigMgmt extends BaseMgmt
{

    /**
     * singlton instance
     */
    private static ConfigMgmt instance = null;

    /**
     * 配置对象
     */
    private Cfg config;

    /**
     * 构造函数
     */
    private ConfigMgmt()
    {
//        if (instance == null)
//        {
//            instance = this;
//        }
    }

    public void init(ServletContext servletContext) throws PortalException
    {
        if (config == null)
        {
            config = new Cfg();
        }

        // 加载数据
        loadConfig(servletContext);

        // 加载个人视频缓存
        //loadPersionAvi(servletContext);

        //加载个人播放页面广告
        //loadPlayAd(servletContext);

        //加载播放页面个人投放图片广告
        //loadPlayPersonAd(servletContext);
    }

    /**
     * 加载全部视频
     *
     * @throws PortalException
     */
    private void loadPersionAvi(ServletContext servletContext)
            throws PortalException
    {
        CacheInit.InitPerMp4(servletContext);
    }

    /**
     * 加载全部配置
     *
     * @throws PortalException
     */
    private void loadConfig(ServletContext servletContext)
            throws PortalException
    {
        //从REDIS获取
//        Map<String, Config> map = (Map<String, Config) RedisUtils
//                .loadRedisMap(RedisUtils.AUDIO_ALL);

        //初始化config
        CacheInit.InitConfig(servletContext);

    }

    public static ConfigMgmt getInstance()
    {
        if (instance == null)
        {
            instance = new ConfigMgmt();
        }
        return instance;
    }

    /**
     * 加载全部广告
     *
     * @throws PortalException
     */
    private void loadPlayAd(ServletContext servletContext)
            throws PortalException
    {

        CacheInit.InitPlayAd(servletContext);
    }


    /**
     * 加载全部广告
     *
     * @throws PortalException
     */
    private void loadPlayPersonAd(ServletContext servletContext)
            throws PortalException
    {

        CacheInit.InitPlayPersonAd(servletContext);

    }

    /**
     * 封装Cfg对象
     *
     * @return Cfg
     */
    public Cfg getCfg()
    {
        if (config == null)
        {
            config = new Cfg();
        }
        return config;
    }
}
