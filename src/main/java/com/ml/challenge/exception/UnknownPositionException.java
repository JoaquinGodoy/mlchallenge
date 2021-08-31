/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.exception;

/**
 * Exception for the Unknown Position.
 * @author jgodoy
 */
public class UnknownPositionException extends MlException {
    
    public UnknownPositionException(int codigo) {
        super(codigo);
    }

    public UnknownPositionException(String message) {
        super(message);
    }
    
    public UnknownPositionException(int codigo, String message) {
        super(codigo, message);
    }
    
    @Override
    public String toString(){
        return "UnknownPositionException: Imposible determinar la ubicación. " +  this.getMessage();
    }
}
