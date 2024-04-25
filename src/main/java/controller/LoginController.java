package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.MySQLConfig;
import entity.Role;
import entity.User;

@WebServlet(name = "loginController", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		Lấy toàn bộ cookie mà client truyền lên
		Cookie[] cookies = req.getCookies();
//	    resp.setContentType("text/jsp");
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
//				Lấy tên cookie duyệt được và lưu vào trong biến cookieName
				String cookieName = cookies[i].getName();
//				Lấy giá trị cookie duyệt được và lưu vào trong biến cookieValue
				String cookieValue = cookies[i].getValue();
				if (cookieName.equals("email")) {
					req.setAttribute("email", cookies[i].getValue());
				}
				if (cookieValue.equals("password")) {
					req.setAttribute("password", cookies[i].getValue());
				}
			}
		}
		
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		String path = req.getContextPath();
//		Chuẩn bị câu truy vấn kiểm tra email pasword có tồn tại trong CSDL hay không
		String query = "SELECT * \r\n" + "FROM users u \r\n" + "WHERE u.email= '" + email + "' AND u.password = '"
				+ password + "'";

//		Mở kết nối CSDL
		Connection connection = MySQLConfig.getConnection();
		// Truyền câu query đã chuẩn bị cho CSDL đã kết nối
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			/**
			 * 
			 * executeQuery: chỉ sử dụng khi câu truy vấn là câu SELECT 
			 * excuteUpdate: chỉ sử dụng khi câu truy vấn là câu UPDATE,CREATE, DELETE, ...
			 */
//			ResultSet : đại diện cho dữ liệu truy vấn được
			ResultSet resultSet = statement.executeQuery();
//			Tạo List user chưa có dữ liệu
			List<User> listUser = new ArrayList<User>();
//			Khi nào còn next được thì tạo dữ liệu và lưu vào list user
			while (resultSet.next()) {
				User user = new User();
//				Lấy giá trị của cột id và gán thuộc tính id của đối tượng user 
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setPhone(resultSet.getString("phone"));
				
				Role role = new Role();
				role.setId(resultSet.getInt("id_role"));
				
				user.setRole(role);
//				Thêm đối tượng user đã gán giá trị vào trong list user 
				listUser.add(user);

			}
			if (listUser.size() > 0) {
				resp.sendRedirect(path);
				System.out.println("Đăng nhập thành công.");
													//chuyển sang kiểu String
												// hoặc listUser.get(0).getIdRole() + ""
				Cookie ckRole = new Cookie("role",String.valueOf(listUser.get(0).getRole().getId()));
				resp.addCookie(ckRole);
//				Kiểm tra checkbox có tích chưa
				if (remember != null) {
//					Tạo cookie
					Cookie email1 = new Cookie("email", email);
					Cookie password1 = new Cookie("password", password);
//					Set thời gian tồn tại của cookie, tính bằng giây, nếu k set thì vĩnh viễn
					email1.setMaxAge(60 * 60 * 24);
					password1.setMaxAge(60 * 60 * 24);
//					Server yêu cầu client tạo ra 2 cookie và lưu phía client
					resp.addCookie(password1);
					resp.addCookie(email1);
//					resp.setContentType("text/jsp");
					
				}
			} else {
				System.out.println("Đăng nhập thất bại.");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
