package cn.arts.cyyarts.service;

import java.sql.SQLException;
import javax.security.auth.login.LoginException;
import cn.arts.cyyarts.dao.UserDao;
import cn.arts.cyyarts.domain.User;
import java.util.List;
import cn.arts.cyyarts.exception.RegisterException;
import cn.arts.cyyarts.exception.ModifyUserException;
import cn.arts.cyyarts.exception.FindAllSalesmanException;

public class UserService {
	private UserDao dao = new UserDao();
	// 注册操作
	public void register(User user) throws RegisterException {
		// 调用dao完成注册操作
		try {
			dao.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RegisterException("注冊失败");
		}
	}
	// 登录操作
	public User login(String username, String password) throws LoginException {
		try {
			//根据登录时表单输入的用户名和密码，查找用户
			User user = dao.findUserByUsernameAndPassword(username, password);
			if (user != null) {
				return user;
			}
			throw new LoginException("用户名或密码错误");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LoginException("登录失败");
		}
	}
	//修改用户信息操作；
	public void modifyUser(User user) throws ModifyUserException {
		try {
			dao.modifyUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ModifyUserException("修改信息失败");
		}
	}
	//查找所有销售人员用户：
	public List<User> findAllSalesman() throws FindAllSalesmanException{
		try {
			List<User> users = dao.findAllSalesman();
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindAllSalesmanException("查找信息失败");
		}
	}
}