package com.rajnish.ems.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T>{

	private String message;
	private boolean success;
	private T data;
	private LocalDateTime timeStamp;
	
	public static <T> ApiResponse<T> success(String message, T data){
		
		ApiResponse response=new ApiResponse();
		response.setMessage(message);
		response.setSuccess(true);
		response.setData(data);
		response.setTimeStamp(LocalDateTime.now());
		
		return response;
	}
	
   public static <T> ApiResponse<T> failure(String message, T data){
		
		ApiResponse response=new ApiResponse();
		response.setMessage(message);
		response.setSuccess(false);
		response.setData(data);
		response.setTimeStamp(LocalDateTime.now());
		
		return response;
	}
}
