package com.audio.commons.startup;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class StartupServlet extends HttpServlet
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -7185792709131202439L;

    /**
     * 启动对象实例
     */
    private CoreStartUp startupLanche = CoreStartUp.getInstance();

    private static String rootPath = "";

    @Override
    public void init() throws ServletException
    {

        rootPath = getServletContext().getRealPath("/");
        try
        {
            startupLanche.init(getServletContext());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }

    }

    public static String getRootPath()
    {
        return rootPath;
    }

}
