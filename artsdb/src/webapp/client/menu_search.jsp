<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
/**
 * my_click和my_blur均是用于前台页面搜索框的函数
 */
//鼠标点击搜索框时执行
function my_click(obj, myid){
	//点击时，如果取得的值和搜索框默认value值相同，则将搜索框清空
	if (document.getElementById(myid).value == document.getElementById(myid).defaultValue){
	  document.getElementById(myid).value = '';
	  obj.style.color='#000';
	}
}
//鼠标不聚焦在搜索框时执行
function my_blur(obj, myid){
	//鼠标失焦时，如果搜索框没有输入值，则用搜索框的默认value值填充
	if (document.getElementById(myid).value == ''){
	 document.getElementById(myid).value = document.getElementById(myid).defaultValue;
	 obj.style.color='#999';
 }
}

/**
 * 点击搜索按钮执行的函数
 */
function search(){
	document.getElementById("searchform").submit();
}
</script>
<div id="divmenu">
		<a href="${pageContext.request.contextPath}/showProductByPage?category=木雕">木雕</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=膏雕">膏雕</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=牙雕">牙雕</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=竹雕">竹雕</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=炭雕">炭雕</a>
		<a href="${pageContext.request.contextPath}/showProductByPage?category=玉雕">玉雕</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=彩雕">彩雕</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=树脂">树脂</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=文玩核桃">文玩核桃</a>
		<a href="${pageContext.request.contextPath}/showProductByPage?category=唐三彩">唐三彩</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=陶瓷">陶瓷</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=剪纸">剪纸</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage?category=景泰蓝">景泰蓝</a>
		<a href="${pageContext.request.contextPath}/showProductByPage?category=石湾公仔">石湾公仔</a> 
		<a href="${pageContext.request.contextPath}/showProductByPage" style="color:#b4d76d">全部商品</a>		
</div>
<div id="divsearch">
<form action="${pageContext.request.contextPath }/MenuSearchServlet" id="searchform">
	<table width="100%" border="0" cellspacing="0">
		<tr>
			<td style="text-align:right; padding-right:220px"> 
				<input type="text" name="textfield" class="inputtable" id="textfield" value="请输入工艺品名"
				onmouseover="this.focus();"
				onclick="my_click(this, 'textfield');"
				onBlur="my_blur(this, 'textfield');"/> 
				<a href="#">
					<img src="${pageContext.request.contextPath}/client/images/serchbutton.gif" border="0" style="margin-bottom:-4px" onclick="search()"/> 
				</a>
			</td>
		</tr>
	</table>
</form>
</div>