package com.audio.controller.sys;

import com.audio.cache.CacheConfig;
import com.audio.commons.SessionUser.SessionUtil;
import com.audio.core.entity.*;
import com.audio.cache.CaheBlog;
import com.audio.controller.my.MyController;
import com.audio.core.manager.Manager;
import com.audio.core.service.user.UserService;
import com.audio.util.DateTimeUtil;
import com.audio.util.PaginationSupport;
import com.audio.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by  on 2017/1/6.
 * e-mail: ooxx@Live.cn
 */
@Controller
@RequestMapping("/sys")
public class SysControllers
{

    private PaginationSupport<InCode> paginationSupport = new PaginationSupport<InCode>();

    private PaginationSupport<BoxUser> paginationSupport_user = new PaginationSupport<BoxUser>();

    private PaginationSupport<PersonAviEvt> paginationSupport_personAviEvt = new PaginationSupport<PersonAviEvt>();

    private PaginationSupport<Config> paginationSupport_Config = new PaginationSupport<Config>();

    private PaginationSupport<Domain> paginationSupport_Domain = new PaginationSupport<Domain>();

    private PaginationSupport<Notice> paginationSupport_Notice = new PaginationSupport<Notice>();

    @Resource
    private UserService userService;


    @RequestMapping("/noticelist")
    public String noticelist(Model model, HttpServletRequest req,
            Integer curPage,
            Integer pageSize)
    {
        pageSize = 20;

        if (null == curPage)
        {
            curPage = 1;
        }
        paginationSupport_Notice = userService.getNoticeList(curPage,
                pageSize, "");
        model.addAttribute("paginationSupport", paginationSupport_Notice);
        model.addAttribute("curPage", curPage);
        return "sys/notice/list";
    }

    @RequestMapping("/incodelist")
    public String incodeList(Model model, HttpServletRequest req,
            Integer curPage,
            Integer pageSize)
    {
        pageSize = 66;

        if (null == curPage)
        {
            curPage = 1;
        }
//        paginationSupport = userService.getInCodeList(curPage,
//                pageSize);
        paginationSupport_user = userService.getUserList(curPage,
                pageSize, "");
        model.addAttribute("paginationSupport", paginationSupport_user);
        model.addAttribute("curPage", curPage);
        return "sys/incode/list";
    }

    @RequestMapping("/addInCode")
    public String addInCode(String uid, HttpServletRequest req)
    {

        BoxUser boxUser = (BoxUser) userService.loadDate(3, uid);

        if (!StringUtil.isEmpty(boxUser.getUserInCode()))
        {
            StringUtil.setErrorCode(req, "该用户已拥有邀请码，无需再次生成!");
            return "redirect:/sys/incodelist";
        }

        String code = StringUtil.getRandomStr(10).toLowerCase();

        //生成邀请码
        BoxUser updateBoxUser = new BoxUser();
        updateBoxUser.setId(uid);
        updateBoxUser.setUserInType("1");
        updateBoxUser.setUserInCode(code);

        boolean result = userService.updateObjectData(11, updateBoxUser);

        if (result)
        {
            StringUtil.setErrorCode(req, boxUser.getAccount() + ",邀请码生成成功");

        }
        else
        {
            StringUtil.setErrorCode(req, boxUser.getAccount() + ",邀请码生失败");
        }

        return "redirect:/sys/incodelist";
    }

    @RequestMapping("/userlist")
    public String userlist(Model model, HttpServletRequest req,
            Integer curPage,
            Integer pageSize, String keyword)
    {
        pageSize = MyController.VAL_10;
        if (!StringUtil.isEmpty(keyword))
        {
            keyword = keyword.trim();
        }
        if (null == curPage)
        {
            curPage = 1;
        }
        paginationSupport_user = userService.getUserList(curPage,
                pageSize, keyword);

        for (BoxUser users : paginationSupport_user.getItems())
        {
            try
            {
                Integer count = (Integer) userService.loadDate(5,
                        users.getAccount());
                users.setUnderCount(count.toString());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                users.setUnderCount("0");
            }
        }

        model.addAttribute("paginationSupport", paginationSupport_user);
        model.addAttribute("curPage", curPage);
        model.addAttribute("keyword", keyword);
        return "sys/user/list";
    }

