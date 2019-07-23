package com.lambdaschool.school.model;

// adapted from https://tools.ietf.org/html/rfc7807

import com.lambdaschool.school.exceptions.ValidationError;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorDetail
{
    // Fields
    private String title;
    private int status;
    private String detail;
    private String timestamp;
    private String developerMessage;
    private Map<String, List<ValidationError>> errors = new HashMap<>();

    // Constructor
    // No constructor = always use default constructor

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp)
    {
        // Converting a Date that has a "Long" type to an actual String that we can display for users
        // Take in a long, and turn it into a date.
        // Then, take that date and turn it into a SimpleDateFormat using this pattern.
        this.timestamp = new SimpleDateFormat("dd MMM yyyy HH:mm::ss:SSS Z").format(new Date(timestamp));
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public Map<String, List<ValidationError>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, List<ValidationError>> errors) {
        this.errors = errors;
    }
}
