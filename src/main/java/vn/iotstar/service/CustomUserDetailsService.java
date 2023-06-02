package vn.iotstar.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.iotstar.dao.UserDAO;
import vn.iotstar.model.UserModel;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel user = userRepository.getUser(username); // Lấy thông tin người dùng từ database
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		// Lấy ra role của người dùng và gán từng role cụ thể cho người dùng đó
		String roleString = "";
		if (user.getRole() == 0) {
			roleString = "ROLE_USER";
		} else if (user.getRole() == 1) {
			roleString = "ROLE_SELLER";
		} else if (user.getRole() == 3) {
			roleString = "ROLE_SHIPPER";
		} else if (user.getRole() == 4) {
			roleString = "ROLE_ADMIN";
		}

		return new org.springframework.security.core.userdetails.User(user.getUsers_username(),
				user.getUsers_pass_word(), getAuthorities(roleString));
	}

	// Hàm tạo danh sách các quyền với vai trò cụ thể
	private Collection<? extends GrantedAuthority> getAuthorities(String role) {
		return Collections.singletonList(new SimpleGrantedAuthority(role));
	}
}
