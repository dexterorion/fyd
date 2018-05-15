package com.fyd.sistema.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.fyd.sistema.api.service.BaseService;

public class BaseServiceImpl<T, U extends MongoRepository<T, String>> implements BaseService<T>{
	@Autowired
	private U repository;

	@Override
	public T createOrUpdate(T user) {
		return repository.save(user);
	}
	
	@Override
	public T findById(String id) {
		return repository.findOne(id);
	}
	
	@Override
	public void delete(String id) {
		repository.delete(id);
	}
	
	@Override
	public Page<T> findAll(int page, int count){
		Pageable pages = new PageRequest(page, count);
		return repository.findAll(pages);
	}

}
