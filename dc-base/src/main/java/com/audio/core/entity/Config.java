package com.audio.core.entity;

import com.audio.util.StringUtil;

import java.io.Serializable;

/**
 * Created by gaoxiang on 2017/1/6.
 */
public class Config extends BaseRedis implements Serializable
{
    private static final long serialVersionUID = -1530813282496676263L;

    private String id;

    private String key;

    private String value;

    private String valueEx;

    private String description;

    private String version;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getValue()
    {
        return value;
    }

    public String getValueEx()
    {
        return StringUtil.getCupValue(value);
    }

    public void setValueEx(String valueEx)
    {
        this.valueEx = valueEx;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }
}
