package com.rajnish.ems.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajnish.ems.employee.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{

	public boolean existsByEmployeeCode(String name);
	public boolean existsByEmail(String email);
}
