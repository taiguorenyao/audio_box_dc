package com.audio.controller.security;

import com.audio.cache.CacheConfig;
import com.audio.commons.SessionUser.SessionUtil;
import com.audio.core.entity.BoxUser;
import com.audio.core.entity.SysUser;
import com.audio.core.entity.User;
import com.audio.core.manager.Manager;
import com.audio.core.service.system.SystemService;
import com.audio.core.service.user.UserService;
import com.audio.util.AESEncrypter;
import com.audio.util.StringUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;

/**
 * Created by taiguorenyao on 2017/1/13.
 * e-mail: ooxx@Live.cn
 */
@Controller

@RequestMapping("/login")
public class LoginController
{

    public static String[] find = {"2095.vc","nb-iko-thk-sbc.com","mbwi.net","ngeh.net","ptet.net","sjil.net","udws.net"
    ,"guake-xian.com","hongsuanpan.com","taobao-tianmao.com","999shuimian.com","i-shengte.com","fxkinganimation.com","zhongxunmall.com","zhuangyangbaojianpin.com","sentri-technology.com","lz-tech.com","iyengaryogachina.com","ruiqivip.com"
    ,"lz-tech.com","iyengaryogachina.com","iyengaryogachina.com","jinshi150.com","bfjgcp.com","outlook-pro.com","lanyuanjieneng.com","ish-jc.com","changhesm.com","hzmiwei.com","home536.com","bygd888.com","zhaozhao-focus.com","hongminzx.com","mutmedi.com","bjjqqc.com","rtcf666.com"};

    public static String[] locaotion = {"http://www.hangzhou.com.cn/","http://www.takungpao.com/","http://www.singpao.com/","http://www.wenweipo.com/","http://www.wenweipo.com/","http://www.wenweipo.com/","http://www.wenweipo.com/"
            ,"http://m.takungpao.com/","http://m.takungpao.com/","http://m.takungpao.com/","http://m.takungpao.com/","http://m.takungpao.com/","http://m.takungpao.com/","http://m.takungpao.com/","http://m.takungpao.com/","http://www.zjol.com.cn/","http://www.zjol.com.cn/","http://m.takungpao.com/","http://m.takungpao.com/"
    ,"http://m.takungpao.com/","http://m.takungpao.com/","http://m.takungpao.com/","http://m.takungpao.com/","http://m.takungpao.com/","http://m.takungpao.com/","http://m.takungpao.com/","http://m.takungpao.com/","http://m.takungpao.com/","http://m.takungpao.com/","http://m.takungpao.com/","http://m.takungpao.com/","http://m.takungpao.com/","http://m.takungpao.com/","http://www.hkcna.hk/","http://www.hkcna.hk/","http://www.hkcna.hk/"};

    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request, String uri)
    {
        request.setAttribute("uri", uri);
        return "modules/login/system-login";
    }

    @RequestMapping("/toReg")
    public String toReg()
    {
        return "security/register";
    }

    @RequestMapping("/register")
    public String register(User user, HttpServletRequest request)
    {
        System.out.println("user name:" + user.getLoginName());
        System.out.println("user name:" + user.getPassword());
        PasswordEncoder passwordEncoder = (PasswordEncoder) Manager.getBean(
                "passwordEncoder",
                request.getServletContext());
        System.out.println("encode password:" +
                passwordEncoder.encode(user.getPassword()));
        return null;
    }



    @RequestMapping("/accessdenied")
    public String accessdenied()
    {
        return "security/accessdenied";
    }

    /**用户登录注册开始*/

    /**
     * @param request
     * @param uri
     * @return
     */
    @RequestMapping("/userlogin")
    public String userlogin(HttpServletRequest request, String uri)
    {

        String urls = request.getRequestURL().toString();
        int i=0;
        for(String s : find)
        {
            if (urls.indexOf(s) >= 0)
            {
               return "redirectPermanent:"+locaotion[i];
            }
            i++;
        }

        request.getSession().removeAttribute(SessionUtil.PORTA_USER);
        return "portal/login/user-login";
    }

    @RequestMapping("/logindo")
    public String logindo(HttpServletRequest req, String code, String account,
            String pwd)
    {

        String error = "redirect:/login/userlogin";

        String verifyCode = (String) req.getSession()
                .getAttribute("caonimadeyanzma");

        if (!verifyCode.equalsIgnoreCase(code))
        {
            StringUtil.setErrorCode(req, "验证码错误");
            return error;
        }

        BoxUser boxUser = userService.loadUserByAccount(account);

        if (boxUser == null)
        {
            StringUtil.setErrorCode(req, "登录账户不存在");
            return error;
        }
        if (!boxUser.getPwd().equals(AESEncrypter.getInstance().encrypt(pwd,
                AESEncrypter.VOCE)))
        {
            StringUtil.setErrorCode(req, "密码错误");
            return error;
        }

        SessionUtil.setPortalUser(req, boxUser);
        return "redirect:/index";
    }

    @RequestMapping("/userreg")
    public String userreg(HttpServletRequest request)
    {

        return "portal/login/user-reg";
    }

    @RequestMapping("/userregdo")
    public String userreg(HttpServletRequest req, BoxUser us, String code,
            String incode, String biread)
    {
        String error = "redirect:/login/userreg";
        if (!"1".equals(biread))
        {
            StringUtil.setErrorCode(req, "未阅读注册协议!");
            return error;
        }
        String configCode = CacheConfig.getConfigMap("REGISTER_CODE").getValue();

        //如果不是配置code
        if (!configCode.equals(incode))
        {

            //验证邀请码是否能找到上线用户
            BoxUser boxUser = (BoxUser)userService.loadDate(4,incode);

            //找不到就提示
            if (boxUser == null)
            {
                StringUtil.setErrorCode(req, "邀请码错误!");
                return error;
            }

            us.setUp_user(boxUser.getAccount());

        }
        else
        {
            us.setUp_user(us.getAccount());
        }

        String verifyCode = (String) req.getSession()
                .getAttribute("caonimadeyanzma");
        if (!verifyCode.equalsIgnoreCase(code))
        {
            StringUtil.setErrorCode(req, "验证码错误");
            return error;
        }
        String results = "fasle";

        if (us == null)
        {
            StringUtil.setErrorCode(req, "用户参数错误");
            return error;
        }

        if (!us.getPwd().equals(us.getPwd1()))
        {
            StringUtil.setErrorCode(req, "密码输入不一致");
            return error;
        }

        try
        {
            results = userService.saveBoxUser(us, incode);

            if ("true".equals(results))
            {
                SessionUtil.setPortalUser(req, us);
                return "redirect:/index";
            }
            else if ("ck_account".equals(results))
            {
                StringUtil.setErrorCode(req, "帐户重复!");
                return error;
            }
            else
            {
                StringUtil.setErrorCode(req, "注册失败");
                return error;
            }
        }
        catch (Exception e)
        {
            StringUtil.setErrorCode(req, "注册失败");
            return error;
        }

    }

    /*********************后太***********************/
    /**
     * @param request
     * @param uri
     * @return
     */
    @RequestMapping("/syslogin")
    public String syslogin(HttpServletRequest request, String uri)
    {
        request.getSession().removeAttribute(SessionUtil.SYS_USER);
        return "sys/login/sys-login";
    }

    @RequestMapping("/syslogindo")
    public String syslogindo(HttpServletRequest req, String code,
            String account,
            String pwd)
    {

        String error = "redirect:/login/syslogin";

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

        return "redirect:/sys/incodelist";
    }

}
