/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.bean;

import com.ml.challenge.bean.rs.PositionRS;

/**
 * Definition por the Kenobi Satellite (Singleton).
 * @author jgodoy
 */
public class KenobiSatellite extends Satellite {
    
    /**
     * Private instance for the unique instance. 
     */
    private static KenobiSatellite INSTANCE;
    
    /**
     * Private constructor for the singleton. 
     * @param x
     * @param y 
     */
    private KenobiSatellite(Double x, Double y){
        this.setPosition(new PositionRS(x, y));
        this.setName("Kenobi");
    }
    
    public static KenobiSatellite getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new KenobiSatellite(-500.00, -200.00);
        }
        return INSTANCE;
    }
}
