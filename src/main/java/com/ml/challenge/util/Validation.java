/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.util;

import com.ml.challenge.bean.rq.InputRQ;
import com.ml.challenge.bean.rq.SatelliteRQ;
import com.ml.challenge.bean.rq.SatelliteSplitRQ;
import com.ml.challenge.exception.InsufficientInformationException;

/**
 *
 * @author jgodoy
 */
public abstract class Validation {
    private Validation(){
    }
    
    public static void validateInputRQ(InputRQ inputRQ) throws InsufficientInformationException {
        if(inputRQ == null) {
            throw new InsufficientInformationException("La entrada no puede estar vacia");
        }
        
        if(inputRQ.getSatellites() == null || inputRQ.getSatellites().isEmpty()){
            throw new InsufficientInformationException("Los datos de los satelites son requeridos para obtener la información");
        }
        
        for(SatelliteRQ satellite : inputRQ.getSatellites()){
            validateSatelliteRQ(satellite);
        }
        
    }
    
    public static void validateSatelliteRQ(SatelliteRQ satelliteRQ) throws InsufficientInformationException {
        if(satelliteRQ == null){
            throw new InsufficientInformationException("Los datos del satelite son requeridos para obtener la información");
        }
        
        if(satelliteRQ.getName() == null){
            throw new InsufficientInformationException("El nombre del satelite no puede estár vacio");
        }
        
        validateSatelliteSplitRQ(satelliteRQ);
        
    }
    
    public static void validateSatelliteSplitRQ(SatelliteSplitRQ satelliteSplitRQ) throws InsufficientInformationException {
        if(satelliteSplitRQ == null){
            throw new InsufficientInformationException("Los datos del satelite son requeridos para obtener la información");
        }
        
        if(satelliteSplitRQ.getMessage() == null || satelliteSplitRQ.getMessage().isEmpty()) {
            throw new InsufficientInformationException("La informacion del mensaje es requerida para decifrarlo");
        }
        
        if(satelliteSplitRQ.getDistance() == null || satelliteSplitRQ.getDistance() < 0) {
            throw new InsufficientInformationException("La informacion de la distancia es requerida para decifrar el mensaje");
        }
        
    }
}
