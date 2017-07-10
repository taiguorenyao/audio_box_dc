package com.audio.commons.tag;


import com.audio.util.PaginationSupport;
import org.apache.log4j.Level;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class BasePageTag extends TagSupport
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 9087412695683723992L;
    
    private String name;
    
    /**
     * 显示页码的个数
     */
    private static final int SHOW_PAGE_NUM = 9;
    
    /**
     * 分页对象
     */
    private String pageContent = null;
    
    /**
     * 当前页
     */
    private String curPage;
    
    /**
     *  总页数
     */
    private String totalPage;
    
    /**
     * 每页多少条
     */
    private String pageSize;
    
    /**
     * 总记录
     */
    private String totalRecord;
    
    /**
     * 分页条数
     */
    private String pageLimit;
    
    /**
     *  存放查询出来的结果
     */
    private List<Object> items;
    
    /**
     * 请求action名称
     */
    private String actionName;
    
    private String hasMemory;
    
    private String hasPageSize;
    
    private String params;
    
    private String plateId;
    

    private HttpServletRequest request = null;
    
    @Override
    public int doStartTag() throws JspException
    {
        pageContext.getServletContext().getAttribute(pageContent);
        //获取分页对象
        // 从堆栈中取出PaginationSupport对象
        @SuppressWarnings("unchecked")
        PaginationSupport<Object> paginationSupport = (PaginationSupport<Object>) pageContext.getRequest()
                .getAttribute(pageContent);
        
        if (null == paginationSupport || null == paginationSupport.getItems())
        {
            return SKIP_BODY;
        }
        
        try
        {
            //设置参数
            setParameter(paginationSupport);
            //生成页面源码
            buildPage();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
    
    private void setParameter(PaginationSupport<Object> paginationSupport)
    {
        //当前页
        curPage = paginationSupport.getCurPage();
        //  总页数
        try
        {
            totalPage = String.valueOf(getTotalPage(Integer.parseInt(paginationSupport.getTotalRecord()),
                    Integer.parseInt(paginationSupport.getPageSize())));
        }
        catch (Exception e)
        {
            totalPage = "0";
        }
        // 每页多少条
        pageSize = paginationSupport.getPageSize();
        // 总记录
        totalRecord = paginationSupport.getTotalRecord();
        //分页条数
        pageLimit = paginationSupport.getPageLimit();
        // 存放查询出来的结果
        items = paginationSupport.getItems();
        // 请求action名称
        actionName = paginationSupport.getActionName();
        //plateId
        plateId = paginationSupport.getPlateId();
        // 请求分页参数
        params = paginationSupport.getParams();
    }
    
    private void buildPage() throws IOException
    {
        int curPageNum = 0;
        int totalPageNum = 0;
        try
        {
            curPageNum = Integer.parseInt(curPage);
            totalPageNum = Integer.parseInt(totalPage);
        }
        catch (Exception e)
        {
            
        }
        if (curPageNum <= 0)
        {
            curPageNum = 1;
        }
        if (curPageNum > totalPageNum)
        {
            curPageNum = totalPageNum;
        }
        JspWriter out = pageContext.getOut();
        StringBuffer buffer = new StringBuffer();
        if ("1".equals(curPage))
        {
            //buffer.append("<a class='disabled' >首页</a>");
            //buffer.append("<a class='disabled' >上页</a>");
        }
        else
        {
            //buffer.append("<a href='/" + params
            //        + "/p1.htm' target='_self'>首页</a>");
            if (curPageNum == 2)
            {
                
                buffer.append("<a href='/" + params
                        + "/' target='_self'>上页</a>");
            }
            else
            {
                buffer.append("<a href='/" + params + "/p" + (curPageNum - 1)
                        + ".htm' target='_self'>上页</a>");
            }
            
        }
        //String prex = StringUtil.getRandomStr(3);
        // 设置起始结束数字;
        int begin = 1;
        int end = begin + SHOW_PAGE_NUM - 1;
        if (curPageNum > (SHOW_PAGE_NUM / 2))
        {
            begin = curPageNum - (SHOW_PAGE_NUM / 2);
            end = curPageNum + (SHOW_PAGE_NUM / 2);
        }
        if (curPageNum > (totalPageNum - 5))
        {
            begin = totalPageNum - (SHOW_PAGE_NUM - 1);
            end = totalPageNum;
        }
        begin = begin < 1 ? 1 : begin;
        end = end > totalPageNum ? totalPageNum : end;
        
        for (int i = begin; i <= end; i++)
        {
            if (i == curPageNum)
            {
                
                buffer.append(caseCurrPage(params,i));
            }
            else
            {
                if (i == 1)
                {
                    buffer.append("<a href='/" + params + "/' target='_self'>"
                            + i + "</a>");
                }
                else
                {
                    buffer.append("<a href='/" + params + "/p" + i
                            + ".htm' target='_self'>" + i + "</a>");
                }
            }
        }
        
        if (curPageNum == totalPageNum)
        {
            //buffer.append("<a class='disabled'>下页</a>");
            //buffer.append("<a class='disabled'>尾页</a>");
        }
        else
        {
            buffer.append("<a href='/" + params + "/p" + (curPageNum + 1)
                    + ".htm' target='_self' class='next'>下页</a>");
            
            //buffer.append("<a href='/" + params + "/p" + totalPageNum
            //        + ".htm' target='_self'>尾页</a>");
        }
        
        out.print(buffer.toString());
    }
    
    /**
     * 根据总记录数得到总页数
     * 
     * @return int 总页数
     */
    private int getTotalPage(int total, int pageSize)
    {
        int totalPage = 1;
        if (total == 0)
        {
            totalPage = 1;
        }
        else
        {
            totalPage = (total % pageSize == 0) ? (total / pageSize) : (total
                    / pageSize + 1);
        }
        return totalPage;
    }
    
    @SuppressWarnings("unused")
    private String getResouce(String key, HttpServletRequest request)
    {
        Locale locale = request.getLocale();
        
        ResourceBundle resb = ResourceBundle.getBundle("messages", locale);
        System.out.println(resb.getString(key));
        return resb.getString(key);
        
    }
    
    private static String caseCurrPage(String str, int i)
    {
        
        if ("business/news".equals(str))
        {
            return "<a href='javascript:;'  class='current'>" + i
                    + "</a>";
        }
        
        return "<b>" + i + "</b>";
    }
    
    public String getPageContent()
    {
        return pageContent;
    }
    
    public void setPageContent(String pageContent)
    {
        this.pageContent = pageContent;
    }
    
    public String getCurPage()
    {
        return curPage;
    }
    
    public void setCurPage(String curPage)
    {
        this.curPage = curPage;
    }
    
    public String getTotalPage()
    {
        return totalPage;
    }
    
    public void setTotalPage(String totalPage)
    {
        this.totalPage = totalPage;
    }
    
    public String getPageSize()
    {
        return pageSize;
    }
    
    public void setPageSize(String pageSize)
    {
        this.pageSize = pageSize;
    }
    
    public String getTotalRecord()
    {
        return totalRecord;
    }
    
    public void setTotalRecord(String totalRecord)
    {
        this.totalRecord = totalRecord;
    }
    
    public String getPageLimit()
    {
        return pageLimit;
    }
    
    public void setPageLimit(String pageLimit)
    {
        this.pageLimit = pageLimit;
    }
    
    public List<Object> getItems()
    {
        return items;
    }
    
    public void setItems(List<Object> items)
    {
        this.items = items;
    }
    
    public String getActionName()
    {
        return actionName;
    }
    
    public void setActionName(String actionName)
    {
        this.actionName = actionName;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getHasMemory()
    {
        return hasMemory;
    }
    
    public void setHasMemory(String hasMemory)
    {
        this.hasMemory = hasMemory;
    }
    
    public String getHasPageSize()
    {
        return hasPageSize;
    }
    
    public void setHasPageSize(String hasPageSize)
    {
        this.hasPageSize = hasPageSize;
    }
    
    public String getParams()
    {
        return params;
    }
    
    public void setParams(String params)
    {
        this.params = params;
    }
    
    public static void main(String[] args)
    {
//        String as = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,0,1,2,3,4,5,6,7,8,9";
//        String[] ass = as.split(",");
//        for (int i = 0; i < ass.length; i++)
//        {
//            System.out.println("<a href=\"/list/" + ass[i].toLowerCase()
//                    + "/p1.htm\">" + ass[i] + "</a> ");
//        }
       System.out.println(9/5);
    }
}
