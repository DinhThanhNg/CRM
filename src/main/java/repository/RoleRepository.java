package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Role;

// Quản lý tất cả các câu truy vấn liên quan tới bảng Role
public class RoleRepository {
	public List<Role> getAll() {
		List<Role> listRole = new ArrayList<Role>();
		try {
			String query = "SELECT * FROM roles";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));

				listRole.add(role);
			}
		} catch (Exception e) {
			System.out.println("Lỗi truy role " + e.getLocalizedMessage());
		}
		return listRole;
	}
	public int save(String name, String description) {
		int count = 0;
		try {
			String query = "INSERT \r\n"
					+ "INTO roles(name,description)\r\n"
					+ "VALUES('" +name+"', '"+ description+ "')";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Lỗi thêm role" +e.getLocalizedMessage());
		}
		return count;
	}
}
