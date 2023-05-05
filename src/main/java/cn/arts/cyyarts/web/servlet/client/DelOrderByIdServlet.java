package cn.arts.cyyarts.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.arts.cyyarts.service.OrderService;

public class DelOrderByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DelOrderByIdServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		OrderService service = new OrderService();
		service.delOrderById(id);
		request.getRequestDispatcher("/findOrderByUser").forward(request, response);
		return;
	}

}
