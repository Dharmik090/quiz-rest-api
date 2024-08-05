package com.quiz.mypackage.Service;

import java.util.List;

import com.quiz.mypackage.Entity.Question;
import com.quiz.mypackage.Entity.Quiz;

public interface QuestionService {
	Question save(Question question);
	Question find(int id);
	List<Question> findAll();
	Question update(int id,Question question);
	void delete(int id);
	
	List<Question> findByTopic(Quiz quiz);
}
