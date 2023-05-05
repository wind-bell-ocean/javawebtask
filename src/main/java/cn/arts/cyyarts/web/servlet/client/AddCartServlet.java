package cn.arts.cyyarts.web.servlet.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.arts.cyyarts.domain.Arts;
import cn.arts.cyyarts.exception.FindProductByIdException;
import cn.arts.cyyarts.service.ProductService;

public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddCartServlet() {
        super();
        }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.得到商品id
		String id = request.getParameter("id");
		// 2.调用service层方法，根据id查找商品
		ProductService service = new ProductService();
		try {
			Arts p = service.findProductById(id);
			//3.将商品添加到购物车；
			//3.1获取session对象；
			HttpSession session = request.getSession();
			//3.2从session中获取购物车对象；
			Map<Arts, Integer> cart = (Map<Arts, Integer>)session.getAttribute("cart");
			//3.3若购物车是空的，说明没有商品存储在购物车中，创建购物车；
			if (cart == null) {
				cart = new HashMap<Arts,Integer>();
			}
			//3.4向购物车中添加商品
			Integer count = cart.put(p, 1);
			//3.5若商品数量不为空，则商品数量加一，否则添加新的商品；
			if (count != null) {
				cart.put(p, count+1);
			}
			session.setAttribute("cart", cart);
			response.sendRedirect(request.getContextPath()+"/client/cart.jsp");
			return;
		} catch (FindProductByIdException e) {
			e.printStackTrace();
		}
	}

}
