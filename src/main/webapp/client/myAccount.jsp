<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="p" uri="http://www.arts.cn/tag"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>个人中心</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/main.css" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/form.js"></script>
</head>
<body class="main">
	<p:user />
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	<div id="dicpagecontent">
		<div style="text-align:right; margin:5px 10px 5px 0px">
			<a href="${pageContext.request.contextPath }/index.jsp">首页</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;我的账户
		</div>
		<form action="${pageContext.request.contextPath}/modifyUser" id="userForm" method="post">
			<table width="80%" border="0" cellspacing="0">
				<tr>
					<td style="padding:30px">您的UID是：&nbsp;&nbsp;&nbsp;&nbsp;${user. userid}</td>
				</tr>
				<tr>
					<td style="text-align:right; width:25%">昵称(用户名):</td>
					<td style="width:40%">
						<input type="text" class="textinput" id="username" 
						name="username" value="${user.username }" onkeyup="checkUsername();" />
					</td>
				</tr>
				<tr>
					<td style="text-align:right; width:25%">性别：:</td>
					<td style="width:40%">
						<input type="radio" name="sex" value="男" checked="checked" /> 男
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="sex" value="女" /> 女
					</td>
				</tr>
				<tr>
					<td style="text-align:right; width:25%">联系电话：:</td>
					<td style="width:40%">
						<input type="text" class="textinput"style="width: 150px" 
						name="telephone" value="${user.telephone }" />
					</td>
				</tr>
				<tr>
					<td style="text-align:right; width:25%">电子邮箱：:</td>
					<td style="width:40%">
						<input type="text" class="textinput"  id="email" name="email" value="${user.email }" onkeyup="checkEmail();"/>
					</td>
				</tr>
				<tr>
					<td style="text-align:right; width:25%">个性签名：:</td>
					<td colspan="2">
						<textarea class="textarea" name="signature">${user.signature }</textarea>
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<table width="100%" border="0" cellspacing="0">
				<tr>
					<td style="padding-top: 20px; text-align: center">
						<input type="image" src="images/submit.gif" name="submit" border="0" width="140" height="35"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>