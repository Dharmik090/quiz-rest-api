package com.quiz.mypackage.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Quiz {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="subject",nullable=false,unique=true)
	private String subject;
	
	@Column(name="topic",nullable=false,unique=true)
	private String topic;
	
	@Column(name="passed_users",nullable=false)
	private int passed_users;

	@OneToMany(mappedBy="topic")
	private List<Question> questions;
	
	public Quiz() {}

	public Quiz(int id, String subject, String topic, int passed_users) {
		super();
		this.id = id;
		this.subject = subject;
		this.topic = topic;
		this.passed_users = passed_users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getPassed_users() {
		return passed_users;
	}

	public void setPassed_users(int passed_users) {
		this.passed_users = passed_users;
	}
	
}
