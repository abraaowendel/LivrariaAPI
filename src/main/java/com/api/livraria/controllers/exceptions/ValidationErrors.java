package com.api.livraria.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrors {
    private Integer status;
    private List<String> messages = new ArrayList<>();
    private String timestamp;

    public ValidationErrors() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
