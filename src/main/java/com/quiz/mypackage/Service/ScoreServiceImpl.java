package com.quiz.mypackage.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.mypackage.DAO.ScoreDAO;
import com.quiz.mypackage.Entity.Score;

@Service
public class ScoreServiceImpl implements ScoreService {

	@Autowired
	private ScoreDAO scoredao;
	
	@Override
	public Score save(Score score) {
		return scoredao.save(score);
	}

	@Override
	public Score find(int id) {
		return scoredao.findById(id).orElse(null);
	}

	@Override
	public List<Score> findAll() {
		return scoredao.findAll();
	}

	@Override
	public Score update(int id, Score score) {
		if(scoredao.existsById(id)) {
			score.setId(id);
			return scoredao.save(score);
		}
		return null;
	}

	@Override
	public void delete(int id) {
		scoredao.deleteById(id);
	}

}
