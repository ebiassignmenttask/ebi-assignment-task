package com.ebi.assignment.task.ebiassignmenttask.exception;

import java.sql.SQLSyntaxErrorException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PersonExceptionHandler {

	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, Object> errorResponce = new LinkedHashMap<>();

        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());

        errorResponce.put("timestamp", DATE_FORMAT.format(new Date()));
        errorResponce.put("status_type", HttpStatus.BAD_REQUEST);
        errorResponce.put("status_value", HttpStatus.BAD_REQUEST.value());
        errorResponce.put("errors", errors);

        return errorResponce;
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String, Object> handleHttpMessageNotReadableExceptions(HttpMessageNotReadableException ex) {

        Map<String, Object> errorResponce = new LinkedHashMap<>();

        errorResponce.put("timestamp", DATE_FORMAT.format(new Date()));
        errorResponce.put("status_type", HttpStatus.BAD_REQUEST);
        errorResponce.put("status_value", HttpStatus.BAD_REQUEST.value());
        errorResponce.put("errors", ex.getMessage());

        return errorResponce;
    }
      
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, Object> handleConstraintViolationExceptions(ConstraintViolationException ex) {
        
    	Map<String, Object> errorResponce = new LinkedHashMap<>();

        List<String> errors = new ArrayList<String>();
        
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
			errors.add(constraintViolation.getMessage());
		}

        errorResponce.put("timestamp", DATE_FORMAT.format(new Date()));
        errorResponce.put("status_type", HttpStatus.BAD_REQUEST);
        errorResponce.put("status_value", HttpStatus.BAD_REQUEST.value());
        errorResponce.put("errors", errors);

        return errorResponce;
    }
    
    
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLSyntaxErrorException.class)
    public Map<String, Object> handleSQLSyntaxErrorExceptions(SQLSyntaxErrorException ex) {
        
    	Map<String, Object> errorResponce = new LinkedHashMap<>();

        errorResponce.put("timestamp", DATE_FORMAT.format(new Date()));
        errorResponce.put("status_type", HttpStatus.BAD_REQUEST);
        errorResponce.put("status_value", HttpStatus.BAD_REQUEST.value());
        errorResponce.put("errors", ex.getMessage());

        return errorResponce;
    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IllegalArgumentException.class)
    public Map<String, Object> handleIllegalArgumentExceptions(IllegalArgumentException ex) {
        
    	Map<String, Object> errorResponce = new LinkedHashMap<>();

        errorResponce.put("timestamp", DATE_FORMAT.format(new Date()));
        errorResponce.put("status_type", HttpStatus.BAD_REQUEST);
        errorResponce.put("status_value", HttpStatus.BAD_REQUEST.value());
        errorResponce.put("errors", ex.getMessage());

        return errorResponce;
    }
}
