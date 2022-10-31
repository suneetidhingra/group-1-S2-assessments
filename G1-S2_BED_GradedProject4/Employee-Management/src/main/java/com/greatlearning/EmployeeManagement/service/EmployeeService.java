package com.greatlearning.EmployeeManagement.service;

import java.util.List;

import com.greatlearning.EmployeeManagement.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();
	
	public void save (Employee employee);
}
