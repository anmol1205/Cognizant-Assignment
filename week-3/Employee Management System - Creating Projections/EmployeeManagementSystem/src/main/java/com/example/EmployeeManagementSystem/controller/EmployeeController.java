package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.service.EmployeeService;


import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
    private EmployeeService employeeService;
	
	//creating a new Employee
	@PostMapping
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	//Get all Employees
//	@GetMapping
//	public List<Employee> getAllEmloyees(){
//		return employeeRepository.findAll();
//	}
	
	@GetMapping("/employees")
    public Page<Employee> getAllEmployeesPaging(Pageable pageable) {
        return employeeService.getAllEmployeesPaging(pageable);
    }
	
	@GetMapping("/employees/search")
    public Page<Employee> searchEmployeesByName(
            @RequestParam("name") String name, 
            Pageable pageable) {
        return employeeService.findEmployeesByNameContainingIgnoreCasePaging(name, pageable);
    }
	
	//Get employee by id
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmloyeeById(@PathVariable Long id){
		 Optional<Employee> employee = employeeRepository.findById(id);
		 if(employee.isPresent()) {
			 return ResponseEntity.ok(employee.get());
		 }
		 else {
			 return ResponseEntity.notFound().build();
		 }
	}
	
	// Update an employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            Employee updatedEmployee = employee.get();
            updatedEmployee.setName(employeeDetails.getName());
            updatedEmployee.setEmail(employeeDetails.getEmail());
            updatedEmployee.setDepartment(employeeDetails.getDepartment());
            return ResponseEntity.ok(employeeRepository.save(updatedEmployee));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	
}
