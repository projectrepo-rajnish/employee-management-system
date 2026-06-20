package com.rajnish.ems.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajnish.ems.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{

	public boolean existsByEmployeeCode(String name);
	public boolean existsByEmail(String email);
}
