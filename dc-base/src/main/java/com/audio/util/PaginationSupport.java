package com.audio.util;

import com.audio.core.entity.BaseInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 分页类
 * @author  heyukun
 * @version  [版本号, 2011-9-27]
 */
public class PaginationSupport<T> implements Serializable
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1930241507259570331L;

    /**
     *  默认每页展示5条
     */
    public final static int DEFAUL_PAGE_SIZE = 30;
    
    /**
     * 当前页
     */
    private String curPage;
    
    /**
     *  总页数
     */
    @SuppressWarnings("unused")
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
     * 参数集合
     */
    private String params;
    
    /**
     *  存放查询出来的结果
     */
    private List<T> items = new ArrayList<T>();
    
    /**
     *  存放查询出来的结果
     */
    private List<? extends BaseInfo> itemsList;
    
    /**
     * （频道ocde）
     */
    private String actionName;
    
    /**
     * plateId
     */
    private String plateId;
    
    
    private String queryString;
    
    
    public String getPlateId()
    {
        return plateId;
    }

    public void setPlateId(String plateId)
    {
        this.plateId = plateId;
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
    	if(items.size() > 0 && items.size() > DEFAUL_PAGE_SIZE)
    	{
    		int i = items.size();
    		return i/DEFAUL_PAGE_SIZE + "";
    	}
    	else
    	{
    		return "1";
    	}
    }
    public String getTotalPages()
    {
    	if(items.size() > 0 && items.size() > 10)
    	{
    		int i = items.size();
    		return i/10 + "";
    	}
    	else
    	{
    		return "1";
    	}
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
    
    
    public String getActionName()
    {
        return actionName;
    }
    
    public void setActionName(String actionName)
    {
        this.actionName = actionName;
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

    public List<T> getItems()
    {
        return items;
    }

    public void setItems(List<T> items)
    {
        this.items = items;
    }
    
    public String getParams()
    {
        return params;
    }

    public void setParams(String params)
    {
        this.params = params;
    }

    public List<? extends BaseInfo> getItemsList()
    {
        return itemsList;
    }

    public void setItemsList(List<? extends BaseInfo> itemsList)
    {
        this.itemsList = itemsList;
    }

	public String getQueryString()
	{
		if(StringUtil.isEmpty(queryString))
		{
			return "";
		}
		return queryString;
	}

	public void setQueryString(String queryString)
	{
		this.queryString = queryString;
	}
    

}
