package com.quiz.mypackage.Service;

import java.util.List;

import com.quiz.mypackage.Entity.Score;

public interface ScoreService {
	Score save(Score score);
	Score find(int id);
	List<Score> findAll();
	Score update(int id,Score score);
	void delete(int id);
}
