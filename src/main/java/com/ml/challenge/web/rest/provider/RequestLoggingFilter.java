/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.web.rest.provider;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 * Logger for obtain the complete data of the request. 
 * @author jgodoy
 */
@Provider
@PreMatching
public class RequestLoggingFilter implements ContainerRequestFilter {

    private Logger LOG = Logger.getLogger(RequestLoggingFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Method: ").append(requestContext.getMethod());
            sb.append("\nUser: ").append(requestContext.getSecurityContext().getUserPrincipal() == null ? "unknown"
                    : requestContext.getSecurityContext().getUserPrincipal());
            sb.append("\n - Path: ").append(requestContext.getUriInfo().getPath());
            sb.append("\n - Header: ").append(requestContext.getHeaders());
            LOG.info("HTTP REQUEST : " + sb.toString());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IOUtils.copy(requestContext.getEntityStream(), baos);
            byte[] bytes = baos.toByteArray();
            
            String postedString = new String(bytes, "UTF-8");            
            LOG.info("Posted: " + postedString);
            requestContext.setEntityStream(new ByteArrayInputStream(bytes));
        } catch (Exception ex) {
            LOG.error(ex);
        }

    }
}
