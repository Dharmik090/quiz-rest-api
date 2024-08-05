package com.quiz.mypackage.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.mypackage.Entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer>{
	User findByUsername(String username);
}
