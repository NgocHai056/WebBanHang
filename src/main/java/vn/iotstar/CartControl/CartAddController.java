package vn.iotstar.CartControl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.dao.CartDAO;
import vn.iotstar.dao.CartItemsDAO;
import vn.iotstar.dao.ProductDAO;
import vn.iotstar.dao.UserDAO;
import vn.iotstar.model.CartItemsModel;
import vn.iotstar.model.CartModel;
import vn.iotstar.model.ProductModel;
import vn.iotstar.model.UserModel;

/**
 * Servlet implementation class CartAddController
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/member/cart-add"})
public class CartAddController extends HttpServlet {
    ProductDAO productDAO = new ProductDAO();
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        
        String pId = req.getParameter("pId");
        String quantity = req.getParameter("quantity");
        
        ProductModel product = productDAO.getProductById(pId);
        
        CartItemsModel cartItem = new CartItemsModel();        
        cartItem.setQuantity(Integer.parseInt(quantity));
        cartItem.setUnitPrice(product.getPrice());
        cartItem.setProduct(product);
        
        HttpSession httpSession = req.getSession();
        Object object = httpSession.getAttribute("cart"); 
        
        //Cart handling
    	UserDAO userDAO = new UserDAO();
        CartDAO cartDAO = new CartDAO();
        
        String acc = (String)httpSession.getAttribute("email");
        if(!acc.equals("")) {
            UserModel userModel = userDAO.getUser(acc);
            CartModel cartModel = cartDAO.getCartByUserId(userModel.getUserID());
            
            if(cartModel == null) {
            	cartModel = new CartModel();
            	cartModel.setBuyer(userModel);
            	cartDAO.insertCart(cartModel);
            }
                        
            cartItem.setCart(cartModel);
            CartItemsDAO cartItemsDAO = new CartItemsDAO();
            
            
            List<CartItemsModel> cartItemsModel = cartItemsDAO.get(cartModel.getCart_id());
            
            int flag = 0;
            for(CartItemsModel items : cartItemsModel) {
            	if(items.getProduct().getId() == Integer.parseInt(pId)) {
            		items.setQuantity(items.getQuantity() + 1);
            		cartItemsDAO.edit(items);
            		flag = 1;
            		break;
            	}
            }
            if(flag == 0) {
            	cartItemsDAO.insert(cartItem);
            }
            
            if(object == null) {
                Map<Integer, CartItemsModel> map = new HashMap<Integer, CartItemsModel>();
                for (CartItemsModel items : cartItemsModel) {
					map.put(items.getProduct().getId(), items);
					System.out.println(items.getProduct().getProductName());
				}
                httpSession.setAttribute("cart", map);
            }else {
                Map<Integer, CartItemsModel> map = extracted(object);
                CartItemsModel exitsredCartItem = map.get(Integer.valueOf(pId));
                if(exitsredCartItem == null) {
                    map.put(product.getId(), cartItem);
                }
                else {
                    exitsredCartItem.setQuantity(exitsredCartItem.getQuantity() + Integer.parseInt(quantity));
                }
                httpSession.setAttribute("cart", map);
            }
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
    @SuppressWarnings("unchecked")
    private Map<Integer, CartItemsModel> extracted(Object obj){
        return (Map<Integer, CartItemsModel>) obj;
    }
}
