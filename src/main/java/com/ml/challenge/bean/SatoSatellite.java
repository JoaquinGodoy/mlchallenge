/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.bean;

import com.ml.challenge.bean.rs.PositionRS;

/**
 * Definition por the Sato Satellite (Singleton).
 * @author jgodoy
 */
public class SatoSatellite extends Satellite {
    
    /**
     * Private instance for the unique instance. 
     */
    private static SatoSatellite INSTANCE;
    
    /**
     * Private constructor for the singleton. 
     * @param x
     * @param y 
     */
    private SatoSatellite(Double x, Double y){
        this.setPosition(new PositionRS(x, y));
        this.setName("Sato");
    }
    
    public static SatoSatellite getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new SatoSatellite(500.00, 100.00);
        }
        return INSTANCE;
    }
}
