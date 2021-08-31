/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.exception;

import com.ml.challenge.bean.rs.ErrorRS;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Exception for the validaton of the format JSON. 
 * @author jgodoy
 */
@ControllerAdvice
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    /**
     * LOGGER.
     */
    private static final Logger LOG = Logger.getLogger(ValidationExceptionMapper.class);

    /**
     * Implementacion del metodo toResponse para la interface ExceptionMapper la
     * cual recibe Excepciones de tipo ValidationException y prepara una
     * respuesta de tipo SpWitchResponse en formato JSON
     *
     * @param validationException
     * @return Objeto Response del api JERSEY
     */
    @Override
    public Response toResponse(ValidationException validationException) {

        LOG.error("Exception de validaciones", validationException);
        LOG.error(validationException.getMessage());
        
        ErrorRS error = new ErrorRS();

        error.setCode(ValidationException.class.getSimpleName());
        error.setDescription(validationException.getMessage());

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
