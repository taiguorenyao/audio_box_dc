package com.audio.commons.startup;

import com.audio.commons.exception.PortalException;
import com.audio.commons.mgmt.BaseMgmt;

import javax.servlet.ServletContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class CoreStartUp implements Serializable
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 7835313583381788881L;

    /**
     * 单实例对象
     */
    private static CoreStartUp instance = new CoreStartUp();

    /**
     * 附件的几个管理类，此处使用spring的配置进行加载
     */
    private static List mgmtList = new ArrayList();

    /**
     * 系统正在启动
     */
    private boolean starting = true;

    /**
     * 构造方法
     */
    private CoreStartUp()
    {

    }

    /**
     * 得到单实例对象
     *
     * @return CoreStartUp
     */
    public static CoreStartUp getInstance()
    {
        return instance;
    }

    /**
     * 判断系统是否正在启动
     *
     * @return boolean
     */
    public boolean isStarting()
    {
        return starting;
    }

    /**
     * 设置扩展启动对象方法，用于spring配置
     *
     * @param list ArrayList
     */
    public void setMgmtList(List list)
    {
        if (list != null)
        {
            mgmtList.clear();
            mgmtList.addAll(list);
        }
    }

    /**
     * 启动整个系统内核，初始化每一个管组件
     *
     * @throws PortalException
     */
    public void init(ServletContext servletContext) throws PortalException
    {
        // 启动扩展管理对象
        for (Iterator allMgmt = mgmtList.iterator(); allMgmt.hasNext(); )
        {
            BaseMgmt mgmt = (BaseMgmt) allMgmt.next();
            mgmt.init(servletContext);
        }

    }

}
