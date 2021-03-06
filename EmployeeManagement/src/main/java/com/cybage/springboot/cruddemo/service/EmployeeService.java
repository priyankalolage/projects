package com.cybage.springboot.cruddemo.service;

import java.util.List;

import com.cybage.springboot.cruddemo.entity.Employee;

public interface EmployeeService {
		//All employees
		public List<Employee> findAll();

		//Single Employee by id
		public Employee findById(int id);
		
		//single employee find by first name
		public List<Employee> findByfname(String fname);
		
		//Save the Employee
		public void save(Employee empoyee);
		
		//Delete the Employee
	    public void deleteById(int id);

		public boolean findRole(int empId);

		public List<Employee> findExtraHours(int empId);
}
