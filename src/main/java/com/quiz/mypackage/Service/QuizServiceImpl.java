package com.quiz.mypackage.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.mypackage.DAO.QuizDAO;
import com.quiz.mypackage.Entity.Quiz;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizDAO quizdao;
	
	@Override
	public Quiz save(Quiz quiz) {
		return quizdao.save(quiz);
	}

	@Override
	public Quiz find(int id) {
		return quizdao.findById(id).orElse(null);
	}

	@Override
	public List<Quiz> findAll() {
		return quizdao.findAll();
	}

	@Override
	public Quiz update(int id, Quiz quiz) {
		if(quizdao.existsById(id)) {
			quiz.setId(id);
			return quizdao.save(quiz);
		}
		return null;
	}

	@Override
	public void delete(int id) {
		quizdao.deleteById(id);
	}

}
