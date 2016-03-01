<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript" src=".../js/jquery-1.5.1.min.js" ></script>
		<script type="text/javascript" src=".../js/jquery_dialog.js" ></script>
		<script>
			function jump(){
				window.location.href="register.jsp";
			}
		</script>
	</head>
  <body>
  
  	
  	<div>
  		<div>
  			<form action="/HomeWork/servlet/UserLoginServlet" method="post" name="form0">
  				<table>
  					<tr>
  						<td>用户名</td>
  						<td><input type="text" name="name" size="15"></td>
  					</tr>
  					<tr>
  						<td>密码</td>
  						<td><input type="password" name="pwd" size="15"></td>
  					</tr>
  					<tr>
  						<td colspan="2" align="center">
  							<input type="submit" value="登录">&nbsp;&nbsp;
  							<input type="button" value="注册" onclick="javacsript:jump()">&nbsp;&nbsp;
  						</td>
  					</tr>
  					<tr>
  						<td height="25" colspan="2">
  							<%
  								String isLogin1 = (String)session.getAttribute("login");
  								String loginError = (String)request.getAttribute("loginError");
  								if(isLogin1 == null && loginError == null){
  							 %>
  							<p><font color="red">用户未登录，无账号请先注册</font></p>
  							<%
  								}else if(isLogin1 != null && loginError == null && !isLogin1.equals("")){
  									String userName = isLogin1;
  							 %>
  							 <p><font color="red"><%=userName %>,您已登入</font></p>
  							 <%
  							 	}else if(isLogin1 == null && "error".equals(loginError)){
  							  %>
  							  <p><font color="red">用户名或密码错误</font></p>
  							  <%} %>
  						</td>
  					</tr>
  				</table>
  			</form>
  		</div>
  	</div>
  </body>
</html>
