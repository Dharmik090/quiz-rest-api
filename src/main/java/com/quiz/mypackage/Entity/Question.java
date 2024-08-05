package com.quiz.mypackage.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="topic_id")
	private Quiz topic;
	
	@Column(name="question",nullable=false,unique=true)
	private String question;
	
	@Column(name="options",nullable=false)
	private List<String> options;
	
	@Column(name="answer",nullable=false)
	private String answer;
	
	
	public Question() {}

	public Question(int id, Quiz topic, String question, List<String> options, String answer) {
		super();
		this.id = id;
		this.topic = topic;
		this.question = question;
		this.options = options;
		this.answer = answer;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Quiz getTopic() {
		return topic;
	}

	public void setTopic(Quiz topic) {
		this.topic = topic;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}
