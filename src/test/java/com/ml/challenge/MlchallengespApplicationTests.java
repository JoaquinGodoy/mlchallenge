package com.ml.challenge;

import com.ml.challenge.bean.rq.InputRQ;
import com.ml.challenge.bean.rq.SatelliteRQ;
import com.ml.challenge.bean.rs.TopSecretRS;
import com.ml.challenge.exception.MlException;
import com.ml.challenge.exception.UnknownPositionException;
import com.ml.challenge.service.TopSecretService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

@SpringBootTest
class MlchallengespApplicationTests {

    @Autowired
    private TopSecretService topSecretService;

    @Test
    public void messageOk() {

        TopSecretRS response = null;

        SatelliteRQ kenobiSatellite = new SatelliteRQ();
        kenobiSatellite.setDistance(500.00);
        String kenobiMsg[] = new String[]{"este", "es", "un", "mensaje", ""};
        kenobiSatellite.setMessage(Arrays.asList(kenobiMsg));
        kenobiSatellite.setName("Kenobi");

        SatelliteRQ skywalkerSatellite = new SatelliteRQ();
        skywalkerSatellite.setDistance(378.00);
        String skywalkerMsg[] = new String[]{"este", "", "un", "", "secreto"};
        skywalkerSatellite.setMessage(Arrays.asList(skywalkerMsg));
        skywalkerSatellite.setName("Skywalker");

        SatelliteRQ satoSatellite = new SatelliteRQ();
        satoSatellite.setDistance(600.00);
        String satoMsg[] = new String[]{"este", "", "un"};
        satoSatellite.setMessage(Arrays.asList(satoMsg));
        satoSatellite.setName("Sato");

        InputRQ inputRQ = new InputRQ();

        List<SatelliteRQ> satellites = new ArrayList<SatelliteRQ>();

        satellites.add(satoSatellite);
        satellites.add(skywalkerSatellite);
        satellites.add(kenobiSatellite);

        inputRQ.setSatellites(satellites);

        try {
            response = topSecretService.provideTopSecret(inputRQ);
        } catch (MlException ex) {
        }

        assertThat(response.getMessage().contains("este es un mensaje secreto"));
    }

    @Test
    public void locationOk() {

        TopSecretRS response = null;

        SatelliteRQ kenobiSatellite = new SatelliteRQ();
        kenobiSatellite.setDistance(500.00);
        String kenobiMsg[] = new String[]{"este", "es", "un", "mensaje", ""};
        kenobiSatellite.setMessage(Arrays.asList(kenobiMsg));
        kenobiSatellite.setName("Kenobi");

        SatelliteRQ skywalkerSatellite = new SatelliteRQ();
        skywalkerSatellite.setDistance(378.00);
        String skywalkerMsg[] = new String[]{"este", "", "un", "", "secreto"};
        skywalkerSatellite.setMessage(Arrays.asList(skywalkerMsg));
        skywalkerSatellite.setName("Skywalker");

        SatelliteRQ satoSatellite = new SatelliteRQ();
        satoSatellite.setDistance(600.00);
        String satoMsg[] = new String[]{"este", "", "un"};
        satoSatellite.setMessage(Arrays.asList(satoMsg));
        satoSatellite.setName("Sato");

        InputRQ inputRQ = new InputRQ();

        List<SatelliteRQ> satellites = new ArrayList<SatelliteRQ>();

        satellites.add(satoSatellite);
        satellites.add(skywalkerSatellite);
        satellites.add(kenobiSatellite);

        inputRQ.setSatellites(satellites);

        try {
            response = topSecretService.provideTopSecret(inputRQ);
        } catch (MlException ex) {
        }

        assertThat(response.getPosition() != null);
    }

}
