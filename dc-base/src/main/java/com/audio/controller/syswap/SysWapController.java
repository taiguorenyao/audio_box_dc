package com.audio.controller.syswap;

import com.audio.cache.CacheConfig;
import com.audio.commons.SessionUser.SessionUtil;
import com.audio.controller.security.LoginController;
import com.audio.core.entity.BoxUser;
import com.audio.core.entity.SysUser;
import com.audio.core.service.user.UserService;
import com.audio.util.AESEncrypter;
import com.audio.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by  on 2017/1/6.
 * e-mail:
 */
@Controller
@RequestMapping("/syswap")
public class SysWapController
{

    @Resource
    private UserService userService;

    @RequestMapping("/syslogin")
    public String syslogin(HttpServletRequest request, String uri)
    {
        request.getSession().removeAttribute(SessionUtil.SYS_USER);
        return "syswap/sys-login";
    }

    @RequestMapping("/syslogindo")
    public String syslogindo(HttpServletRequest req, String code,
            String account,
            String pwd)
    {

        String error = "redirect:/syswap/syslogin";

        String verifyCode = (String) req.getSession()
                .getAttribute("caonimadeyanzma");


        if (!verifyCode.equalsIgnoreCase(code))
        {
            StringUtil.setErrorCode(req, "验证码错误");
            return error;
        }

        SysUser sysUser = userService.loadSysUserByAccount(account);

        if (sysUser == null)
        {
            StringUtil.setErrorCode(req, "登录账户不存在");
            return error;
        }
        if (!sysUser.getPwd().equals(AESEncrypter.getInstance().encrypt(pwd,
                AESEncrypter.VOCE)))
        {
            StringUtil.setErrorCode(req, "密码错误");
            return error;
        }

        SessionUtil.setSysUser(req, sysUser);

        return "redirect:/syswp/index";
    }



}
