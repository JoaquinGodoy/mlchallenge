/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.bean.rq;

import java.io.Serializable;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Bean for the REST input data. 
 * @author jgodoy
 */
public class InputRQ implements Serializable {
    
    @Valid
    @NotNull(message="Los satelites no pueden estar vacios")
    private List<SatelliteRQ> satellites;

    public List<SatelliteRQ> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<SatelliteRQ> satellites) {
        this.satellites = satellites;
    }
    
}
