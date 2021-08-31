/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.bean.rq;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Bean for satellite data in the REST data. Extends from the @SatelliteSplitRQ
 * @author jgodoy
 */
public class SatelliteRQ extends SatelliteSplitRQ {
    
    @Valid
    @NotNull(message = "Se debe proporcionar un nombre para el satélite")
    @NotBlank(message = "El nombre del satélite no es un nombre válido")
    private String name;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
