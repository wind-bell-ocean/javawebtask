package cn.arts.cyyarts.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.arts.cyyarts.domain.Arts;
import cn.arts.cyyarts.exception.FindProductByIdException;
import cn.arts.cyyarts.service.ProductService;

public class FindProductByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FindProductByIdServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 得到商品的id
		String id = request.getParameter("id");
		// 获取type参数值，此处的type用于区别消费者和销售人员
		String type = request.getParameter("type");		
		ProductService service = new ProductService();		
		try {
			// 调用service层方法，通过id查找商品
			Arts p = service.findProductById(id);
			request.setAttribute("p", p);
			// 普通用户默认不传递type值，会跳转到info.jsp页面
			if (type == null) {
				request.getRequestDispatcher("/client/info.jsp").forward(request,response);
				return;
			}						
			request.getRequestDispatcher("/salesperson/products/edit.jsp").forward(request, response);
			return;
		} catch (FindProductByIdException e) {
			e.printStackTrace();
		}
	}

}
