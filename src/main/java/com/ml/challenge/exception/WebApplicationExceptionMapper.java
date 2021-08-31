/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.exception;

import com.ml.challenge.bean.rs.ErrorRS;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 
 * @author jgodoy
 */
@ControllerAdvice
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
    
    private static final Logger LOG = Logger.getLogger(WebApplicationExceptionMapper.class);

    /**
     * Mapeo de excepcion web.
     * @param webApplicationException
     * @return 
     */
    @Override
    public Response toResponse(WebApplicationException webApplicationException) {
        
        LOG.error("Valor: " + webApplicationException.getMessage());
        LOG.error("Recurso inválido o desconocido.", webApplicationException);        
        
        ErrorRS error = new ErrorRS();

        error.setCode(WebApplicationExceptionMapper.class.getSimpleName());
        error.setDescription(webApplicationException.getMessage());

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    
}
