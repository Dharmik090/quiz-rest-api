package com.quiz.mypackage.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.mypackage.Entity.Score;

@Repository
public interface ScoreDAO extends JpaRepository<Score, Integer> {

}
