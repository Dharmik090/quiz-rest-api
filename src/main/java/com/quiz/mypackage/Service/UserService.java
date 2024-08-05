package com.quiz.mypackage.Service;

import java.util.List;

import com.quiz.mypackage.Entity.User;

public interface UserService {
	User save(User user);
	User find(int id);
	User findByUsername(String username);
	List<User> findAll();
	User update(int id,User user);
	void delete(int id);
}
