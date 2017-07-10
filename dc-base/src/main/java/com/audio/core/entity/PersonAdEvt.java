package com.audio.core.entity;

import java.io.Serializable;

/**
 * Created by Gaoxiang on 2017/3/13.
 *
 *  用户标题图片表
 */
public class PersonAdEvt extends BaseRedis implements Serializable
{

    private static final long serialVersionUID = 1L;

    private String id;

    private String uid;

    private String txt_title;

    private String txt_url;

    private String img_url;

    private String img_access_url;

    private String fontcolor;

    private String xf_img1;
    private String xf_img2;
    private String xf_img3;
    private String xf_url1;
    private String xf_url2;
    private String xf_url3;

    public String getXf_img1()
    {
        return xf_img1;
    }

    public void setXf_img1(String xf_img1)
    {
        this.xf_img1 = xf_img1;
    }

    public String getXf_img2()
    {
        return xf_img2;
    }

    public void setXf_img2(String xf_img2)
    {
        this.xf_img2 = xf_img2;
    }

    public String getXf_img3()
    {
        return xf_img3;
    }

    public void setXf_img3(String xf_img3)
    {
        this.xf_img3 = xf_img3;
    }

    public String getXf_url1()
    {
        return xf_url1;
    }

    public void setXf_url1(String xf_url1)
    {
        this.xf_url1 = xf_url1;
    }

    public String getXf_url2()
    {
        return xf_url2;
    }

    public void setXf_url2(String xf_url2)
    {
        this.xf_url2 = xf_url2;
    }

    public String getXf_url3()
    {
        return xf_url3;
    }

    public void setXf_url3(String xf_url3)
    {
        this.xf_url3 = xf_url3;
    }

    public String getFontcolor()
    {
        return fontcolor;
    }

    public void setFontcolor(String fontcolor)
    {
        this.fontcolor = fontcolor;
    }

    public static long getSerialVersionUID()
    {
        return serialVersionUID;
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

    public String getTxt_title()
    {
        return txt_title;
    }

    public void setTxt_title(String txt_title)
    {
        this.txt_title = txt_title;
    }

    public String getTxt_url()
    {
        return txt_url;
    }

    public void setTxt_url(String txt_url)
    {
        this.txt_url = txt_url;
    }

    public String getImg_url()
    {
        return img_url;
    }

    public void setImg_url(String img_url)
    {
        this.img_url = img_url;
    }

    public String getImg_access_url()
    {
        return img_access_url;
    }

    public void setImg_access_url(String img_access_url)
    {
        this.img_access_url = img_access_url;
    }
}
