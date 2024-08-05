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

import com.quiz.mypackage.Entity.Score;
import com.quiz.mypackage.Service.ScoreService;

@RestController
@RequestMapping("/api")
public class ScoreController {
	
	@Autowired
	private ScoreService scoreService;
	
	@GetMapping("/score/{id}")
	public Score find(@PathVariable int id) {
		return scoreService.find(id);
	}
	
	@GetMapping("/scores")
	public List<Score> findAll(){
		return scoreService.findAll();
	}
	
	@PostMapping("/score")
	public Score save(@RequestBody Score score) {
		return scoreService.save(score);
	}
	
	@PutMapping("/score/{id}")
	public Score update(@PathVariable int id,@RequestBody Score score) {
		return scoreService.update(id, score);
	}
	
	@DeleteMapping("/score/{id}")
	public void delete(@PathVariable int id) {
		scoreService.delete(id);
		
	}
}
