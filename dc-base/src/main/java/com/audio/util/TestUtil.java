package com.audio.util;

import com.audio.core.entity.BaseRedis;
import com.audio.core.entity.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gaoxiang on 2017/3/7.
 */
public class TestUtil
{
    public static void main(String[] args)
    {
//        List<Config> cfglist = new ArrayList<Config>();
//        Config cfg = new Config();
//        cfg.setConfKey("name");
//        cfg.setConfValue("高翔");
//        Config cfg1 = new Config();
//        cfg1.setConfKey("age");
//        cfg1.setConfValue("17");
//        cfglist.add(cfg1);
//        cfglist.add(cfg);
//        Map<String, List<? extends BaseRedis>> map = new HashMap<>();
//        map.put("1010", cfglist);
//        RedisUtils.saveRedisMapList("a", map);

        Map<String, List<? extends BaseRedis>> maps = (Map<String, List<? extends BaseRedis>>) RedisUtils
                .loadRedisMapList("a");
        List<Config> list =  (List<Config>)maps.get("1010");
        System.out.println(list.size());
    }
}
