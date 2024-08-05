package com.quiz.mypackage.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.mypackage.Entity.Question;
import com.quiz.mypackage.Entity.Quiz;
import com.quiz.mypackage.Service.QuestionService;
import com.quiz.mypackage.Service.QuizService;

@RestController
@RequestMapping("/api")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	@Autowired
	private QuizService quizService;
	
	@GetMapping("/question/{id}")
	public Question find(@PathVariable int id) {
		return questionService.find(id);
	}
	
	@GetMapping("/questions/{quiz_id}")
	public List<Question> findAll(@PathVariable int quiz_id){
		Quiz quiz = quizService.find(quiz_id);
		if(quiz == null)
			return null;
		
		return questionService.findByTopic(quiz);
	}
	
	@PostMapping("/question/{quiz_id}")
	public Question save(@PathVariable int quiz_id, @RequestBody Question question) {
		Quiz quiz = quizService.find(quiz_id);
		if(quiz == null)
			return null;
		
		question.setTopic(quiz);
		return questionService.save(question);
	}
	
	@PutMapping("/question/{id}")
	public Question update(@PathVariable int id,@RequestBody Question question) {
		return questionService.update(id, question);
	}
	
	@DeleteMapping("/question/{id}")
	public void delete(@PathVariable int id) {
		questionService.delete(id);
	}
}
