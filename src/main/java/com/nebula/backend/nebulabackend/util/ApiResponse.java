package com.nebula.backend.nebulabackend.util;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    private T data;
    private String message;
    private boolean success;
    private HttpStatus statusCode;

    public ApiResponse(T data, String message, boolean success, HttpStatus statusCode) {
        this.data = data;
        this.message = message;
        this.success = success;
        this.statusCode = statusCode;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(data, "Operação bem-sucedida", true, HttpStatus.OK);
    }

    public static <T> ApiResponse<T> error(String message, HttpStatus statusCode) {
        return new ApiResponse<T>(null, message, false, statusCode);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    };    
    
}
