/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.bean.rs;

import java.io.Serializable;

/**
 * The Top Secret Information of the Rebelion. (Do not share)
 * @author jgodoy
 */
public class TopSecretRS implements Serializable {
    
    /**
     * The coordenates for triangulate the position. 
     */
    public PositionRS position;
    
    /**
     * The distress message. 
     */
    public String message;

    public PositionRS getPosition() {
        return position;
    }

    public void setPosition(PositionRS position) {
        this.position = position;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
