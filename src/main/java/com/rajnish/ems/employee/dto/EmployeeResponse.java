package com.rajnish.ems.employee.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.rajnish.ems.employee.enums.EmployeeStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeResponse {

    private Long id;
    private String employeeCode;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String department;
    private String designation;
    private LocalDate dateOfJoining;
    private EmployeeStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}