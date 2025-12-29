package com.api.controller.Exception;

import com.api.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    //use for (custom exception)resource not found
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDto> resourceNotFound(
            ResourceNotFound resource, WebRequest webRequest){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(resource.getMessage());
        errorDto.setStatusCode(500);
        errorDto.setDate(new Date());
        errorDto.setUrl(webRequest.getDescription(false));
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //used for All types of Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> globalExceptionHandler(
            Exception e,WebRequest webRequest){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());
        errorDto.setStatusCode(500);
        errorDto.setDate(new Date());
        errorDto.setUrl(webRequest.getDescription(false));
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
