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
    
    <title>My JSP 'index.jsp' starting page</title>
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
  
  <h1>主页</h1><br>
  <%@ include file="Denglu.jsp"%>
  
  <form action="servlet/SearchServlet" method="post">
  	<table>
  		<tr>
  			<td>书名</td>
  			<td><input type="text" name="name"></td>
  		</tr>
  		<tr>
  			<td>出版社</td>
  			<td><input type="text" name="book1"></td>
  		</tr>
  <!--  	<tr>
  			<td>类型</td>
  			<td><input type="text" name="types"></td>
  		</tr>
  		-->	
  		<tr><td colspan="2" align="center"><input type="submit" value="搜索"></td></tr>
  	</table>
  </form>
  </body>
</html>
