/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.exception;

/**
 * Generic Exception for the proyect. (All the exceptions mus be extended from
 * this Exception).
 * @author jgodoy
 */
public class MlException extends Exception {
    private Integer codigo;
    
    private String message = "";
    
    public MlException(){
    }
    
    public MlException(int codigo) {
        this.codigo = codigo;
    }

    public MlException(String message) {
        super(message);
        this.message = message;
    }
    
    public MlException(int codigo, String message) {
        this.codigo = codigo;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
    
    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }
    
    @Override
    public String toString(){
        return message = "Generic MlException: " + message;
    }
}
