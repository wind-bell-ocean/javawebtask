<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" uri="http://www.arts.cn/tag"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>手工艺术之家</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/main.css" type="text/css" />
</head>
<body class="mian">
	<p:user/>
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td>
					<div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath }/index.jsp">首页</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath }/findOrderByUser">我的订单</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;订单详细信息
					</div>
					<table cellspacing="0" class="infocontent">
						<tr>
							<td><p>订单编号:${order.orderid }</p></td>
						</tr>
						<tr>
							<td>
								<table cellspacing="1" class="carttable">
									<tr>
										<td width="10%">商品ID</td>
										<td width="40%">商品名称</td>
										<td width="10%">购买数量</td>
										<td width="10%">小计</td>
										<td width="10%">订单状态</td>
									</tr>
								</table>
								<table width="100%" border="0" cellspacing="0">
									<td width="10%">
										<a href="${pageContext.request.contextPath}/findProductById?id=${order.artsid}">${order.artsid }</a>
									</td>
									<td width="40%">${order.artsname }</td>
									<td width="10%">${order.salenum }</td>
									<td width="10%">${order.totalprice }</td>
									<td width="10%">
										<c:choose>
											<c:when test="${orderstatus==0 }">未支付</c:when>
											<c:when test="${orderstatus==1 }">已支付</c:when>
											<c:when test="${orderstatus==2 }">已发货</c:when>
											<c:when test="${orderstatus==3 }">订单完成</c:when>
										</c:choose>
									</td>
								</table>
								<p>
									收货地址：${order.receiveraddr }&nbsp;&nbsp;&nbsp;&nbsp;<br />
									收货人：&nbsp;&nbsp;&nbsp;&nbsp;${order.receivername }&nbsp;&nbsp;&nbsp;&nbsp;<br />
									联系方式：${order.receivertel }&nbsp;&nbsp;&nbsp;&nbsp;
								</p>
								<hr>
								<c:if test="${order.orderstatus == 0 }">
									<p style="text-align:right">
										<a href="${pageContext.request.contextPath}/client/pay.jsp?id=${order.orderid}&money=${order.totalprice}">
											<img src="${pageContext.request.contextPath }/client/images/gif53_030.gif" width="204" height="51" border="0" /> 
										</a>
									</p>
								</c:if>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					
				</td>
			</tr>
		</table>
	</div>
</body>
</html>