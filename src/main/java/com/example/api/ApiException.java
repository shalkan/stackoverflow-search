package com.example.api;

@javax.annotation.Generated(value = "com.example.codegen.v3.generators.java.SpringCodegen", date = "2019-01-21T14:54:46.412Z[GMT]")
public class ApiException extends Exception{
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
