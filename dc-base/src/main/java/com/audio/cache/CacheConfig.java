package com.audio.cache;

import com.audio.core.entity.Config;
import com.audio.core.entity.PersonAdEvt;
import com.audio.core.entity.PersonAviEvt;
import com.audio.core.entity.vipAd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gaoxiang on 2017/4/12.
 */
public class CacheConfig
{

    //Config 内存缓存 根据KEY获取COnfig 对象
    public static Map<String, Config> configMap = new HashMap<String, Config>();

    //获取缓存KEY
    public static Config getConfigMap(String key)
    {
        return configMap.get(key);
    }

    /**
     * 根据key 放入key
     *
     * @param key
     * @param config
     */
    public static void putConfigMap(String key, Config config)
    {
        configMap.put(key, config);
    }

    //VIP广告缓存 根据用户ID获取List<vipAd> 对象
    public static Map<String, List<vipAd>> playAdMap = new HashMap<String, List<vipAd>>();

    public static List<vipAd> getPlayAdMap(String uid)
    {
        return playAdMap.get(uid);
    }

    public static void putPlayAdMap(String uid, List<vipAd> list)
    {
        playAdMap.put(uid, list);
    }

    //VIP广告缓存 根据用户ID获取List<vipAd> 对象
    public static Map<String, List<PersonAviEvt>> perMp4Map = new HashMap<String, List<PersonAviEvt>>();

    public static List<PersonAviEvt> getPerMp4Map(String uid)
    {
        return perMp4Map.get(uid);
    }

    public static void putPerMp4Map(String uid, List<PersonAviEvt> list)
    {
        perMp4Map.put(uid, list);
    }

    /**
     * 播放页面用户自定义广告
     */
    public static Map<String, PersonAdEvt> playPersonAdMap = new HashMap<String, PersonAdEvt>();

    /**
     * 播放页面用户自定义广告
     */
    public static PersonAdEvt getPlayPersonAd(String key)
    {
        return playPersonAdMap.get(key);
    }

    /**
     * 播放页面用户自定义广告
     */
    public static void septPlayPersonAd(String key, PersonAdEvt personAdEvt)
    {
        playPersonAdMap.put(key, personAdEvt);
    }

}
