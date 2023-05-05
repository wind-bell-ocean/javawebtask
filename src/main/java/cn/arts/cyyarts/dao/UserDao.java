package cn.arts.cyyarts.dao;

import cn.arts.cyyarts.domain.Orders;
import cn.arts.cyyarts.domain.User;
import cn.arts.cyyarts.utils.DataSourceUtils;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.ResultSetHandler;
import java.util.List;

public class UserDao {
	
	//添加用户；
	public void addUser(User user) throws SQLException {
		String sql = "insert into user(user_name,password,userrole,sex,telephone,email,signature,enrolltime) values(?,?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, user.getUsername(), user.getPassword(),
				user.getUserrole(),user.getsex(),user.getTelephone(),
				user.getEmail(),user.getSignature(),user.getEnrollTime());
		if (row == 0) {
			throw new RuntimeException();
		}
	}
	
	//根据用户名与密码查找用户
	public User findUserByUsernameAndPassword(String username, String password) throws SQLException {
		String sql="select * from user where user_name=? and password=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class),username,password);
	}
	
	//修改用户信息；
	public void modifyUser(User user)throws SQLException {
		String sql = "update user set username=?,sex=?,telephone=?,email=?,signature=? where uid=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql,user.getUsername(),user.getsex(),
				user.getTelephone(),user.getEmail(),user.getSignature(),
				user.getUserid());
		if (row == 0) {
			throw new RuntimeException();
		}
	}
	//查找所有销售人员用户：
	public List<User> findAllSalesman() throws SQLException{
		String sql = "select * from user where userrole='salesperson'";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ResultSetHandler<List<User>>() {
			public List<User> handle(ResultSet rs) throws SQLException {
				List<User> users = new ArrayList<User>();
				while (rs.next()) {
					User user = new User();
					user.setUserid(rs.getInt("user.uid"));
					user.setUsername(rs.getString("user.user_name"));
					user.setPassword(rs.getString("user.password"));
					user.setUserrole(rs.getString("user.userrole"));
					user.setSex(rs.getString("user.sex"));
					user.setTelephone(rs.getString("user.telephone"));
					user.setEmail(rs.getString("user.email"));
					user.setSignature(rs.getString("user.signature"));
					user.setEnrollTime(rs.getDate("user.enrolltime"));
					
					users.add(user);
				}
				return users;
			}
		});
	}
}