    @RequestMapping("/aviadlist")
    public String aviadlist(Model model, HttpServletRequest req,
            Integer curPage,
            Integer pageSize)
    {
        pageSize = 15;

        if (null == curPage)
        {
            curPage = 1;
        }

        paginationSupport_personAviEvt = userService.getPersonAviEvtList(curPage,
                pageSize);

        model.addAttribute("paginationSupport", paginationSupport_personAviEvt);
        model.addAttribute("curPage", curPage);
        return "sys/avi_ad/list";
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
        return "sys/config/list";
    }

    @RequestMapping("/updateUser/{uid}")
    public String updateUser(@PathVariable String uid, Model model,
            HttpServletRequest req)
    {
        BoxUser boxUser = (BoxUser) userService.loadDate(1, uid);
        model.addAttribute("boxUser", boxUser);
        return "sys/user/add";
    }

    @RequestMapping("/updateUserdo/{uid}")
    public String updateUserdo(@PathVariable String uid, BoxUser boxUser,
            HttpServletRequest req)
    {
        if (boxUser == null)
        {
            StringUtil.setErrorCode(req, "修改失败!");
            return "redirect:/sys/userlist";
        }

        boxUser.setId(uid);

        boolean result = userService.updateObjectData(6, boxUser);

        if (result)
        {
            StringUtil.setErrorCode(req, "设置成功!");
        }
        else
        {
            StringUtil.setErrorCode(req, "修改失败!");
        }

        return "redirect:/sys/updateUser/" + boxUser.getId();
    }

    @RequestMapping("/addConfig")
    public String addConfig(HttpServletRequest req)
    {

        return "sys/config/add";
    }

    @RequestMapping("/addConfigdo")
    public String addConfigdo(Config config, HttpServletRequest req)
    {
        boolean result = userService.updateObjectData(7, config);

        if (result)
        {
//            RedisUtils.saveRedisObject(RedisUtils.BASE_CONFIG + config.getKey(),
//                    config);

            //刷新所有节点配置
            String resMsg = CaheBlog.refreshNode("/cache/refreshAllConfig", "");

            StringUtil.setErrorCode(req, "保存成功!-" + resMsg);
        }
        else
        {
            StringUtil.setErrorCode(req, "保存失败!");
        }

        return "redirect:/sys/configlist";
    }

    @RequestMapping("/delConfig/{uid}")
    public String delConfig(@PathVariable String uid, BoxUser boxUser,
            HttpServletRequest req)
    {

        boolean result = userService.updateObjectData(9, uid);

        if (result)
        {
            StringUtil.setErrorCode(req, "成功!");
        }
        else
        {
            StringUtil.setErrorCode(req, "失败!");
        }

        return "redirect:/sys/configlist";
    }

    @RequestMapping("/upConfig/{id}")
    public String upConfig(@PathVariable String id, Model model)
    {
        Config config = (Config) userService.loadDate(2, id);

        model.addAttribute("config", config);
        return "sys/config/update";
    }

    @RequestMapping("/updateConfigdo/{id}")
    public String updateConfigdo(@PathVariable String id, Config config,
            Model model, HttpServletRequest req)
    {
        config.setId(id);
        //如果配置项key为315_URL_S 则 值为开启 value=1
        //那么删除person_avi整表
        //然后获取所有会员遍历给批量增加视频315_URL的配置值  type=1
        if ("315_URL_S".equals(config.getKey()) && "1".equals(config.getValue()))
        {
            //person_avi 整表数据删除
            userService.updateData(6);

            paginationSupport_user = userService.getUserList(1,
                    10000, "");

            List<BoxUser> userList = paginationSupport_user.getItems();

            Config urlConfig = CacheConfig.getConfigMap("315_URL");

            String urls = urlConfig.getValue();

            String[] urlAttr = urls.split(",");





            for (BoxUser us : userList)
            {
                List<PersonAviEvt> aviList =  new ArrayList<PersonAviEvt>();
                for (String s : urlAttr)
                {
                    if (!StringUtil.isEmpty(s))
                    {
                        PersonAviEvt item = new PersonAviEvt();
                        item.setUid(us.getId());
                        item.setTypes("1");
                        item.setUrl(s);
                        item.setTitle("视频播放");
                        aviList.add(item);
                    }

                }
                //批量添加 参数 aviList
                userService.updateObjectData(27, aviList);
            }





        }

        boolean result = userService.updateObjectData(8, config);
        if (result)
        {
//            RedisUtils.saveRedisObject(RedisUtils.BASE_CONFIG + config.getKey(),
//                    config);
            //刷新所有节点配置
            String resMsg = CaheBlog.refreshNode("/cache/refreshAllConfig", "");

            StringUtil.setErrorCode(req, "保存成功!-" + resMsg);
        }
        else
        {
            StringUtil.setErrorCode(req, "保存失败!");
        }
        return "redirect:/sys/configlist";
    }

