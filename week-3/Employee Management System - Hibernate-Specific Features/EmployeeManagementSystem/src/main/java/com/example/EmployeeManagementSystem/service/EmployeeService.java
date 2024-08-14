package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.entity.Department;

import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.repository.DepartmentRepository;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.example.EmployeeManagementSystem.projection.EmployeeProjection;
import com.example.EmployeeManagementSystem.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
	@Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    
    
    @PersistenceContext
    private EntityManager entityManager;
    
    //pagination
    public Page<Employee> getAllEmployeesPaging(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }
    
    public Page<Employee> findEmployeesByNameContainingIgnoreCasePaging(String name, Pageable pageable) {
        return employeeRepository.findByNameContainingIgnoreCase(name, pageable);
    }
    
    //projections
    public List<EmployeeProjection> getAllEmployeeProjections() {
        return employeeRepository.findAllBy();
    }

    public List<EmployeeDTO> getEmployeeDTOs() {
        return employeeRepository.findEmployeeDTOs();
    }
    
    
    @Transactional
    public void batchInsertEmployees(List<Employee> employees) {
        int batchSize = 50;
        for (int i = 0; i < employees.size(); i++) {
            entityManager.persist(employees.get(i));
            if (i % batchSize == 0 && i > 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
    }
    
    
    
    //custom named queries
    public List<Employee> findEmployeesByDepartmentName(String departmentName) {
        return entityManager.createNamedQuery("Employee.findByDepartmentName", Employee.class)
                            .setParameter("departmentName", departmentName)
                            .getResultList();
    }

    public Employee findEmployeeByEmail(String email) {
        return entityManager.createNamedQuery("Employee.findByEmailQuery", Employee.class)
                            .setParameter("email", email)
                            .getSingleResult();
    }

    public List<Employee> findEmployeesByNameContainingIgnoreCase(String name) {
        return entityManager.createNamedQuery("Employee.findByNameContainingIgnoreCase", Employee.class)
                            .setParameter("name", name)
                            .getResultList();
    }
    
    
    // CRUD operations
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
