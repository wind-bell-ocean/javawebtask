package cn.arts.cyyarts.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.security.auth.login.LoginException;
import cn.arts.cyyarts.domain.User;
import cn.arts.cyyarts.service.UserService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取登录页面的用户角色、用户名和密码；
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//2.调用service完成登录操作；
		UserService service = new UserService();
		try {
			User user = service.login(username, password);
			//3.登录成功，将用户储存到session中；
			request.getSession().setAttribute("user", user);
			//获取用户的角色，其中用户角色分为consumer、salesperson、arranger三种；
			String userrole = user.getUserrole();
			//若是consumer,进入系统前台首页；若是salesperson,进入到销售人员管理平台；若是arranger，进入到系统管理平台；
			if("consumer".equals(userrole)) {
				response.sendRedirect(request.getContextPath()+"/client/index.jsp");
				return;
			} else if ("salesperson".equals(userrole)) {
				response.sendRedirect(request.getContextPath()+"/salesperson/firstpage/home.jsp");
				return;
			} else {
				response.sendRedirect(request.getContextPath()+"/arranger/firstpage/home.jsp");
				return;
			}
		} catch (LoginException e) {
			// 如果出现问题，将错误信息存储到request范围，并跳转回登录页面显示错误信息
			e.printStackTrace();
			request.setAttribute("register_message", e.getMessage());
			request.getRequestDispatcher("/client/login.jsp").forward(request, response);
			return;
		}
	}

}
