package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.MySQLConfig;

public class AssignTaskRepository {
	public int save(int idUser, int idTask, int idStatus) {
		int count = 0;
		try {
			String query = "INSERT \r\n" + "INTO assigntask (id_user,id_task,id_status)\r\n" + "VALUES(" + idUser + ","
					+ idTask + "," + idStatus + ")";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi thêm assigntask " + e.getLocalizedMessage());
		}
		return count;
	}
	public int deleteAssignTaskByIdTask(int idTask) {
		int count = 0;
		try {
			String query = "DELETE\r\n"
					+ "FROM assigntask a \r\n"
					+ "WHERE  a.id_task ="+idTask;
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
		} catch (Exception e){
			System.out.println("Xóa assignTask thất bại " + e.getLocalizedMessage());
		}
		return count;
	}
	public int deleteAssignTaskByIdUser(int idUser) {
		int count = 0;
		try {
			String query = "DELETE \r\n"
					+ "FROM assigntask a WHERE a.id_user="+idUser;
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
		} catch (Exception e){
			System.out.println("Xóa assignTask theo idUser thất bại " + e.getLocalizedMessage());
		}
		return count;
	}
	
}
