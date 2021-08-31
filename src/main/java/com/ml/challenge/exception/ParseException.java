/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.ml.challenge.bean.rs.ErrorRS;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Exception for the JSON Parse. 
 * @author jgodoy
 */
@ControllerAdvice
public class ParseException implements ExceptionMapper<JsonParseException> {

    private static final Logger LOG = Logger.getLogger(ParseException.class);

    @Override
    public Response toResponse(JsonParseException exception) {
        LOG.error("Error de parseo", exception);

        ErrorRS error = new ErrorRS();

        error.setCode(ParseException.class.getSimpleName());
        error.setDescription("El contenido del mensaje no tiene un formato JSON válido");

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
