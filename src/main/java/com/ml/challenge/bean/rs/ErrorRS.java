/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.bean.rs;

import java.io.Serializable;

/**
 * Class for the error and code for the error REST Response. 
 *
 * @author Joaquin
 */
public class ErrorRS implements Serializable {

    /**
     * Codigo de exception, se refiere al nombre de la excepcion a lanzar.
     */
    private String code;

    /**
     * Descripcion de la excepcion.
     */
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
