package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.entity.Department;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.repository.DepartmentRepository;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
	@Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    
    // Save an employee
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    
    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    // Get an Employee by Id
    public Optional<Employee> getEmployeeById(Long id){
    	return employeeRepository.findById(id);
    }
    
    //Get an employee by name
    public List<Employee> getEmployeeByName(String name){
    	return employeeRepository.findByName(name);
    }
    
    // Get employees by department ID
    public List<Employee> getEmployeesByDepartmentId(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }
    
    // Get employee by email
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    // Get employees with names containing a specific substring
    public List<Employee> getEmployeesByNameContaining(String substring) {
        return employeeRepository.findByNameContaining(substring);
    }

    // Delete an employee by ID
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // Save a department
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Get all departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Get department by name
    public Department getDepartmentByName(String name) {
        return departmentRepository.findByName(name);
    }

    // Get departments with names containing a specific substring
    public List<Department> getDepartmentsByNameContaining(String substring) {
        return departmentRepository.findByNameContaining(substring);
    }
}
