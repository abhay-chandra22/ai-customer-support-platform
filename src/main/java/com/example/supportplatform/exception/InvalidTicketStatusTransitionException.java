package com.example.supportplatform.exception;

public class InvalidTicketStatusTransitionException extends RuntimeException{

    public InvalidTicketStatusTransitionException(String message){
        super(message);
    }
}
