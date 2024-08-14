package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.EmployeeManagementSystem.projection.EmployeeProjection;
import com.example.EmployeeManagementSystem.dto.EmployeeDTO;

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
    
    //paging methods
    Page<Employee> findAll(Pageable pageable);
    Page<Employee> findByNameContainingIgnoreCase(String name, Pageable pageable);

    
    //for projections
    List<EmployeeProjection> findAllBy();
    
    @Query("SELECT new com.example.EmployeeManagementSystem.dto.EmployeeDTO(e.name, e.email) FROM Employee e")
    List<EmployeeDTO> findEmployeeDTOs();
    
// // Custom JPQL query to find employees by department name
//    @Query("SELECT e FROM Employee e WHERE e.department.name = :departmentName")
//    List<Employee> findEmployeesByDepartmentName(@Param("departmentName") String departmentName);
//
//    // Custom native SQL query to find employees by email
//    @Query(value = "SELECT * FROM employees e WHERE e.email = :email", nativeQuery = true)
//    Employee findEmployeeByEmail(@Param("email") String email);
    
    // Use named query to find employees by department name
    List<Employee> findByDepartmentName(@Param("departmentName") String departmentName);

 // Use named query to find employees by email
    Employee findByEmailQuery(@Param("email") String email);
}
