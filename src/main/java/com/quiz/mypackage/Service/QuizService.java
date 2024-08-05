package com.quiz.mypackage.Service;

import java.util.List;

import com.quiz.mypackage.Entity.Quiz;

public interface QuizService {
	Quiz save(Quiz quiz);
	Quiz find(int id);
	List<Quiz> findAll();
	Quiz update(int id,Quiz quiz);
	void delete(int id);
}
