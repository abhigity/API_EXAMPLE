package com.api.dto;

import java.util.Date;

public class ErrorDto {
    //"timestamp": "2025-11-25T06:16:26.374+00:00",
    //    "status": 500,
    //    "error": "Internal Server Error",
    //    "path": "/api/v1/employee/id"
    private String message;
    private int statusCode;
    private Date date;
    private String url;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
