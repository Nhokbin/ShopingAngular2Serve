package com.dav.shopping.service.impl;

import com.dav.shopping.entity.Role;
import com.dav.shopping.entity.UserRole;
import com.dav.shopping.service.RoleService;
import com.dav.shopping.service.UserRoleService;
import org.hibernate.Hibernate;
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
import java.util.List;
import java.util.Set;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private static final int PAGE_SIZE = 20;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        try {
            userRepository.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    @Override
    public User update(User user){
        User entity = userRepository.findOne(user.getId());
        if(entity!=null){
            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());
            entity.setUsername(user.getUsername());
            entity.setEmail(user.getEmail());
            entity.setAddress(user.getAddress());
            entity.setPhoneNumber(user.getPhoneNumber());
            entity.setPassword(user.getPassword());
            entity.setAddress(user.getAvatar());
            entity.setCreated(user.getCreated());
            entity.setDateOfBirth(user.getDateOfBirth());
            entity.setActive(user.isActive());
            entity.setGender(user.isGender());
            entity.getUserRoles().clear();
        }
        return entity;
    }

    @Override
    public Page<User> findAll(int page, int pageSize, String filter) {
        PageRequest request = new PageRequest(page - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
        Page<User> users = userRepository.findAll(filter,request);
        for (User user: users) {
            System.out.println("Role: " + user.getUserRoles().size());
        }
        return users;
    }

    @Override
    public User findById(long id) {
        return userRepository.findOne(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
