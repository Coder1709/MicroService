package com.eazybytes.accounts.exception;


import com.eazybytes.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(ResourceNotFoundExxxception exxxception , WebRequest webRequest){

        ErrorResponseDto error = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exxxception.getMessage(),
                LocalDateTime.now()

        );
        return new ResponseEntity<>(error , HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerALreadyExistException(CustomerAlreadyExistsException exception , WebRequest webRequest)
    {
        ErrorResponseDto error = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error , HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundExxxception.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundExxxception exxxception , WebRequest webRequest){

        ErrorResponseDto error = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exxxception.getMessage(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(error , HttpStatus.NOT_FOUND);

    }


}
