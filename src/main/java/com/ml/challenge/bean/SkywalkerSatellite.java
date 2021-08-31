/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.bean;

import com.ml.challenge.bean.rs.PositionRS;

/**
 * Definition por the Skywalker Satellite (Singleton).
 * @author jgodoy
 */
public class SkywalkerSatellite extends Satellite {
    
    /**
     * Private instance for the unique instance. 
     */
    private static SkywalkerSatellite INSTANCE;
    
    /**
     * Private constructor for the singleton. 
     * @param x
     * @param y 
     */
    private SkywalkerSatellite(Double x, Double y){
        this.setPosition(new PositionRS(x, y));
        this.setName("Skywalker");
    }
    
    public static SkywalkerSatellite getInstance(){
        if(INSTANCE == null) {
            INSTANCE = new SkywalkerSatellite(100.00, -100.00);
        }
        return INSTANCE;
    }
}
