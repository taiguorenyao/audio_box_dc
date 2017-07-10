package com.audio.controller.syswap;

import com.audio.commons.SessionUser.SessionUtil;
import com.audio.controller.my.MyController;
import com.audio.core.entity.Config;
import com.audio.core.entity.SysUser;
import com.audio.core.service.user.UserService;
import com.audio.util.AESEncrypter;
import com.audio.util.PaginationSupport;
import com.audio.util.StringUtil;
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
@RequestMapping("/syswp")
public class SysWpController
{

    private PaginationSupport<Config> paginationSupport_Config = new PaginationSupport<Config>();

    @Resource
    private UserService userService;

    /**
     * @param request
     * @param uri
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request, String uri)
    {
        return "syswap/home-index";
    }


    @RequestMapping("/configlist")
    public String configlist(Model model, HttpServletRequest req,
            Integer curPage,
            Integer pageSize)
    {
        pageSize = MyController.VAL_10;

        if (null == curPage)
        {
            curPage = 1;
        }

        paginationSupport_Config = userService.getConfigList(curPage,
                pageSize);

        model.addAttribute("paginationSupport", paginationSupport_Config);
        model.addAttribute("curPage", curPage);
        return "syswap/config/list";
    }


}
