<%@page pageEncoding="UTF-8"%>
<%
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader("Expires",0);
String errmsg = (String)request.getAttribute("errmsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>error</title>
</head>
<body>
抱歉， 系统异常请稍后再试!<br>
<br>
异常信息：<%=errmsg %>
</body>
</html>
