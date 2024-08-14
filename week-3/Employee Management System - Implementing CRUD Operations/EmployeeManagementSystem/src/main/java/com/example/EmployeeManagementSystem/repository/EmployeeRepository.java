package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	//declaring methods which could be used for the modification using query customization
	// Find employees by name
    List<Employee> findByName(String name);
    
    //Find employees by department
    List<Employee> findByDepartmentId(Long departmentId);

    // Find employees by email
    Employee findByEmail(String email);

    // Find employees with names containing a specific substring
    List<Employee> findByNameContaining(String substring);
}
