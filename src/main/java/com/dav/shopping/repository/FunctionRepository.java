package com.dav.shopping.repository;

import com.dav.shopping.entity.Function;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Duong Vu on 27/06/2017.
 */
@Repository
public interface FunctionRepository extends JpaRepository<Function, Long> {

    @Query(value = "SELECT f FROM Function f WHERE f.parentId.id IS NULL")
    List<Function> findAll();

    @Query(value = "SELECT f FROM Function f WHERE f.parentId.id = :parentId")
    List<Function> findByParentId(@Param("parentId") long parentId);

}
