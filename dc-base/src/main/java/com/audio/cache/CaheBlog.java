package com.audio.cache;

import com.audio.core.entity.Config;
import com.audio.util.StringUtil;
import com.audio.util.URLTool;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Gaoxiang on 2017/4/12.
 */
public class CaheBlog
{

    public static String refreshNode(String uri, String param)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("缓存节点[");
        Config bolgcfg = CacheConfig.getConfigMap("BOLG_IPS");
        Config accesscfg = CacheConfig.getConfigMap("BOLG_ACCESS");
        //sb.append(bolgcfg.getValue()+"]");
        String[] blogArrs = bolgcfg.getValue().split(",");
        String[] accessArrs = accesscfg.getValue().split(",");
        int index = 0;
        for (String s : blogArrs)
        {

            //s = ip
            String res = URLTool.sendPost(accessArrs[index]+uri, s,param);
            if (StringUtil.isEmpty(res))
            {
                res = s.substring(s.lastIndexOf(".")+1)+"可能已经挂了..";
            }
            sb.append("["+res+"]");
            index++;
        }

        return sb.toString();
    }



}
