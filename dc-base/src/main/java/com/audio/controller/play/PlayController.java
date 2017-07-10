package com.audio.controller.play;

import com.audio.cache.CacheConfig;
import com.audio.core.entity.*;
import com.audio.core.service.data.DataService;
import com.audio.core.service.user.UserService;
import com.audio.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Created by s on 2017/1/6.
 * e-mail: ooxx@Live.cn
 */
@Controller
@RequestMapping("/play")
public class PlayController
{

    @Resource
    private UserService userService;

    @Resource
    private DataService dataService;

    @RequestMapping("/cen/{uid}")
    public String cen(@PathVariable String uid, Model model,
            HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        //判断用户端
        String ua = req.getHeader("User-Agent");
        if (ua.indexOf("MicroMessenger") < 0)
        {
            if ("0".equals(StringUtil.TEST_MODE))
            {
                resp.sendRedirect(
                        "https://news.html5.qq.com/share/2000121624?ch=060000&tabId=0&tagId=0&docId=2000121624&url=http%3A%2F%2Fkuaibao.qq.com%2Fs%2F20170603A01QEQ00&clientWidth=360&dataSrc=&qburl=qb%3A%2F%2Fext%2Fread%3Fcid%3D0%26type%3D0%26mttsummaryid%3D2000121624%26b_f%3D060000%26bizid%3D1&sc_id=yVIbIEC");
                return null;
            }

        }

//        Config config = (Config) RedisUtils.loadRedisObject(
//                RedisUtils.BASE_CONFIG + "AVI_URL");
        Config config = CacheConfig.getConfigMap("AVI_URL");

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
            String qian = StringUtil.generateWord() + ".";
            hosts = hosts.replaceAll("http://", "http://" + qian);

            model.addAttribute("domain", hosts);
            model.addAttribute("uid", uid);
        }


            //随机获取一个播放
            List<PersonAviEvt> aviList = CacheConfig.getPerMp4Map(uid);
            PersonAviEvt playAvi = new PersonAviEvt();
            if (aviList != null && !aviList.isEmpty())
            {
                int i = getRondom(aviList.size());
                playAvi = aviList.get(i);
                model.addAttribute("pid", playAvi.getTypes());
                model.addAttribute("murl", playAvi.getUrl());
                //model.addAttribute("murl", "https://v.qq.com/iframe/player.html?vid=q0507slo6q5&tiny=0&auto=0");

            }


