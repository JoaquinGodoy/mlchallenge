/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ml.challenge.util;

import com.ml.challenge.bean.KenobiSatellite;
import com.ml.challenge.bean.SatoSatellite;
import com.ml.challenge.bean.SkywalkerSatellite;
import com.ml.challenge.bean.rs.PositionRS;
import com.ml.challenge.exception.ItsaTrapException;
import com.ml.challenge.exception.UnknownPositionException;
import java.util.List;

/**
 * Abstract class for unlock the Secret...
 * @author jgodoy
 */
public abstract class Secret {

    /**
     * Function that provides the position of the secret message. 
     * @param kenobiDistance
     * @param skywalkerDistance
     * @param satoDistance
     * @return
     * @throws UnknownPositionException 
     */
    public static PositionRS getLocation(double kenobiDistance, double skywalkerDistance, double satoDistance) throws UnknownPositionException {
        KenobiSatellite kenobi = KenobiSatellite.getInstance();
        SkywalkerSatellite skywalker = SkywalkerSatellite.getInstance();
        SatoSatellite sato = SatoSatellite.getInstance();

        kenobi.setDistance(kenobiDistance);
        skywalker.setDistance(skywalkerDistance);
        sato.setDistance(satoDistance);

        return getLocationByTrilateration(kenobi, skywalker, sato);
    }

    /**
     * Function that calculate the location position by trilateration. 
     * @param kenobiSatellite
     * @param skywalkerSatellite
     * @param satoSatellite
     * @return
     * @throws UnknownPositionException 
     */
    private static PositionRS getLocationByTrilateration(KenobiSatellite kenobiSatellite, SkywalkerSatellite skywalkerSatellite, SatoSatellite satoSatellite) throws UnknownPositionException {
        // TEST

        // Dado 
        // (X-X1)^2 + (Y-Y1)^2 = R1^2
        // (X-X2)^2 + (Y-Y3)^2 = R2^2
        // (X-X3)^2 + (Y-Y4)^2 = R3^2
        //Resolvemos la ecuacion primero para Y despejandola de todo el conjunto:
        //    [(x2-x1)(x3^2+y3^2-r3^2)+(x1-x3)(x2^2+y2^2-r2^2)+(x3-x2)(x1^2+y1^2-r1^2)]
        //y = -------------------------------------------------------------------------
        //                        2[y3(x2-x1)+y2(x1-x3)+y1(x3-x2)]
        // Sustituimos el valor obtenido de y en la ecuacion despejada de x:
        //    [r2^2+x1^2+y1^2-r1^2-x2^2-y2^2-2(y1-y2)y]
        //x = -----------------------------------------
        //                   2(x1-x2)
        //Agregamos las variables:
        double x1, x2, x3;
        double y1, y2, y3;
        double r1, r2, r3;

        x1 = kenobiSatellite.getPosition().getX();
        y1 = kenobiSatellite.getPosition().getY();
        r1 = kenobiSatellite.getDistance();

        x2 = skywalkerSatellite.getPosition().getX();
        y2 = skywalkerSatellite.getPosition().getY();
        r2 = skywalkerSatellite.getDistance();

        x3 = satoSatellite.getPosition().getX();
        y3 = satoSatellite.getPosition().getY();
        r3 = satoSatellite.getDistance();

        //Validamos los radios Con las coordenadas
        if (!circlesIntersect(kenobiSatellite.getPosition(), kenobiSatellite.getDistance(), skywalkerSatellite.getPosition(), skywalkerSatellite.getDistance())) {
            throw new UnknownPositionException("Imposible Determinar la distancia con los valores obtenidos...");
        } else if (!circlesIntersect(kenobiSatellite.getPosition(), kenobiSatellite.getDistance(), satoSatellite.getPosition(), satoSatellite.getDistance())) {
            throw new UnknownPositionException("Imposible Determinar la distancia con los valores obtenidos...");
        } else if (!circlesIntersect(skywalkerSatellite.getPosition(), skywalkerSatellite.getDistance(), satoSatellite.getPosition(), satoSatellite.getDistance())) {
            throw new UnknownPositionException("Imposible Determinar la distancia con los valores obtenidos...");
        }

        double valuey = (((x2 - x1) * ((x3 * x3) + (y3 * y3) - (r3 * r3))) + ((x1 - x3) * ((x2 * x2) + (y2 * y2) - (r2 * r2))) + ((x3 - x2) * ((x1 * x1) + (y1 * y1) - (r1 * r1)))) / (2 * (y3 * (x2 - x1) + (y2 * (x1 - x3)) + (y1 * (x3 - x2))));

        double valuex = ((r2 * r2) + (x1 * x1) + (y1 * y1) - (r1 * r1) - (x2 * x2) - (y2 * y2) - (2 * (y1 - y2) * valuey)) / (2 * (x1 - x2));
        
        valuex = Math.floor(valuex * 100) / 100;
        valuey = Math.floor(valuey * 100) / 100;

        return new PositionRS(valuex, valuey);
    }

