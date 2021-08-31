/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.bean.rs;

import java.io.Serializable;

/**
 * The objec to define one position. 
 * @author jgodoy
 */
public class PositionRS implements Serializable {
    
    /**
     * The latitude of the position. 
     */
    private Double x;
    
    /**
     * The longitude of the position. 
     */
    private Double y;
    
    public PositionRS(Double x, Double y) {
        this.x = x;
        this.y = y;
    }
    
    public PositionRS(){
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
