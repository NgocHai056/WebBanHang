package vn.iotstar.securityweb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import vn.iotstar.dao.UserDAO;
import vn.iotstar.model.UserModel;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	UserDAO dao;

	@Autowired
	HttpSession httpSession;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		// Lấy ra thông tin người dùng
		User user = (User) authentication.getPrincipal();
		UserModel acc = dao.checkAccount(user.getUsername(), user.getPassword());
		
		String redirectUrl = request.getContextPath(); // Lấy đường dẫn mà người dùng muốn truy cập

		// Kiểm tra quyền của từng user và thực hiện chuyển hướng theo vai trò của người dùng đó
		if (acc.getRole() == 4) {
			redirectUrl += "/admin";
		} else if (acc.getRole() == 3) {
			redirectUrl += "/admin-shipper";
		} else {
			redirectUrl += "/home";
		}
		response.sendRedirect(redirectUrl);
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
