package com.dav.shopping.service;

import com.dav.shopping.entity.Action;

import java.util.List;

public interface ActionService {

	List<Action> findByType(String name);
	Action findByName(String name);

}
