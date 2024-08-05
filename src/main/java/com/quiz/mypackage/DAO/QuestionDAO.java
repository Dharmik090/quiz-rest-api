package com.quiz.mypackage.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.mypackage.Entity.Question;
import com.quiz.mypackage.Entity.Quiz;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer> {
	
	List<Question> findByTopic(Quiz quiz);
}
