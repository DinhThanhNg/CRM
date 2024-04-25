package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Status;

public class StatusRepository {
	public List<Status> getAll(){
		List<Status> listStatus = new ArrayList<Status>();
		try {
			String query = "SELECT * FROM status";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resulSet = statement.executeQuery();
			while (resulSet.next()) {
				Status status = new Status();
				status.setId(resulSet.getInt("id"));
				status.setName(resulSet.getString("name"));
				listStatus.add(status);
			}
		} catch (Exception e) {
			System.out.println("Lỗi lấy danh sách Status " + e.getLocalizedMessage());
		}
		return listStatus;
	}
}
