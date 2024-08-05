package com.quiz.mypackage.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.mypackage.Entity.Quiz;

@Repository
public interface QuizDAO extends JpaRepository<Quiz, Integer> {

}
