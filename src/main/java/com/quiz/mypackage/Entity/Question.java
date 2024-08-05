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
	
	@Column(name="optionA",nullable=false)
	private String optionA;
	
	@Column(name="optionB",nullable=false)
	private String optionB;	
	
	@Column(name="optionC",nullable=false)
	private String optionC;	
	
	@Column(name="optionD",nullable=false)
	private String optionD;
	
	@Column(name="answer",nullable=false)
	private String answer;
	
	
	public Question() {}
	
	public Question(int id, Quiz topic, String question, String optionA, String optionB, String optionC, String optionD,
			String answer) {
		super();
		this.id = id;
		this.topic = topic;
		this.question = question;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
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

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}
