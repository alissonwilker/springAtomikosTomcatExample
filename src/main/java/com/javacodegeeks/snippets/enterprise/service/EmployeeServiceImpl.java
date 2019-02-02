package com.javacodegeeks.snippets.enterprise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javacodegeeks.snippets.enterprise.dao.EmployeeADAO;
import com.javacodegeeks.snippets.enterprise.dao.EmployeeBDAO;
import com.javacodegeeks.snippets.enterprise.model.EmployeeA;
import com.javacodegeeks.snippets.enterprise.model.EmployeeB;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeADAO employeeADAO;
	
	@Autowired
	EmployeeBDAO employeeBDAO;
	
	@Transactional(rollbackFor=Exception.class)
	public void persistEmployees(EmployeeA employeeA, EmployeeB employeeB) throws Exception {
		System.out.println("Persist A");
		employeeADAO.persistEmployee(employeeA);
		System.out.println("Persist A OK - persist B");
		employeeBDAO.persistEmployee(employeeB);
		System.out.println("Persist B okk");
	}

}
