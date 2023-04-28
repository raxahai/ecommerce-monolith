package com.raza.ecommerce.exception;

import com.raza.ecommerce.commons.response.CustomResponse;
import com.raza.ecommerce.commons.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CustomResponse<Object> handleException(Exception ex) {
        return CustomResponse.builder()
                .errors(List.of(ErrorResponse.builder().errorCode("Exception-001").errorMessage(ex.getMessage()).build()))
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomResponse<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return CustomResponse.builder()
                .errors(ex.getBindingResult().getFieldErrors().stream().map(val->new ErrorResponse("MA-001", val.getDefaultMessage())).collect(Collectors.toList()))
                .build();
    }

    @ExceptionHandler(ProductException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected CustomResponse<Object> handleProductException(ProductException ex){
        return CustomResponse.builder()
                .errors(List.of(ErrorResponse.builder().errorCode("Product-001").errorMessage(ex.getMessage()).build()))
                .build();
    }
}