    @RequestMapping("/delUser/{uid}")
    public String delUser(@PathVariable String uid, Model model,
            HttpServletRequest req)
    {
        boolean result = userService.updateObjectData(10, uid);
        if (result)
        {
            StringUtil.setErrorCode(req, "删除成功!");
        }
        else
        {
            StringUtil.setErrorCode(req, "删除失败!");
        }
        return "redirect:/sys/userlist";
    }

    @RequestMapping("/domainlist")
    public String domainlist(Model model, HttpServletRequest req,
            Integer curPage,
            Integer pageSize)
    {
        pageSize = 20;

        if (null == curPage)
        {
            curPage = 1;
        }

        paginationSupport_Domain = userService.getDomainList(curPage,
                pageSize, "");

        model.addAttribute("paginationSupport", paginationSupport_Domain);
        model.addAttribute("curPage", curPage);
        return "sys/domain/list";
    }

    @RequestMapping("/domaincenterlist")
    public String domaincenterlist(Model model, HttpServletRequest req,
            Integer curPage,
            Integer pageSize)
    {
        pageSize = 20;

        if (null == curPage)
        {
            curPage = 1;
        }

        paginationSupport_Domain = userService.getDomainCenterList(curPage,
                pageSize, "");

        model.addAttribute("paginationSupport", paginationSupport_Domain);
        model.addAttribute("curPage", curPage);
        return "sys/domaincenter/list";
    }

    @RequestMapping("/addDomain")
    public String addDocmain()
    {
        return "sys/domain/add";
    }

    @RequestMapping("/addDocmainCenter")
    public String addDocmainCenter()
    {
        return "sys/domaincenter/add";
    }

    @RequestMapping("/addDomainDo")
    public String addDomainDo(Domain domain, HttpServletRequest req)
    {

        if (domain.getVal().indexOf(",") >= 0)
        {
            String[] urls = domain.getVal().split(",");
            for (int i = 0; i <= (urls.length - 1); i++)
            {
                String u1 = urls[i].trim();
                String u = StringUtil.cleanXSS(u1);
                Domain saveDomain = new Domain();
                saveDomain.setStatus("2");
                saveDomain.setVal(u);
                boolean result = userService.updateObjectData(12, saveDomain);
            }
        }
        else
        {
            domain.setVal(StringUtil.cleanXSS(domain.getVal()));
            domain.setStatus("2");
            boolean result = userService.updateObjectData(12, domain);
        }

        StringUtil.setErrorCode(req, "添加成功");

        return "redirect:/sys/domainlist";
    }

    @RequestMapping("/addDomainCenterDo")
    public String addDomainCenterDo(Domain domain, HttpServletRequest req)
    {

        if (domain.getVal().indexOf(",") >= 0)
        {
            String[] urls = domain.getVal().split(",");
            for (int i = 0; i <= (urls.length - 1); i++)
            {
                String u1 = urls[i].trim();
                String u = StringUtil.cleanXSS(u1);
                Domain saveDomain = new Domain();
                saveDomain.setStatus("2");
                saveDomain.setVal(u);
                boolean result = userService.updateObjectData(19, saveDomain);
            }
        }
        else
        {
            domain.setVal(StringUtil.cleanXSS(domain.getVal()));
            domain.setStatus("2");
            boolean result = userService.updateObjectData(19, domain);
        }

        StringUtil.setErrorCode(req, "添加成功");

        return "redirect:/sys/domaincenterlist";
    }

