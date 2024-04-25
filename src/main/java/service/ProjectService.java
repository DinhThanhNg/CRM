package service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import entity.Project;
import repository.AssignTaskRepository;
import repository.ProjectRepository;

public class ProjectService {
	private ProjectRepository projectRepository = new ProjectRepository();

	public List<Project> getAll() {
		return projectRepository.getAll();
	}

	public boolean insertProject(String name, Timestamp dateStart, Timestamp dateEnd) {
		return projectRepository.save(name, dateStart, dateEnd) > 0;
	}

	public boolean deleteProjectById(int id) {
		return projectRepository.deleteProjectById(id) > 0;
	}

	public Project getProjectById(int id) {
		return projectRepository.getProjectById(id);
	}

	public boolean updateProject(int id, String name, Timestamp startDate, Timestamp endDate) {
		return projectRepository.updateProject(id, name, startDate, endDate) > 0;
	}
}
