package com.rajnish.ems.employee.dto;

public class EmailExistsResponse {

    private String email;
    private boolean exists;
    private String message;

    public EmailExistsResponse(String email, boolean exists, String message) {
        this.email = email;
        this.exists = exists;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public boolean isExists() {
        return exists;
    }

    public String getMessage() {
        return message;
    }
}