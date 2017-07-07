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
@Transactional(readOnly = true)
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
    @Transactional
    public boolean delete(Function function){
        try {
            functionRepository.delete(function.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public Function update(Function function){
        return functionRepository.save(function);
    }
}
