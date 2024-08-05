package com.quiz.mypackage;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.quiz.mypackage.DAO.UserDAO;

@SpringBootTest
public class UserServiceImplTest {

	@Autowired
	private UserDAO userdao;
	
	@Test
	public void testFindByUsername() {
		assertNotNull(userdao.findByUsername("john"));
	}
}
