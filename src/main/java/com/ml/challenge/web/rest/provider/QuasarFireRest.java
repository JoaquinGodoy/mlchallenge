/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.web.rest.provider;

import com.ml.challenge.bean.rq.InputRQ;
import com.ml.challenge.bean.rq.SatelliteSplitRQ;
import com.ml.challenge.bean.rs.ErrorRS;
import com.ml.challenge.bean.rs.TopSecretRS;
import com.ml.challenge.exception.MlException;
import com.ml.challenge.service.TopSecretService;
import com.ml.challenge.util.Codes;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Servide for the Quasar Fire.
 *
 * @author jgodoy
 */
@RestController
public class QuasarFireRest {

    @Autowired
    private TopSecretService topSecretService;

    @PostMapping(Codes.TOP_SECRET_URL)
    public ResponseEntity<TopSecretRS> topSecret(@Valid @RequestBody InputRQ inputRQ) {
        TopSecretRS response = null;
        try {
            response = topSecretService.provideTopSecret(inputRQ);
        } catch (MlException ex) {
            ErrorRS er = new ErrorRS();
            er.setDescription(ex.getMessage());
            if (ex.getCodigo() != null) {
                er.setCode(ex.getCodigo().toString());
            }
            return new ResponseEntity(er, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping(Codes.TOP_SECRET_SPLIT_URL + "/{satName}")
    public ResponseEntity<TopSecretRS> topSecretSplit(@Valid @RequestBody SatelliteSplitRQ satelliteSplitRQ, @PathVariable("satName") String satName) {
        TopSecretRS response = null;
        try {
            topSecretService.postTopSecretSplit(satelliteSplitRQ, satName);
        } catch (MlException ex) {
            ErrorRS er = new ErrorRS();
            er.setDescription(ex.getMessage());
            if (ex.getCodigo() != null) {
                er.setCode(ex.getCodigo().toString());
            }
            return new ResponseEntity(er, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(Codes.TOP_SECRET_SPLIT_URL)

    public ResponseEntity<TopSecretRS> topSecretSplitGet() {
        TopSecretRS response = null;
        try {
            response = topSecretService.getTopSecretSplit();
        } catch (MlException ex) {
            ErrorRS er = new ErrorRS();
            er.setDescription(ex.getMessage());
            if (ex.getCodigo() != null) {
                er.setCode(ex.getCodigo().toString());
            }
            return new ResponseEntity(er, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
