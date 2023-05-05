package cn.arts.cyyarts.web.servlet.salesperson;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.arts.cyyarts.domain.Orders;
import cn.arts.cyyarts.service.OrderService;

public class FindOrderByManyConditionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FindOrderByManyConditionServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取订单编号和收件人名称
		String id = request.getParameter("id");
		String receiverName = request.getParameter("receiverName");
		//创建Service层对象
		OrderService service = new OrderService();
		//调用Service层OrderService类的findOrderByManyCondition()方法查询数据
		List<Orders> orders = service.findOrderByManyCondition(id, receiverName);
		//将查询结果添加到request作用域中
		request.setAttribute("orders", orders);
		//请求转发到list.jsp页面，并将request请求和response响应也转发到该页面中
		request.getRequestDispatcher("/salesperson/order/list.jsp").forward(request,
				response);
	}

}
