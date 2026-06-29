package com.rajnish.ems.employee.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajnish.ems.employee.dto.CreateEmployeeRequest;
import com.rajnish.ems.employee.dto.EmailExistsResponse;
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
	
	@PostMapping
	public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(@Valid @RequestBody CreateEmployeeRequest request){
		
		EmployeeResponse createEmployee = this.services.createEmployee(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Employee created", createEmployee));
	}
	
	
	@GetMapping("/mesg1")
	public String getMessage1() {
	
		return "Hello from getMessage1";
	}
	
	@GetMapping("/mesg2")
	public String[] getMessage2() {
		
		String [] a= {"Message1","Message2"};
		return a;
	}
	
	@GetMapping("/mesg3")
	public ResponseEntity< String> getMessage3() {
	
		return ResponseEntity.ok("Message3");
	}
	
	@GetMapping("/mesg4")
	public ResponseEntity< String> getMessage4() {
	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message4");
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<Page<EmployeeResponse>>> getAllEmployee(@RequestParam(defaultValue ="0") int page, @RequestParam(defaultValue ="10") int size){
	    
		
		Page<EmployeeResponse> allEmployees = this.services.getAllEmployees(page, size);
		
	    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Data fetched successfully!", allEmployees));
	}
	
	@GetMapping("/exist-by-email")
	public ResponseEntity<ApiResponse<EmailExistsResponse>> existByEmail(@RequestParam String email){
		
		EmailExistsResponse existsByEmail = this.services.existsByEmail(email);
		
		return ResponseEntity.ok(ApiResponse.success(existsByEmail.getMessage(),existsByEmail));
		
	}
	
}
