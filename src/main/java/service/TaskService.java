package service;

import java.sql.Timestamp;
import java.util.List;

import entity.Project;
import entity.Task;
import repository.ProjectRepository;
import repository.TaskRepository;

public class TaskService {
	private TaskRepository taskRepository = new TaskRepository();
	private ProjectRepository projectRepository = new ProjectRepository();

	public boolean insertTask(String name, Timestamp startDate, Timestamp endDate, int idProject, int idStatus) {
		return taskRepository.save(name, startDate, endDate, idProject, idStatus) > 0;
	}

	public List<Task> getAll() {
		return taskRepository.getAll();
	}

	public boolean deleteTaskById(int id) {
		return taskRepository.deleteTaskById(id) > 0;
	}

	public boolean deleteTaskByIdProject(int id) {
		return taskRepository.deleteTaskByIdProject(id) > 0;
	}
}
