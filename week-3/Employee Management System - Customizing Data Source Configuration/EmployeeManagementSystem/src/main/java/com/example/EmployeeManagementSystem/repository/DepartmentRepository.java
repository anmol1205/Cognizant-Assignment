package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
	// Find department by name
    Department findByName(String name);

    // Find departments with names containing a specific substring
    List<Department> findByNameContaining(String substring);
    
}
