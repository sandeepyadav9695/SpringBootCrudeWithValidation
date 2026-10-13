package com.inwithsky.CrudeSpringBootDemo.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidExceptionResponseDto {
    private LocalDateTime dateTime;
    private int stutasCode;
    private String error;
    private String message;
    private String path;
    private Map<String,String> fieldError;

    public ValidExceptionResponseDto(LocalDateTime dateTime, int stutasCode, String error, String message, String path, Map<String, String> fieldError) {
        this.dateTime = dateTime;
        this.stutasCode = stutasCode;
        this.error = error;
        this.message = message;
        this.path = path;
        this.fieldError = this.fieldError;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getStutasCode() {
        return stutasCode;
    }

    public void setStutasCode(int stutasCode) {
        this.stutasCode = stutasCode;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getFieldError() {
        return fieldError;
    }

    public void setFieldError(Map<String, String> fieldError) {
        this.fieldError = fieldError;
    }
}
