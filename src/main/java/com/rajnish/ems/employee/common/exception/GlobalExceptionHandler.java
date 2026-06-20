package com.rajnish.ems.employee.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rajnish.ems.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	public ResponseEntity<ApiResponse<Object>> handleResourceNotFoundException(ResourceNotFoundException ex){
		
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.failure(ex.getMessage(), null));
	}
	
	public ResponseEntity<ApiResponse<Object>> handleBadrequestExcpetion(BadRequestException ex){
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.failure(ex.getMessage(), null));
	}
	
	public ResponseEntity<String> handleGenericExcpetion(Exception ex){
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SOMETHONG WEN WRONG..");
	}
}
