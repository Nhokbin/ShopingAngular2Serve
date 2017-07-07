package com.dav.shopping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dav.shopping.entity.Action;
import com.dav.shopping.repository.ActionRepository;
import com.dav.shopping.service.ActionService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("actionService")
@Transactional
public class ActionServiceImpl implements ActionService{

	@Autowired
	private ActionRepository actionRepository;

	@Override
	public List<Action> findByType(String name) {
		return actionRepository.findByType(name);
	}

	@Override
	public Action findByName(String name) {
		// TODO Auto-generated method stub
		return actionRepository.findByName(name);
	}

}
