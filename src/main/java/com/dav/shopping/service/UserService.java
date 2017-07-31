package com.dav.shopping.service;

import com.dav.shopping.entity.Role;
import com.dav.shopping.entity.User;
import org.springframework.data.domain.Page;


public interface UserService {
	User save(User user);
	User update(User user);
	boolean delete(User user);

	Page<User> findAll(int page, int pageSize, String filter);

	User findById(long id);
	User findByEmail(String email);

}
