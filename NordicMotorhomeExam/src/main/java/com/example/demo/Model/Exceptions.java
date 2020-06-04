package com.example.demo.Model;

public class Exceptions extends  RuntimeException{

    //This class is an extension of the RuntimeException class
    //We need this for finding exceptions in the SQL
    public Exceptions() {
    }

    public Exceptions(String message) {
        super(message);
    }

    public Exceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
