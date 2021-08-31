/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.bean.rq;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Bean for satellite split data in the REST data.
 * @author jgodoy
 */
public class SatelliteSplitRQ {
    
    @Valid
    @NotNull(message="El valor de la distancia no puede estár vacio.")
    private Double distance;
    
    private List<String> message;

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}
