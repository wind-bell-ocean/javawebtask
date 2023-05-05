package cn.arts.cyyarts.web.servlet.client;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.arts.cyyarts.domain.Arts;

public class ChangeCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ChangeCartServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.得到商品ID
		String id = request.getParameter("id");
		//2.得到要修改的数量;
		int count = Integer.parseInt(request.getParameter("count"));
		//3.从session中获取购物车对象；
		HttpSession session = request.getSession();
		Map<Arts,Integer> cart = (Map<Arts, Integer>) session.getAttribute("cart");
		Arts p = new Arts();
		p.setArtsid(id);
		if (count != 0) {
			cart.put(p, count);
		} else {
			cart.remove(p);
		}
		response.sendRedirect(request.getContextPath()+"client/cart.jsp");
		return;
	}

}
