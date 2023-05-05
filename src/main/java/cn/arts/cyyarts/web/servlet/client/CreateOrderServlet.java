package cn.arts.cyyarts.web.servlet.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import cn.arts.cyyarts.domain.Orders;
import cn.arts.cyyarts.domain.Arts;
import cn.arts.cyyarts.domain.User;
import cn.arts.cyyarts.service.OrderService;
import java.util.Date;

public class CreateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateOrderServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.得到当前用户；
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		OrderService service = new OrderService();
		//2.从购物车中获取商品信息；
		Map<Arts, Integer> cart = (Map<Arts, Integer>)session.getAttribute("cart");
		//3.将数据封装到订单对象中；
		Orders order = new Orders();
		try {
			BeanUtils.populate(order, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		Date date = new Date();
		for (Arts a :cart.keySet()) {
			order.setArtsid(a.getArtsid());
			order.setArtsname(a.getArtsname());
			order.setSalemanid(a.getSalemanid());
			order.setUserid(user.getUserid());
			order.setOrderstatus(0);
			order.setOrdertime(date);
			order.setSalenum(cart.get(a));
			
			System.out.println(order);
			//4.调用service向表中添加订单；
			service.addOrder(order);
		}
		response.sendRedirect(request.getContextPath() + "/client/createOrderSuccess.jsp");
	}

}
