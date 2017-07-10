package com.audio.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by Gaoxiang on 2017/1/16.
 */
public class Test
{

    public static String[] NAMES = { "", "" };

    public static void main(String[] args)
    {
        String val = "{\"url_short\":\"http://t.cn/R6G5uyx\",\"url_long\":\"http://3UanRoXv42d5cS8.127.0.0.1:8080//play/cen/20170320913472\",\"type\":0}";
        String cal = "{status:1,address:2}";
        String cal1 = "{url_short://t.cn/R6GV9N3,url_long://W3imhYPoV478laj1270018080//play/cen/20170320913472,type:0}";
        JsonObject obj = new JsonParser().parse(cal1).getAsJsonObject();
        String str = obj.get("url_short").getAsString();
        System.out.println(str);

    }

}
