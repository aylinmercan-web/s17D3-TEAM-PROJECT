package com.workintech.zoo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ZooGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handlerException(ZooException zooException){
        ZooErrorResponse zooErrorResponse = new ZooErrorResponse(
                zooException.getMessage(),
                zooException.getHttpStatus().value(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(zooErrorResponse,zooException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handlerException(Exception exception){
        ZooErrorResponse zooErrorResponse = new ZooErrorResponse(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(zooErrorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
