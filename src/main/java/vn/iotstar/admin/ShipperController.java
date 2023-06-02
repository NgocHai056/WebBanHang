package vn.iotstar.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;

import vn.iotstar.dao.UserDAO;
import vn.iotstar.model.UserModel;

/**
 * Servlet implementation class ShipperController
 */
@WebServlet("/admin-shipper")
public class ShipperController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@PreAuthorize("hasRole('ROLE_SHIPPER')")
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		HttpSession mySession = req.getSession();

		UserDAO userDAO = new UserDAO();
		UserModel userModel = (UserModel) mySession.getAttribute("adAccount");

		if (userModel != null && userModel.getRole() == 3) {
			req.getRequestDispatcher("/viewAdmin/Shipper.jsp").forward(req, resp);

		} else {
			resp.sendRedirect("home");
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

}
