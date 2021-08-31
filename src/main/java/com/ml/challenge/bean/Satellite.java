/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.bean;

import com.ml.challenge.bean.rs.PositionRS;
import java.util.List;

/**
 * Definition por the Generic Satellite.
 * @author jgodoy
 */
public class Satellite {
    
    private PositionRS position;
    
    private Double distance;

    private List<String> messages;
    
    private String name;

    public PositionRS getPosition() {
        return position;
    }

    public void setPosition(PositionRS position) {
        this.position = position;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
