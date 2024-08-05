package com.quiz.mypackage.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.mypackage.Entity.Quiz;
import com.quiz.mypackage.Service.QuizService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api")
public class QuizController {
	
	@Autowired
	private QuizService quizService;
	
	@GetMapping("/quiz/{id}")
	public Quiz find(@PathVariable int id) {
		return quizService.find(id);
	}
	
	@GetMapping("/quizzes")
	public List<Quiz> findAll(){
		return quizService.findAll();
	}
	
	@PostMapping("/quiz")
	public Quiz save(@RequestBody Quiz quiz) {
		return quizService.save(quiz);
	}
	
	@PutMapping("/quiz/{id}")
	public Quiz update(@PathVariable int id,@RequestBody Quiz quiz) {
		return quizService.update(id, quiz);
	}
	
	@DeleteMapping("/quiz/{id}")
	public void delete(@PathVariable int id) {
		quizService.delete(id);
	}
}
