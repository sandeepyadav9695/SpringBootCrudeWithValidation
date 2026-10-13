package com.inwithsky.CrudeSpringBootDemo.dto;

import java.time.LocalDateTime;

public class ExceptionDto {
    private LocalDateTime dateTime;
    private int stutasCode;
    private String error;
    private String message;
    private String path;

    public ExceptionDto(LocalDateTime dateTime, int stutasCode, String error, String message, String path) {
        this.dateTime = dateTime;
        this.stutasCode = stutasCode;
        this.error = error;
        this.message = message;
        this.path = path;
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
}
