package com.quiz.mypackage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(version = "3.1.0",
					title = "QuizApp API", 
					description = "This API is for some purpose",
					termsOfService = "Terms&Condition",
					contact = @Contact(name = "Dharmik",
									   email = "d@gamil.com")))

public class QuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}

}
