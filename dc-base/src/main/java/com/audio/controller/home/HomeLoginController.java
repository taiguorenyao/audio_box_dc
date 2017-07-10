package com.audio.controller.home;

import com.audio.core.entity.Config;
import com.audio.core.service.system.SystemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by taiguorenyao on 2017/1/6.
 * e-mail: ooxx@Live.cn
 */
@Controller
@RequestMapping("/home")
public class HomeLoginController
{

//    @Resource
//    private DataService systemService;

    @RequestMapping("/login")
    public String login()
    {
        return "modules/login/system-login";
    }

}
