package com.rajnish.ems.health;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajnish.ems.response.ApiResponse;

@RestController
public class HealthController {

	Map<String,String> healthData=new HashMap<>();
	
	@GetMapping("/api/health")
	public ResponseEntity<ApiResponse<Map<String,String>>> healthCheck(){
		
		Map<String , String> heatlhMap=new HashMap<>();
		heatlhMap.put("status","UP");
		heatlhMap.put("service","Employee-managament-system");
		ApiResponse<Map<String,String>> apiResponse=ApiResponse.success("Application is running successfully", heatlhMap);
		return ResponseEntity.ok(apiResponse);
	}
	
	
	@GetMapping("/api/message")
	public String getMessage() {
		return "Application is running";
	}
	@GetMapping("/api/message2")
	public Map<String,String> getMoreMessage(){
		
		Map<String,String> message=Map.of(
				"message1","Application is running"
				);
		
		return message;
	}
}
