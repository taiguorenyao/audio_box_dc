package com.audio.controller.wap;

import com.audio.cache.CacheConfig;
import com.audio.cache.CaheBlog;
import com.audio.commons.SessionUser.SessionUtil;
import com.audio.controller.security.LoginController;
import com.audio.core.entity.*;
import com.audio.core.service.user.UserService;
import com.audio.util.AESEncrypter;
import com.audio.util.PaginationSupport;
import com.audio.util.RedisUtils;
import com.audio.util.StringUtil;
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
@RequestMapping("/wp")
public class WpController
{

    @Resource
    private UserService userService;

    private PaginationSupport<PersonAviEvt> paginationSupport = new PaginationSupport<PersonAviEvt>();


    /**
     * @param request
     * @param uri
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request, String uri)
    {
        return "wap/home-index";
    }

    @RequestMapping("/boxindex")
    public String index(Model model,HttpServletRequest req)
    {
        BoxUser us = SessionUtil.getPortalUser(req);
        //获取用户的15条广告
        List<vipAd> vipadlist = (List<vipAd>)userService.loadAllDate(5,us.getId());

        model.addAttribute("vipadlist",vipadlist);


        return "wp/box-index";
    }


    @RequestMapping("/saveVipAd")
    @ResponseBody
    public Result saveVipAd(String t_id, String t_t, String t_url,
            String t_font,String adsize,String bgcolor)
    {
        vipAd vip = new vipAd();
        Result result = new Result();

        vip.setId(t_id);
        vip.setAdtitle(StringUtil.cleanXSS(t_t));
        vip.setAdurl(StringUtil.cleanXSS(t_url));
        vip.setFontColor(StringUtil.cleanXSS(t_font));
        vip.setSize(StringUtil.cleanXSS(adsize));
        vip.setBgColor(StringUtil.cleanXSS(bgcolor));

        boolean results = userService.updateObjectData(4, vip);
        if (results)
        {
            result.setCode("0");
            result.setMsg("成功");
        }
        else
        {
            result.setCode("1");
            result.setMsg("失败了");
        }

        return result;
    }


    @RequestMapping("/saveVipAds")
    public String saveVipAds(HttpServletRequest req)
    {
        vipAd vip = new vipAd();

        String[] adid = req.getParameterValues("adid");
        String[] adnames = req.getParameterValues("adname");
        String[] adurl = req.getParameterValues("adurl");
        String[] adcolor = req.getParameterValues("adcolor");
        String[] adsize = req.getParameterValues("adsize");
        String[] bgcolor = req.getParameterValues("bgColor");
        for (int i=0;i<adid.length;i++)
        {
            String title = StringUtil.cleanXSS(adnames[i]);
            String url = StringUtil.cleanXSS(adurl[i]);
            String color = StringUtil.cleanXSS(adcolor[i]);
            String size = StringUtil.cleanXSS(adsize[i]);
            String bgcolor_ = StringUtil.cleanXSS(bgcolor[i]);
            if (!StringUtil.isEmpty(title) ||
                    !StringUtil.isEmpty(url) ||
                    !StringUtil.isEmpty(color) ||
                    !StringUtil.isEmpty(color) ||
                    !StringUtil.isEmpty(bgcolor_))

            {
                vip.setId(adid[i]);
                vip.setAdtitle(title);
                vip.setAdurl(url);
                vip.setFontColor(color);
                vip.setSize(size);
                vip.setBgColor(bgcolor_);
                boolean results = userService.updateObjectData(4, vip);
            }

        }
        return "redirect:/wp/boxindex";
    }


    @RequestMapping("/refreshAdVip")
    public String refreshAdVip(String uid, HttpServletRequest req)
    {
        String result =  CaheBlog.refreshNode("/cache/refreshPlayAd","uid="+uid);
        StringUtil.setErrorCode(req, result);
        return "redirect:/wp/boxindex";
    }


    @RequestMapping("/sinaUrl")
    public String sinaUrl(String uid, HttpServletRequest req)
    {
        Config config = CacheConfig.getConfigMap("CEN_URL");
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<=7;i++)
        {
            //获取配置地址
            //随机获取一个配置地址
            String hosts = "";
            String curHost = config.getValue();
            if (curHost.indexOf(",") > 0)
            {
                String[] s = curHost.split(",");
                int index = getRondom((s.length));
                hosts = s[index];
            }
            else
            {
                hosts = curHost;
            }
            //增加二级随机域名，配置完成地址+参数
            hosts = hosts.replaceAll("http://", "");
            String urls = "http://"+StringUtil.generateWord()+"."+hosts;
            //缩短返回
            urls = urls + "/play/cen/"+uid;
            sb.append(StringUtil.getlimitUrl(urls)).append("\r\n");
        }

        StringUtil.setSortUrl(req, sb.toString());

        return "redirect:/wp/boxindex";
    }






    @RequestMapping("/audioList")
    public String audioList(Model model, HttpServletRequest req,
            Integer curPage,
            Integer pageSize)
    {
        pageSize = 20;

        if (null == curPage)
        {
            curPage = 1;
        }
        BoxUser boxUser = SessionUtil.getPortalUser(req);
        paginationSupport = userService.getPersonAviList(curPage,
                pageSize,
                boxUser.getId());
        model.addAttribute("paginationSupport", paginationSupport);
        model.addAttribute("curPage", curPage);
        return "wp/audio_list";
    }


    @RequestMapping("/delAudio/{id}")
    public String delAudio(@PathVariable
            String id, Model model, HttpServletRequest req)
    {
        boolean result = userService.updateData(1, id);
        if (result)
        {
            StringUtil.setErrorCode(req, "删除成功");

        }
        else
        {
            StringUtil.setErrorCode(req, "删除成功");
        }
        return "redirect:/wp/audioList";
    }


    @RequestMapping("/delallAvi")
    public String delallAvi(String id, HttpServletRequest req)
    {

        if (!StringUtil.isEmpty(id))
        {
            id = id.substring(0,(id.length()-1));
        }

        boolean result = userService.updateData(3,id);

        if (result)
        {
            StringUtil.setErrorCode(req, "删除成功!");
        }
        else
        {
            StringUtil.setErrorCode(req, "删除失败!");
        }

        return "redirect:/wp/audioList";
    }



    @RequestMapping("/refreshAudio")
    public String refreshAudio(String uid, HttpServletRequest req)
    {
        String result =  CaheBlog.refreshNode("/cache/refreshPlayMp4","uid="+uid);
        StringUtil.setErrorCode(req, result);
        //RedisUtils.saveRedisMapList(RedisUtils.AUDIO_ALL, aviEvtMap);
        return "redirect:/wp/audioList";
    }



    @RequestMapping("/addAudio")
    public String addAudio(PersonAviEvt data, HttpServletRequest req)
    {

        return "wp/audio_add";
    }


    @RequestMapping("/addAudiodo")
    public String addAudiodo(PersonAviEvt data, HttpServletRequest req)
    {

        if (data.getUrl().indexOf("@") >= 0)
        {
            String[] urls = data.getUrl().split("@");
            for (int i = 0; i <= (urls.length - 1); i++)
            {
                String u1 = urls[i].trim();
                String u = StringUtil.cleanXSS(u1);
                if (StringUtil.isEmpty(u))
                {
                    continue;
                }
                PersonAviEvt dataEvt = new PersonAviEvt();
                dataEvt.setUid(SessionUtil.getPortalUser(req).getId());
                dataEvt.setTitle(data.getTitle());
                dataEvt.setUrl(u);
                dataEvt.setTypes(data.getTypes());
                boolean result = userService.updateObjectData(1, dataEvt);
            }
        }
        else
        {
            if (!StringUtil.isEmpty(StringUtil.cleanXSS(data.getUrl())))
            {
                data.setUrl(StringUtil.cleanXSS(data.getUrl()));
                data.setUid(SessionUtil.getPortalUser(req).getId());
                boolean result = userService.updateObjectData(1, data);
            }

        }

        StringUtil.setErrorCode(req, "添加成功");

        return "redirect:/wp/addAudio";
    }


    @RequestMapping("/addpersonad")
    public String addpersonad(Model model, HttpServletRequest req)
    {
        BoxUser boxUser = SessionUtil.getPortalUser(req);
        //获取用户是否有数据
        List<PersonAdEvt> perList = (List<PersonAdEvt>) userService.loadAllDate(
                4,
                boxUser.getId());

        if (null == perList || perList.isEmpty())
        {
            model.addAttribute("nodata", "0");
        }
        else
        {
            PersonAdEvt personAdEvt = perList.get(0);
            model.addAttribute("personAdEvt", personAdEvt);
            model.addAttribute("nodata", "1");
        }
        return "/wp/playPersonAdadd";
    }

    @RequestMapping("/addpersonaddo")
    public String addpersonaddo(Model model, HttpServletRequest req,
            String nodata, PersonAdEvt personAdEvt)
    {
        BoxUser boxUser = SessionUtil.getPortalUser(req);

        if (personAdEvt != null)
        {
            personAdEvt.setTxt_title(StringUtil.cleanXSS(personAdEvt.getTxt_title()));
            personAdEvt.setTxt_url(StringUtil.cleanXSS(personAdEvt.getTxt_url()));
            personAdEvt.setImg_url(StringUtil.cleanXSS(personAdEvt.getImg_url()));
            personAdEvt.setImg_access_url(StringUtil.cleanXSS(personAdEvt.getImg_access_url()));
            personAdEvt.setFontcolor(StringUtil.cleanXSS(personAdEvt.getFontcolor()));

            personAdEvt.setXf_img1(StringUtil.cleanXSS(personAdEvt.getXf_img1()));
            personAdEvt.setXf_img2(StringUtil.cleanXSS(personAdEvt.getXf_img2()));
            personAdEvt.setXf_img3(StringUtil.cleanXSS(personAdEvt.getXf_img3()));
            personAdEvt.setXf_url1(StringUtil.cleanXSS(personAdEvt.getXf_url1()));
            personAdEvt.setXf_url2(StringUtil.cleanXSS(personAdEvt.getXf_url2()));
            personAdEvt.setXf_url3(StringUtil.cleanXSS(personAdEvt.getXf_url3()));
        }

        List<PersonAdEvt> perList = (List<PersonAdEvt>) userService.loadAllDate(
                4,
                boxUser.getId());
        if (null == perList || perList.isEmpty())
        {
            //保存
            personAdEvt.setUid(boxUser.getId());
            boolean result = userService.updateObjectData(2, personAdEvt);
            if (result)
            {
                String msg =  CaheBlog.refreshNode("/cache/refreshPlayPersonAd","uid="+boxUser.getId());
                StringUtil.setErrorCode(req, "保存成功"+msg);
//                StringUtil.setErrorCode(req, "保存成功!");
//                RedisUtils.saveRedisObject(
//                        RedisUtils.BASE_PERSON_AD + boxUser.getId(),
//                        personAdEvt);

                //PersonAdEvt obj = (PersonAdEvt)RedisUtils.loadRedisObject(RedisUtils.BASE_PERSON_AD+boxUser.getId());
                //System.out.println(obj.getTxt_title());
            }
            else
            {
                StringUtil.setErrorCode(req, "保存失败!");
            }
        }
        else
        {
            personAdEvt.setUid(boxUser.getId());
            //修改
            boolean result = userService.updateObjectData(3, personAdEvt);
            if (result)
            {
                String msg =  CaheBlog.refreshNode("/cache/refreshPlayPersonAd","uid="+boxUser.getId());
                StringUtil.setErrorCode(req, "保存成功"+msg);
//                RedisUtils.saveRedisObject(
//                        RedisUtils.BASE_PERSON_AD + boxUser.getId(),
//                        personAdEvt);

                //PersonAdEvt obj = (PersonAdEvt)RedisUtils.loadRedisObject(RedisUtils.BASE_PERSON_AD+boxUser.getId());
                // System.out.println(obj.getTxt_title());
            }
            else
            {
                StringUtil.setErrorCode(req, "保存失败!");
            }
        }

        return "redirect:/wp/addpersonad";
    }


    public static int getRondom(int var)
    {
        Random random = new java.util.Random();// 定义随机类
        int result = random.nextInt(var);
        return result;
    }


    @RequestMapping("/myMember")
    public String myMember(Model model, HttpServletRequest req)
    {
        BoxUser boxUser = SessionUtil.getPortalUser(req);

        List<BoxUser> data =  (List<BoxUser>)userService.loadAllDate(8,boxUser.getAccount());

        model.addAttribute("data",data);

        return "wp/member_list";
    }


    @RequestMapping("/delAllAdudioByUID/{uid}")
    public String delAllAdudioByUID(@PathVariable String uid, BoxUser boxUser,
            HttpServletRequest req)
    {
        boolean result = userService.updateObjectData(26, uid);

        if (result)
        {

            StringUtil.setErrorCode(req, "成功!");
        }
        else
        {
            StringUtil.setErrorCode(req, "失败!");
        }

        return "redirect:/wp/audioList";
    }

}
