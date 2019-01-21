package com.example.api;

@javax.annotation.Generated(value = "com.example.codegen.v3.generators.java.SpringCodegen", date = "2019-01-21T14:54:46.412Z[GMT]")
public class NotFoundException extends ApiException {
    private int code;
    public NotFoundException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
