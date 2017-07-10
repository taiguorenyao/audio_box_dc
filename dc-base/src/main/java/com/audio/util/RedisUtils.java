package com.audio.util;

import com.audio.core.entity.BaseRedis;
import com.audio.core.entity.Config;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gaoxiang on 2017/3/7.
 */
public class RedisUtils
{
    public static final String AUDIO_ALL = "audio_all";

    public static final String BASE_CONFIG = "Config_";

    public static final String BASE_PERSON_AD = "Person_ad_";

    public static final String AD_VIP_ALL = "ad_vip_all";

    private static Jedis jedis;

    private static RedisUtils redisUtils;


    static
    {
        jedis = new Jedis(StringUtil.REDIS_IP);
        jedis.auth(StringUtil.REDIS_AUTH);
    }

    public static RedisUtils Inits()
    {
        if (jedis == null)
        {
            jedis = new Jedis("118.178.183.144");
            jedis.auth("gaoxiang188.");
        }
        if (redisUtils == null)
        {
            redisUtils = new RedisUtils();
        }
        return redisUtils;
    }

    public static void test1()
    {
        String name = "name";
        String value = "qq";
        jedis.set(name, value);
        System.out.println("追加前：" + jedis.get(name)); // 追加前：qq

        // 在原有值得基础上添加,如若之前没有该key，则导入该key
        jedis.append(name, "ww");
        System.out.println("追加后：" + jedis.get(name)); // 追加后：qqww

        jedis.append("id", "ee");
        System.out.println("没此key：" + jedis.get(name));
        System.out.println("get此key：" + jedis.get("id"));
    }

    public static void test3()
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "fujianchao");
        map.put("password", "123");
        map.put("age", "12");
        // 存入一个map
        jedis.hmset("user", map);

        // map key的个数
        System.out.println("map的key的个数" + jedis.hlen("user"));

        // map key
        System.out.println("map的key" + jedis.hkeys("user"));

        // map value
        System.out.println("map的value" + jedis.hvals("user"));

        // (String key, String... fields)返回值是一个list
        List<String> list = jedis.hmget("user", "age", "name");

        System.out.println("redis中key的各个 fields值："
                + jedis.hmget("user", "age", "name") + list.size());

        // 删除map中的某一个键 的值 password
        // 当然 (key, fields) 也可以是多个fields
        jedis.hdel("user", "age");

        System.out.println("删除后map的key" + jedis.hkeys("user"));

        List<String> list1 = jedis.hmget("user", "name");

        System.out.println(list1.get(0));

    }

    public static void test5()
    {

//        Config cfg = new Config();
//        cfg.setConfKey("name");
//        cfg.setConfValue("gaoxiang");
//
//        // 存入一个 user对象
//        jedis.set("user".getBytes(), SerializationUtil.serialize(cfg));
//
//        // 获取
//        byte[] bs = jedis.get("user".getBytes());
//        Config desUser = (Config) SerializationUtil.deserialize(bs);
//        System.out.println(desUser.getConfKey() + ":" + desUser.getConfValue());

    }

    public static void test6()
    {

        List<Config> cfglist = new ArrayList<Config>();
        Config cfg = new Config();
//        cfg.setConfKey("name");
//        cfg.setConfValue("gaoxiang");
//
//        Config cfg1 = new Config();
//        cfg1.setConfKey("age");
//        cfg1.setConfValue("16");
//        cfglist.add(cfg1);
//
//        cfglist.add(cfg);
//        cfglist.add(cfg1);

        // 存入一个 user对象
        jedis.set("cfglist".getBytes(), SerializationUtil.serialize(cfglist));

        // 获取
        byte[] bs = jedis.get("cfglist".getBytes());
        List<Config> list = (List<Config>) SerializationUtil.deserialize(bs);
        System.out.println(list.size());

    }

    public static void test7()
    {

        List<Config> cfglist = new ArrayList<Config>();
//        Config cfg = new Config();
//        cfg.setConfKey("name");
//        cfg.setConfValue("gaoxiang");
//        Config cfg1 = new Config();
//        cfg1.setConfKey("age");
//        cfg1.setConfValue("17");
//        cfglist.add(cfg1);
//        cfglist.add(cfg);
//        cfglist.add(cfg1);
        Map<String, List<Config>> map = new HashMap<String, List<Config>>();
        map.put("gaoxiang", cfglist);

        // 存入一个 user对象
        jedis.set("gaoxiang".getBytes(), SerializationUtil.serialize(map));

        // 获取
        byte[] bs = jedis.get("gaoxiang".getBytes());
        Map<String, List<Config>> list = (Map<String, List<Config>>) SerializationUtil
                .deserialize(bs);
//        System.out.println(list.get("gaoxiang").get(0).getConfValue());

    }

    /**
     * 存储MAP<String,String>参数
     *
     * @param redisKey
     * @param map
     */
    public static void saveRedisMapString(String redisKey,
            Map<String, String> map)
    {
        jedis.hmset(redisKey, map);
    }

    public static String getReisMapString(String redisKey, String key)
    {
        try
        {
            List<String> list = jedis.hmget(redisKey, key);
            return list.get(0);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return "";
        }
    }

    public static void saveRedisObject(String redisKey, Object obj)
    {
        jedis.set(redisKey.getBytes(), SerializationUtil.serialize(obj));
    }

    public static Object loadRedisObject(
            String redisKey)
    {
        try
        {
            return SerializationUtil.deserialize(
                    jedis.get(redisKey.getBytes()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveRedisMapList(String redisKey,
            Map<String, List<? extends BaseRedis>> map)
    {
        jedis.set(redisKey.getBytes(), SerializationUtil.serialize(map));
    }



    public static Map<String, List<? extends BaseRedis>> loadRedisMapList(
            String redisKey)
    {
        try
        {
            return (Map<String, List<? extends BaseRedis>>) SerializationUtil.deserialize(
                    jedis.get(redisKey.getBytes()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveRedisMap(String redisKey,
            Map<String, ? extends BaseRedis> map)
    {
        jedis.set(redisKey.getBytes(), SerializationUtil.serialize(map));
    }

    public static Map<String, ? extends BaseRedis> loadRedisMap(
            String redisKey)
    {
        try
        {
            return (Map<String, ? extends BaseRedis>) SerializationUtil.deserialize(
                    jedis.get(redisKey.getBytes()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args)
    {
        String redisKey = "gaoxiang";
        Map<String, String> map = new HashMap<String, String>();
        map.put("a","1");
        map.put("a1","a1");
        map.put("a2","a2");
        map.put("a3","a3");
        saveRedisMapString(redisKey,map);

         System.out.println(getReisMapString(redisKey,"a2"));

        //test7();
    }
}
