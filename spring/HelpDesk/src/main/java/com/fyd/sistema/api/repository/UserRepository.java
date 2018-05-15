package com.fyd.sistema.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fyd.sistema.api.entity.User;

public interface UserRepository extends MongoRepository<User, String>{

	User findByEmail(String email);
	
	
}
