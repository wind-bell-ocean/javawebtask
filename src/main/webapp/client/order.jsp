<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" uri="http://www.arts.cn/tag"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>手工艺术之家</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/order.js"></script>
</head>
<body>
	<p:user/>
	<jsp:include page="head.jsp"></jsp:include>
	<jsp:include page="menu_search.jsp"></jsp:include>
	<div id="divpagecontent">
	<table width="100%" border="0" cellspacing="0">
			<tr>
				<td><div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath }/index.jsp">首页</a>
						   &nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath}/client/cart.jsp">&nbsp;购物车</a>
						   &nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;订单
					</div>
					<form id="orderForm" action="${pageContext.request.contextPath}/createOrder" method="post">
						<table cellspacing="0" class="infocontent">
							<tr>
								<td>
								<table width="100%" border="0" cellspacing="0">
										<tr>
											<td>
												<p>你好，${user.username}！欢迎您来到手工艺术之家结算中心</p>
											</td>
										</tr>
										<tr>
											<td>
											    <table cellspacing="1" class="carttable">
													<tr>
														<td width="10%">序号</td>
														<td width="40%">商品名称</td>
														<td width="10%">价格</td>
														<td width="10%">类别</td>
														<td width="10%">数量</td>
														<td width="10%">小计</td>
													</tr>
												</table> 
												<c:set value="0" var="totalPrice"/>
												<c:forEach items="${cart}" var="entry" varStatus="vs">
													<table width="100%" border="0" cellspacing="0">
														<tr>
															<td width="10%">${vs.count}</td>
															<td width="40%">${entry.key.artsname }</td>
															<td width="10%">${entry.key.price }</td>
															<td width="10%">${entry.key.category}</td>
															<td width="10%">
															  <input name="text" type="text" value="${entry.value}" style="width:20px" readonly="readonly"/>
															</td>
															<td width="10%">${entry.key.price*entry.value}</td>
														</tr>
													</table>
													<c:set var="totalPrice" value="${totalPrice+entry.key.price*entry.value}"/>
												</c:forEach>

												<table cellspacing="1" class="carttable">
													<tr>
														<td style="text-align:right; padding-right:40px;"><font
															style="color:#FF0000">合计：&nbsp;&nbsp;${totalPrice}元</font>
															<input type="hidden" name="totalprice" value="${totalPrice}">
														</td>
													</tr>
												</table>
												<p>
													收货地址：<input id="receiverAddress" name="receiveraddr" type="text" value=""style="width:350px" onkeyup="checkReceiverAddress();" />
													&nbsp;&nbsp;&nbsp;&nbsp;
													<span id="receiverAddressMsg"></span>
													<br/>
												        收货人：&nbsp;&nbsp;&nbsp;&nbsp;<input id="receiverName" name="receivername" type="text" value="${user.username}" style="width:150px" onkeyup="checkReceiverName();" />
												    <span id="receiverNameMsg"></span>
												    &nbsp;&nbsp;&nbsp;&nbsp;
													<br/> 
													联系方式：<input type="text" id="receiverPhone" name="receivertel" value="${user.telephone}" style="width:150px" onkeyup="checkReceiverPhone();" />
													<span id="receiverPhoneMsg"></span>
													&nbsp;&nbsp;&nbsp;&nbsp;
												</p>
												<hr />
												<p style="text-align:right">
													<img src="images/gif53_029.gif" width="204" height="51" border="0" onclick="checkOnSubmit();"/>
												</p>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>	
	</div>
</body>
</html>