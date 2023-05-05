package cn.arts.cyyarts.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.arts.cyyarts.domain.User;
import cn.arts.cyyarts.service.UserService;
import cn.arts.cyyarts.exception.ModifyUserException;

public class ModifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ModifyUserServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String username = request.getParameter("username");
		String sex = request.getParameter("sex");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String signature = request.getParameter("signature");
		user.setUsername(username);
		user.setEmail(email);
		user.setSex(sex);
		user.setSignature(signature);
		user.setTelephone(telephone);
		UserService service = new UserService();
		try {
			service.modifyUser(user);
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("/MyAccount").forward(request, response);
		} catch (ModifyUserException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath()+"/client/index.jsp");
		}
	}

}
