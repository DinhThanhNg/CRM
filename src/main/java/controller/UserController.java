package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.parser.Cookie;

import config.MySQLConfig;
import entity.Role;
import entity.User;
import service.AssignTaskService;
import service.UserService;

@WebServlet(name="userController",urlPatterns = {"/add-user","/users","/edit-user"})
public class UserController extends HttpServlet{
	private UserService userService = new UserService();
	private AssignTaskService assignTaskService = new AssignTaskService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		
		if(servletPath.equals("/add-user")) {
			req.setAttribute("listRole", userService.getAllRole());
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
			
		}else if (servletPath.equals("/users")){
			
			String action = req.getParameter("action");
			if(action == null) {
				List<User> list = userService.getAllUser();
				req.setAttribute("listUser", list);
				req.getRequestDispatcher("user-table.jsp").forward(req, resp);
			}
			else if(action.equals("delete")) {
				int id = Integer.parseInt(req.getParameter("id"));
				userService.deleteUserById(id);
				assignTaskService.deleteAssignTaskByIdUser(id);
				resp.sendRedirect("users");
			}else if(action.equals("edit")) {
				int id = Integer.parseInt(req.getParameter("id"));
//				System.out.println("kiem tra " + id);
	            User user = userService.getUserById(id);
	            req.setAttribute("user", user);
	            req.setAttribute("listRole", userService.getAllRole());
	            req.getRequestDispatcher("user-edit.jsp").forward(req, resp);
			}
		} 
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		String id = req.getParameter("id");
		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String phone = req.getParameter("phone");
		int idRole = Integer.parseInt(req.getParameter("idRole")) ;
		System.out.println("kiem tra " + id);
		
		if(id == null) {
			boolean isSuccess = userService.insertUser(fullname, email, password, phone, idRole);
		}else {
			int idUser = Integer.parseInt(req.getParameter("id"));
			userService.updateUser(idUser, email, password, fullname, phone, idRole);
		}
		resp.sendRedirect("users");
	}
	
}
	 