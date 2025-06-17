package com.example.demo;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class DemoApplicationTests
{
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private String getBaseUrl(String path)
	{
		return "http://localhost:" + port + path;
	}

	@Test
	void testCreateAndGetUser()
	{
		User user = new User();
		user.setName("David Smith");
		user.setEmail("davidsmith@example.com");

		ResponseEntity<User> createResponse = restTemplate.postForEntity(getBaseUrl("/api/users"), user, User.class);
		assertEquals(HttpStatus.OK, createResponse.getStatusCode());
		assertNotNull(createResponse.getBody());
		assertNotNull(createResponse.getBody().getId());

		Long userId = createResponse.getBody().getId();
		ResponseEntity<User> getResponse = restTemplate.getForEntity(getBaseUrl("/api/users/" + userId), User.class);
		assertNotNull(getResponse.getBody());
		assertEquals("David Smith", getResponse.getBody().getName());
	}

	@Test
	void testCreateAndGetProduct()
	{
		Product product = new Product();
		product.setName("Binder");
		product.setPrice(10.99);

		ResponseEntity<Product> createResponse = restTemplate.postForEntity(getBaseUrl("/api/products"), product, Product.class);
		assertEquals(HttpStatus.OK, createResponse.getStatusCode());
		assertNotNull(createResponse.getBody());
		assertNotNull(createResponse.getBody().getId());

		Long productId = createResponse.getBody().getId();
		ResponseEntity<Product> getResponse = restTemplate.getForEntity(getBaseUrl("/api/products/" + productId), Product.class);
		assertNotNull(getResponse.getBody());
		assertEquals("Binder", getResponse.getBody().getName());
	}
}