<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.audio.util.StringUtil" %>
<%


    if (StringUtil.clientIsMoblie(request))
    {
        response.sendRedirect(request.getContextPath() + "/wap/userlogin");
    }
    else
    {
        response.sendRedirect(request.getContextPath() + "/login/userlogin");
    }


%>