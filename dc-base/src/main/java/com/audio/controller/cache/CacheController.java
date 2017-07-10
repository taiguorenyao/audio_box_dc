package com.audio.controller.cache;

import com.audio.cache.CacheConfig;
import com.audio.cache.CacheInit;
import com.audio.commons.SessionUser.SessionUtil;
import com.audio.core.entity.*;
import com.audio.core.service.user.UserService;
import com.audio.util.PaginationSupport;
import com.audio.util.RedisUtils;
import com.audio.util.StringUtil;
import com.audio.util.URLTool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by  on 2017/1/6.
 * e-mail: ooxx@Live.cn
 */
@Controller
@RequestMapping("/cache")
public class CacheController
{

    private static boolean isIpFlag(String ip)
    {
        Config bolgcfg = CacheConfig.getConfigMap("BOLG_IPS");
        String[] blogArrs = bolgcfg.getValue().split(",");
        boolean ipFlag = false;
        for (String s : blogArrs)
        {
            if (ip.equals(s))
            {
                ipFlag = true;
            }
        }
        return ipFlag;
    }

    @Resource
    private UserService userService;

    @RequestMapping("/refreshPlayAd")
    @ResponseBody
    public Result refreshPlayAd(HttpServletRequest req, String uid,
            String blogid)
    {
        String xparam = req.getHeader("taiguo");
        Result result = new Result();
        String type = req.getMethod();
        if (!type.equals("POST") && !URLTool.AUTH.equals(xparam))
        {
            result.setCode("1");
            result.setMsg("非法请求");
            return result;
        }

        try
        {
            List<vipAd> perList = (List<vipAd>) userService.loadAllDate(
                    5,
                    uid);
            List<vipAd> tlist = new ArrayList<vipAd>();
            for (vipAd vip : perList)
            {
                if (!StringUtil.isEmpty(vip.getAdtitle()) &&
                        !StringUtil.isEmpty(vip.getAdurl()))
                {
                    tlist.add(vip);
                }
            }
            CacheConfig.putPlayAdMap(uid, tlist);
            System.out.println("用户" + uid + "缓存播放页面广告本节点刷新成功;");
            result.setCode("0");
            result.setMsg(blogid.substring(blogid.lastIndexOf(".") + 1) +
                    "node success!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result.setCode("1");
            result.setMsg("error");
        }
        return result;
    }

    @RequestMapping("/refreshAllConfig")
    @ResponseBody
    public Result refreshAllConfig(HttpServletRequest req, String blogid)
    {
        //请求鉴权
        String xparam = req.getHeader("taiguo");
        Result result = new Result();
        String type = req.getMethod();
        if (!type.equals("POST") && !URLTool.AUTH.equals(xparam))
        {
            result.setCode("1");
            result.setMsg("非法请求");
            return result;
        }

        try
        {
            //重新加载节点
            CacheInit.InitConfig(req.getServletContext());

            System.out.println("配置缓存本节点刷新成功;");
            result.setCode("0");
            result.setMsg(blogid.substring(blogid.lastIndexOf(".") + 1) +
                    "node success!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result.setCode("1");
            result.setMsg("error");
        }
        return result;
    }

    @RequestMapping("/refreshPlayMp4")
    @ResponseBody
    public Result refreshPlayMp4(HttpServletRequest req, String uid,
            String blogid)
    {
        //请求鉴权
        String xparam = req.getHeader("taiguo");
        Result result = new Result();
        String type = req.getMethod();
        if (!type.equals("POST") && !URLTool.AUTH.equals(xparam))
        {
            result.setCode("1");
            result.setMsg("非法请求");
            return result;
        }

        try
        {

            List<PersonAviEvt> perList = (List<PersonAviEvt>) userService.loadAllDate(
                    3,
                    uid);

            List<PersonAviEvt> tlist = new ArrayList<PersonAviEvt>();

            for (PersonAviEvt avi : perList)
            {
                if (!StringUtil.isEmpty(avi.getTitle()) &&
                        !StringUtil.isEmpty(avi.getUrl()))
                {
                    tlist.add(avi);
                }
            }

            CacheConfig.putPerMp4Map(uid, tlist);

            System.out.println("配置缓存本节点刷新成功;");
            result.setCode("0");
            result.setMsg(blogid.substring(blogid.lastIndexOf(".") + 1) +
                    "node success!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result.setCode("1");
            result.setMsg("error");
        }
        return result;
    }


    @RequestMapping("/refreshPlayPersonAd")
    @ResponseBody
    public Result refreshPlayPersonAd(HttpServletRequest req, String uid,
            String blogid)
    {
        //请求鉴权
        String xparam = req.getHeader("taiguo");
        Result result = new Result();
        String type = req.getMethod();
        if (!type.equals("POST") && !URLTool.AUTH.equals(xparam))
        {
            result.setCode("1");
            result.setMsg("非法请求");
            return result;
        }

        try
        {
            List<PersonAdEvt> perList = (List<PersonAdEvt>) userService.loadAllDate(
                    4,
                    uid);

            if (perList != null)
            {
                CacheConfig.septPlayPersonAd(uid, perList.get(0));
            }

            System.out.println("配置缓存本节点刷新成功;");
            result.setCode("0");
            result.setMsg(blogid.substring(blogid.lastIndexOf(".") + 1) +
                    "node success!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result.setCode("1");
            result.setMsg("error");
        }
        return result;
    }

}
