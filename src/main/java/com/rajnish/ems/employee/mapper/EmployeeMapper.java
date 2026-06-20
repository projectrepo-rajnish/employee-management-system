package com.rajnish.ems.employee.mapper;

import org.springframework.stereotype.Component;

import com.rajnish.ems.employee.dto.CreateEmployeeRequest;
import com.rajnish.ems.employee.dto.EmployeeResponse;
import com.rajnish.ems.employee.entity.Employee;
import com.rajnish.ems.employee.enums.EmployeeStatus;

@Component
public class EmployeeMapper {

    public Employee toEntity(CreateEmployeeRequest request) {

        Employee employee = new Employee();

        employee.setEmployeeCode(request.getEmployeeCode());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setDepartment(request.getDepartment());
        employee.setDesignation(request.getDesignation());
        employee.setDateOfJoining(request.getDateOfJoining());
        employee.setStatus(EmployeeStatus.ACTIVE);

        return employee;
    }

    public EmployeeResponse toResponse(Employee employee) {

        EmployeeResponse response = new EmployeeResponse();

        response.setId(employee.getId());
        response.setEmployeeCode(employee.getEmployeeCode());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setEmail(employee.getEmail());
        response.setPhone(employee.getPhone());
        response.setDepartment(employee.getDepartment());
        response.setDesignation(employee.getDesignation());
        response.setDateOfJoining(employee.getDateOfJoining());
        response.setStatus(employee.getStatus());
        response.setCreatedAt(employee.getCreatedAt());
        response.setUpdatedAt(employee.getUpdatedAt());

        return response;
    }
}