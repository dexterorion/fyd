package com.fyd.helpdesk.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fyd.helpdesk.api.entity.User;

public interface UserRepository extends MongoRepository<User, String>{

	User findByEmail(String email);
	
	
}