    @RequestMapping("/delDomain/{id}")
    public String delDomain(@PathVariable String id, HttpServletRequest req)
    {
        boolean result = userService.updateObjectData(13, id);

        if (result)
        {
            StringUtil.setErrorCode(req, "成功!");
        }
        else
        {
            StringUtil.setErrorCode(req, "失败!");
        }

        return "redirect:/sys/domainlist";
    }

    @RequestMapping("/delDomainCenter/{id}")
    public String delDomainCenter(@PathVariable String id,
            HttpServletRequest req)
    {

        boolean result = userService.updateObjectData(20, id);

        if (result)
        {
            StringUtil.setErrorCode(req, "成功!");
        }
        else
        {
            StringUtil.setErrorCode(req, "失败!");
        }

        return "redirect:/sys/domaincenterlist";
    }

    @RequestMapping("/refreshDomain/{id}")
    public String refreshDomain(@PathVariable String id, HttpServletRequest req)
    {

        Domain domain = (Domain) userService.loadDate(7, null);

        if (domain != null)
        {
            StringUtil.setErrorCode(req, "请先删除存在的使用中域名.在变更域名");
            return "redirect:/sys/domainlist";
        }

        userService.updateObjectData(18,
                "0",
                id,
                "【" +
                        DateTimeUtil.getNow(DateTimeUtil.FORMAT_LONG) +
                        "】手动强制替换成功.！",
                "强制变更，比变更其群缓存，请到配置项同步该域名。");
        StringUtil.setErrorCode(req, "变更成功");
        return "redirect:/sys/domainlist";
    }



    @RequestMapping("/refreshDomainCenter/{id}")
    public String refreshDomainCenter(@PathVariable String id, HttpServletRequest req)
    {

        Domain domain = (Domain) userService.loadDate(9, null);

        if (domain != null)
        {
            StringUtil.setErrorCode(req, "请先删除存在的使用中域名.在变更域名");
            return "redirect:/sys/domainlist";
        }

        userService.updateObjectData(23,
                "0",
                id,
                "【" +
                        DateTimeUtil.getNow(DateTimeUtil.FORMAT_LONG) +
                        "】手动强制替换成功.！",
                "强制变更，比变更其群缓存，请到配置项同步该域名。");
        StringUtil.setErrorCode(req, "变更成功");
        return "redirect:/sys/domainlist";
    }

    @RequestMapping("/deldomains")
    public String deldomains(String id, HttpServletRequest req)
    {

        if (!StringUtil.isEmpty(id))
        {
            id = id.substring(0,(id.length()-1));
        }

        boolean result = userService.updateData(4,id);

        if (result)
        {
            StringUtil.setErrorCode(req, "删除成功!");
        }
        else
        {
            StringUtil.setErrorCode(req, "删除失败!");
        }

        return "redirect:/sys/domainlist";
    }


    @RequestMapping("/deldomains_center")
    public String deldomains_center(String id, HttpServletRequest req)
    {

        if (!StringUtil.isEmpty(id))
        {
            id = id.substring(0,(id.length()-1));
        }

        boolean result = userService.updateData(5,id);

        if (result)
        {
            StringUtil.setErrorCode(req, "删除成功!");
        }
        else
        {
            StringUtil.setErrorCode(req, "删除失败!");
        }

        return "redirect:/sys/domaincenterlist";
    }


    @RequestMapping("/addNotice")
    public String addNotice(HttpServletRequest req)
    {

        return "sys/notice/add";
    }

    @RequestMapping("/addNoticedo")
    public String addNoticedo(Notice notice, HttpServletRequest req)
    {
        boolean result = userService.updateObjectData(24, notice);

        if (result)
        {
            StringUtil.setErrorCode(req, "保存成功!");
        }
        else
        {
            StringUtil.setErrorCode(req, "保存失败!");
        }

        return "redirect:/sys/noticelist";
    }


    @RequestMapping("/delNotice/{uid}")
    public String delNotice(@PathVariable String uid, BoxUser boxUser,
            HttpServletRequest req)
    {

        boolean result = userService.updateObjectData(25, uid);

        if (result)
        {
            StringUtil.setErrorCode(req, "成功!");
        }
        else
        {
            StringUtil.setErrorCode(req, "失败!");
        }

        return "redirect:/sys/noticelist";
    }





}
