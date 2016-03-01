<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="java.util.ArrayList" %>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'bookDetail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%@include file="Denglu.jsp" %>
    <%
    	ArrayList al = (ArrayList)request.getAttribute("bookinf");
    	ArrayList bookinf = (ArrayList)al.get(1);
     %>
     
     <div>
     	<h1>图书详细信息</h1>
     	<ul>
     		<li>书名：<%=bookinf.get(1) %></li>
     		<li>作者：<%=bookinf.get(2) %></li>
     		<li>出版社：<%=bookinf.get(3) %></li>
     		<li>ISBN：<%=bookinf.get(4) %></li>
     		<li>价格：<%=bookinf.get(5) %></li>
     		<li>库存：<%=bookinf.get(6) %></li>
     	</ul>
     </div>
  </body>
</html>
