package com.cybage.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	private EntityManager entitymanager;
	
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entitymanager) {
		this.entitymanager = entitymanager;
	}

	@Override
	//@Transactional
	public List<Employee> findAll() {
		
		//GET hibernate session
		Session session=entitymanager.unwrap(Session.class);
				
		//create a query
		Query<Employee> theQuery=session.createQuery("from Employee",Employee.class);
		
		//execute and return a list of result
		List<Employee> employees= theQuery.getResultList();
		
		//return the result
		return employees;
		
	}

	@Override
	public Employee findById(int id) {
		//get current session
		Session session=entitymanager.unwrap(Session.class);
		
		//get the employee
		Employee employee=session.get(Employee.class,id);
	 
		//return the employee
		return employee;

	}

	@Override
	public void save(Employee empoyee) {
		Session session=entitymanager.unwrap(Session.class);
		
		session.saveOrUpdate(empoyee);
		
	}

	@Override
	public void deleteById(int empid) {
		Session session=entitymanager.unwrap(Session.class);
		Query query=session.createQuery("delete from Employee where id=:empid");
		query.setParameter("empid",empid);
		query.executeUpdate();
		
		
	}

	@Override
	public List<Employee> findByfname(String fname) {
		Session session=entitymanager.unwrap(Session.class);
		Query query=session.createQuery("select e from Employee e where e.firstName= :fname");
		query.setParameter("fname",fname);
		List<Employee> empList=query.getResultList();
		return empList;
	}

	@Override
	public boolean findRole(int empId) {
		Session session=entitymanager.unwrap(Session.class);		
		  Query query=session.
		  createQuery("from Employee e where e.id= :empId and (e.role=1 OR e.role=2)");
		  query.setParameter("empId",empId);
		  List<Employee> empList=query.getResultList();
		  if(empList.isEmpty())
			  return false;
		  return  true;
		 
		
		
	}

	@Override
	public List<Employee> findExtraHours(int empId) {
		Session session=entitymanager.unwrap(Session.class);		
		  Query query=session.createQuery("from Employee e where e.reporter=:empId");
		  query.setParameter("empId",empId);
		  List<Employee> empList=query.getResultList();
		  return empList;
		
	}

}
