package com.dav.shopping.service.impl;

import com.dav.shopping.entity.Role;
import com.dav.shopping.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dav.shopping.entity.User;
import com.dav.shopping.repository.UserRepository;
import com.dav.shopping.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private static final int PAGE_SIZE = 20;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;

    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean delete(User user) {
        try {
            userRepository.delete(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    @Override
    public User update(User user){

        User entity = userRepository.findOne(user.getUserId());
        if(entity!=null){
            entity.setUserId(user.getUserId());
            entity.setPassword(user.getPassword());
            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());
            entity.setEmail(user.getEmail());
            entity.setAddress(user.getAddress());
            entity.setPhoneNumber(user.getPhoneNumber());
            entity.setAvatar(user.getAvatar());
            entity.setCreated(user.getCreated());
            entity.setDateOfBirth(user.getDateOfBirth());
            entity.setActive(user.getActive());
            entity.setRoles(user.getRoles());
        }

        return entity;
    }

    @Override
    public Page<User> findAll(int page, int pageSize, String filter) {
        PageRequest request = new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
        return userRepository.findAll(filter, request);
    }

    @Override
    public User findById(long id) {
        return userRepository.findOne(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
