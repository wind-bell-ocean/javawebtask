package cn.arts.cyyarts.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.arts.cyyarts.service.OrderService;

public class ChangeOrderStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ChangeOrderStateServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderid = request.getParameter("orderid");
		String orderstatus = request.getParameter("status");
		String paySuccess = "恭喜支付成功！";
		String oprationSuccess = "发货成功！";
		if (null != orderid) {
			OrderService service = new OrderService();
			try {
				if ("1".equals(orderstatus)) {
					service.updateState(orderid, 1);
					request.setAttribute("paySuccess", paySuccess);
					request.getRequestDispatcher("/findOrderByUser").forward(request, response);
				} else if ("2".equals(orderstatus)) {
					service.updateState(orderid, 2);
					request.setAttribute("oprationSuccess", oprationSuccess);
					request.getRequestDispatcher("/salesperson/order/view.jsp").forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write("修改订单状态失败");
			}
		}
	}

}
