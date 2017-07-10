package com.audio.commons.filter;

import java.io.IOException;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.audio.cache.CacheConfig;
import com.audio.core.entity.Config;
import com.audio.util.RedisUtils;
import com.audio.util.StringUtil;


public class MPFilter implements Filter
{

    private Config config;

    public static String DOMAIN = "";

    public void doFilter(ServletRequest arg0, ServletResponse arg1,
            FilterChain filterChain) throws ServletException, IOException
    {
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String vailCode = request.getParameter("vail");

        if ("pass".equals(vailCode))
        {
            filterChain.doFilter(request, response);
        }
        else
        {
            config =  CacheConfig.getConfigMap("DOMAIN");
            if (config == null)
            {
                filterChain.doFilter(request, response);
            }
            else
            {
                String uri = request.getRequestURI();

                if (config != null)
                {
                    String curHost = config.getValue();
                    String hosts = "";
                    if (curHost.indexOf(",") > 0)
                    {
                        String[] s = curHost.split(",");
                        int i = getRondom((s.length));
                        hosts = s[i];
                    }
                    else
                    {
                        hosts = curHost;
                    }
                    String queryString = request.getQueryString();

                    Config two_domain_config =  CacheConfig.getConfigMap("DOMAIN_S");
                    if (two_domain_config == null || "0".equals(two_domain_config.getValue()))
                    {
                        String qian = StringUtil.generateWord() + ".";
                        hosts = hosts.replaceAll("http://", "http://" + qian);
                    }

                    String urls = hosts + uri + "?" +queryString +"&vail=pass";
                    response.sendRedirect(urls);
                    return;
                }

            }

        }
    }

    public void destroy()
    {
    }

    public void init(FilterConfig arg0) throws ServletException
    {

    }

    public static int getRondom(int var)
    {

        Random random = new java.util.Random();// 定义随机类
        int result = random.nextInt(var);
        return result;
    }

}
