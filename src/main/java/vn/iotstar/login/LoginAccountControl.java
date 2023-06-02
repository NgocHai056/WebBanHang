package vn.iotstar.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.dao.CartDAO;
import vn.iotstar.dao.CartItemsDAO;
import vn.iotstar.dao.UserDAO;
import vn.iotstar.model.CartItemsModel;
import vn.iotstar.model.CartModel;
import vn.iotstar.model.UserModel;
import vn.iotstar.securityweb.JwtUtil;

/**
 * Servlet implementation class LoginAccountControl
 */
@WebServlet("/loginAccount")
public class LoginAccountControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(req, resp);
	}

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		HttpSession mySession = req.getSession();

		UserDAO userDAO = new UserDAO();
		UserModel userModel = userDAO.checkAccount(userName, password);

		// ACLUserDAO aclUserDAO = new ACLUserDAO();
		// ACLUserModel aclUserModel = aclUserDAO.getRoleSellerByName(userName);
		// Pour data into cart
		Object object = mySession.getAttribute("cart");

		if (userModel != null) {
			if (userModel.getRole() != -1) {
				

				CartDAO cartDAO = new CartDAO();
				CartModel cartModel = cartDAO.getCartByUserId(userModel.getUserID());

				if (cartModel == null) {
					cartModel = new CartModel();
					cartModel.setBuyer(userModel);
					cartDAO.insertCart(cartModel);
				}

				CartItemsDAO cartItemsDAO = new CartItemsDAO();
				List<CartItemsModel> cartItemsModel = cartItemsDAO.get(cartModel.getCart_id());

				if (object == null) {
					Map<Integer, CartItemsModel> map = new HashMap<Integer, CartItemsModel>();
					for (CartItemsModel items : cartItemsModel) {
						map.put(items.getProduct().getId(), items);
					}
					mySession.setAttribute("cart", map);
				}
				String token = JwtUtil.generateToken(userModel.getUsers_username(), userModel.getUserID());
				mySession.setAttribute("Authorization", "Bearer " + token);

				Cookie cookie = new Cookie("Authorization", token);
				cookie.setMaxAge(24 * 60 * 60); // Thời gian tồn tại của cookie (ở đây là 1 ngày)
				resp.addCookie(cookie);
				if(userModel.getRole() == 3 || userModel.getRole() == 4) {
					mySession.setAttribute("adAccount", userModel);
					mySession.setAttribute("adEmail", userName);
					mySession.setAttribute("adIdUser", userModel.getUserID());
					mySession.setAttribute("adName", userModel.getUsers_first_name() + " " + userModel.getUsers_last_name());			
					
					if(userModel.getRole() == 3) {
						resp.sendRedirect("admin-shipper");
					}
					if(userModel.getRole() == 4) {
						resp.sendRedirect("admin");
					}
				}else {
					mySession.setAttribute("account", userModel);
					mySession.setAttribute("email", userName);
					mySession.setAttribute("idUser", userModel.getUserID());
					mySession.setAttribute("name", userModel.getUsers_first_name() + " " + userModel.getUsers_last_name());
					resp.sendRedirect("home");
				}
				
			} else {
				mySession.setAttribute("alert", "Tài khoản đã bị khóa!!!");
				resp.sendRedirect("layout-Login?mask=login");
			}

		} else {
			mySession.setAttribute("alert", "Sai thông tin đăng nhập!!!");
			resp.sendRedirect("layout-Login?mask=login");
		}
	}
}
