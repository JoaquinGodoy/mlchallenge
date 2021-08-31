/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.exception;

import com.ml.challenge.bean.rs.ErrorRS;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Maps the general exceptions to a object.
 * 
 * @author jgodoy
 */
@ControllerAdvice
public class GeneralExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Logger LOG = Logger.getLogger(GeneralExceptionMapper.class);

    /**
     * Implementacion del metodo toResponse para la interface ExceptionMapper la
     * cual recibe Excepciones de tipo Throwable y prepara una respuesta de tipo
     * SpWitchResponse en formato JSON
     *
     * @param exception
     * @return Objeto Response del api JERSEY
     */
    @Override
    public Response toResponse(Throwable exception) {

        LOG.error("Excepcion no controlada: ", exception);

        
        ErrorRS error = new ErrorRS();

        error.setCode(GeneralExceptionMapper.class.getSimpleName());

        error.setDescription((exception instanceof Exception) ? exception.getMessage() : "Error: " + exception.getMessage());

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
