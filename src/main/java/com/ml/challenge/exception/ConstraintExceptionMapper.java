/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.exception;

import com.ml.challenge.bean.rs.ErrorRS;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;



/**
 * Maps the validation beans exceptions for the JSON REST Response.
 * 
 * @author jgodoy
 */
@ControllerAdvice
public class ConstraintExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    
    /**
     * LOGGER.
     */
    private static final Logger LOG = Logger.getLogger(ConstraintExceptionMapper.class);
    
    /**
     * Metodo que prepara la respuesta tomando el mensaje de la excepcion y
     * setteandolo a un objeto de tipo SpSwitchResponse el cual se inserta como
     * parte de la respuesta del API
     *
     * @param ex
     * @return Response objeto del api de JERSEY
     */
    @Override
    public Response toResponse(ConstraintViolationException ex) {
        
        List<String> errors = new ArrayList<>();

        
        ErrorRS error = new ErrorRS();

        ex.getConstraintViolations().stream().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath() + " - " + constraintViolation.getMessage());
            LOG.error(constraintViolation.getPropertyPath() + " - " + constraintViolation.getMessage());
        });
        
        if(errors.isEmpty()) {
            errors.add(ex.getMessage());
        }

        error.setCode(ConstraintExceptionMapper.class.getSimpleName());
        error.setDescription(errors.toString());

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
