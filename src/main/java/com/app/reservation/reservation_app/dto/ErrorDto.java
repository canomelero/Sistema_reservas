package com.app.reservation.reservation_app.dto;

public class ErrorDto {
    private String error;
    private String message;
    private Integer status;
    
    public ErrorDto(String error, String message, Integer status) {
        this.error = error;
        this.message = message;
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Error [error=" + error + ", message=" + message + ", status=" + status + "]";
    }
}
