package com.dav.shopping.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dav.shopping.entity.Permission;
import com.dav.shopping.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.dav.shopping.entity.Action;
import com.dav.shopping.entity.Role;
import com.dav.shopping.entity.User;
import com.dav.shopping.service.ActionService;
import com.dav.shopping.service.RoleService;
import com.dav.shopping.service.UserService;



public class JwtFilter extends GenericFilterBean {

	@Autowired
	private TokenHandler tokenHandler;

	@Autowired
	private UserService userService;

	@Autowired
	private ActionService actionService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PermissionService permissionService;

	private void createService(ServletRequest request) {
		ServletContext servletContext = request.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		if (userService == null) {
			userService = webApplicationContext.getBean(UserService.class);
		}

		if (tokenHandler == null) {
			tokenHandler = webApplicationContext.getBean(TokenHandler.class);
		}

		if (actionService == null) {
			actionService = webApplicationContext.getBean(ActionService.class);
		}
		if (roleService == null) {
			roleService = webApplicationContext.getBean(RoleService.class);
		}
		if (permissionService == null) {
			permissionService = webApplicationContext.getBean(PermissionService.class);
		}
	}
	
	@SuppressWarnings("unused")
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
			throws IOException {

		createService(req);
		
		try {
			final HttpServletRequest request = (HttpServletRequest) req;
			final HttpServletResponse response = (HttpServletResponse) res;
			final String authHeader = request.getHeader("authorization");

			String url = request.getRequestURI();

			if ("OPTIONS".equals(request.getMethod())) {
				response.setStatus(HttpServletResponse.SC_OK);

				chain.doFilter(req, res);

			} else {
				if (authHeader == null || !authHeader.startsWith("Bearer ")) {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Chưa đăng nhập");
					return;
				}
				
				final String token = authHeader.substring(7);
				String username = "";
				
				try {
					username = tokenHandler.parseUserFromToken(token);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				if (username.equals("")) {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Lỗi token, xin đăng nhập lại");
					return;
				}

				User user = userService.findByEmail(username);

				if (user == null) {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Không tìm thấy dữ liệu");
					return;
				}

				Action action = actionService.findByName(url);
				System.out.println(url);
				if (action == null) {
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
					response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy trang");
					return;
				} else {

					int countPermission = permissionService.countPermission(action.getName(),user.getId());

					if(countPermission!=0){
						response.setStatus(HttpServletResponse.SC_OK);
					}else {
						response.setStatus(HttpServletResponse.SC_FORBIDDEN);
						response.sendError(HttpServletResponse.SC_FORBIDDEN, "Không có quyền truy cập");
						return;
					}
				}
				chain.doFilter(req, res);

			}
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
