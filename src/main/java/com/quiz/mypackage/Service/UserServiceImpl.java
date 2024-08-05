package com.quiz.mypackage.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.mypackage.DAO.UserDAO;
import com.quiz.mypackage.Entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userdao;
	
	@Override
	public User save(User user) {
		return userdao.save(user);
	}
	
	@Override
	public User find(int id) {
		return userdao.findById(id).orElse(null);
	}
	
	@Override
	public User findByUsername(String username) {
		return userdao.findByUsername(username);
	}
	
	@Override
	public List<User> findAll(){
		return userdao.findAll();
	}
	
	@Override
	public User update(int id,User user) {
		if(userdao.existsById(id)) {
			user.setId(id);
			return userdao.save(user);
		}
		return null;
	}
	
	@Override
	public void delete(int id) {
		userdao.deleteById(id);
	}
}
