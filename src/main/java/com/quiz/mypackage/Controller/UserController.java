package com.quiz.mypackage.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.mypackage.Dto.LoginRequest;
import com.quiz.mypackage.Dto.LoginResponse;
import com.quiz.mypackage.Entity.User;
import com.quiz.mypackage.Jwt.JwtUtils;
import com.quiz.mypackage.Service.UserService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserDetailsManager userDetailsManager;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils jwtUtils;
	
	@GetMapping("/id/{id}")
	public User find(@PathVariable int id) {
		return userService.find(id);
	}
	
	@GetMapping("/username/{username}")
	public User findByUsername(@PathVariable String username) {
		System.out.println(username);
	
		return userService.findByUsername(username);
	}
	
	@GetMapping("/users")
	public List<User> findAll(){
		return userService.findAll();
	}
	
	@PostMapping("/user")
	public User save(@RequestBody User user) {		
		UserDetails userdetails = org.springframework.security.core.userdetails.User
				.withUsername(user.getUsername())
				.password(passwordEncoder.encode(user.getPassword()))
				.roles("USER")
				.build();
		
		userDetailsManager.createUser(userdetails); 
		return userService.save(user);
	}
	
	@PutMapping("/user/{id}")
	public User update(@PathVariable int id,@RequestBody User user) {
		return userService.update(id, user);
	}
	
	@DeleteMapping("/user/{id}")
	public void delete(@PathVariable int id){
		userService.delete(id);
	}
	
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
		Authentication authentication;
		try {
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		}catch(AuthenticationException e) {
			Map<String, Object> body = new HashMap<>();
			body.put("status", false);
			body.put("message", "Bad credentials");
			
			return new ResponseEntity<Object>(body,HttpStatus.NOT_FOUND);
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		LoginResponse response = new LoginResponse(jwtToken,roles,userDetails.getUsername());
		
		return ResponseEntity.ok(response);
	}

}
