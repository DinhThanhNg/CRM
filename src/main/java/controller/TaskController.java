package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import repository.AssignTaskRepository;
import service.AssignTaskService;
import service.ProjectService;
import service.TaskService;
import service.UserService;

@WebServlet(name="taskController",urlPatterns = {"/add-task","/task"})
public class TaskController extends HttpServlet{
	private TaskService taskService =  new TaskService();
	private ProjectService projectService = new ProjectService();
	private UserService userService = new UserService();
	private AssignTaskService assignTaskService = new AssignTaskService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		if(servletPath.equals("/task")) {
			req.setAttribute("listTask", taskService.getAll());
			req.getRequestDispatcher("task.jsp").forward(req, resp);
		}else if(servletPath.equals("/add-task")){
			List<User> a = userService.getAllUser();
			req.setAttribute("listProject", projectService.getAll());
			req.setAttribute("listUser", userService.getAllUser());
//			System.out.println("kiem tra list size " + a.size());
			req.getRequestDispatcher("task-add.jsp").forward(req, resp);
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		int idUser = Integer.parseInt(req.getParameter("idUser"));
		int idProject = Integer.parseInt(req.getParameter("idProject"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			java.util.Date startDate = dateFormat.parse(req.getParameter("startdate")); 
			java.util.Date endDate = dateFormat.parse(req.getParameter("enddate")); 
			Timestamp  dateStart = new Timestamp(startDate.getTime());
			Timestamp dateEnd = new Timestamp(endDate.getTime());
			boolean isSuccessTask = taskService.insertTask(name, dateStart, dateEnd, idProject, 2);
//			System.out.println("kiem tra thoi gian task " + dateStart);
		} catch (Exception e) {
			System.out.println("Lỗi thời gian Task " +e.getLocalizedMessage());
		}
		boolean isSuccessAssigntask = assignTaskService.insertAssignTask(idUser, idProject);
		req.getRequestDispatcher("task-add.jsp").forward(req, resp);
	}
}
