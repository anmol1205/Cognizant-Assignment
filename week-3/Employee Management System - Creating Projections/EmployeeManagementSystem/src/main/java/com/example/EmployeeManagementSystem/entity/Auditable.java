package com.example.EmployeeManagementSystem.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDateTime;



@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {
	
//	@CreatedBy
//	@Column(name = "created_by")
//	private String createdBy;
//
//	@LastModifiedBy
//	@Column(name = "last_modified_by")
//	private String lastModifiedBy;
	
	@CreatedBy
	@Column(name = "created_by")
	private String createdBy;

	@LastModifiedBy
	@Column(name = "last_modified_by")
	private String lastModifiedBy;
	
	@CreatedDate
    @Column(name = "created_date", nullable= false, updatable = false)
    private LocalDateTime createdDate;

	@LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;
	
	//getters and setters
	public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
