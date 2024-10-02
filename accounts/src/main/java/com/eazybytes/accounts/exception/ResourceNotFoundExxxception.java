package com.eazybytes.accounts.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundExxxception extends RuntimeException {


    public  ResourceNotFoundExxxception(String resouceName , String filedName , String fieldValue){
        super(String.format("%s not found with the given input data %s : '%s'" , resouceName , filedName , fieldValue));
    }

}
