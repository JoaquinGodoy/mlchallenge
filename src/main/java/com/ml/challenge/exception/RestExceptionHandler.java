/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.exception;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.ml.challenge.bean.rs.ErrorRS;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.WebApplicationException;
import org.apache.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author jgodoy
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    private static final Logger LOG = Logger.getLogger(RestExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";

        ErrorRS er = new ErrorRS();
        er.setDescription(ex.getMessage());

        return new ResponseEntity(er, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        ErrorRS er = new ErrorRS();
        er.setDescription(ex.getMessage());

        return new ResponseEntity(er, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(UnrecognizedPropertyException.class)
    protected ResponseEntity<Object> handlePropertyNotRecognized(
            UnrecognizedPropertyException ex) {
        
        ErrorRS error = new ErrorRS();

        error.setCode(UnrecognizedFieldException.class.getSimpleName());
        error.setDescription("El campo " + ex.getPropertyName() + " no es un campo válido en el JSON. ");

        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(WebApplicationException.class)
    protected ResponseEntity<Object> handlePropertyNotRecognized(
            WebApplicationException webApplicationException) {
        
        LOG.error("Valor: " + webApplicationException.getMessage());
        LOG.error("Recurso inválido o desconocido.", webApplicationException);        
        
        ErrorRS error = new ErrorRS();

        error.setCode(WebApplicationExceptionMapper.class.getSimpleName());
        error.setDescription(webApplicationException.getMessage());

        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
    
    
    
    
    

}
