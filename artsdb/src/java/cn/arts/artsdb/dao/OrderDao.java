package cn.arts.artsdb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import cn.arts.artsdb.domain.Order;
import cn.arts.artsdb.domain.User;
import cn.arts.artsdb.utils.DataSourceUtils;
/**
 * 订单
 * @author admin
 *
 */
public class OrderDao {
	/**
	 *  生成订单
	 * @param order
	 * @throws SQLException
	 */
	public void addProduct(Order order) throws SQLException {
		// 1.生成Sql语句
		String sql = "insert into order values(?,?,?,?,?,0,null,?)";
		// 2.生成执行sql语句的QueryRunner,不传递参数
		QueryRunner runner = new QueryRunner();
        // 3.执行update()方法插入数据
		runner.update(DataSourceUtils.getConnection(), sql, order.getId(),
				order.getMoney(), order.getReceiverAddress(), order
						.getReceiverName(), order.getReceiverPhone(), order
						.getUser().getId());
	}
	/**
	 * 查找用户所属订单
	 */
	public List<Order> findOrderByUser(final User user) throws SQLException {
		String sql = "select * from order where userid=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ResultSetHandler<List<Order>>() {
			public List<Order> handle(ResultSet rs) throws SQLException {
				List<Order> orders = new ArrayList<Order>();
				while (rs.next()) {
					Order order = new Order();
					order.setId(rs.getString("order_id"));
					order.setMoney(rs.getDouble("order_price"));
					order.setOrdertime(rs.getDate("order_time"));
					order.setPaystate(rs.getInt("order_state"));
					order.setReceiverAddress(rs.getString("receiverAddress"));
					order.setReceiverName(rs.getString("receiverName"));
					order.setReceiverPhone(rs.getString("receiverTel"));
					order.setUser(user);
					orders.add(order);
				}
				return orders;
			}
		}, user.getId());
	}
	/**
	 * 根据id查找订单信息
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Order findOrderById(String id) throws SQLException {
		String sql = "select * from order,user where order.userid=user.user_id and order.order_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ResultSetHandler<Order>() {
			public Order handle(ResultSet rs) throws SQLException {
				Order order = new Order();
				while (rs.next()) {
					order.setId(rs.getString("order.order_id"));
					order.setMoney(rs.getDouble("order.order_price"));
					order.setOrdertime(rs.getDate("order.order_time"));
					order.setPaystate(rs.getInt("order.order_state"));
					order.setReceiverAddress(rs.getString("order.receiverAddress"));
					order.setReceiverName(rs.getString("order.receiverName"));
					order.setReceiverPhone(rs.getString("order.receiverTel"));

					User user = new User();
					user.setId(rs.getInt("user.user_id"));
					user.setEmail(rs.getString("user.email"));
					user.setGender(rs.getString("user.gender"));
					user.setActiveCode(rs.getString("user.activeCode"));
					user.setIntroduce(rs.getString("user.introduction"));
					user.setPassword(rs.getString("user.password"));
					user.setRegistTime(rs.getDate("user.registTime"));
					user.setRole(rs.getString("user.user_role"));
					user.setState(rs.getInt("user.user_state"));
					user.setTelephone(rs.getString("user.user_tel"));
					user.setUsername(rs.getString("user.user_name"));
					order.setUser(user);
				}
				return order;
			}
		}, id);
	}
	/**
	 *  查找所有订单
	 * @return
	 * @throws SQLException
	 */
	public List<Order> findAllOrder() throws SQLException {
		//1.创建sql
		String sql = "select order.*,user.* from order,user where user.user_id=order.userid order by order.userid";
		//2.创建QueryRunner对象
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        //3.返回QueryRunner对象query()方法的查询结果
		return runner.query(sql, new ResultSetHandler<List<Order>>() {			
			public List<Order> handle(ResultSet rs) throws SQLException {
				//创建订单集合
				List<Order> orders = new ArrayList<Order>();
                //循环遍历订单和用户信息
				while (rs.next()) {
					Order order = new Order();
					order.setId(rs.getString("order.order_id"));
					order.setMoney(rs.getDouble("order.order_price"));
					order.setOrdertime(rs.getDate("order.order_time"));
					order.setPaystate(rs.getInt("order.order_state"));
					order.setReceiverAddress(rs.getString("order.receiverAddress"));
					order.setReceiverName(rs.getString("order.receiverName"));
					order.setReceiverPhone(rs.getString("order.receiverTel"));
					orders.add(order);

					User user = new User();
					user.setId(rs.getInt("user.user_id"));
					user.setEmail(rs.getString("user.email"));
					user.setGender(rs.getString("user.gender"));
					user.setActiveCode(rs.getString("user.activeCode"));
					user.setIntroduce(rs.getString("user.introduction"));
					user.setPassword(rs.getString("user.password"));
					user.setRegistTime(rs.getDate("user.registTime"));
					user.setRole(rs.getString("user.user_role"));
					user.setState(rs.getInt("user.user_state"));
					user.setTelephone(rs.getString("user.user_tel"));
					user.setUsername(rs.getString("user.user_name"));
					order.setUser(user);
				}
				return orders;
			}
		});
	}
	/**
	 *  根据订单号修改订单状态
	 * @param id
	 * @throws SQLException
	 */
	public void updateOrderState(String id) throws SQLException {
		String sql = "update order set order_state=1 where order_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
		System.out.println(runner.update(sql, id));
	}
	/**
	 *  多条件查询
	 * @param id
	 * @param receiverName
	 * @return
	 * @throws SQLException
	 */
	public List<Order> findOrderByManyCondition(String id, String receiverName)
			throws SQLException {
		//1.创建集合对象
		List<Object> objs = new ArrayList<Object>();
		//2.定义查询sql
		String sql = "select order.*,user.* from order,user where user.user_id=order.userid ";
		//3.根据参数拼接sql语句
		if (id != null && id.trim().length() > 0) {
			sql += " and order.order_id=?";
			objs.add(id);
		}
		if (receiverName != null && receiverName.trim().length() > 0) {
			sql += " and receiverName=?";
			objs.add(receiverName);
		}
		sql += " order by order.userid";
		//4.创建QueryRunner对象
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		//5.返回QueryRunner对象query方法的执行结果
		return runner.query(sql, new ResultSetHandler<List<Order>>() {
			public List<Order> handle(ResultSet rs) throws SQLException {
				List<Order> orders = new ArrayList<Order>();
               //循环遍历出订单和用户信息
				while (rs.next()) {
					Order order = new Order();
					order.setId(rs.getString("order.order_id"));
					order.setMoney(rs.getDouble("order.order_price"));
					order.setOrdertime(rs.getDate("order.order_time"));
					order.setPaystate(rs.getInt("order.order_state"));
					order.setReceiverAddress(rs
							.getString("order.receiverAddress"));
					order.setReceiverName(rs.getString("order.receiverName"));
					order.setReceiverPhone(rs.getString("order.receiverTel"));
					orders.add(order);
					User user = new User();
					user.setId(rs.getInt("user.user_id"));
					user.setEmail(rs.getString("user.email"));
					user.setGender(rs.getString("user.gender"));
					user.setActiveCode(rs.getString("user.activeCode"));
					user.setIntroduce(rs.getString("user.introduction"));
					user.setPassword(rs.getString("user.password"));
					user.setRegistTime(rs.getDate("user.registTime"));
					user.setRole(rs.getString("user.user_role"));
					user.setState(rs.getInt("user.user_state"));
					user.setTelephone(rs.getString("user.user_tel"));
					user.setUsername(rs.getString("user.user_name"));
					order.setUser(user);

				}

				return orders;
			}
		}, objs.toArray());
	}
	/**
	 * 根据id删除订单
	 * @param id
	 * @throws SQLException
	 */
	public void delOrderById(String id) throws SQLException {
		String sql="delete from order where order_id=?";		
		QueryRunner runner = new QueryRunner();		
		runner.update(DataSourceUtils.getConnection(),sql,id);		
	}
}
