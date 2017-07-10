
import com.audio.core.entity.Config;
import com.audio.core.service.system.SystemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by  on 2017/1/6.
 * e-mail: ooxx@Live.cn
 */
@Controller
@RequestMapping("/system")
public class SystemController
{

    @Resource
    private SystemService systemService;

    @RequestMapping("/user")
    public String user(){
        return "/modules/system/system-user";
    }

    @RequestMapping("/role")
    public String role(){
        return "/modules/system/system-role";
    }

    @RequestMapping("menu")
    public String menu(){
        return "/modules/system/system-menu";
    }

    @RequestMapping("log")
    public String log(){
        return "/modules/system/system-log";
    }

    @RequestMapping("/sayHello")
    public String sayHello()
    {
        return "redirect:/system/sayHello2";
    }

    @RequestMapping("/sayHello2")
    public String sayHello2(HttpServletRequest request)
    {
        List<Config> configList = systemService.getSaBdConfig();
        request.setAttribute("configList", configList);
        return "test";
    }

    @RequestMapping("/getSystemInfo")
    @ResponseBody
    public List<Config> getSystemInfo()
    {
        List<Config> configList = systemService.getSaBdConfig();
        return configList;
    }
    @RequestMapping("/user-manager")
    public String systemUser()
    {
        return "modules/system/system-user";
    }

}
