package com.aotain.common.utils.hadoop;

import com.aotain.common.config.LocalConfig;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hadoop工具类
 */
public class HadoopUtils {

	private static Logger logger = LoggerFactory.getLogger(HadoopUtils.class);
	private static DataSource dataSource;
	private static DataSource hiveDataSource;
	private static synchronized DataSource getImpalaDataSource() {
		if(dataSource!=null){
			return dataSource;
		}
		dataSource = ImpalaHouder.getDataSource();
		return dataSource;
	}
	private static synchronized DataSource getHiveDataSource(){
		if(hiveDataSource!=null){
			return hiveDataSource;
		}
		hiveDataSource = HiveHouder.getDataSource();
		return hiveDataSource;
	}

	private static class ImpalaHouder {
		private static BasicDataSource getDataSource(){
			BasicDataSource source = new BasicDataSource();
			source.setDriverClassName(LocalConfig.getInstance().getHashValueByHashKey("hadoop.driver"));
			source.setUrl(LocalConfig.getInstance().getHashValueByHashKey("hadoop.url"));
			source.setUsername(LocalConfig.getInstance().getHashValueByHashKey("hadoop.username"));
			source.setPassword(LocalConfig.getInstance().getHashValueByHashKey("hadoop.password"));
			source.setInitialSize(Integer.parseInt(LocalConfig.getInstance().getHashValueByHashKey("hadoop.initialSize")));
			source.setMinIdle(Integer.parseInt(LocalConfig.getInstance().getHashValueByHashKey("hadoop.minIdle")));
			source.setMaxActive(Integer.parseInt(LocalConfig.getInstance().getHashValueByHashKey("hadoop.maxActive")));
			source.setMaxWait(120000);
			source.setRemoveAbandonedTimeout(180);
			source.setRemoveAbandoned(true);
			source.setTestWhileIdle(true);
			source.setTestOnBorrow(true);
			source.setPoolPreparedStatements(true);
			return source;
		}

	}
	private static class HiveHouder {
		private static BasicDataSource getDataSource(){
			BasicDataSource source = new BasicDataSource();
			source.setDriverClassName(LocalConfig.getInstance().getHashValueByHashKey("hadoop.driver"));
			source.setUrl(LocalConfig.getInstance().getHashValueByHashKey("hadoop.url.hive"));
			source.setUsername(LocalConfig.getInstance().getHashValueByHashKey("hadoop.username"));
			source.setPassword(LocalConfig.getInstance().getHashValueByHashKey("hadoop.password"));
			source.setInitialSize(Integer.parseInt(LocalConfig.getInstance().getHashValueByHashKey("hadoop.initialSize")));
			source.setMinIdle(Integer.parseInt(LocalConfig.getInstance().getHashValueByHashKey("hadoop.minIdle")));
			source.setMaxActive(Integer.parseInt(LocalConfig.getInstance().getHashValueByHashKey("hadoop.maxActive")));
			source.setMaxWait(180000);
			source.setRemoveAbandonedTimeout(180);
			source.setRemoveAbandoned(true);
			source.setTestWhileIdle(true);
			source.setTestOnBorrow(true);
			source.setPoolPreparedStatements(true);
			return source;
		}

	}

	public static void resetDatasource(){
		dataSource=null;
	}

	/**
	 * 获取Connection实例
	 * @return Connection对象
	 */
	public static Connection getConnection(){
		Connection conn = null;
		try {
			DataSource dataSource = getImpalaDataSource();
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			logger.error("",e);
			resetDatasource();
		}
		return conn;
	}

	/**
	 * 获取Connection实例
	 * @return Connection对象
	 */
	public static Connection getHiveConnection(){
		Connection conn = null;
		try {
			DataSource dataSource = getHiveDataSource();
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			logger.error("",e);
			resetDatasource();
		}
		return conn;
	}
	
	/**
	 * 关闭资源
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public static void close(Connection conn, Statement st, ResultSet rs) {
		try {
			if (rs != null) rs.close();
			if (st != null) st.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			logger.error("",e);
		}
	}
	
	/**
	 * 关闭资源
	 * @param st
	 * @param rs
	 */
	public static void close(Statement st, ResultSet rs) {
		try {
			if (rs != null) rs.close();
			if (st != null) st.close();
		} catch (SQLException e) {
			logger.error("",e);
		}
	}
	
