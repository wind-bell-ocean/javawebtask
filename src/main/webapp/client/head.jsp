<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.arts.cyyarts.domain.User"%>
<script type="text/javascript">
//退出确认框
function confirm_logout() {   
    var msg = "您确定要退出登录吗？";   
    if (confirm(msg)==true){   
    return true;   
    }else{   
    return false;   
    }   
} 
</script>
<div id="divhead">
	<table style="cellspace:0" class="headtable">
		<tr>
			<td>
				<a href="${pageContext.request.contextPath }/index.jsp">
					<img src="${pageContext.request.contextPath}/client/images/logo.jpg" width="200" height="65" border="0" /> 
				</a>
			</td>
			<td style="text-align:right">
				  <a href="${pageContext.request.contextPath}/client/cart.jsp">购物车</a>  
				| <a href="${pageContext.request.contextPath}/myAccount">个人中心</a>
				| <a href="${pageContext.request.contextPath}/findOrderByUser">我的订单</a>
				<% 
				User user = (User) request.getSession().getAttribute("user");
				if(null == user){
				%>
				| <a href="${pageContext.request.contextPath}/client/login.jsp">登录</a>
				| <a href="${pageContext.request.contextPath}/client/register.jsp">注册</a>					
				<% 	
				}else{
				%>
				| <a href="${pageContext.request.contextPath}/logout" onclick="javascript:return confirm_logout()">退出</a>
				<br><br><br>欢迎您： ${user.username}
				<% 	
				}
				%>			
			</td>		
		</tr>
	</table>
</div>