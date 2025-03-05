package com.nebula.backend.nebulabackend.model;

public class ApiResponse<T> {
    private T data;
    private String message;
    private boolean success;
    private int statusCode;

    public ApiResponse(T data, String message, boolean success, int statusCode) {
        this.data = data;
        this.message = message;
        this.success = success;
        this.statusCode = statusCode;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data, "Operation completed", true, 200);
    }

    public static <T> ApiResponse<T> error(String message, int statusCode) {
        return new ApiResponse<>(null, message, false, statusCode);
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

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

}
