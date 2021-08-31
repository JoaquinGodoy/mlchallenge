/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.exception;

import com.ml.challenge.bean.rs.ErrorRS;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Exception for the Resource that doesnt exist. 
 * @author jgodoy
 */
@ControllerAdvice
public class InvalidResourceException implements ExceptionMapper<NotFoundException> {
    
    private static final Logger LOG = Logger.getLogger(InvalidResourceException.class);

    /**
     * Mapeo de de recurso inválido.
     * @param notFoundException
     * @return 
     */
    @Override
    public Response toResponse(NotFoundException notFoundException) {
        
        LOG.error("Valor: " + notFoundException.getMessage());
        LOG.error("El recurso que se intento acceder no es válido." + notFoundException.getMessage());
        
        ErrorRS error = new ErrorRS();
        
        error.setCode(InvalidResourceException.class.getName());
        error.setDescription(notFoundException.getMessage());

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    
}
