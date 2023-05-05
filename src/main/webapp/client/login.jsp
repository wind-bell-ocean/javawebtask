<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>手工艺术之家登录页面</title>
    <%--导入css和js --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css"/>
</head>
<body class="main">
    <%@include file="head.jsp" %>
    <%@include file="menu_search.jsp" %>
    
    <div id="divcontent">
        <form action="${pageContext.request.contextPath}/login" method="post">
            <table width="900px" border="0" cellspacing="0">
                <tr>
                    <td style="padding:30px">
                    <h1>用户登录</h1>
                        <table width="85%" border="0" cellspacing="0">
                            <tr>
                                <td style="text-align:right">角色：</td>
                                <td colspan="3">&nbsp;&nbsp;
                                    <input type="radio" name="userrole" value="consumer" />普通用户
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input type="radio" name="userrole" value="salesperson" />销售人员
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input type="radio" name="userrole" value="arranger" />系统管理员
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align:right; padding-top:5px; width:25%">用户名：</td>
                                <td style="text-align:left"><input type="text" name="username" class="textinput" /></td>
                            </tr>
                            <tr>
                                <td style="text-align:right; padding-top:5px">密&nbsp;&nbsp;&nbsp;码</td>
                                <td style="text-align:left">
                                    <input type="password" name="password" class="textinput" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" style="text-align:center">
                                    <input type="checkbox" name="checkbox" value="checkbox01" />记住用户名&nbsp;&nbsp;
                                    <input type="checkbox" name="checkbox" value="checkbox02" />自动登录
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-top: 20px; text-align: center">
                                    <input type="image" name="image" onclick="return formcheck()"
                                    src="${pageContext.request.contextPath }/client/images/submit.gif" border="0" width="140" height="35"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td style="text-align:left;padding-top:30px;width:60%">
                        <h1>还没有手工艺术之家的账号？</h1>
                        <img src="${pageContext.request.contextPath }/client/images/logo.jpg" width="200" height="65" border="0">
                        <p>立即注册账号~</p>
                        <p style="text-align:left">
							<a href="${pageContext.request.contextPath }/client/register.jsp">
								<img src="${pageContext.request.contextPath }/client/images/gotoregister.jpg" width="135" height="33" />
							</a>
						</p>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>