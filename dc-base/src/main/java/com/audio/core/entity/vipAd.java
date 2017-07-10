package com.audio.core.entity;

import com.audio.util.StringUtil;

import java.io.Serializable;

/**
 * Created by Gaoxiang on 2017/3/19.
 */
public class vipAd extends BaseRedis implements Serializable
{

    private static final long serialVersionUID = -1530813282496676263L;

    private String id;

    private String uid;

    private String title;

    private String adtitle;

    private String adurl;

    private String fontColor;

    private String bgColor;

    private String addtime;

    private String size;

    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }

    public String getBgColor()
    {
        if (StringUtil.isEmpty(bgColor))
        {
            return "ffffff";
        }
        return bgColor;
    }

    public void setBgColor(String bgColor)
    {
        this.bgColor = bgColor;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
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

    public String getAdtitle()
    {
        return adtitle;
    }

    public void setAdtitle(String adtitle)
    {
        this.adtitle = adtitle;
    }

    public String getAdurl()
    {
        return adurl;
    }

    public void setAdurl(String adurl)
    {
        this.adurl = adurl;
    }

    public String getFontColor()
    {
        return fontColor;
    }

    public void setFontColor(String fontColor)
    {
        this.fontColor = fontColor;
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
