package com.audio.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayUtil
{

    public static String[] unique(String[] array)
    {
        if (null == array || array.length == 0)
        {
            return null;
        }
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < array.length; i++)
        {
            // 如果list数组不包括num[i]中的值的话，就返回true。
            if (null != array[i] && !list.contains(array[i]))
            {
                // 在list数组中加入num[i]的值。已经过滤过。
                list.add(array[i]);
            }
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    public static Object[] unique(Object[] a)
    {
        // array_unique
        List<Object> list = new LinkedList<Object>();
        for (int i = 0; i < a.length; i++)
        {
            if (!list.contains(a[i]))
            {
                list.add(a[i]);
            }
        }
        return (Object[]) list.toArray(new Object[list.size()]);
    }

    public static String arrayToString(String[] ids)
    {
        return arrayToString(ids, ",");
    }

    public static String arrayToString(String[] ids, String sp)
    {
        String str = "";
        if (null != ids)
        {
            sp = (null == sp) ? "," : sp;
            for (String id : ids)
            {
                str += id + "" + sp;
            }
            if (null != str && !"".equals(ids) && str.endsWith(sp))
            {
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    public static String arrayToString(Integer[] ids)
    {
        return arrayToString(ids, ",");
    }

    public static String arrayToString(Integer[] ids, String sp)
    {
        return arrayToString(integerToString(ids), sp);
    }

    public static String[] integerToString(Integer[] array)
    {
        if (null != array)
        {
            String[] arr = new String[array.length];
            String tempStr = null;
            for (int i = 0; i < array.length; i++)
            {
                tempStr = String.valueOf(array[i]);
                if (null != tempStr && !"null".equals(tempStr))
                {
                    arr[i] = String.valueOf(array[i]);
                }
            }
            return arr;
        }
        return null;
    }

}
