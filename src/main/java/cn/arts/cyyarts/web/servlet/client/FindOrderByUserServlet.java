package cn.arts.cyyarts.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import cn.arts.cyyarts.domain.Orders;
import cn.arts.cyyarts.domain.User;
import cn.arts.cyyarts.service.OrderService;

public class FindOrderByUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FindOrderByUserServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查找当前用户的订单信息；
		User user = (User) request.getSession().getAttribute("user");
		OrderService service = new OrderService();
		List<Orders> orders = service.findOrderByUser(user);
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/client/orderlist.jsp").forward(request, response);
		}

}
