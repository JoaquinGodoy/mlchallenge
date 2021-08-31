/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.service.impl;

import com.ml.challenge.bean.KenobiSatellite;
import com.ml.challenge.bean.SatoSatellite;
import com.ml.challenge.bean.SkywalkerSatellite;
import com.ml.challenge.bean.rq.InputRQ;
import com.ml.challenge.bean.rq.SatelliteRQ;
import com.ml.challenge.bean.rq.SatelliteSplitRQ;
import com.ml.challenge.bean.rs.PositionRS;
import com.ml.challenge.bean.rs.TopSecretRS;
import com.ml.challenge.exception.InsufficientInformationException;
import com.ml.challenge.exception.ItsaTrapException;
import com.ml.challenge.exception.MlException;
import com.ml.challenge.service.TopSecretService;
import com.ml.challenge.util.Codes.SatellitesNames;
import com.ml.challenge.util.Secret;
import com.ml.challenge.util.Validation;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * The service class to define all the TopSecretSevice.  
 * @author jgodoy
 */
@Service
public class TopSecretServiceImpl implements TopSecretService {
    
    private static final Logger LOG = Logger.getLogger(TopSecretServiceImpl.class);

    @Override
    public TopSecretRS provideTopSecret(InputRQ inputRQ) throws MlException {
        TopSecretRS topSecretRS = new TopSecretRS();
        
        Validation.validateInputRQ(inputRQ);
        
        Double kenobiDistance = null;
        Double skywalkerDistance = null;
        Double satoDistance = null;
        
        List<String> kenobiMessages = new ArrayList<String>();
        List<String> skywalkerMessages = new ArrayList<String>();
        List<String> satoMessages = new ArrayList<String>();
        
        for(SatelliteRQ satellite : inputRQ.getSatellites()){
            String satName = satellite.getName();
            if(satName != null){
                if(SatellitesNames.KENOBI.equalsIgnoreCase(satName)){
                    kenobiDistance = satellite.getDistance();
                    kenobiMessages = satellite.getMessage();
                    continue;
                } else if(SatellitesNames.SKYWALKER.equalsIgnoreCase(satName)){
                    skywalkerDistance = satellite.getDistance();
                    skywalkerMessages = satellite.getMessage();
                    continue;
                } else if(SatellitesNames.SATO.equalsIgnoreCase(satName)){
                    satoDistance = satellite.getDistance();
                    satoMessages = satellite.getMessage();
                    continue;
                } else{
                    throw new ItsaTrapException();
                }
            } else {
                LOG.error("Imposible determinar el nombre del satelite.");
            }
        }
        
        if(kenobiDistance == null || skywalkerDistance == null || satoDistance == null){
            throw new ItsaTrapException();
        }
        
        PositionRS posRS = Secret.getLocation(kenobiDistance, skywalkerDistance, satoDistance);
        topSecretRS.setPosition(posRS);
        
        String message = Secret.getMessage(kenobiMessages, skywalkerMessages, satoMessages);
        topSecretRS.setMessage(message);
        
        return topSecretRS;
        
    }
    
    

    @Override
    public void postTopSecretSplit(SatelliteSplitRQ satelliteSplitRQ, String satelliteName) throws MlException {
        
        Validation.validateSatelliteSplitRQ(satelliteSplitRQ);
        
        if(satelliteName != null){
            if(SatellitesNames.KENOBI.equalsIgnoreCase(satelliteName)){
                KenobiSatellite.getInstance().setDistance(satelliteSplitRQ.getDistance());
                KenobiSatellite.getInstance().setMessages(satelliteSplitRQ.getMessage());
            } else if(SatellitesNames.SKYWALKER.equalsIgnoreCase(satelliteName)){
                SkywalkerSatellite.getInstance().setDistance(satelliteSplitRQ.getDistance());
                SkywalkerSatellite.getInstance().setMessages(satelliteSplitRQ.getMessage());
            } else if(SatellitesNames.SATO.equalsIgnoreCase(satelliteName)){
                SatoSatellite.getInstance().setDistance(satelliteSplitRQ.getDistance());
                SatoSatellite.getInstance().setMessages(satelliteSplitRQ.getMessage());
            } else{
                throw new ItsaTrapException();
            }
        } else {
            LOG.error("Imposible determinar el nombre del satelite.");
        }
    }

    @Override
    public TopSecretRS getTopSecretSplit() throws MlException {
        
        TopSecretRS topSecretRS = new TopSecretRS();
        
        if(KenobiSatellite.getInstance().getDistance() == null || KenobiSatellite.getInstance().getMessages().isEmpty()){
            throw new InsufficientInformationException();
        } else if(SkywalkerSatellite.getInstance().getDistance() == null || SkywalkerSatellite.getInstance().getMessages().isEmpty()){
            throw new InsufficientInformationException();
        } else if(SatoSatellite.getInstance().getDistance() == null || SatoSatellite.getInstance().getMessages().isEmpty()){
            throw new InsufficientInformationException();
        } 
        
        PositionRS posRS = Secret.getLocation(KenobiSatellite.getInstance().getDistance(),
                SkywalkerSatellite.getInstance().getDistance(), 
                SatoSatellite.getInstance().getDistance());
        topSecretRS.setPosition(posRS);
        
        String message = Secret.getMessage(KenobiSatellite.getInstance().getMessages(), 
                SkywalkerSatellite.getInstance().getMessages(), 
                SatoSatellite.getInstance().getMessages());
        topSecretRS.setMessage(message);
        
        return topSecretRS;
    }
    
}
