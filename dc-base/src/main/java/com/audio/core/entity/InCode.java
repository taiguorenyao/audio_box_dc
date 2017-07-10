package com.audio.core.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by taiguorenyao on 2017/1/9.
 * e-mail: ooxx@Live.cn
 */
public class InCode extends BaseRedis implements Serializable
{
    private String id;

    private String code;

    private String state;

    private String addtime;

    private String account;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getAddtime()
    {
        return addtime;
    }

    public void setAddtime(String addtime)
    {
        this.addtime = addtime;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }
}