	/**
	 * 关闭资源
	 * @param st
	 */
	public static void close(Statement st) {
		try {
			if (st != null) st.close();
		} catch (SQLException e) {
			logger.error("",e);
		}
	}
	
	/**
	 * 关闭资源
	 * @param rs
	 */
	public static void close(ResultSet rs) {
		try {
			if (rs != null) rs.close();
		} catch (SQLException e) {
			logger.error("",e);
		}
	}
	
	/**
	 * 关闭资源
	 * @param conn
	 * @param rs
	 */
	public static void close(Connection conn, ResultSet rs) {
		try {
			if (rs != null) rs.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			logger.error("",e);
		}
	}
	
	/**
	 * 关闭资源
	 * @param conn
	 */
	public static void close(Connection conn) {
		try {
			if (conn != null) conn.close();
		} catch (SQLException e) {
			logger.error("",e);
		}
	}
	
	/**
	 * 刷新表
	 * @param conn Connection对象
	 * @param tableName 表名
	 */
	public static void refresh(Connection conn, String tableName){
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("refresh " + tableName);
			ps.execute();
		} catch (SQLException e) {
			logger.error("",e);
		}finally{
			HadoopUtils.close(ps);
		}
	}
	
	/**
	 * 获取结果集
	 * @param conn Connection对象
	 * @param sql 查询语句
	 * @return
	 */
	public static ResultSet executeQuery(Connection conn, String sql){
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			logger.error("",e);
		}finally {
			HadoopUtils.close(ps, rs);
		}
		return rs;
	}
	
	/**
	 * 获取总记录数
	 * @param fromSql sql语句
	 * @param conn Connection对象
	 * @return
	 */
	public static int count(String fromSql, Connection conn){
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			ps = conn.prepareStatement("select count(1) " + fromSql);
			System.out.println("select count(1) " + fromSql);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {

			logger.error("",e);
		}finally{
			HadoopUtils.close(ps, rs);
		}
		return count;
	}

	public static int groupCount(String fromSql, Connection conn){
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			ps = conn.prepareStatement("SELECT COUNT(1) FROM (SELECT  COUNT(1) " + fromSql + ")t");
			rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			logger.error("",e);
		}finally{
			HadoopUtils.close(ps, rs);
		}
		return count;
	}
	
	public static <T> List executeQueryReturnObj(Connection conn, String sql,Class<T> clazz){
		List<T> tList = new ArrayList<T>();
        T t = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
	            t = clazz.newInstance();
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int count = rsmd.getColumnCount();
	            for (int i = 1; i <= count; i++) {
	                String name = rsmd.getColumnName(i);
	                Field field = clazz.getDeclaredField(name);
	                field.setAccessible(true);
	                field.set(t, rs.getObject(name));
	            }
	            tList.add(t);
	        }
		} catch (SQLException e) {
			logger.error("",e);
		} catch (NoSuchFieldException e) {
			logger.error("",e);
		} catch (SecurityException e) {
			logger.error("",e);
		} catch (IllegalArgumentException e) {
			logger.error("",e);
		} catch (IllegalAccessException e) {
			logger.error("",e);
		} catch (InstantiationException e) {
			logger.error("",e);
		}finally {
			HadoopUtils.close(ps, rs);
		}
		return tList;
	}
	
	public static long getCount(Connection conn,String fromSql){
		PreparedStatement ps = null;
		ResultSet rs = null;
		long count = 0;
		try {
			ps = conn.prepareStatement("SELECT COUNT(1) FROM ( " + fromSql +" ) t");
			rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			logger.error("",e);
		}finally{
			HadoopUtils.close(ps, rs);
		}
		return count;
	}


	/**
	 * 获取总记录数
	 * @param countSql sql语句
	 * @return
	 */
	public static int count(String countSql){
		try{
			String sql = "select count(1) from (" + countSql +") a";
			logger.info("countSql="+sql);
			Connection connection = HadoopUtils.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery(sql);
			int total = 0;
			while (rs.next()){
				total = rs.getInt(1);
			}
			return total;
		} catch (Exception e){
			return 0;
		}

	}
}
