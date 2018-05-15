package com.fyd.sistema.api.service;

import com.fyd.sistema.api.entity.User;

public interface UserService extends BaseService<User>{
	User findByEmail(String email);
}
