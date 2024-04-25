package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import config.MySQLConfig;
import entity.Project;

public class ProjectRepository {
	
	public int updateProject(int id, String name,Timestamp startDate, Timestamp endDate) {
		int count = 0;
		try {
			String query = "UPDATE project  SET name  = '" + name + "', start_date = '" + startDate + "', end_date ='"
					+ endDate +"\r\n" + "WHERE id =" + id;
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
		}catch (Exception e) {
			System.out.println("Loi update project " + e.getLocalizedMessage());
		}
		return count;
	}
	
	public List<Project> getAll() {
		List<Project> listProject = new ArrayList<Project>();
		try {
			String query = "SELECT * FROM project p";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Project project = new Project();
				project.setId(resultSet.getInt("id"));
				project.setName(resultSet.getString("name"));
				project.setStartDate(resultSet.getTimestamp("start_date"));
				project.setEndDate(resultSet.getTimestamp("end_date"));
				listProject.add(project);
			}
		} catch (Exception e) {
			System.out.println("Lỗi get danh sách project " + e.getLocalizedMessage());
		}
		return listProject;
	}

	public int save(String name, Timestamp startDate, Timestamp endDate) {
		int count = 0;
		try {
			String query = "INSERT \r\n" + "INTO project (name,start_date ,end_date)\r\n" + "VALUES('" + name + "','"
					+ startDate + "','" + endDate + "')";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi thêm project " + e.getLocalizedMessage());
		}
		return count;
	}
	
	public int deleteProjectById(int id) {
		int count = 0;
		
		try {
			String query = "DELETE \r\n"
					+ "FROM project u\r\n"
					+ "WHERE u.id = " + id ;
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi xóa Project " + e.getLocalizedMessage());
		}
		return count;
	}
	
	public Project getProjectById(int id) {
		Project project = new Project();
		try {
			String query ="SELECT * FROM project u  \r\n"
					+ "WHERE u.id ="+id;
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				project.setId(resultSet.getInt("id"));
				project.setName(resultSet.getString("name"));
				project.setStartDate(resultSet.getTimestamp("start_date"));
				project.setEndDate(resultSet.getTimestamp("end_date"));
			}
		} catch (Exception e) {
			System.out.println("Lỗi lấy project by id " + e.getLocalizedMessage());
		}
		return project;
	}
}
