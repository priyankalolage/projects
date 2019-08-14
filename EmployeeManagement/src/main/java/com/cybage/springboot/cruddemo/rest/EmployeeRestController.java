package com.cybage.springboot.cruddemo.rest;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.springboot.cruddemo.dao.EmployeeDAO;
import com.cybage.springboot.cruddemo.entity.Employee;
import com.cybage.springboot.cruddemo.exception.NoRecordWithFirstNameException;
import com.cybage.springboot.cruddemo.exception.RecordNotFoundException;
import com.cybage.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

    @GetMapping("/employees")
	public List<Employee> findAll()
	{
		return employeeService.findAll();
	}
	
	/*
	 * @GetMapping("/employees/{empId}") public Employee findById(@PathVariable int
	 * empId) { Employee emp=employeeService.findById(empId); if(emp==null) throw
	 * new RuntimeException("Id: "+empId+"Not found!!");
	 * 
	 * return emp; }
	 */
	
	
	@GetMapping("/employees/{empfName}")
	public List<Employee> findByfName(@PathVariable String empfName)
	{
		List<Employee> empList=employeeService.findByfname(empfName);
		if(empList.isEmpty())
			throw new NoRecordWithFirstNameException("Invalid first name : "+empfName);
		return empList;
	}
	
	
	@PostMapping("/employees")
	public Employee save(@RequestBody Employee emp)
	{
		System.out.println(emp);
		emp.setId(0);		
		employeeService.save(emp);
		return emp;
	}
	
	@PutMapping("/employees")
	public Employee update(@RequestBody Employee emp)
	{
		employeeService.save(emp);
		return emp;
	}
	
	@DeleteMapping("/employees/{empId}")
	public String delete(@PathVariable int empId)
	{
		Employee emp=employeeService.findById(empId);
		if(emp==null)
			throw new RecordNotFoundException("Invalid employee id : " + empId);
		
		employeeService.deleteById(empId);
		return "Deleted id :"+emp.getId();
		
	}
	
	
	  @GetMapping("/employees/countExtraWorkHours/{empId}")
	  public HashMap<Employee, Double> countWorkHours(@PathVariable int empId) 
	  {
		 Employee emp=employeeService.findById(empId);
		 if(emp==null)
			 throw new RecordNotFoundException("Invalid employee id : " + empId);
		 //If manager or associate is requesting the API.
		 boolean isEligible=employeeService.findRole(empId); 
		 if(isEligible==true)
		 {
			 List<Employee> empList=employeeService.findExtraHours(empId);
			 HashMap<Employee, Double> empMap=new HashMap<Employee, Double>();
			 
			 for(Employee e:empList)
			 {
				 if(e.getWorkHours()>8)
				 {
					 double extra=e.getWorkHours()-8.00;
					 empMap.put(e, extra);					 
				 }
				 else
				 {
					 empMap.put(e, (double) 0);
				 }
					 
			 }
			 return empMap;
		 }
		 else
			 throw new RuntimeException("you are not eligible to see the work hours: ");
		  
	  }
	 
	
	
	
	
	
	
	
}
