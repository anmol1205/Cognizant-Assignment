package com.example.EmployeeManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.EmployeeManagementSystem.entity.Department;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class EmployeeManagementSystemApplication implements CommandLineRunner {
	
	@Autowired
    private EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
        // Create departments
        Department department1 = new Department("HR");
        Department department2 = new Department("IT");

        employeeService.saveDepartment(department1);
        employeeService.saveDepartment(department2);

        // Create employees
        Employee employee1 = new Employee("John Doe", "john.doe@example.com", department1);
        Employee employee2 = new Employee("Jane Smith", "jane.smith@example.com", department2);

        employeeService.saveEmployee(employee1);
        employeeService.saveEmployee(employee2);

        // Fetch all employees
        List<Employee> employees = employeeService.getAllEmployees();
        System.out.println("All Employees: " + employees);

        // Fetch employee by ID
        Employee employee = employeeService.getEmployeeById(employee1.getId()).orElse(null);
        System.out.println("Employee with ID " + employee1.getId() + ": " + employee);

        // Fetch employees by name
        List<Employee> employeesByName = employeeService.getEmployeeByName("John Doe");
        System.out.println("Employees with name 'John Doe': " + employeesByName);

        // Fetch employees by department
        List<Employee> employeesInIT = employeeService.getEmployeesByDepartmentId(department2.getId());
        System.out.println("Employees in IT department: " + employeesInIT);

        // Fetch employee by email
        Employee employeeByEmail = employeeService.getEmployeeByEmail("jane.smith@example.com");
        System.out.println("Employee with email 'jane.smith@example.com': " + employeeByEmail);

        // Fetch employees with names containing "Jane"
        List<Employee> employeesWithNameContaining = employeeService.getEmployeesByNameContaining("Jane");
        System.out.println("Employees with names containing 'Jane': " + employeesWithNameContaining);

        // Delete an employee by ID
        employeeService.deleteEmployee(employee2.getId());
        System.out.println("Employee with ID " + employee2.getId() + " has been deleted.");

        // Fetch all employees after deletion
        employees = employeeService.getAllEmployees();
        System.out.println("All Employees after deletion: " + employees);
    }
	//output for the above code:
	//All Employees: [com.example.EmployeeManagementSystem.entity.Employee@6edb093f, com.example.EmployeeManagementSystem.entity.Employee@6805415d]
	//Employee with ID 1: com.example.EmployeeManagementSystem.entity.Employee@1a916120
	//Employees with name 'John Doe': [com.example.EmployeeManagementSystem.entity.Employee@6d3194ff]
	//Employees in IT department: [com.example.EmployeeManagementSystem.entity.Employee@701c413]
	//Employee with email 'jane.smith@example.com': com.example.EmployeeManagementSystem.entity.Employee@7cb4d4ee
	//Employees with names containing 'Jane': [com.example.EmployeeManagementSystem.entity.Employee@7894a250]
	//Employee with ID 2 has been deleted.
	//All Employees after deletion: [com.example.EmployeeManagementSystem.entity.Employee@140fa482]

}



