package com.audio.commons.tag;

import com.audio.cache.CacheConfig;
import com.audio.core.entity.Config;
import com.audio.core.entity.Notice;
import com.audio.core.manager.Manager;
import com.audio.util.PaginationSupport;
import com.audio.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class InitNoticeTag extends TagSupport
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 9087412695683723992L;
    
    private HttpServletRequest request = null;

    // 0 pc  1 wap
    private String types;
    
    @Override
    public int doStartTag() throws JspException
    {
        try
        {
            if ("0".equals(types))
            {
                //生成页面源码
                buildPage();
            }
            else
            {

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
    

    
    private void buildPage() throws IOException
    {
        Config topCount = CacheConfig.getConfigMap("TOP_COUNT");
        String count = "";
        if (null != topCount)
        {
            count = topCount.getValue();
        }
        StringBuffer buffer = new StringBuffer();
        List<Notice> list =  (List<Notice> )Manager.invoke().userManager(pageContext.getRequest().getServletContext()).
                loadAllDate(9,count);
        String color  = "red";
        String fontsize  = "18px";
        try
        {
            if (null == list || list.isEmpty())
            {
                buffer.append("<li><a style='color:"+color+";font-size:"+fontsize+"' href='javascript:;'>暂无公告</a></li>");
            }
            else
            {
                for (Notice notice : list)
                {
                    if (StringUtil.isEmpty(notice.getUrl()))
                    {
                        buffer.append("<li><a style='color:"+color+";font-size:"+fontsize+"' href='javascript:;'>"+notice.getTitle()+"</a></li>");
                    }
                    else
                    {
                        buffer.append("<li><a style='color:"+color+";font-size:"+fontsize+"' target='_blank' href='"+notice.getUrl()+"'>"+notice.getTitle()+"</a></li>");
                    }
                }
            }

        }
        catch (Exception e)
        {

        }

        JspWriter out = pageContext.getOut();
        out.print(buffer.toString());
    }

    public String getTypes()
    {
        return types;
    }

    public void setTypes(String types)
    {
        this.types = types;
    }
}
