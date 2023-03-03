package com.bank.webAppliaction.exception;

public class ContentNoFound extends RuntimeException{

    public ContentNoFound(){
        super("Content no found");
    }

    public ContentNoFound(String message){
        super(message);
    }

}
