/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.exception;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.ml.challenge.bean.rs.ErrorRS;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Exception for the property of the JSON that is not in the object defined. 
 * @author jgodoy
 */
@ControllerAdvice
public class UnrecognizedFieldException implements ExceptionMapper<UnrecognizedPropertyException> {
    
    private static final Logger LOG = Logger.getLogger(UnrecognizedFieldException.class);

    @Override
    public Response toResponse(UnrecognizedPropertyException exception) {
        LOG.error("Campo no reconocido.", exception);
        
        ErrorRS error = new ErrorRS();

        error.setCode(UnrecognizedFieldException.class.getSimpleName());
        error.setDescription("El campo " + exception.getPropertyName() + " no es un campo válido en el JSON. ");

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    
}