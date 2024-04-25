package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConfig {
//	Tạo ra hàm mở kết nối cơ cở dữ liệu
	public static Connection getConnection() {
		Connection connection = null;
		try {
			String url = "jdbc:mysql://localhost:3307/crmapp";
			String username = "root";
			String password ="admin123";
//			Khai báo driver sử dụng là MYSQL
			Class.forName("com.mysql.cj.jdbc.Driver");
//			Khei baos thoong tin đường dẫn MYSQL Server sẽ kết nối tới
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println("Lỗi kết nối CSDL " + e.getMessage());
		}
		return connection;
	}
	
}
