package com.audio.controller.share;

import com.audio.core.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by  on 2017/1/6.
 * e-mail:
 */
@Controller
@RequestMapping("")
public class ShareController
{

    /**
     * @param request
     * @return
     */
    @RequestMapping("/share")
    public String index(Model model, HttpServletRequest request,
            String t, String imgUrl)
    {
        model.addAttribute("t", t);
        return "share/share";
    }

}
