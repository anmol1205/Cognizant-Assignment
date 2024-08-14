package com.example.EmployeeManagementSystem.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import javax.persistence.*;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
public class Employee {
	
	//setting the primary key of the table
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;
    
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
    
    public Employee() {
    	
    }
    
    public Employee(String name, String email, Department department) {
        this.name = name;
        this.email = email;
        this.department = department;
    }

	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public Object getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	public Object getEmail() {
		return this.email;
	}

	public Object getDepartment() {
		// TODO Auto-generated method stub
		return this.department;
	}

	public void setName(Object name2) {
		// TODO Auto-generated method stub
		this.name = (String) name2;
	}

	public void setEmail(Object email2) {
		// TODO Auto-generated method stub
		this.email = (String) email2;
	}

	public void setDepartment(Object department2) {
		// TODO Auto-generated method stub
		this.department = (Department) department2;
	}
	
	
	
	
	
	
	
}
