package com.codegym.blog.model;

import lombok.Data;

@Data
public class Response {
    private int status;
    private String message;
    private Object data;
}
