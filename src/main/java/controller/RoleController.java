package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.RoleService;

@WebServlet(name="roleControler", urlPatterns = {"/add-role","/role"})
public class RoleController extends HttpServlet{
	private RoleService roleService = new RoleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		if(servletPath.equals("/add-role")) {
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
		}else if(servletPath.equals("/role")) {
			req.setAttribute("listRole", roleService.getAllRole());
			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		boolean isSuccess = roleService.insertRole(name, description);
		req.getRequestDispatcher("role-add.jsp").forward(req, resp);
	}
}
