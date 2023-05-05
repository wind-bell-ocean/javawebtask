package cn.arts.cyyarts.service;

import java.sql.SQLException;
import java.util.List;
import cn.arts.cyyarts.dao.OrderDao;
import cn.arts.cyyarts.dao.ArtsDao;
import cn.arts.cyyarts.domain.Orders;
import cn.arts.cyyarts.domain.User;
import cn.arts.cyyarts.utils.*;

public class OrderService {
	private OrderDao odao = new OrderDao();
	private ArtsDao adao = new ArtsDao();
	
	//1.添加订单；
	public void addOrder(Orders order) {
		try {
			//1.1开启事务；
			DataSourceUtils.startTransaction();
			//1.2向orders中添加数据；
			odao.addOrder(order);
			//1.3修改商品表中数据；
			adao.changeProductNum(order);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();  //事务回滚；
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				//关闭，释放以及提交事务；
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//2.根据用户查找订单；
	public List<Orders> findOrderByUser(User user) {
		List<Orders> orders = null;
		try {
			orders = odao.findOrderByUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	//3.根据id查找订单；
	public Orders findOrderById(String id) {
		Orders order = null;
		try {
			order = odao.findOrderById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}
	//4.查找所有订单；
	public List<Orders> findAllOrder() {
		List<Orders> orders = null;
		try {
			//查找订单信息；
			orders = odao.findAllOrder();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	//5.修改订单状态；
	public void updateState(String id,int status) {
		try {
			odao.updateOrderState(id, status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//6.多条件查询订单信息；
	public List<Orders> findOrderByManyCondition(String id, String receiverName) {
		List<Orders> orders = null;
		try {
			orders = odao.findOrderByManyCondition(id, receiverName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	//7.根据id删除已支付的订单；
	public void delOrderById(String id) {
		try {
			DataSourceUtils.startTransaction();
			Orders order = odao.findOrderById(id);
			adao.updateProductNum(order);
			odao.delOrderById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//根据销售人员id查找订单：
	public List<Orders> findOrderBySalemanid(int id) {
		List<Orders> orders = null;
		try {
			orders = odao.findOrderBySalemanid(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
}
