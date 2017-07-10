package com.audio.core.entity;

import com.audio.util.StringUtil;

import java.io.Serializable;

/**
 * Created by   on 2017/1/6.
 */
public class AcessLog extends BaseRedis implements Serializable
{
    private static final long serialVersionUID = -1530813282496676263L;

    private String id;

    private String uid;

    private String ip;

    private String addtime;

    private int count;

    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUid()
    {
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getAddtime()
    {
        return addtime;
    }

    public void setAddtime(String addtime)
    {
        this.addtime = addtime;
    }







}
