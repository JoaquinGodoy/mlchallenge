/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.service;

import com.ml.challenge.bean.rq.InputRQ;
import com.ml.challenge.bean.rq.SatelliteSplitRQ;
import com.ml.challenge.bean.rs.TopSecretRS;
import com.ml.challenge.exception.MlException;

/**
 * This is the TopSecretService Interface.
 * @author jgodoy
 */
public interface TopSecretService {
    
    /**
     * Set all the info for triangulate the position and decoded the message.
     * @param inputRQ
     * @return
     * @throws MlException 
     */
    TopSecretRS provideTopSecret(InputRQ inputRQ) throws MlException;
    
    /**
     * Set thie info of the position and message for one satellite. 
     * @param satelliteSplitRQ
     * @param satelliteName
     * @throws MlException 
     */
    void postTopSecretSplit(SatelliteSplitRQ satelliteSplitRQ, String satelliteName) throws MlException;
    
    /**
     * Return the data of the position and decoded message... if it's possible.
     * @return 
     * @throws MlException 
     */
    TopSecretRS getTopSecretSplit() throws MlException;
}
