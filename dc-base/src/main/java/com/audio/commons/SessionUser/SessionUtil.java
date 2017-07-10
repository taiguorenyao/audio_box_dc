package com.audio.commons.SessionUser;

import com.audio.core.entity.BoxUser;
import com.audio.core.entity.SysUser;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;

/**
 * Created by Gaoxiang on 2017/3/12.
 */
public class SessionUtil
{

    public static final String PORTA_USER = "PORTAL_USER";
    public static final String SYS_USER = "SYS_USER";

    public static void setPortalUser(HttpServletRequest req, BoxUser boxUser)
    {
        req.getSession().setAttribute(PORTA_USER, boxUser);
    }

    public static BoxUser getPortalUser(HttpServletRequest req)
    {
        BoxUser us = (BoxUser) req.getSession().getAttribute(PORTA_USER);
        return us;
    }

    public static void setSysUser(HttpServletRequest req, SysUser boxUser)
    {
        req.getSession().setAttribute(SYS_USER, boxUser);
    }

    public static SysUser getSysUserUser(HttpServletRequest req)
    {
        SysUser us = (SysUser) req.getSession().getAttribute(SYS_USER);
        return us;
    }

}
