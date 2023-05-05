package cn.arts.cyyarts.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import cn.arts.cyyarts.domain.Orders;
import cn.arts.cyyarts.domain.Arts;
import cn.arts.cyyarts.utils.DataSourceUtils;

public class ArtsDao {
	
	//添加商品；
	public void addProduct(Arts a) throws SQLException {
		String sql = "insert into arts value(?,?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, a.getArtsname(), a.getCategory(),a.getDescription(),a.getImgurl(),a.getPrice(),a.getStorenum(),a.getSalemanid(),a.getArtsid());
	}
	//查找所有商品
	public List<Arts> listAll() throws SQLException {
		String sql = "select * from arts";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Arts>(Arts.class));
	}
	//获取查找数据总条数；
	public int findAllCount(String category) throws SQLException {
		String sql = "select count(*) from arts";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		if (!"全部商品".equals(category)) {
			sql += " where category=?";
			Long count = (Long) runner.query(sql, new ScalarHandler(), category);
			return count.intValue();
		} else {
			Long count = (Long) runner.query(sql, new ScalarHandler());

			return count.intValue();
		}
	}
	//获取当前页数据；
	public List<Arts> findByPage(int currentPage, int currentCount,
			String category) throws SQLException {
		//要执行的sql语句
		String sql = null;
		//参数
		Object[] obj = null;
		//若category不是null，代表是按分类查找；
		if (!"全部商品".equals(category)) {
			sql = "select * from arts where category=? limit ?,?";
			obj = new Object[] {category,(currentPage-1) * currentCount, 
					currentCount,};
		} else {
			sql = "select * from arts limit ?,?";
			obj = new Object[] { (currentPage-1)*currentCount, currentCount, };
		}
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Arts>(Arts.class),obj);
	}
	//根据id查找商品；
	public Arts findProductById(String id) throws SQLException {
		String sql = "select * from arts where arts_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<Arts>(Arts.class), id);
	}
	//生成订单时，将商品数量减少；
	public void changeProductNum(Orders order) throws SQLException {
		String sql = "update arts set storenum = storenum-? where arts_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, order.getSalenum(), order.getArtsid());
	}
	//销售榜单
	public List<Object[]> saleList(String year, String month) throws SQLException {
		String sql = "select arts.arts_id,SUM(orders.salenum) totalsalenum from orders,arts "
				+ "where orders.artsid = arts.arts_id and year(ordertime)=? and month(ordertime)=? "
				+ "GROUP BY arts.arts_id ORDER BY totalsalenum DESC";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler(), year, month);
	}
	//多条件查询
	public List<Arts> findProductByManyCondition(String id, String name, 
			String category, String minprice, String maxprice) 
					throws SQLException {
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from arts where 1=1";
		
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

		return runner.query(sql, new BeanListHandler<Arts>(Arts.class), params);
	}
	//修改商品信息
	public void editProduct(Arts a) throws SQLException {
		//1.创建集合并将商品信息添加到集合中
		List<Object> obj = new ArrayList<Object>();
		obj.add(a.getArtsname());
		obj.add(a.getPrice());
		obj.add(a.getCategory());
		obj.add(a.getStorenum());
		obj.add(a.getDescription());
		obj.add(a.getSalemanid());
		//2.创建sql语句，拼接语句；
		String sql = "update arts" + "set arts_name=?,arts_price=?,category=?,storenum=?,description=?,salemanid=?";
		//判断是否有图片
		if (a.getImgurl() != null && a.getImgurl().trim().length()>0) {
			sql += ",arts_picture=?";
			obj.add(a.getImgurl());
		}
		sql += " where arts_id=?";
		obj.add(a.getArtsid());
		System.out.println(sql);		
		System.out.println(obj);
		//3.创建QueryRunner对象
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		//4.使用QueryRunner对象的update()方法更新数据
		runner.update(sql, obj.toArray());
	}
	//删除订单时，修改商品数量
    public void updateProductNum(Orders order) throws SQLException {
    	String sql = "update arts set storenum = storenum+? where arts_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, order.getSalenum(), order.getArtsid());
    }
    //前台，获取本周热销商品
    public List<Object[]> getWeekHotProduct() throws SQLException {
    	String sql = "select arts.arts_id, arts.arts_name,arts.arts_picture"
    			+ "SUM(orders.salenum) totalsalenum from orders,arts "
    			+ "where orders.artsid = arts.arts_id and "
    			+ "orders.ordertime > DATE_SUB(NOW(), INTERVAL 7 DAY) "
    			+ "group by arts.arts_id,arts.arts_name,arts.arts_picture "
    			+ "order by totalsalenum DESC limit 0,2";
    	QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler());
    }
    //前台，用于搜索框中根据工艺品名来模糊查询相应的图书
    public List<Arts> findArtsByName(int currentPage, int currentCount,
    		String searchfield) throws SQLException {
    	//根据名字模糊查询图书
    	String sql = "SELECT * FROM arts WHERE arts_name LIKE '%"+searchfield+"%' LIMIT ?,?";
    	QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
    	//用于分页查询的数据
    	return runner.query(sql, 
    			new BeanListHandler<Arts>(Arts.class),currentPage-1,currentCount);
    }
  //前台搜索框，根据书名模糊查询出的图书总数量
  	public int findBookByNameAllCount(String searchfield) throws SQLException {
  		String sql = "SELECT COUNT(*) FROM arts WHERE arts_name LIKE '%"+searchfield+"%'";
  		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
  		//查询出满足条件的总数量，为long类型
  		Long count = (Long)runner.query(sql, new ScalarHandler());
  		return count.intValue();
  	}
  //销售人员后台系统，根据id删除商品信息
  	public void deleteProduct(String id) throws SQLException {
  		String sql = "DELETE FROM arts WHERE arts_id = ?";
  		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
  		runner.update(sql, id);
  	}
}