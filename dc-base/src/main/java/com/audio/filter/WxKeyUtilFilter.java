package com.audio.filter;

import com.audio.util.StringUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Gaoxiang on 2017/3/31.
 */
public class WxKeyUtilFilter implements Filter
{

    public static String wxflag = "nsukey=HZD3mLKAElPIg4pfScpH32u1g74p9mV7qJXtH%2F1yGgei6P%2BreBP3ez4h45N2EHc6dBv82Gc%2FcnM%2FjZT5oh2iFvE5z3eyFlwop7UTG%2FuaAngpiWwXY9mKGVfIcR9u2hEe0wRYLfT8mE3OVtTolsFJQXwftkIQxrvBVnwjA5MMkuQuN4Z8uOF1g5izNh6HEWNn";

    public static String queryFlag = "nsukey";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException
    {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rsp = (HttpServletResponse) response;
        //增加微信参数
        String uri = req.getRequestURI();
        String queryString = req.getQueryString();
        if (queryString != null && queryString.indexOf(queryFlag) >= 0)
        {
            chain.doFilter(req, rsp);
        }
        else
        {
            if (StringUtil.isEmpty(queryString))
            {
                rsp.sendRedirect(uri+"?"+wxflag);
                return;
            }
            else
            {
                rsp.sendRedirect(uri+"?"+queryString+"&"+wxflag);
                return;
            }
        }

    }

    @Override
    public void destroy()
    {

    }
}
