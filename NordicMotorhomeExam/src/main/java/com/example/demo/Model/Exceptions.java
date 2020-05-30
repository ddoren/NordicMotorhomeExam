package com.example.demo.Model;

public class Exceptions extends  RuntimeException{
    //
    //

    public Exceptions() {
    }

    public Exceptions(String message) {
        super(message);
    }

    public Exceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
