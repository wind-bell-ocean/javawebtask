package cn.arts.cyyarts.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
	//创建一个c3p0连接池：但是这个连接池只需要创建一次即可
	//使用ComboPooledDataSource()构造方法读取c3p0-config.xml配置文件中的信息创建数据源对象，然后获取数据库连接对象；
	//构造方法中传入空值，使用默认配置；
	private static DataSource dataSource = new ComboPooledDataSource();
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	public static DataSource getDataSource() {
		return dataSource;
	}
	/**
	 * 当DBUtils需要手动控制事务时，调用该方法获得一个连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection con = tl.get();
		if (con == null) {
			con = dataSource.getConnection();
			tl.set(con);
		}
		return con;
	}
	/**
	 * 开启事务
	 * @throws SQLException
	 */
	public static void startTransaction() throws SQLException {
		Connection con = getConnection();
		if (con != null)
			con.setAutoCommit(false);
	}
	/**
	 * 从ThreadLocal中释放并且关闭Connection,并结束事务
	 * @throws SQLException
	 */
	public static void releaseAndCloseConnection() throws SQLException {
		Connection con = getConnection();
		if (con != null) {
			con.commit();
			tl.remove();
			con.close();
		}
	}
	/**
	 * 事务回滚
	 * @throws SQLException 
	 */
	public static void rollback() throws SQLException {
		Connection con = getConnection();
		if (con != null) {
			con.rollback();
		}
	}
}