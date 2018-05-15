package com.fyd.sistema.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fyd.sistema.api.entity.User;
import com.fyd.sistema.api.repository.UserRepository;
import com.fyd.sistema.api.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserRepository> implements UserService{
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
