package cn.arts.cyyarts.web.servlet.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import cn.arts.cyyarts.domain.User;
import cn.arts.cyyarts.exception.RegisterException;
import cn.arts.cyyarts.service.UserService;
import java.util.Date;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//将表单提交的数据封装到javaBean中；
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			user.setUserrole("consumer");
			Date date = new Date();
			user.setEnrollTime(date);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		// 调用service完成注册操作。
		UserService service = new UserService();
		try {
			service.register(user);
		} catch (RegisterException e) {
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
			return;
		}
		// 注册成功，跳转到registersuccess.jsp
		response.sendRedirect(request.getContextPath() + "/client/registersuccess.jsp");
		return;
	}

}
