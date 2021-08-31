/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.util;

/**
 *
 * @author jgodoy
 */
public abstract class Codes {
    
    /**
     * Private constuctor. Never allow instance
     */
    private Codes(){
        //Nothing here
    }
    
    public static final String QUASAR_URL = "/quasar";
    
    public static final String TOP_SECRET_URL = "/topsecret";
    
    public static final String TOP_SECRET_SPLIT_URL = "/topsecret_split";
    
    public static class SatellitesNames{
        public static final String KENOBI = "KENOBI";
        public static final String SKYWALKER = "SKYWALKER";
        public static final String SATO = "SATO";
    }
}
