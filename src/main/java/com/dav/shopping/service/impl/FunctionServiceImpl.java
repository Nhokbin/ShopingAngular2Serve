package com.dav.shopping.service.impl;

import com.dav.shopping.entity.Function;
import com.dav.shopping.repository.FunctionRepository;
import com.dav.shopping.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Duong Vu on 27/06/2017.
 */
@Service
<<<<<<< HEAD
@Transactional
=======
@Transactional(readOnly = true)
>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92
public class FunctionServiceImpl implements FunctionService {

    @Autowired
    private FunctionRepository functionRepository;

    @Override
    public List<Function> getAll() {
        return functionRepository.findAll();
    }

    @Override
    public List<Function> findByParentId(long parentId) {
        return functionRepository.findByParentId(parentId);
    }

    @Override
    public Function findById(long id) {
        return functionRepository.findOne(id);
    }

    @Override
    public boolean create(Function function){
        return functionRepository.saveAndFlush(function)!= null ? true : false;
    }

    @Override
<<<<<<< HEAD
    public boolean delete(Function function){
        try {
            functionRepository.delete(function);
=======
    @Transactional
    public boolean delete(Function function){
        try {
            functionRepository.delete(function.getId());
>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
<<<<<<< HEAD
    public Function update(Function function){
        Function entity = functionRepository.findOne(function.getId());
        if(entity!=null){
            entity.setId(function.getId());
            entity.setName(function.getName());
            entity.setDescription(function.getDescription());
            entity.setDisplayOrder(function.getDisplayOrder());
            entity.setStatus(function.getStatus());
            entity.setUrl(function.getUrl());
            entity.setIconCss(function.getIconCss());
            entity.setRoles(function.getRoles());
            entity.setParentId(function.getParentId());
            entity.getFunctions().clear(); // không có sẽ lỗi A collection with cascade="all-delete-orphan" was no longer referenced by the owning entity instance
            entity.getFunctions().addAll(function.getFunctions());
        }
        return entity;
=======
    @Transactional
    public Function update(Function function){
        return functionRepository.save(function);
>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92
    }
}
