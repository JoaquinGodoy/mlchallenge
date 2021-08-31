/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.exception;

/**
 * The exception fo te inssufficient data from the REST Request. 
 * @author jgodoy
 */
public class InsufficientInformationException extends MlException {
    public InsufficientInformationException(int codigo) {
        super(codigo);
    }

    public InsufficientInformationException() {
        super("Falta información para decifrar el mensaje y posición. ");
    }
    
    public InsufficientInformationException(String message){
        super(message);
    }
    
    public InsufficientInformationException(int codigo, String message) {
        super(codigo, message);
    }
    
    @Override
    public String toString(){
        return "InsufficientInformationException:" +  this.getMessage();
    }
}
