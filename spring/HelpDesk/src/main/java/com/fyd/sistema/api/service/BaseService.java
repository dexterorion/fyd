package com.fyd.sistema.api.service;

import org.springframework.data.domain.Page;

public interface BaseService<T> {
	T createOrUpdate(T user);
	
	T findById(String id);
	
	void delete(String id);
	
	Page<T> findAll(int page, int count);
}
