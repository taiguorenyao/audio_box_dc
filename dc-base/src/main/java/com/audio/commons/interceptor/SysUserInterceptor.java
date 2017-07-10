package com.audio.commons.interceptor;

import com.audio.commons.SessionUser.SessionUtil;
import com.audio.core.entity.BoxUser;
import com.audio.core.entity.SysUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Gaoxiang on 2017/3/12.
 */
public class SysUserInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, Object o) throws Exception
    {
        SysUser us = SessionUtil.getSysUserUser(httpServletRequest);
        if (us == null)
        {
            httpServletResponse.sendRedirect("/login/syslogin");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, Object o,
            ModelAndView modelAndView) throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, Object o, Exception e)
            throws Exception
    {

    }
}
