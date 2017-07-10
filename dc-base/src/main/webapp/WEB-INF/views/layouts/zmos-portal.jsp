<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html>
<head>
	<title><sitemesh:title/></title>
	<%@include file="/WEB-INF/views/include/zmos-head.jsp" %>
	<sitemesh:head/>
</head>
<body>
<sitemesh:body/>
<%@include file="/WEB-INF/views/include/zmos-footer.jsp" %>
</body>
</html>