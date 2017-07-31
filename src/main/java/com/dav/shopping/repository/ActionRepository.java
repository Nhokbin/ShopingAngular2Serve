package com.dav.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dav.shopping.entity.Action;

import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long>{

	@Query(value = "SELECT a FROM Action a WHERE a.name LIKE %:name%")
	List<Action> findByType(@Param("name")String name);

	Action findByName(String name);
	
}
