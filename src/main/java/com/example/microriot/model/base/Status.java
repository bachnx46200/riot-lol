package com.example.microriot.model.base;

public class Status {
    public static String SUCCESS = "SUCCESS";
    public static String FAIL = "FAIL";

    private String code = "";
    private String message = "";

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
