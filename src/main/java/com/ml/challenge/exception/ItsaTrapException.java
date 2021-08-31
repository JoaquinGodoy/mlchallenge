/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.exception;

/**
 * The Almiral Ackbar says what?
 * @author jgodoy
 */
public class ItsaTrapException extends MlException {
    
    public ItsaTrapException() {
        super("Almiral Ackbar: It's a TRAAAAAP");
    }
    
    @Override
    public String toString(){
        return "Almiral Ackbar: It's a TRAAAAAP";
    }
}
