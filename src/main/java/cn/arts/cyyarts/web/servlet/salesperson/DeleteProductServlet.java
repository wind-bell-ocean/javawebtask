package cn.arts.cyyarts.web.servlet.salesperson;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import cn.arts.cyyarts.domain.Arts;
import cn.arts.cyyarts.domain.User;
import cn.arts.cyyarts.exception.FindProductByIdException;
import cn.arts.cyyarts.service.ProductService;

public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteProductServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求参数，产品id
		String id = request.getParameter("id");
		ProductService service = new ProductService();
		// 调用service完成删除商品操作
		//1.得到当前用户；
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		try {
			// 调用service层方法，通过id查找商品
			Arts p = service.findProductById(id);
			int sid = p.getSalemanid();
			int uid = user.getUserid();
			if (sid != uid) {
				JOptionPane.showMessageDialog(null, "您没有权限删除该商品","权限错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			service.deleteProduct(id);
			response.sendRedirect(request.getContextPath() + "/listProduct");
		} catch (FindProductByIdException e) {
			e.printStackTrace();
		}
		return;
	}

}
