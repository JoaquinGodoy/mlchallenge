/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.web.rest.provider;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;


/**
 * 
 * @author jgodoy
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonJsonProvider extends JacksonJaxbJsonProvider {

    public JacksonJsonProvider() {
          ObjectMapper mapper = new ObjectMapper();
          mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
          mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
          mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
          mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
          super.setMapper(mapper);
    }
}

