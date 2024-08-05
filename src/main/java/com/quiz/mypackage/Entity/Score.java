package com.quiz.mypackage.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Score {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private User user;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Quiz topic;
	
	@Column(name="score",nullable=false)
	private double score;
	
	@Column(name="time_taken",nullable=false)
	private int time_taken;
	
	
	public Score() {}
	
	public Score(int id, User user, Quiz topic, double score, int time_taken) {
		super();
		this.id = id;
		this.user = user;
		this.topic = topic;
		this.score = score;
		this.time_taken = time_taken;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Quiz getTopic() {
		return topic;
	}


	public void setTopic(Quiz topic) {
		this.topic = topic;
	}


	public double getScore() {
		return score;
	}


	public void setScore(double score) {
		this.score = score;
	}


	public int getTime_taken() {
		return time_taken;
	}


	public void setTime_taken(int time_taken) {
		this.time_taken = time_taken;
	}
	
	
}
