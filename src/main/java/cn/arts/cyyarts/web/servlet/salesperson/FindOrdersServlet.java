package cn.arts.cyyarts.web.servlet.salesperson;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.arts.cyyarts.domain.Orders;
import cn.arts.cyyarts.domain.User;
import cn.arts.cyyarts.service.OrderService;

public class FindOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FindOrdersServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.得到当前用户；
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int salemanid = user.getUserid();
		// 创建Service层对象
		OrderService service = new OrderService();
		// 调用Service层对象的findAllOrder()方法查询订单列表
		List<Orders> orders = service.findOrderBySalemanid(salemanid);
		//将查询到的订单信息添加到request作用域
		request.setAttribute("orders", orders);
		// 将请求转发到list.jsp页面
		request.getRequestDispatcher("/salesperson/orders/list.jsp").forward(request,response);
	}

}
