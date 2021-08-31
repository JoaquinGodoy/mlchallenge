/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.ml.challenge.bean.rs.ErrorRS;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Exception for the data contained in the Request JSON.
 * @author jgodoy
 */
@ControllerAdvice
public class MappingException implements ExceptionMapper<JsonMappingException> {
    
    private static final Logger LOG = Logger.getLogger(MappingException.class);

    @Override
    public Response toResponse(JsonMappingException exception) {
        LOG.error("Error de formato", exception);
        
        ErrorRS error = new ErrorRS();

        error.setCode(MappingException.class.getSimpleName());
        error.setDescription(exception.getMessage());

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    
}