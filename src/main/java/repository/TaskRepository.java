package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Project;
import entity.Status;
import entity.Task;
import entity.User;

public class TaskRepository {
	public int save(String name, Timestamp startDay, Timestamp endDay, int idProject, int idStatus) {
		int count = 0;
		try {
			String query = "INSERT \r\n" + "INTO task (name,start_date,end_date,id_project,id_status)\r\n" + "VALUES('"
					+ name + "','" + startDay + "','" + endDay + "'," + idProject + "," + idStatus + ")";

			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi thêm task " + e.getLocalizedMessage());
		}
		return count;
	}

	public int getIdAssignTaskAdded() {
		int id = 0;
		try {
			String query = "SELECT * FROM task ORDER BY id DESC LIMIT 1";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getInt("id");
			}
		} catch (Exception e) {
			System.out.println("Lỗi get id Task " + e.getLocalizedMessage());
		}
		return id;
	}

	public List<Task> getAll() {
		List<Task> listTask = new ArrayList<Task>();
		try {
			String query = "SELECT r.id,r.name,u.fullname,r.start_date ,r.end_date ,p.name as name_project, s.name as name_status,u.id as id_user, p.id as id_project, s.id as id_status\r\n"
					+ "	FROM task r\r\n"
					+ "	JOIN project p ON r.id_project = p.id \r\n"
					+ "	JOIN status s ON r.id_status =s.id\r\n"
					+ "	JOIN assigntask a ON r.id =a.id_task \r\n"
					+ "	JOIN users u ON u.id =a.id_user";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Task task = new Task();
				task.setId(resultSet.getInt("id"));
				task.setName(resultSet.getString("name"));
				task.setStartDate(resultSet.getTimestamp("start_date"));
				task.setEndDate(resultSet.getTimestamp("end_date"));

				User user = new User();
				user.setId(resultSet.getInt("id_user"));
				user.setFullname(resultSet.getString("fullname"));
				task.setUser(user);
				
				Project project = new Project();
				project.setId(resultSet.getInt("id_project"));
				project.setName(resultSet.getString("name_project"));
				task.setProject(project);

				Status status = new Status();
				status.setId(resultSet.getInt("id_status"));
				status.setName(resultSet.getString("name_status"));
				task.setStatus(status);
				listTask.add(task);
			}
		} catch (Exception e) {
			System.out.println("Lỗi lấy danh sách Task " + e.getLocalizedMessage());
		}
		return listTask;
	}

	public int deleteTaskById(int id) {
		int count = 0;

		try {
			String query = "DELETE \r\n" + "FROM task u\r\n" + "WHERE u.id = " + id;
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi xóa Task " + e.getLocalizedMessage());
		}
		return count;
	}
	
	public int deleteTaskByIdProject(int id) {
		int count = 0;
		try {
			String query = "DELETE \r\n" + "FROM task u\r\n" + "WHERE u.id_project = " + id;
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi xóa Task by idProject " + e.getLocalizedMessage());
		}
		return count;
	}
	

	public ArrayList<Integer> getListIdTaskByIdProject(int id) {
		ArrayList<Integer> list = new ArrayList<>();
		try {
			String query = "SELECT * FROM task u  \r\n"
					+ "WHERE u.id_project  ="+id;
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				list.add(resultSet.getInt("id"));
			}
		} catch (Exception e) {
			System.out.println("Lỗi get id Task " + e.getLocalizedMessage());
		}
		return list;
	}
}
