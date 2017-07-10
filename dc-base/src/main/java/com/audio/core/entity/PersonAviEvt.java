package com.audio.core.entity;

import java.io.Serializable;

/**
 * Created by Gaoxiang on 2017/3/13.
 * 没人15条广告
 *
 */
public class PersonAviEvt extends BaseRedis implements Serializable
{

    private static final long serialVersionUID = 1L;

    private String id;

    private String uid;

    private String title;

    private String url;

    private String addtime;

    private String types;

    public String getTypes()
    {
        return types;
    }

    public void setTypes(String types)
    {
        this.types = types;
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

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
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
