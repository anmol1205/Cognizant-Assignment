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
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.example.EmployeeManagementSystem.projection.EmployeeProjection;
import com.example.EmployeeManagementSystem.dto.EmployeeDTO;
import com.example.EmployeeManagementSystem.repository.primary.EmployeePrimaryRepository;
import com.example.EmployeeManagementSystem.repository.secondary.DepartmentSecondaryRepository;


import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class EmployeeManagementSystemApplication implements CommandLineRunner {
	
	@Autowired
    private EmployeeService employeeService;
	
	@Autowired
	private EmployeePrimaryRepository employeePrimaryRepository;

	@Autowired
	private DepartmentSecondaryRepository departmentSecondaryRepository;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
		
		
		
		
		
//         Create departments
        Department department1 = new Department("HR");
        Department department2 = new Department("IT");
        Department department3 = new Department("Product");

        employeeService.saveDepartment(department1);
        employeeService.saveDepartment(department2);

        // Create employees
        Employee employee1 = new Employee("John Doe", "john.doe@example.com", department1);
        Employee employee2 = new Employee("Jane Smith", "jane.smith@example.com", department2);
        Employee employee4 = new Employee("Hari Kuchipudi", "harikuchipudi1@gmail.com", department3);
        

        employeeService.saveEmployee(employee1);
        employeeService.saveEmployee(employee2);

        // Fetch all employees
        List<Employee> employees = employeeService.getAllEmployees();
        System.out.println("All Employees: " + employees);

        // Fetch employee by ID
        Employee employee = employeeService.getEmployeeById(employee1.getId()).orElse(null);
        System.out.println("Employee with ID " + employee1.getId() + ": " + employee);
        


        // Fetch employees by name
        List<Employee> employeesByName = employeeService.getEmployeeByName("Hari Kuchipudi");
        System.out.println("Employees with name 'John Doe': " + employeesByName);
        //auditing methods for exercise 7
        System.out.println("------Output for Exercise 7: Auditing-----------");
        System.out.println("Created Date: " + employee.getCreatedDate());
        System.out.println("Last Modified Date: " + employee.getLastModifiedDate());

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
        
        
        //Testing custom queries
        System.out.println("----- Testing Custom Queries -----");
        
        // Find employees by department name
        List<Employee> employeesInHR = employeeService.findEmployeesByDepartmentName("HR");
        System.out.println("Employees in HR department: " + employeesInHR);

        // Find employee by email
        Employee employeeWithEmail = employeeService.findEmployeeByEmail("john.doe@example.com");
        System.out.println("Employee with email 'john.doe@example.com': " + employeeWithEmail);

        // Find employees by name containing (case-insensitive)
        List<Employee> employeesWithDoe = employeeService.findEmployeesByNameContainingIgnoreCase("Doe");
        System.out.println("Employees with names containing 'Doe': " + employeesWithDoe);
        
        
        System.out.println("-------Testing Projections---------");
        
        List<EmployeeProjection> employeeProjections = employeeService.getAllEmployeeProjections();
	    employeeProjections.forEach(ep -> System.out.println("Name: " + ep.getName() + ", Email: " + ep.getEmail()));

	    // Test class-based projection
	    List<EmployeeDTO> employeeDTOs = employeeService.getEmployeeDTOs();
	    employeeDTOs.forEach(dto -> System.out.println(dto));
	    
	    
	    
	    
	    //// Interact with the primary data source
	    System.out.println("Exercise-9 implementation");
	    System.out.println("---------Customizing Data Source Configuration---------------");
	    Department department9 = new Department("SDE-9");
	    
        Employee employee9 = new Employee("Khushi", "happiness@example.com", department9);
        employeePrimaryRepository.save(employee9);

        // Interact with the secondary data source
        Department department = new Department("HR");
        departmentSecondaryRepository.save(department);

        // Verify data in both data sources
        System.out.println("Employees: " + employeePrimaryRepository.findAll());
        System.out.println("Departments: " + departmentSecondaryRepository.findAll());
        
       
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



