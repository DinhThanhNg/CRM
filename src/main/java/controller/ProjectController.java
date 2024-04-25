package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Project;
import service.AssignTaskService;
import service.ProjectService;
import service.TaskService;

@WebServlet(name = "projectController", urlPatterns = { "/add-project","/project"})
public class ProjectController extends HttpServlet {
	private ProjectService projectService = new ProjectService();
	private AssignTaskService assignTaskService = new AssignTaskService();
	private TaskService taskService = new TaskService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		if(servletPath.equals("/add-project")) {
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
		}else if(servletPath.equals("/project")) {
			String action = req.getParameter("action");
			if(action == null) {
				List<Project> listProject = projectService.getAll();
				req.setAttribute("listProject", listProject);
				req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
			}else if(action.equals("delete")) {
				int id = Integer.parseInt(req.getParameter("id"));
				projectService.deleteProjectById(id);
				resp.sendRedirect("project");
			}else if(action.equals("edit")) {
				int id = Integer.parseInt(req.getParameter("id"));
				Project project = projectService.getProjectById(id);
				req.setAttribute("project", project);
				req.getRequestDispatcher("groupwork-edit.jsp").forward(req, resp);
			}
			
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		if(id == null) {
			try {
				java.util.Date startDate = dateFormat.parse(req.getParameter("datestart")); 
				java.util.Date endDate = dateFormat.parse(req.getParameter("dateend")); 
				Timestamp  dateStart = new Timestamp(startDate.getTime());
				Timestamp dateEnd = new Timestamp(endDate.getTime());
				boolean isSucess1 = projectService.insertProject(name,dateStart, dateEnd);
			} catch (Exception e) {
				System.out.println("Lỗi thgian  project " + e.getLocalizedMessage());
			}
			
		}else {
			try {
				java.util.Date startDate = dateFormat.parse(req.getParameter("datestart")); 
				java.util.Date endDate = dateFormat.parse(req.getParameter("dateend")); 
				Timestamp  dateStart = new Timestamp(startDate.getTime());
				Timestamp dateEnd = new Timestamp(endDate.getTime());
				int idProject = Integer.parseInt(req.getParameter("id"));
				boolean isSuccess = projectService.updateProject(idProject, name, dateStart, dateEnd);
			} catch (Exception e) {
				System.out.println("Lỗi thgian  project " + e.getLocalizedMessage());
			}
		}
		resp.sendRedirect("project");

	}
}
