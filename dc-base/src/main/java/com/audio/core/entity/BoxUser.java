package com.audio.core.entity;

import java.io.Serializable;

/**
 * Created by Gaoxiang on 2017/3/5.
 */
public class BoxUser extends BaseRedis implements Serializable
{

    private  String  id;
    private  String  account;
    private  String  pwd;
    private  String  pwd1;
    private  String  t_name;
    private  String  phone;
    private  String  qq;
    private  String  addtime;
    private  String  yqcode;
    private  String  advip;
    private  String  fivela;

    private  String  userInCode;
    private  String  userInType;
    private  String  up_user;
    private  String  leval;

    private  String  underCount;

    public String getUnderCount()
    {
        return underCount;
    }

    public void setUnderCount(String underCount)
    {
        this.underCount = underCount;
    }

    public String getUserInCode()
    {
        return userInCode;
    }

    public void setUserInCode(String userInCode)
    {
        this.userInCode = userInCode;
    }

    public String getUserInType()
    {
        return userInType;
    }

    public void setUserInType(String userInType)
    {
        this.userInType = userInType;
    }

    public String getUp_user()
    {
        return up_user;
    }

    public void setUp_user(String up_user)
    {
        this.up_user = up_user;
    }

    public String getLeval()
    {
        return leval;
    }

    public void setLeval(String leval)
    {
        this.leval = leval;
    }

    public String getFivela()
    {
        return fivela;
    }

    public void setFivela(String fivela)
    {
        this.fivela = fivela;
    }

    public String getAdvip()
    {
        return advip;
    }

    public void setAdvip(String advip)
    {
        this.advip = advip;
    }

    public String getPwd1()
    {
        return pwd1;
    }

    public void setPwd1(String pwd1)
    {
        this.pwd1 = pwd1;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getPwd()
    {
        return pwd;
    }

    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }

    public String getT_name()
    {
        return t_name;
    }

    public void setT_name(String t_name)
    {
        this.t_name = t_name;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getQq()
    {
        return qq;
    }

    public void setQq(String qq)
    {
        this.qq = qq;
    }

    public String getAddtime()
    {
        return addtime;
    }

    public void setAddtime(String addtime)
    {
        this.addtime = addtime;
    }

    public String getYqcode()
    {
        return yqcode;
    }

    public void setYqcode(String yqcode)
    {
        this.yqcode = yqcode;
    }
}


