package com.example.tldspringboot;

import com.example.tldspringboot.dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TldSpringBootApplicationTests {

	@Test
	void contextLoads() throws JsonProcessingException {

		System.out.println("Hello Object Mapper");

		var objectMapper = new ObjectMapper();
		// object -> text
		var user = new User("Harry", 30);

		var text = objectMapper.writeValueAsString(user);
		System.out.println(text);
		// text -> object

		var objectUser = objectMapper.readValue(text, User.class);
		System.out.println(objectUser);
	}

}
