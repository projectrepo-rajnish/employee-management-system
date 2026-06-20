package com.rajnish.ems.employee.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajnish.ems.employee.dto.CreateEmployeeRequest;
import com.rajnish.ems.employee.dto.EmployeeResponse;
import com.rajnish.ems.employee.service.EmployeeService;
import com.rajnish.ems.response.ApiResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

	private EmployeeService services;
	
	public EmployeeResponse createEmployee(@Valid @RequestBody CreateEmployeeRequest request){
		
		EmployeeResponse createEmployee = this.services.createEmployee(request);
		return createEmployee;
	}
}
