package com.audio.core.entity;

import java.io.Serializable;

/**
 * Created by   on 2017/1/6.
 */
public class AcessStatistical extends BaseRedis implements Serializable
{
    private static final long serialVersionUID = -1530813282496676263L;

    private String id;

    private String uid;

    private String accessCount;

    private String addtime;

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

    public String getAccessCount()
    {
        return accessCount;
    }

    public void setAccessCount(String accessCount)
    {
        this.accessCount = accessCount;
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
