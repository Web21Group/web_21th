<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userHomepageRight.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/font.css">
	

  </head>
  
  <body background="img/bg.jpg">
     
	<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
	  
	  
	  <tr>
	    
	    
	    <td width="24%">
		    <table width="99%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td width="19%" rowspan="4"><img src="img/p1.jpg" width="114" height="157"  alt=""/></td>
		        <td width="81%" height="33">品种</td>
		      </tr>
		      <tr>
		        <td height="32">价格</td>
		      </tr>
		      <tr>
		        <td height="38">店铺名称</td>
		      </tr>
		      <tr>
		        <td>描述</td>
		      </tr>
		    </table>
		 </td>
	    <td width="27%">
		    <table width="98%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td width="19%" rowspan="4"><img src="img/p1.jpg" width="114" height="157"  alt=""/></td>
		        <td width="81%" height="33">品种</td>
		      </tr>
		      <tr>
		        <td height="32">价格</td>
		      </tr>
		      <tr>
		        <td height="38">店铺名称</td>
		      </tr>
		      <tr>
		        <td>描述</td>
		      </tr>
		    </table>
		</td>
	  </tr>
	  
	  <tr>
	    <td height="34" colspan="4" bgcolor="#FFCCCC">&nbsp;</td>
	  </tr>
	</table>
  </body>
  </body>
</html>
