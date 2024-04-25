package service;

import java.util.ArrayList;

import repository.AssignTaskRepository;
import repository.TaskRepository;

public class AssignTaskService {
	private AssignTaskRepository assignTaskRepository = new AssignTaskRepository();
	private TaskRepository taskRepository = new TaskRepository();

	public boolean insertAssignTask(int idUser, int idStatus) {
		return assignTaskRepository.save(idUser, taskRepository.getIdAssignTaskAdded(), idStatus) > 0;
	}

	public boolean deleteAssignTaskByIdTask(int idTask) {
		return assignTaskRepository.deleteAssignTaskByIdTask(idTask) > 0;
	}
	

	public boolean deleteAssignTaskByIdUser(int idUser) {
		return assignTaskRepository.deleteAssignTaskByIdUser(idUser) > 0;
	}

	
}
