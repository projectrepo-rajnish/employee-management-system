package com.rajnish.ems.employee.service.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rajnish.ems.employee.common.exception.BadRequestException;
import com.rajnish.ems.employee.common.exception.ResourceNotFoundException;
import com.rajnish.ems.employee.dto.CreateEmployeeRequest;
import com.rajnish.ems.employee.dto.EmailExistsResponse;
import com.rajnish.ems.employee.dto.EmployeeResponse;
import com.rajnish.ems.employee.entity.Employee;
import com.rajnish.ems.employee.mapper.EmployeeMapper;
import com.rajnish.ems.employee.repository.EmployeeRepository;
import com.rajnish.ems.employee.service.EmployeeService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository repository;
	private EmployeeMapper mapper;
	
	
	@Override
	@Transactional
	public EmployeeResponse createEmployee(CreateEmployeeRequest request) {
		
		if(this.repository.existsByEmail(request.getEmail())) {
			throw new BadRequestException("Email already present!");
		}
		
		
		Employee save = repository.save(mapper.toEntity(request));
		EmployeeResponse response = mapper.toResponse(save);
		return response;
		
	}

	@Override
	public EmployeeResponse getEmployeeById(Long id) {
		
		Employee employee = this.repository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Resource not found"));
		
		EmployeeResponse response = mapper.toResponse(employee);
		return response;
	}

	@Override
	public Page<EmployeeResponse> getAllEmployees(int pageno, int pageSize) {
		 Pageable pageable = PageRequest.of(pageno, pageSize, Sort.by(Sort.Direction.DESC, "id"));

	        Page<Employee> employees = repository.findAll(pageable);
	        return employees.map(employee -> mapper.toResponse(employee));
	}

	@Override
	public EmailExistsResponse existsByEmail(String email) {
		// TODO check if emailid present or not
		
		if(email==null || email.trim().isEmpty())
			throw new BadRequestException("Email nis required");
		
		boolean existsByEmail = this.repository.existsByEmail(email);
		String messge=existsByEmail?"Email found":"Email not found";
		
		EmailExistsResponse emailesponse=new EmailExistsResponse(email,existsByEmail,messge);
		return emailesponse;
	}

}
