package com.airline.airport_management_demo.utilities;

public class APIResponse<T> {

    private int status; // HTTP status code
    private String message; // Success or error message
    private T data; // The response payload (can be null for errors)

    // Constructors
    public APIResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public APIResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    // Getters and Setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

