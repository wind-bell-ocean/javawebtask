package cn.arts.cyyarts.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PayServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderid = request.getParameter("orderid");
		String money = request.getParameter("money");
		String bank = request.getParameter("yh");
		String status = "1";
		request.setAttribute("bank", bank);
		request.setAttribute("orderid", orderid);
		request.setAttribute("money", money);
		request.setAttribute("status", status);
		request.getRequestDispatcher("/client/confirm.jsp").forward(request, response);
	}

}
