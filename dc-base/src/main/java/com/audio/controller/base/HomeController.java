package com.audio.controller.base;

import com.audio.commons.SessionUser.SessionUtil;
import com.audio.core.entity.BoxUser;
import com.audio.core.entity.vipAd;
import com.audio.core.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by gaoxiang on 2017/1/15.
 * e-mail: ooxx@Live.cn
 */
@Controller
public class HomeController {

    @Resource
    private UserService userService;

    @RequestMapping("/")
    public String home(){
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(Model model,HttpServletRequest req)
    {
        BoxUser us = SessionUtil.getPortalUser(req);
        //获取用户的15条广告
        List<vipAd> vipadlist = (List<vipAd>)userService.loadAllDate(5,us.getId());

        model.addAttribute("vipadlist",vipadlist);


        return "modules/index/home-index";
    }


}
