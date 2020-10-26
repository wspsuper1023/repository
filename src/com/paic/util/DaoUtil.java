package com.paic.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import com.paic.constant.Constant;
import com.paic.constant.SourceConstant;
import com.paic.service.impl.DataSourceDaoImpl;

public class DaoUtil {
	
	private static DataSourceDaoImpl dataSourceDao = new DataSourceDaoImpl();
	
	private static String drivername;
	private static String url;
	private static String user;
	private static String password;
	
	public static DataSourceDaoImpl getDao() {
		return dataSourceDao;
	}
	
	static {
		try {
			Map<String, Object> dataSorceInfo = getDataSorceInfo();
			drivername = (String) dataSorceInfo.get(Constant.DRIVER_NAME);
			url = (String) dataSorceInfo.get(Constant.URL);
			user = (String) dataSorceInfo.get(Constant.USER);
			password = (String) dataSorceInfo.get(Constant.PASS_WORD);
			
			Class.forName(drivername);
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static PreparedStatement getPreparedStatement(String sql) {
		PreparedStatement pst = null;  
		Connection conn = null;  
		
		try {
			conn = getConnection();
			pst = conn.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return pst;
	}
	
	public static void release(Connection cnn, Statement st, PreparedStatement ps, ResultSet rs) {
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
		if (null != ps) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ps = null;	
			}
		}
		if (null != st) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				st = null;	//java GC
			}
		}
		if (null != cnn) {
			try {
				cnn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				cnn = null;
			}
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Map<String, Object> getDataSorceInfo() {
		Map<String, Object> result = new HashMap<String, Object>();
		
		Map<String, Object> properties = getProperties();
		if (SourceConstant.INIT_SOURCE.equals(Constant.MYSQL_SOURCE)) {
			result.put(Constant.URL, properties.get("mysqlurl"));
			result.put(Constant.DRIVER_NAME, properties.get("mysqldrivername"));
			result.put(Constant.USER, properties.get("mysqluser"));
			result.put(Constant.PASS_WORD, properties.get("mysqlpassword"));
		} else if(SourceConstant.INIT_SOURCE.equals(Constant.ORACLE_SOURCE)) {
			result.put(Constant.URL, properties.get("oracleurl"));
			result.put(Constant.DRIVER_NAME, properties.get("oracledrivername"));
			result.put(Constant.USER, properties.get("oracleuser"));
			result.put(Constant.PASS_WORD, properties.get("oraclepassword"));
		}
			
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	private static Map<String, Object> getProperties() {
		Map<String, Object> result = new HashMap<String, Object>();
		Properties prop = new Properties(); 
		InputStream in = null; 
		File file = null;
		FileInputStream fileInputStream = null;
		
		try {
			String filePath = getPath();
			file = new File(filePath);
			fileInputStream = new FileInputStream(file);
			in = new BufferedInputStream(fileInputStream); 
			prop.load(in);
			Enumeration en = prop.propertyNames();  
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement(); 
				String value = prop.getProperty(key);  
				result.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					in = null;
				}
			}
			if (null != fileInputStream) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					fileInputStream = null;
				}
			}
		}
		
		return result;
	}
	
	private static String getPath() {
		String path = DataSourceDaoImpl.class.getClassLoader().getResource("").toString();
		int m = path.indexOf("/");
		path = path.substring(m + 1) + "jdbc.properties";
		return path;
	}
}