        return "play/cen";
    }

    @RequestMapping("/player")
    public String player(String uid, String pid, String murl, Model model,
            HttpServletRequest req, HttpServletResponse resp) throws IOException
    {

        //判断用户端
        String ua = req.getHeader("User-Agent");
        if (ua.indexOf("MicroMessenger") < 0)
        {
            if ("0".equals(StringUtil.TEST_MODE))
            {
                resp.sendRedirect(
                        "https://news.html5.qq.com/share/2000121624?ch=060000&tabId=0&tagId=0&docId=2000121624&url=http%3A%2F%2Fkuaibao.qq.com%2Fs%2F20170603A01QEQ00&clientWidth=360&dataSrc=&qburl=qb%3A%2F%2Fext%2Fread%3Fcid%3D0%26type%3D0%26mttsummaryid%3D2000121624%26b_f%3D060000%26bizid%3D1&sc_id=yVIbIEC");
                return null;
            }

        }
        //初始化头部广告
        Config config = new Config();
        getConfig("PLAY_TOP_FOOTER_IMG_S", model, config);
        getConfig("PLAY_TOP_FOOTER_IMG", model, config);
        getConfig("PLAY_TOP_FOOTER_IMG_URL", model, config);
        getConfig("PLAY_TOP_AD_IMG_S", model, config);
        getConfig("PLAY_TOP_AD_IMG", model, config);
        getConfig("PLAY_TOP_AD_IMG_URL", model, config);
        getConfig("XF_S", model, config);

        //加载圆形广告
        getConfig("PF_IMG", model, config);
        getConfig("PF_URL", model, config);
        getConfig("PF_S", model, config);

        //首先全部设置系统广告，如果符合要求下面将会覆盖
        getConfig("XF_1", model, config);
        getConfig("XF_2", model, config);
        getConfig("XF_3", model, config);
        getConfig("XF_1_URL", model, config);
        getConfig("XF_2_URL", model, config);
        getConfig("XF_3_URL", model, config);


        Config config_cirecel_uid = CacheConfig.getConfigMap("CIRCEL_UID");
        boolean cirecelFlag = false;
        if (config_cirecel_uid != null)
        {
            String val = config_cirecel_uid.getValue();
            if (val.indexOf(uid) >= 0)
            {
                cirecelFlag = true;
            }
        }
        //前台 circel_s = 1 就展示 CIRCEL_URL 的地址
        if (cirecelFlag)
        {
            model.addAttribute("circel_s","1");
        }

        getConfig("CIRCEL_URL", model, config);

        //获取用户上线用户设置广告
        BoxUser upUser1 = (BoxUser)userService.loadDate(1,uid);
        BoxUser upUser = null;
        if (upUser1 != null)
        {
             upUser = userService.loadUserByAccount(upUser1.getUp_user());
        }
        //如果有上线用户，判断用户级别，每个级别判断是否存在广告，存在就设置
        if (upUser != null)
        {
            //验证上线用户是否有资格
            if("1".equals(upUser.getUserInType()))
            {

                List<PersonAdEvt> perList = (List<PersonAdEvt>) userService.loadAllDate(
                        4,
                        upUser.getId());
                //结果不为空
                if (perList != null && !perList.isEmpty())
                {
                    PersonAdEvt luserPerAd = perList.get(0);

                    //如果上线用户级别为1则取上线用户1的广告设置下线播放页广告
                    if (Integer.parseInt(upUser.getLeval()) >= 1)
                    {
                        //不等于空且设置
                        if (!StringUtil.isEmpty(luserPerAd.getXf_img1()) &&
                                !StringUtil.isEmpty(luserPerAd.getXf_url1()))
                        {
                            model.addAttribute("XF_1",luserPerAd.getXf_img1());
                            model.addAttribute("XF_1_URL",luserPerAd.getXf_url1());
                        }
                    }
                    //如果上线用户级别为2则取上线用户2的广告设置下线播放页广告
                    if (Integer.parseInt(upUser.getLeval()) >= 2)
                    {
                        //不等于空且设置
                        if (!StringUtil.isEmpty(luserPerAd.getXf_img2()) &&
                                !StringUtil.isEmpty(luserPerAd.getXf_url2()))
                        {
                            model.addAttribute("XF_2",luserPerAd.getXf_img2());
                            model.addAttribute("XF_2_URL",luserPerAd.getXf_url2());
                        }
                    }
                    //如果上线用户级别为3则取上线用户1的广告设置下线播放页广告
                    if (Integer.parseInt(upUser.getLeval()) >= 3)
                    {
                         //不等于空且设置
                        if (!StringUtil.isEmpty(luserPerAd.getXf_img3()) &&
                                !StringUtil.isEmpty(luserPerAd.getXf_url3()))
                        {
                            model.addAttribute("XF_3",luserPerAd.getXf_img3());
                            model.addAttribute("XF_3_URL",luserPerAd.getXf_url3());
                        }
                    }

                }

            }
        }


        //初始化播放视频 cen中获取这里根据传输地址播放

        //随机获取一个播放
        List<PersonAviEvt> aviList =  CacheConfig.getPerMp4Map(uid);
        PersonAviEvt playAvi = new PersonAviEvt();
        if (aviList != null && !aviList.isEmpty())
        {
            int i = getRondom(aviList.size());
            playAvi = aviList.get(i);
            model.addAttribute("playAvi", playAvi);
        }


        //315
        Config _315_config = CacheConfig.getConfigMap("315_URL_S");
        //获取配置如果是
        if (_315_config != null && "1".equals(_315_config.getValue()))
        {
            Config _315_URL_config = CacheConfig.getConfigMap("315_URL");
            if (null != _315_URL_config)
            {
                //如果存在配置地址  则随机选择视频覆盖上述随机用户视频参数
                String[] urls = _315_URL_config.getValue().split(",");
                int i = getRondom(urls.length-1);
                PersonAviEvt _315_playAvi = new PersonAviEvt();
                _315_playAvi.setTypes("1");
                _315_playAvi.setUrl(urls[i]);
                model.addAttribute("playAvi", _315_playAvi);

            }
        }
        //315
//        PersonAviEvt playAvi = new PersonAviEvt();
//        playAvi.setTypes(pid);
//        playAvi.setUrl(murl);
//        model.addAttribute("playAvi", playAvi);

        List<vipAd> vipadList = (List<vipAd>) CacheConfig.getPlayAdMap(uid);
        model.addAttribute("list", vipadList);
        model.addAttribute("uid", uid);


        //记录访问记录
        AcessLog acessLog = new AcessLog();
        acessLog.setIp(StringUtil.getRequestIp(req));
        acessLog.setUid(uid);
        dataService.saveAccessLog(acessLog);
        acessLog = null;
        //初始化用户自定义广告
        PersonAdEvt play_person_ad =CacheConfig.getPlayPersonAd(uid);
//                (PersonAdEvt) RedisUtils.loadRedisObject(
//                RedisUtils.BASE_PERSON_A  D + uid);
        model.addAttribute("play_person_ad", play_person_ad);

        return "play/render";
    }

    public static void getConfig(String key, Model model, Config config)
    {
        config = CacheConfig.getConfigMap(key);

        if (config != null)
        {
            model.addAttribute(key, config.getValue());
        }
    }

    public static int getRondom(int var)
    {
        Random random = new java.util.Random();// 定义随机类
        int result = random.nextInt(var);
        return result;
    }
}
