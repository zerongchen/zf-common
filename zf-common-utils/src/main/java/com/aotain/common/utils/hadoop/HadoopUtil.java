package com.aotain.common.utils.hadoop;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class HadoopUtil {
	
	private static Logger logger = LoggerFactory.getLogger(HadoopUtil.class);
	
	public static Connection getConnection(String houseIdStr) {
		return getConnection(houseIdStr, false);
	}

	public static Connection getConnection(String houseIdStr, boolean hiveConnection) {
		String driver = "org.apache.hive.jdbc.HiveDriver", url = "jdbc:hive2://192.168.50.201:21050/zf;auth=noSasl", username = "", password = "";
		Connection conn = null;
		try {
			/*IdcHouses idcHouse = LocalConfig.getInstance().getIdcHouse(houseIdStr);
			int clusterId = idcHouse.getClusterId();
			ConnectionInformation info = LocalConfig.getInstance().getConnectionInformation(clusterId + "");
			
			if (hiveConnection) {
				driver = info.getHiveDriver();
				url = info.getHiveUrl();
				username = info.getUserName();
				password = info.getPassword();
			} else {
				driver = info.getImpalaDriver();
				url = info.getImpalaUrl();
				username = info.getUserName();
				password = info.getPassword();
			}*/
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			logger.error("The driver class is not found!", e);
		} catch (SQLException e) {
			logger.error("The SQLException:", e);
		}
		return conn;
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("Close connection failed!", e);
			}
		}
	}

	public static void closeConnection(Connection conn, Statement st, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			logger.error("Close connection failed!", e);
		}
	}

	public static void closeConnection(Connection conn, Statement st) {
		try {
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			logger.error("Close connection failed!", e);
		}
	}

	public static void closeStatement(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (Exception e) {
			logger.error("Close connection failed!", e);
		}
	}
	
	public <T> List<T> setMetaData(ResultSet rs, Class<T> clazz) throws Exception {
        List<T> tList = new ArrayList<T>();
        T t = null;
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
        return tList;
    }
}
