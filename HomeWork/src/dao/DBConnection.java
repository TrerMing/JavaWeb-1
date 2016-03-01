package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DBConnection {
//		//数据库驱动
//		private static final String driver = "com.mysql.jdbc.Driver";
//		//URL地址
//		private static final String url = "jdbc:mysql://localhost:3306/homework";
//		//数据库用户名
//		private static final String user = "root";
//		//数据库密码
//		private static final String password ="xulinsong";
	
		private static String driver;
		private static String url;
		private static String user;
		private static String password;
		//数据库连接对象
//		private static Connection conn = null;
		private Statement st = null;
		private ResultSet rs = null;
		//静态代码块加载驱动
//		static{
//			try {
//				Class.forName(driver);
//				conn = DriverManager.getConnection(url, user, password);
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		static{
			loadConfig();
		}
		
		public static void loadConfig(){
			
			try {
				InputStream inStream = DBConnection.class.getResourceAsStream("/jdbc.properties");
				Properties prop = new Properties();
				prop.load(inStream);
				user = prop.getProperty("jdbc.username");
				password = prop.getProperty("jdbc.password");
				driver = prop.getProperty("jdbc.driver");
				url = prop.getProperty("jdbc.url");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("读取数据库文件异常",e);
			}
		}
		
		//返回数据库链接对象
		public static Connection getConnection(){
			try {
				Class.forName(driver);
				Connection conn = DriverManager.getConnection(url, user, password);
				if(conn == null){
					conn = DriverManager.getConnection(url, user, password);
					return conn;
				}
				return conn;
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return null;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return null;
			}
			
		}
		//关闭
		public static void close(Connection conn, Statement st, ResultSet rs){
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(st != null){
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
}
