package cn.arts.cyyarts.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import cn.arts.cyyarts.domain.Orders;
import cn.arts.cyyarts.domain.User;
import cn.arts.cyyarts.utils.DataSourceUtils;

public class OrderDao {
	//生成订单
	public void addOrder(Orders order) throws SQLException {
		//1.生成sql语句；
		String sql = "insert into orders value(NULL,?,?,?,?,?,?,?,?,?,?,?)";
		//2.生成执行sql语句的QueryRunner，不传递参数
		QueryRunner runner = new QueryRunner();
		//3.执行update()方法插入数据；
		runner.update(DataSourceUtils.getConnection(), sql, 
				order.getUserid(),order.getSalemanid(),order.getOrderstatus(),
				order.getOrdertime(),order.getSalenum(),order.getTotalprice(),
				order.getArtsid(),order.getArtsname(),order.getReceivername(),
				order.getReceiveraddr(),order.getReceivertel());
	}
	
	//查找对应用户的订单；
	public List<Orders> findOrderByUser(final User user) throws SQLException {
		String sql = "select * from orders where userid = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ResultSetHandler<List<Orders>>() {
			public List<Orders> handle(ResultSet rs) throws SQLException {
				List<Orders> orders = new ArrayList<Orders>();
				while (rs.next()) {
					Orders order = new Orders();
					order.setOrderid(rs.getInt("orderid"));
					order.setUserid(rs.getInt("userid"));
					order.setSalemanid(rs.getInt("salemanid"));
					order.setOrderstatus(rs.getInt("orderstatus"));
					order.setOrdertime(rs.getDate("ordertime"));
					order.setSalenum(rs.getInt("salenum"));
					order.setTotalprice(rs.getInt("totalprice"));
					order.setArtsid(rs.getString("artsid"));
					order.setArtsname(rs.getString("artsname"));
					order.setReceivername(rs.getString("receivername"));
					order.setReceiveraddr(rs.getString("receiveraddr"));
					order.setReceivertel(rs.getString("receivertel"));
					orders.add(order);
				}
				return orders;
			}
		}, user.getUserid());
	}
	
	//根据id查找订单信息；
	public Orders findOrderById(String id) throws SQLException {
		String sql = "select * from orders,user where orders.userid = user.uid and orderid=?";
		long idd = Long.parseLong(id);
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ResultSetHandler<Orders>() {
			public Orders handle(ResultSet rs) throws SQLException {
				Orders order =  new Orders();
				while (rs.next()) {
					order.setOrderid(rs.getInt("orders.orderid"));
					order.setUserid(rs.getInt("orders.userid"));
					order.setSalemanid(rs.getInt("orders.salemanid"));
					order.setOrderstatus(rs.getInt("orders.orderstatus"));
					order.setOrdertime(rs.getDate("orders.ordertime"));
					order.setSalenum(rs.getInt("orders.salenum"));
					order.setTotalprice(rs.getInt("orders.totalprice"));
					order.setArtsid(rs.getString("orders.artsid"));
					order.setArtsname(rs.getString("orders.artsname"));
					order.setReceivername(rs.getString("orders.receivername"));
					order.setReceiveraddr(rs.getString("orders.receiveraddr"));
					order.setReceivertel(rs.getString("orders.receivertel"));
					
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
					order.setUser(user);
				}
				return order;
			}
		}, idd);
	}
	
	//查找所有订单；
	public List<Orders> findAllOrder() throws SQLException {
		//1.创建sql语句；
		String sql = "select orders.*,user.* from orders,user "
				+ "where user.uid=orders.userid order by orders.userid";
		//2.创建QueryRunner对象；
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		//3.返回quereyrunner对象query()方法的查询结果；
		return runner.query(sql, new ResultSetHandler<List<Orders>>() {
			public List<Orders> handle(ResultSet rs) throws SQLException {
				List<Orders> orders = new ArrayList<Orders>();
				while (rs.next()) {
					Orders order = new Orders();
					order.setOrderid(rs.getInt("orders.orderid"));
					order.setUserid(rs.getInt("orders.userid"));
					order.setSalemanid(rs.getInt("orders.salemanid"));
					order.setOrderstatus(rs.getInt("orders.orderstatus"));
					order.setOrdertime(rs.getDate("orders.ordertime"));
					order.setSalenum(rs.getInt("orders.salenum"));
					order.setTotalprice(rs.getInt("orders.totalprice"));
					order.setArtsid(rs.getString("orders.artsid"));
					order.setArtsname(rs.getString("orders.artsname"));
					order.setReceivername(rs.getString("orders.receivername"));
					order.setReceiveraddr(rs.getString("orders.receiveraddr"));
					order.setReceivertel(rs.getString("orders.receivertel"));
					orders.add(order);
					
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
					order.setUser(user);
				}
				return orders;
			}
		});
	}
	//根据订单号修改订单状态；0:未支付；1：已支付；2：已发货；3：订单完成；
	public void updateOrderState(String id,int status) throws SQLException {
		String sql = "update orders set orderstatus=? where orderid=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql,status,id);
		System.out.println(runner.update(sql,status,id));
	}
	//多条件查询；
	public List<Orders> findOrderByManyCondition(String id,String receiverName) throws SQLException {
		//1.创建集合对象；
		List<Object> objs = new ArrayList<Object>();
		//2.定义查询sql语句；
		String sql = "select orders.*,user.* from orders,users "
				+ "where user.uid=orders.userid";
		//3.根据参数拼接sql语句；
		if (id != null && id.trim().length() > 0) {
			sql += " and orders.orderid=?";
			objs.add(id);
		}
		if (receiverName != null && receiverName.trim().length() > 0) {
			sql += " and receivername=?";
			objs.add(receiverName);
		}
		sql += " order by orders.userid";
		//4.创建QueryRunner对象
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		//5.返回runner的query方法的执行结果；
		return runner.query(sql, new ResultSetHandler<List<Orders>>() {
			public List<Orders> handle(ResultSet rs) throws SQLException {
				List<Orders> orders = new ArrayList<Orders>();
				//6.循环遍历出订单和用户信息；
				while (rs.next()) {
					Orders order = new Orders();
					order.setOrderid(rs.getInt("orders.orderid"));
					order.setUserid(rs.getInt("orders.userid"));
					order.setSalemanid(rs.getInt("orders.salemanid"));
					order.setOrderstatus(rs.getInt("orders.orderstatus"));
					order.setOrdertime(rs.getDate("orders.ordertime"));
					order.setSalenum(rs.getInt("orders.salenum"));
					order.setTotalprice(rs.getInt("orders.totalprice"));
					order.setArtsid(rs.getString("orders.artsid"));
					order.setArtsname(rs.getString("orders.artsname"));
					order.setReceivername(rs.getString("orders.receivername"));
					order.setReceiveraddr(rs.getString("orders.receiveraddr"));
					order.setReceivertel(rs.getString("orders.receivertel"));
					orders.add(order);
					
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
					order.setUser(user);
				}
				return orders;
			}
		},objs.toArray());
	}
	//根据id删除订单；
	public void delOrderById(String id) throws SQLException {
		String sql = "delete from orders where orderid = ?";
		QueryRunner runner = new QueryRunner();
		runner.update(DataSourceUtils.getConnection(),sql,id);
	}
	//查找对应销售人员的订单；
	public List<Orders> findOrderBySalemanid(int id) throws SQLException {
		String sql = "select order.*,user.* from orders,user where salemanid = ? and orders.salemanid = user.uid";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ResultSetHandler<List<Orders>>() {
			public List<Orders> handle(ResultSet rs) throws SQLException {
				List<Orders> orders = new ArrayList<Orders>();
				while (rs.next()) {
					Orders order = new Orders();
					order.setOrderid(rs.getInt("orderid"));
					order.setUserid(rs.getInt("userid"));
					order.setSalemanid(rs.getInt("salemanid"));
					order.setOrderstatus(rs.getInt("orderstatus"));
					order.setOrdertime(rs.getDate("ordertime"));
					order.setSalenum(rs.getInt("salenum"));
					order.setTotalprice(rs.getInt("totalprice"));
					order.setArtsid(rs.getString("artsid"));
					order.setArtsname(rs.getString("artsname"));
					order.setReceivername(rs.getString("receivername"));
					order.setReceiveraddr(rs.getString("receiveraddr"));
					order.setReceivertel(rs.getString("receivertel"));
					
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
					order.setUser(user);
					
					orders.add(order);
				}
				return orders;
			}
		}, id);
	}
}