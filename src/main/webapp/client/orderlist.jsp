<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" uri="http://www.arts.cn/tag"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>手工艺术之家</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
	<script type="text/javascript">
	//删除订单
	function o_del() {   
	    var msg = "您确定要删除该订单吗？";   
	    if (confirm(msg)==true){   
	    return true;   
	    }else{   
	    return false;   
	    }   
	} 
	</script>
</head>
<body class="main">
	<p:user/>
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td width="100%">
					<div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath }/index.jsp">首页</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;
						我的订单
					</div>
					<table cellspacing="0" class="infocontent">
						<td style="padding:20px">
							<p>
								共有<font style="color:#FF0000" >${orders.size()}</font>订单
							</p>
							<table width="100%" border="0" cellspacing="0" class="tableopen">
								<tr>
									<td bgcolor="#A3E6DF" class="tableopentd01">订单号</td>
									<td bgcolor="#A3D7E6" class="tableopentd01">收件人</td>
									<td bgcolor="#A3CCE6" class="tableopentd01">订单时间</td>
									<td bgcolor="#A3B6E6" class="tableopentd01">状态</td>
									<td bgcolor="#A3E2E6" class="tableopentd01">操作</td>
								</tr>
								<c:forEach items="${orders}" var="order">
									<tr>
										<td class="tableopentd02">${order.orderid}</td>
										<td class="tableopentd02">${order.receivername }</td>
										<td class="tableopentd02">${order.ordertime}</td>
										<td class="tableopentd02">
											<c:choose>
												<c:when test="${orderstatus==0 }">未支付</c:when>
												<c:when test="${orderstatus==1 }">已支付</c:when>
												<c:when test="${orderstatus==2 }">已发货</c:when>
												<c:when test="${orderstatus==3 }">订单完成</c:when>
											</c:choose>
											<%-- ${order.orderstatus==0?"未支付":"已支付"} --%>
										</td>
										<td class="tableopentd03">
											<a href="${pageContext.request.contextPath}/findOrderById?id=${order.orderid}">查看(支付)</a>&nbsp;&nbsp;
											<a href="${pageContext.request.contextPath}/delOrderById?id=${order.orderid}"  onclick="javascript:return o_del()">刪除</a>
										</td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>