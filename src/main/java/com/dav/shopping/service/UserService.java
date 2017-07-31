package com.dav.shopping.service;

import com.dav.shopping.entity.Role;
import com.dav.shopping.entity.User;
import org.springframework.data.domain.Page;


public interface UserService {
	User save(User user);
	User update(User user);
<<<<<<< HEAD
	boolean delete(User user);
=======
	boolean delete(Long id);
>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92

	Page<User> findAll(int page, int pageSize, String filter);

	User findById(long id);
	User findByEmail(String email);

}
