package com.example.EmployeeManagementSystem.repository.secondary;

import com.example.EmployeeManagementSystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository


public interface DepartmentSecondaryRepository extends JpaRepository<Department, Long>  {

}
