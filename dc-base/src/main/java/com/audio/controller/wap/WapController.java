package com.audio.controller.wap;

import com.audio.cache.CacheConfig;
import com.audio.cache.CaheBlog;
import com.audio.commons.SessionUser.SessionUtil;
import com.audio.controller.security.LoginController;
import com.audio.core.entity.*;
import com.audio.core.manager.Manager;
import com.audio.core.service.user.UserService;
import com.audio.util.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;

/**
 * Created by  on 2017/1/6.
 * e-mail:
 */
@Controller
@RequestMapping("/wap")
public class WapController
{

    @Resource
    private UserService userService;

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
        for(String s : LoginController.find)
        {
            if (urls.indexOf(s) >= 0)
            {
                return "redirectPermanent:"+LoginController.locaotion[i];
            }
            i++;
        }

        request.getSession().removeAttribute(SessionUtil.PORTA_USER);
        return "wap/user-login";
    }

    @RequestMapping("/logindo")
    public String logindo(HttpServletRequest req, String code, String account,
            String pwd)
    {

        String error = "redirect:/wap/userlogin";

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
        return "redirect:/wp/index";
    }


    @RequestMapping("/userreg")
    public String userreg(HttpServletRequest request)
    {

        return "wap/user-reg";
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
                return "redirect:/wp/index";
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



}
