package com.audio.cache;

import com.audio.core.entity.*;
import com.audio.core.manager.Manager;
import com.audio.util.RedisUtils;
import com.audio.util.StringUtil;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gaoxiang on 2017/4/12.
 */
public class CacheInit
{

    public static void InitConfig(ServletContext servletContext)
    {
        List<Config> configList = (List<Config>) Manager.invoke()
                .userManager(servletContext)
                .loadAllDate(2, "");

        //先初始化redis信息
        for (Config config : configList)
        {
            if ("REDIS_IP".equals(config.getKey()))
            {
                StringUtil.REDIS_IP = config.getValue();
            }

            if ("REDIS_AUTH".equals(config.getKey()))
            {
                StringUtil.REDIS_AUTH = config.getValue();
            }

            if ("TEST_MODE".equals(config.getKey()))
            {
                StringUtil.TEST_MODE = config.getValue();
            }

        }

        //在把信息丢到redis
        for (Config config : configList)
        {
//            RedisUtils.saveRedisObject(RedisUtils.BASE_CONFIG + config.getKey(),
//                    config);
            CacheConfig.putConfigMap(config.getKey(), config);
        }

        System.out.println("获取总配置项总数");
        System.out.println(CacheConfig.configMap.size());

    }

    public static void InitPlayAd(ServletContext servletContext)
    {
        //获取数据
        List<vipAd> aviList = (List<vipAd>) Manager.invoke()
                .userManager(servletContext)
                .loadAllDate(6, "");

        //重组数据
        for (vipAd vipad : aviList)
        {
            //查找MAP是否存在
            List<vipAd> list = CacheConfig.getPlayAdMap(vipad.getUid());
            //不存在创建list添加
            if (null == list || list.isEmpty())
            {
                list = new ArrayList<vipAd>();
                if (!StringUtil.isEmpty(vipad.getAdurl()) &&
                        !StringUtil.isEmpty(vipad.getAdtitle()))
                {
                    list.add(vipad);
                }
                //放入map
                CacheConfig.putPlayAdMap(vipad.getUid(), list);
            }
            else
            {
                if (!StringUtil.isEmpty(vipad.getAdurl()))
                {
                    list.add(vipad);
                }
                //直接增加list覆盖map原list
                CacheConfig.putPlayAdMap(vipad.getUid(), list);
            }
        }
        System.out.println("获取初始化播放页面广告总用户数");
        System.out.println(CacheConfig.playAdMap.size());

    }

    public static void InitPerMp4(ServletContext servletContext)
    {

        //获取数据
        List<PersonAviEvt> aviList = (List<PersonAviEvt>) Manager.invoke()
                .userManager(servletContext)
                .loadAllDate(1, "");

        //重组数据
        for (PersonAviEvt personAviEvt : aviList)
        {
            //查找MAP是否存在
            List<PersonAviEvt> list = CacheConfig.getPerMp4Map(
                    personAviEvt.getUid());
            //不存在创建list添加
            if (null == list || list.isEmpty())
            {
                list = new ArrayList<PersonAviEvt>();
                if (!StringUtil.isEmpty(personAviEvt.getUrl()))
                {
                    list.add(personAviEvt);
                }
                //放入map
                CacheConfig.putPerMp4Map(personAviEvt.getUid(), list);
            }
            else
            {
                if (!StringUtil.isEmpty(personAviEvt.getUrl()))
                {
                    list.add(personAviEvt);
                }
                //直接增加list覆盖map原list
                CacheConfig.putPerMp4Map(personAviEvt.getUid(), list);
            }
        }

        System.out.println("获取视频信息总数如下");
        System.out.println(CacheConfig.perMp4Map.size());
    }


    public static void InitPlayPersonAd(ServletContext servletContext)
    {
        //获取数据
        List<PersonAdEvt> perList = (List<PersonAdEvt>) Manager.invoke()
                .userManager(servletContext).loadAllDate(
                7);

        //重组数据
        for (PersonAdEvt personAdEvt : perList)
        {

            CacheConfig.septPlayPersonAd(personAdEvt.getUid(),personAdEvt);

        }
        System.out.println("获取初始化播放页面用户广告总数");
        System.out.println(CacheConfig.playPersonAdMap.size());

    }


}
