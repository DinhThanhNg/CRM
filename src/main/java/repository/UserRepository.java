package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Role;
import entity.User;

public class UserRepository {

	public int updateUserById(int id, String email, String password, String fullname, String phone, int idRole) {
		int count = 0;
		try {
			String query = "UPDATE users  SET email  = '" + email + "', password = '" + password + "', fullname ='"
					+ fullname + "',phone ='" + phone + "', id_role=" + idRole + "\r\n" + "WHERE id =" + id;
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi update user " + e.getLocalizedMessage());
		}
		return count;
	}

	public int deleteUserById(int id) {
		int count = 0;

		try {
			String query = "DELETE \r\n" + "FROM users u\r\n" + "WHERE u.id = " + id;
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi xóa User " + e.getLocalizedMessage());
		}
		return count;
	}

	public List<User> getAlUser() {
		List<User> listUser = new ArrayList<User>();
		try {
			String query = "SELECT u.id ,u.first_name ,u.last_name ,u.email, u.fullname ,r.name as role_name \r\n"
					+ "FROM users u \r\n" + "JOIN roles r ON u.id_role = r.id";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setFullname(resultSet.getString("fullname"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));

				Role role = new Role();
				role.setName(resultSet.getString("role_name"));

				user.setRole(role);

				listUser.add(user);
			}
		} catch (Exception e) {
			System.out.println("Lỗi lấy danh sách User." + e.getLocalizedMessage());
		}
		return listUser;
	}

	public int save(String fullname, String email, String password, String phone, int idRole) {
		int count = 0;
		try {
			String query = "INSERT INTO users(fullname,email,password,phone,id_role)\r\n" + "VALUES ('" + fullname
					+ "','" + email + "','" + password + "','" + phone + "'," + idRole + ")";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi insert user " + e.getLocalizedMessage());
		}

		return count;
	}

	public User getUserById(int id) {
		User user = new User();
		try {
			String query = "SELECT * FROM users u  \r\n" + "WHERE u.id =" + id;
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setFullname(resultSet.getString("fullname"));
				user.setPhone(resultSet.getString("phone"));
				user.setPassword(resultSet.getString("password"));
				Role role = new Role();
				role.setId(resultSet.getInt("id_role"));
				user.setRole(role);
			}
		} catch (Exception e) {
			System.out.println("Loi tim user by id " + e.getLocalizedMessage());
		}
		return user;
	}
}
