package cn.arts.cyyarts.web.servlet.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.arts.cyyarts.service.ProductService;

/**
 *	前台页面展示的servlet 
 *	1、展示用户经常访问的商品(用户登录时)
 *  2、展示本周热销商品(用户未登录时)
 */
public class ShowIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShowIndexServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查询本周热销的两条商品，传递到index.jsp页面进行展示
		ProductService pService = new ProductService();
		List<Object[]> pList =  pService.getWeekHotProduct();
		request.setAttribute("pList", pList);
		//请求转发
		request.getRequestDispatcher("/client/index.jsp").forward(request, response);
	}

}
