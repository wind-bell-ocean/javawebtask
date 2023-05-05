package cn.arts.cyyarts.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.arts.cyyarts.domain.Orders;
import cn.arts.cyyarts.exception.FindProductByIdException;
import cn.arts.cyyarts.domain.Arts;
import cn.arts.cyyarts.service.OrderService;
import cn.arts.cyyarts.service.ProductService;

public class FindOrderByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FindOrderByIdServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取用户类型；
		String type = request.getParameter("type");
		//2.得到要查询的订单号；
		String id = request.getParameter("id");
		//根据id查找订单；
		OrderService service = new OrderService();
		Orders order = service.findOrderById(id);
		String artsid = order.getArtsid();
		ProductService pservice = new ProductService();
		try {
			Arts art = pservice.findProductById(artsid);
			request.setAttribute("arts", art);
			//4.将查询得到的订单信息添加到request作用域中；
			request.setAttribute("order", order);
			//5.如果用户类型不为null，则将请求转发到view.jsp页面，否则转发到orderinfo.jsp页面；
			if (type != null) {
				request.getRequestDispatcher("/salesperson/order/view.jsp").forward(request, response);
				return;
			}
			request.getRequestDispatcher("/client/orderInfo.jsp").forward(request, response);
		} catch (FindProductByIdException e) {
			//如果出错，跳转回首页
			e.printStackTrace();
			request.getRequestDispatcher("/client/index.jsp").forward(request, response);
			return;
		}
	}

}
