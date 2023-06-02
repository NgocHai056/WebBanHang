package vn.iotstar.securityweb;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.model.UserModel;
import vn.iotstar.security.JwtUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(urlPatterns = {"/admin/*", "/layout-Payment/*", "/showCart/*"})
public class JwtFilter implements Filter {

    private static final String AUTH_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final List<String> EXCLUDED_URLS = Arrays.asList("/home", "/trang-chu");

    // Hàm bỏ qua  những url không cần lọc
    private boolean shouldSkipFilter(HttpServletRequest request) {
        String url = request.getRequestURI();
        return EXCLUDED_URLS.contains(url);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // Bỏ qua những url không cần xác thực
        if (shouldSkipFilter(httpRequest)) {
            
            chain.doFilter(request, response);
            return;
        }
        HttpSession mySession = httpRequest.getSession();
        String authHeader = (String) mySession.getAttribute(AUTH_HEADER);

        if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
            String token = authHeader.substring(TOKEN_PREFIX.length());
            
            // Xác thực người dùng. Nếu hợp lệ thì yêu cầu của người dùng sẽ được xử lý tiếp ở phía server
            if (JwtUtil.validateToken(token)) {
            	
                UserModel subject = vn.iotstar.securityweb.JwtUtil.getUserFromToken(token);
                
                // Set thông tin xác thực cho quá trình xử lý tiếp theo
                httpRequest.setAttribute("userToken", subject);

                chain.doFilter(request, response);
            } else {
                // Token không hợp lệ hoặc hết hạn
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            // Token không được truyền vào
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
    

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}

