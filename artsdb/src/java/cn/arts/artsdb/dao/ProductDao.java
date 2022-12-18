package cn.arts.artsdb.dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
//import com.sun.faces.taglib.jsf_core.MaxMinValidatorTag;
import cn.arts.artsdb.domain.Order;
import cn.arts.artsdb.domain.OrderItem;
import cn.arts.artsdb.domain.Product;
import cn.arts.artsdb.utils.DataSourceUtils;

public class ProductDao {
	// 添加商品
	public void addProduct(Product p) throws SQLException {

		String sql = "insert into arts values(?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, p.getId(), p.getName(), p.getPrice(),
				p.getCategory(), p.getPnum(), p.getImgurl(), p.getDescription());
	}
	// 查找所有商品
	public List<Product> listAll() throws SQLException {
		String sql = "select * from arts";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class));
	}
	// 获取数据总条数
	public int findAllCount(String category) throws SQLException {
		String sql = "select count(*) from arts";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		if (!"全部商品".equals(category)) {
			sql += " where category=?";

			Long count = (Long) runner
					.query(sql, new ScalarHandler(), category);
			return count.intValue();
		} else {
			Long count = (Long) runner.query(sql, new ScalarHandler());

			return count.intValue();
		}
	}
	// 获取当前页数据
	public List<Product> findByPage(int currentPage, int currentCount,
			String category) throws SQLException {
		// 要执行的sql语句
		String sql = null;
		// 参数
		Object[] obj = null;
		// 如果category不为null,代表是按分类查找
		if (!"全部商品".equals(category)) {
			sql = "select * from arts  where category=? limit ?,?";
			obj = new Object[] { category, (currentPage - 1) * currentCount,
					currentCount, };
		} else {
			sql = "select * from arts  limit ?,?";
			obj = new Object[] { (currentPage - 1) * currentCount,
					currentCount, };
		}
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Product>(Product.class),
				obj);
	}

	// 根据id查找商品
	public Product findProductById(String id) throws SQLException {
		String sql = "select * from arts where arts_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<Product>(Product.class), id);
	}

	// 生成订单时，将商品数量减少
	public void changeProductNum(Order order) throws SQLException {
		String sql = "update arts set storenum=storenum-? where arts_id=?";
		QueryRunner runner = new QueryRunner();
		List<OrderItem> items = order.getOrderItems();
		Object[][] params = new Object[items.size()][2];

		for (int i = 0; i < params.length; i++) {
			params[i][0] = items.get(i).getBuynum();
			params[i][1] = items.get(i).getP().getId();
		}

		runner.batch(DataSourceUtils.getConnection(), sql, params);
	}

	// 销售榜单
	public List<Object[]> salesList(String year, String month)
			throws SQLException {
		String sql = "SELECT arts.arts_name,SUM(orderitem.buynum) totalsalnum FROM order,arts,orderitem WHERE order.order_id=orderitem.orderid AND arts.arts_id=orderitem.artsid AND order.order_state=1 and year(order_time)=? and month(order_time)=? GROUP BY arts.arts_name ORDER BY totalsalnum DESC";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler(), year, month);
	}

	// 多条件查询
	public List<Product> findProductByManyCondition(String id, String name,
			String category, String minprice, String maxprice)
			throws SQLException {
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from arts where 1=1 ";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		if (id != null && id.trim().length() > 0) {
			sql += " and arts_id=?";
			list.add(id);
		}

		if (name != null && name.trim().length() > 0) {
			sql += " and arts_name=?";
			list.add(name);
		}
		if (category != null && category.trim().length() > 0) {
			sql += " and category=?";
			list.add(category);
		}
		if (minprice != null && maxprice != null
				&& minprice.trim().length() > 0 && maxprice.trim().length() > 0) {
			sql += " and arts_price between ? and ?";
			list.add(minprice);
			list.add(maxprice);
		}

		Object[] params = list.toArray();

		return runner.query(sql, new BeanListHandler<Product>(Product.class),
				params);
	}
	// 修改商品信息
	public void editProduct(Product p) throws SQLException {
		//1.创建集合并将商品信息添加到集合中
		List<Object> obj = new ArrayList<Object>();
		obj.add(p.getName());
		obj.add(p.getPrice());
		obj.add(p.getCategory());
		obj.add(p.getPnum());
		obj.add(p.getDescription());
		//2.创建sql语句，并拼接sql
		String sql  = "update arts " +
				      "set  arts_name=?,arts_price=?,category=?,storenum=?,description=? ";
		//判断是否有图片
		if (p.getImgurl() != null && p.getImgurl().trim().length() > 0) {
			sql += " ,imgurl=?";
			obj.add(p.getImgurl());
		}
		sql += " where arts_id=?";
		obj.add(p.getId());		
		System.out.println(sql);		
		System.out.println(obj);
		//3.创建QueryRunner对象
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		//4.使用QueryRunner对象的update()方法更新数据
		runner.update(sql, obj.toArray());
	}
	//删除订单时，修改商品数量
	public void updateProductNum(List<OrderItem> items) throws SQLException {
		
		String sql = "update arts set storenum=storenum+? where arts_id=?";
		QueryRunner runner = new QueryRunner();
		
		Object[][] params = new Object[items.size()][2];

		for (int i = 0; i < params.length; i++) {
			params[i][0] = items.get(i).getBuynum();
			params[i][1] = items.get(i).getP().getId();
		}

		runner.batch(DataSourceUtils.getConnection(), sql, params);
	}

	//前台，获取本周热销商品
	public List<Object[]> getWeekHotProduct() throws SQLException {
		String sql = "SELECT arts.arts_id,arts.arts_name, "+
                             " arts.imgurl,SUM(orderitem.buynum) totalsalnum "+
                     " FROM orderitem,order,arts "+
                     " WHERE orderitem.orderid = order.order_id "+
                             " AND arts.arts_id = orderitem.artsid "+
                             " AND order.order_state=1 "+
                             " AND order.order_time > DATE_SUB(NOW(), INTERVAL 7 DAY) "+
                     " GROUP BY arts.arts_id,arts.arts_name,arts.imgurl "+
                     " ORDER BY totalsalnum DESC "+
                     " LIMIT 0,2 ";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler());
	}

	//前台，用于搜索框根据书名来模糊查询相应的图书
	public List<Product> findBookByName(int currentPage, int currentCount,
			String searchfield) throws SQLException {
		//根据名字模糊查询图书
		String sql = "SELECT * FROM arts WHERE arts_name LIKE '%"+searchfield+"%' LIMIT ?,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//		//用于分页查询的数据
//		Object obj = new Object[] { (currentPage - 1) * currentCount, currentCount };
		return runner.query(sql, 
				new BeanListHandler<Product>(Product.class),currentPage-1,currentCount);
	}

	//前台搜索框，根据书名模糊查询出的图书总数量
	public int findBookByNameAllCount(String searchfield) throws SQLException {
		String sql = "SELECT COUNT(*) FROM arts WHERE arts_name LIKE '%"+searchfield+"%'";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		//查询出满足条件的总数量，为long类型
		Long count = (Long)runner.query(sql, new ScalarHandler());
		return count.intValue();
	}

	//后台系统，根据id删除商品信息
	public void deleteProduct(String id) throws SQLException {
		String sql = "DELETE FROM arts WHERE arts_id = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
	}
}
