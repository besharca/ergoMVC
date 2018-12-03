package com.lil.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=com.lil.demo.WebNewApplication.class, webEnvironment=WebEnvironment.RANDOM_PORT) 
public class FullStackTests {
 
	@Autowired
	private TestRestTemplate templ;
	
	@Test
	public void test(int ...a) {
		 for(int x : a) {
			 	
		 }
	}

}
