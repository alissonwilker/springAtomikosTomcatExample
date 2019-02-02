package com.javacodegeeks.snippets.enterprise.service;

import com.javacodegeeks.snippets.enterprise.model.EmployeeA;
import com.javacodegeeks.snippets.enterprise.model.EmployeeB;

public interface EmployeeService {

	void persistEmployees(EmployeeA employeeA, EmployeeB employeeB) throws Exception;
	
}