    /**
     * This function check if two points intersects or not. 
     * @param pos1
     * @param distance1
     * @param pos2
     * @param distance2
     * @return 
     */
    private static boolean circlesIntersect(PositionRS pos1, double distance1, PositionRS pos2, double distance2) {

        double x1 = pos1.getX();
        double y1 = pos1.getY();

        double x2 = pos2.getX();
        double y2 = pos2.getY();

        double r1 = distance1;
        double r2 = distance2;

        double distSq = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
        double radSumSq = (r1 + r2) * (r1 + r2);

        if (distSq == radSumSq) {
            return true;
        } else if (distSq > radSumSq) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Function to decode the secret message. 
     * @param kenobiMessages
     * @param skywalkerMessages
     * @param satoMessages
     * @return
     * @throws ItsaTrapException 
     */
    public static String getMessage(List<String> kenobiMessages, List<String> skywalkerMessages, List<String> satoMessages) throws ItsaTrapException {

        KenobiSatellite kenobi = KenobiSatellite.getInstance();
        SkywalkerSatellite skywalker = SkywalkerSatellite.getInstance();
        SatoSatellite sato = SatoSatellite.getInstance();

        kenobi.setMessages(kenobiMessages);
        skywalker.setMessages(skywalkerMessages);
        sato.setMessages(satoMessages);

        String arrayString[][] = new String[3][];

        String[] kenobiArray = new String[kenobiMessages.size()];
        arrayString[0] = kenobiMessages.toArray(kenobiArray);

        String[] skywalkerArray = new String[skywalkerMessages.size()];
        arrayString[1] = skywalkerMessages.toArray(skywalkerArray);

        String[] satoArray = new String[satoMessages.size()];
        arrayString[2] = satoMessages.toArray(satoArray);

        String arrayAux[] = new String[3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if (arrayString[j].length < arrayString[j + 1].length) {
                    arrayAux = arrayString[j];
                    arrayString[j] = arrayString[j + 1];
                    arrayString[j + 1] = arrayAux;
                }
            }
        }

        return getPhrase(arrayString);
    }

    /**
     * Function used to form the secret phrase. 
     * @param arrayString
     * @return 
     */
    private static String getPhrase(String arrayString[][]) {
        StringBuffer phrase = new StringBuffer("");
        for (int column = 0; arrayString[0].length > column; column++) {
            String word = "";
            for (int row = 0; row < arrayString.length; row++) {
                if (arrayString[row].length > column) {
                    if (arrayString[row][column] != null && !arrayString[row][column].isEmpty()) { // La palabra no está vacía
                        String esteValor = arrayString[row][column];
                        if (!word.equals(arrayString[row][column])) { //La palabra es distinta
                            if (word.isEmpty()) { //La palabra estaba vacia. 
                                word = arrayString[row][column];
                            } else if (word.length() < arrayString[row][column].length()) {
                                word = arrayString[row][column];
                            }
                        }
                    }
                }
            }
            phrase.append(word);
            if (!word.isEmpty()) {
                phrase.append(" ");
            }
        }

        return phrase.toString().trim();
    }

}
