
package com.audio.util;

import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil
{

    public static String REDIS_IP;

    public static String REDIS_AUTH;

    public static String TEST_MODE;

    public static String generateWord() {
        String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7",
                "8", "9", "a", "v", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z" };
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(1, 16);
        return result;
    }

    public static int getTotalPage(int total, int pageSize)
    {
        int totalPage = 1;
        if (total == 0)
        {
            totalPage = 1;
        }
        else
        {
            totalPage = (total % pageSize == 0) ? (total / pageSize) : (total
                    / pageSize + 1);
        }
        return totalPage;
    }

    /**
     * @param str
     * @return boolean
     */
    public static boolean isEmpty(String str)
    {
        return (null == str || "".equals(str));
    }

    public static String firstUpperCase(String str)
    {
        if (isNotEmpty(str))
        {
            str = str.replaceFirst(str.substring(0, 1),
                    str.substring(0, 1).toUpperCase());
        }
        return str;
    }

    /**
     * @param strArray
     * @param str
     * @return boolean
     */
    public static boolean inArray(String[] strArray, String str)
    {
        boolean inarray = false;
        if (null != strArray && isNotEmpty(str))
        {
            for (String temp : strArray)
            {
                if (null != temp && str.equals(temp))
                {
                    inarray = true;
                    break;
                }
            }
        }
        return inarray;
    }

    public static String removeLastStr(String str, String sp)
    {
        if (isNotEmpty(str) && isNotEmpty(sp) && str.endsWith(sp))
        {
            return str.substring(0, str.length() - 1);
        }
        return str;
    }

    /**
     * 获取随机数
     */
    public static String getRandomStr(int length)
    {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < length; i++)
        {
            int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写还是小写
            result += String.valueOf((char) (choice + random.nextInt(26)));
        }
        return result;
    }

    /**
     * @param str
     * @return boolean
     */
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

    /**
     * 匹配出一个字符串中的数字并返回
     *
     * @param str
     * @return
     * @author zhoulin
     * @time 2014-05-08
     * @since 1.0
     */
    public static String getNumberInString(String str)
    {
        if (null == str || "".equals(str))
        {
            return null;
        }
        Matcher matcher = Pattern.compile("\\d+").matcher(str);
        String theNumber = null;
        while (matcher.find())
        {
            theNumber = matcher.group(0);
        }
        return ("".equals(theNumber)) ? null : theNumber;
    }

    /**
     * 非法字符替换
     *
     * @param paramValue
     * @return String
     */
    public static String secutityReplace(String paramValue)
    {
        if (isNotEmpty(paramValue))
        {
            paramValue = paramValue.replaceAll("\t", "");
            paramValue = paramValue.replaceAll("\r\n", "");
            paramValue = paramValue.replaceAll("\r", "");
            paramValue = paramValue.replaceAll("\n", "");
            paramValue = paramValue.replaceAll(" ", "");
            paramValue = paramValue.replaceAll("\"", "");
            paramValue = paramValue.replaceAll("%0a", "");
            paramValue = paramValue.replaceAll("%0d", "");
            paramValue = paramValue.replaceAll("%0D", "");
            paramValue = paramValue.replaceAll("%0A", "");
            paramValue = paramValue.replaceAll("%", "");
            paramValue = paramValue.replaceAll("<", "&lt");
            paramValue = paramValue.replaceAll(">", "&gt");
            paramValue = paramValue.replaceAll("%3C", "&lt");
            paramValue = paramValue.replaceAll("%3E", "&gt");
            paramValue = paramValue.replaceAll("#", "");
            //paramValue = paramValue.replaceAll("/", "");
            paramValue = paramValue.replaceAll("\\\\", "");
            paramValue = paramValue.replaceAll("'", "");
            paramValue = paramValue.replaceAll("\"", "");
        }
        return paramValue;
    }

    /**
     * 将特殊符号转换成html代码
     */
    public static String htmlspecialchars(String str)
    {
        if (null == str || "".equals(str))
        {
            return "";
        }
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&quot;");
        return str;
    }

    public static Map<String, Object> getParamMap(Object... paramMap)
    {
        Map<String, Object> pmap = new HashMap<String, Object>();
        for (int i = 0; i < paramMap.length; i++)
        {
            if (i % 2 != 0)
            {
                continue;
            }
            pmap.put(String.valueOf(paramMap[i]), paramMap[i + 1]);
        }
        return pmap;
    }

    public static void setErrorCode(HttpServletRequest req, String val)
    {
        req.getSession().setAttribute("errorCode", val);
    }

    public static String getlimitUrl(String urls)
    {
        String url = "http://api.t.sina.com.cn/short_url/shorten.json?source=3207829341&url_long="+urls;
        String val = URLTool.sendGet(url);
        if (!StringUtil.isEmpty(val))
        {
            //[{"url_short":"http://t.cn/R6GcTlb","url_long":"http://oVlS726UaXiek8h.127.0.0.1:8080//play/cen/20170320913472","type":0}]
            val = val.substring(val.indexOf("[{\"url_short\":\"")+15,val.indexOf("\",\"url_long"));
            //JsonObject obj = new JsonParser().parse(val).getAsJsonObject();
            //String str = obj.get("url_short").getAsString();
            return val;
        }
        return "";
    }

    public static void setSortUrl(HttpServletRequest req, String val)
    {
        req.getSession().setAttribute("sort_url", val);
    }

    public static String cleanXSS(String value) {
        if (value != null) {
            value = StringEscapeUtils.escapeHtml3(value);
            value = secutityReplace(value);
        }
        return value;
    }

    public static String getRandomNum(int length) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getId() {
        return DateTimeUtil.getNow("yyyyMMdd") + StringUtil.getRandomNum(6);
    }


    public static String getCupValue(String val)
    {
        if (!isEmpty(val))
        {
            if (val.length() >= 10)
            {
                return val.substring(0,10)+"..";
            }
        }
        return val;
    }







    /**
     * 判断是否移动设备访问
     * @param request
     * @return
     */
    public static boolean clientIsMoblie(HttpServletRequest request) {
        boolean isMoblie = false;
        String[] mobileAgents = { "iphone", "android", "phone", "mobile",
                "wap", "netfront", "java", "opera mobi", "opera mini", "ucweb",
                "windows ce", "symbian", "series", "webos", "sony",
                "blackberry", "dopod", "nokia", "samsung", "palmsource", "xda",
                "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
                "docomo", "up.browser", "up.link", "blazer", "helio", "hosin",
                "huawei", "novarra", "coolpad", "webos", "techfaith",
                "palmsource", "alcatel", "amoi", "ktouch", "nexian",
                "ericsson", "philips", "sagem", "wellcom", "bunjalloo", "maui",
                "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
                "pantech", "gionee", "portalmmm", "jig browser", "hiptop",
                "benq", "haier", "^lct", "320x320", "240x320", "176x220",
                "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq",
                "bird", "blac", "blaz", "brew", "cell", "cldc", "cmd-", "dang",
                "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs", "kddi",
                "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo",
                "midp", "mits", "mmef", "mobi", "mot-", "moto", "mwbp", "nec-",
                "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play",
                "port", "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-",
                "send", "seri", "sgh-", "shar", "sie-", "siem", "smal", "smar",
                "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-",
                "upg1", "upsi", "vk-v", "voda", "wap-", "wapa", "wapi", "wapp",
                "wapr", "webc", "winw", "winw", "xda", "xda-",
                "Googlebot-Mobile" };
        if (request.getHeader("User-Agent") != null) {
            for (String mobileAgent : mobileAgents) {
                if (request.getHeader("User-Agent").toLowerCase().indexOf(mobileAgent) >= 0) {
                    isMoblie = true;
                    System.out.println("当前访问设备:"+mobileAgent);
                    break;
                }
            }
        }
        System.out.println("当前是否移动访问:"+isMoblie);
        return isMoblie;
    }

    /**
     * 获取用户请求的IP地址
     * @return [参数说明]
     */
    public static String getRequestIp(HttpServletRequest request)
    {
        //获取用户的IP信息
        InetAddress inet = null;
        String ip = request.getHeader("X-Real-IP");

        if (StringUtil.isEmpty(ip))
        {
            ip = request.getRemoteHost();
        }
        try
        {
            inet = InetAddress.getLocalHost();
            if (ip.equals("127.0.0.1"))
                ip = inet.getHostAddress();
        }
        catch (Exception e)
        {
            System.out.println("get host IP fail"+
                    e);
        }
        return ip;
    }






}
