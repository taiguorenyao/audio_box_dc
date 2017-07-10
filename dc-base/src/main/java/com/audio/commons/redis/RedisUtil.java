package com.audio.commons.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by Gaoxiang on 2017/1/16.
 */
public class RedisUtil
{

    private Jedis jedis;


    /**
     * 初始化连接
     * <br>------------------------------<br>
     */
    @Before
    public void beforeClass() {
        //47.52.31.34 117.78.31.16
        jedis = new Jedis("47.52.31.34");
        jedis.auth("gaoxiang188.");
    }

    /**
     * set 新增
     * <br>------------------------------<br>
     */
    @Test
    public void testSet() {
        jedis.set("blog","");
    }

    /**
     *  获取
     * <br>------------------------------<br>
     */
    @Test
    public void testGet() {
        System.out.println(jedis.get("blog"));
    }

    /**
     * 修改key
     * <br>------------------------------<br>
     */
    @Test
    public void testRenameKey() {
        jedis.rename("blog", "blog_new");
    }

    /**
     * 按key删除
     * <br>------------------------------<br>
     */
    @Test
    public void testDel() {
        jedis.del("blog_new");
    }

    /**
     * 获取所有的key
     * <br>------------------------------<br>
     */
    @Test
    public void testKeys() {
        System.out.println(jedis.keys("*"));
    }

    public static void main(String[] args)
    {

        RedisUtil redis = new RedisUtil();
        redis.beforeClass();
        redis.jedis.set("gaoxiang","gaoxiang1");
        System.out.println("111"+redis.jedis.get("gaoxiang"));
    }
}
