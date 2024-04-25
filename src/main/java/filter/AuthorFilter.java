package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enumdata.RoleName;
										//Chỉ đường dẫn đăng ký mới kích hoạt filter
										//Các đường dẫn sẽ kích hoạt filter
@WebFilter(filterName = "authorFilter",urlPatterns = {"/add-user","/add-project","/add-task","/add-role"})
public class AuthorFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
//		ép kiểu từ class cha ServletRequest => HttpServletRequest
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
		Cookie[] cookies =httpRequest.getCookies();
		
		String roleUser="";
		for (int i = 0; i < cookies.length; i++) {
			String name = cookies[i].getName();
			String value = cookies[i].getValue();
			if (name.equals("role")) {
				roleUser = value;
//				đạt được mục đích không duyệt mảng tiếp nữa
				break;
			}
		}
		
		//		Lấy đường dẫn servlet mà client gọi
		String path = httpRequest.getServletPath();
		
		if (roleUser.equals(RoleName.ADMIN.getName())) {
			if (path.equals("/add-user") || path.equals("/add-project") || path.equals("/add-task") || path.equals("/add-role")) {
				chain.doFilter(request, response);
			}else {
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.html");
			}
		} else if(roleUser.equals(RoleName.LEAD.getName())){
			if (path.equals("/add-project") || path.equals("/add-task")) {
				chain.doFilter(request, response);
			}else {
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.html");
			}
		}
		
	}
}
