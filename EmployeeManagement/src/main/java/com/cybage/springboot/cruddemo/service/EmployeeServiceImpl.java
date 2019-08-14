package com.cybage.springboot.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.springboot.cruddemo.dao.EmployeeDAO;
import com.cybage.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	public EmployeeDAO employeeDAO;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		
		return employeeDAO.findAll();
	}

	@Override
	@Transactional
	public Employee findById(int id) {
		
		return employeeDAO.findById(id);
	}

	@Override
	@Transactional
	public void save(Employee employee) {
		employeeDAO.save(employee);

	}

	@Override
	@Transactional
	public void deleteById(int id) {
		employeeDAO.deleteById(id);

	}

	@Override
	@Transactional
	public List<Employee> findByfname(String fname) {
		return employeeDAO.findByfname(fname);
		
	}

	@Override
	@Transactional
	public boolean findRole(int empId) {
		return employeeDAO.findRole(empId);
		
	}

	@Override
	@Transactional
	public List<Employee> findExtraHours(int empId) {
		return employeeDAO.findExtraHours(empId);
		
	}

}
