<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<html>
<script type="text/javascript" >
	function Ok(){
		return true;
	}
	function check(){
		if(document.getElementById("n").value == ""){
			alert("用户名不能为空！");
			document.getElementById("n").focus();
			return false
		}
		if(document.getElementById("pwd").value == ""){
			alert("密码不能为空！");
			document.getElementById("pwd").focus();
			return false
		}
		if(document.getElementById("qpwd").value == ""){
			alert("确认密码不能为空！");
			document.getElementById("qpwd").focus();
			return false
		}
		if(document.getElementById("qpwd").value != document.getElementById("pwd").value ){
			alert("两次密码不一致");
			document.getElementById("qpwd").focus();
			return false
		}
		if(document.getElementById("a").value == ""){
			alert("年龄不能为空！");
			document.getElementById("a").focus();
			return false
		}
		if(document.getElementById("add").value == ""){
			alert("地址不能为空！");
			document.getElementById("add").focus();
			return false
		}
		if(document.getElementById("ph").value == ""){
			alert("电话不能为空！");
			document.getElementById("ph").focus();
			return false
		}
	}
</script>
<script>
	function jump1(){
		window.location.href="index.jsp";
	}
</script>
<%
	String error = (String)request.getAttribute("error");
	if(error != null && error.equals("1")){
 %>
 <script>alert("用户名重复，请更换用户名！");</script>
 <%} %>
 
  <body>
  	<div align="center">
	  	<form action="/HomeWork/servlet/ServletRegister" method="post" name="form1" onsubmit="return check()">
	  	
	  		<table border="1">
	  			<tr>
	  				<td colspan="2" align="center" height="25">
	  					<font>请填写用户信息（*为必填项）</font>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>用户名</td>
	  				<td><input type="text" id="n" name="logname">*</td>
	  			</tr>
	  			<tr>
	  				<td>密码</td>
	  				<td><input type="password" id="pwd" name="password">*</td>
	  			</tr>
	  			<tr>
	  				<td>确认密码</td>
	  				<td><input type="password" id="qpwd" name="qpassword">*</td>
	  			</tr>
	  			<tr>
	  				<td>性别</td>
	  				<td><input type="radio" name="sex" checked="男" value="男">男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="女">女</td>
	  			</tr>
	  			<tr>
	  				<td>年龄</td>
	  				<td><input type="text" id="a" name="age">*</td>
	  			</tr>
	  			<tr>
	  				<td>地址</td>
	  				<td><input type="text" id="add" name="address">*</td>
	  			</tr>
	  			<tr>
	  				<td>电话</td>
	  				<td><input type="text" id="ph" name="phone">*</td>
	  			</tr>
	  			<tr>
	  				<td colspan="2" align="center"><input type="submit" value="提交"></td>
	  			</tr>
	<!--  		<tr>
	  				<td colspan="2" align="center"><input type="button" value="返回" onclick="javascript:jump1()"></td>
	  			</tr>
	  -->  	
	  		
				<%if(request.getAttribute("ok") != null){ %>
					<tr>
	  					<td colspan="2" align="center"><font size="3" color="red">恭喜您注册成功</font></td>
	  				</tr>
	  			<%} %>
<!--    			else if(request.getAttribute("erroe") != null){ %>
	  				<tr>
	  					<td colspan="2" align="center"><font size="3" color="red">用户名重复</font></td>
	  				</tr>
	  		
-->	
	  		</table>
	  	</form>
  	</div>
  </body>
</html>
