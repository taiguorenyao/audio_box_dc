package com.audio.core.entity;

import java.io.Serializable;

/**
 * Created by jl on 2017/1/9.
 * e-mail: ooxx@Live.cn
 */
public class Notice extends BaseRedis implements Serializable
{
    private String id;

    private String title;

    private String url;

    private String addtime;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
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
