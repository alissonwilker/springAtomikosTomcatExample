package com.javacodegeeks.snippets.enterprise.servlet;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javacodegeeks.snippets.enterprise.model.EmployeeA;
import com.javacodegeeks.snippets.enterprise.model.EmployeeB;
import com.javacodegeeks.snippets.enterprise.service.EmployeeService;

public class App {

	public static void main(String[] args) throws Exception {
		System.out.println("load context");
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmployeeA em1 = new EmployeeA();
		em1.setId("123");
		em1.setName("John");
		em1.setAge(35);
		EmployeeB em2 = new EmployeeB();
		em2.setId("123");
		em2.setName("Mary");
		em2.setAge(31);
		EmployeeService emService = (EmployeeService) context.getBean("employeeService");
		emService.persistEmployees(em1, em2);
		context.close();
	}

}

