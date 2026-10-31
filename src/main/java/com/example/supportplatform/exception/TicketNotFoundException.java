package com.example.supportplatform.exception;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message){
        super(message);
    }
}
