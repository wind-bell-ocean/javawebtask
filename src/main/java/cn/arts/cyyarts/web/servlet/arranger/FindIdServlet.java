package cn.arts.cyyarts.web.servlet.arranger;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.arts.cyyarts.exception.FindAllSalesmanException;
import cn.arts.cyyarts.service.UserService;
import cn.arts.cyyarts.domain.User;
import java.util.List;

public class FindIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FindIdServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService service = new UserService();
		try {
			List<User> users = service.findAllSalesman();
			request.setAttribute("users", users);
			request.getRequestDispatcher("/arranger/person/list.jsp").forward(request, response);
			return;
		} catch (FindAllSalesmanException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/arranger/firstpage/home.jsp").forward(request, response);
			return;
		}
		
	}

}
