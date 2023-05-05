package cn.arts.cyyarts.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.arts.cyyarts.domain.PageBean;
import cn.arts.cyyarts.service.ProductService;

public class MenuSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MenuSearchServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 1;
		String _currentPage = request.getParameter("currentPage");
		if (_currentPage != null) {
			currentPage = Integer.parseInt(_currentPage);
		}
		// 2.定义每页显示条数,默认为4
		int currentCount = 4;	
		//获取前台页面搜索框输入的值
		String searchfield = request.getParameter("textfield");
		//如果搜索框中没有输入值，则表单传递的为默认值，此时默认查询全部商品目录
		if("请输入书名".equals(searchfield)){
			request.getRequestDispatcher("/showProductByPage").forward(request, response);
			return;
		}
		//调用service层的方法，通过商品名模糊查询，查找相应的商品；
		ProductService service = new ProductService();
		PageBean bean = service.findArtsByName(currentPage, currentCount, searchfield);
		request.setAttribute("bean", bean);
		request.getRequestDispatcher("/client/product_search_list.jsp").forward(request, response);
	}

}
