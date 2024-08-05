package com.quiz.mypackage.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.mypackage.DAO.QuestionDAO;
import com.quiz.mypackage.Entity.Question;
import com.quiz.mypackage.Entity.Quiz;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDAO questiondao;
	
	@Override
	public Question save(Question question) {
		return questiondao.save(question);
	}

	@Override
	public Question find(int id) {
		return questiondao.findById(id).orElse(null);
	}

	@Override
	public List<Question> findAll() {
		return questiondao.findAll();
	}

	@Override
	public Question update(int id, Question question) {
		if(questiondao.existsById(id)) {
			question.setId(id);
			return questiondao.save(question);
		}
		return null;
	}

	@Override
	public void delete(int id) {
		questiondao.deleteById(id);

	}

	@Override
	public List<Question> findByTopic(Quiz quiz){
		return questiondao.findByTopic(quiz);
	}
	
}
