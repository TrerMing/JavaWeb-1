<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'booklist.jsp' starting page</title>
    
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
    <h1>图书列表</h1>
    <div>
    	<form>
    		<table width="656" height="46" border="1">
    			<tr>
    				<td>书籍</td>
    				<td>作者</td>
    				<td>出版社</td>
    				<td>价格</td>
    				<td>ISBN</td>
    				<td>购买</td>
    			</tr>
    			<%
    				ArrayList bookSearch = (ArrayList)request.getAttribute("search");
    				ArrayList al = null;
    				if(bookSearch != null){
    					for(int i = 1; i < bookSearch.size(); i++){
    						al = (ArrayList)bookSearch.get(i);
    					//	out.print(alRow.get(0));
    			 %>
    			 <tr>
    			 	<td>
    			 		<a href="servlet/BookDetails?bookId=<%=al.get(0)%>" title="单击查看详细信息"><%=al.get(1) %></a>
    			 	</td>
    			 	<td><%=al.get(2) %></td>
    			 	<td><%=al.get(3) %></td>
    			 	<td><%=al.get(5) %></td>
    			 	<td><%=al.get(4) %></td>
    			 	<td></td>
    			 </tr>
    			 <%}}%>

    			 

    			
    			 
    		</table>
    		
    	</form>
    </div>
    <%
    	Integer currentPage = (Integer)request.getAttribute("currentPage");//当前页数
    	Integer pageCount = (Integer)request.getAttribute("pageCount");//总页数
    	Integer totalCount = (Integer)request.getAttribute("totalCount");//总页数
    	if(currentPage == null || pageCount == null){
    		currentPage = pageCount = 1;
    	}
     %>
    <table>
    	<tr>
    		<td><a href="servlet/SearchServlet?page=1">首页</a></td>
    		<td><a href="servlet/SearchServlet?page=<%=currentPage - 1 %>">上一页</a></td>
    		<td>第 <%=currentPage %> 页</td>
    		<td><a href="servlet/SearchServlet?page=<%=currentPage + 1 %>">下一页</a></td>
    		<td><a href="servlet/SearchServlet?page=<%=pageCount%>">最后一页</a></td>
    		<td>共<%=totalCount %> 条记录 共 <%=pageCount %> 页</td>
    	</tr>
    
    </table>
  </body>
</html>
