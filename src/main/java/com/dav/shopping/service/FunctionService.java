package com.dav.shopping.service;

import com.dav.shopping.entity.Function;

import java.util.List;

/**
 * Created by Duong Vu on 27/06/2017.
 */
public interface FunctionService {

    List<Function> getAll();
    List<Function> findByParentId(long parentId);
    Function findById(long id);

    boolean create(Function function);
    boolean delete(Function function);
    Function update(Function function);
}
