package ru.sereske;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 07.05.2017.
 */
public class Wire {
    private String marka;

    private double alSize;
    private double stSize;
    private double diameter;
    private double massPerKm;
    private double sigmaNb;
    private double sigmaMinus;
    private double sigmaSe;
    private double e;
    private double alpha;

    public Wire(String marka) {
        this.marka = marka;
        switch (marka) {
            case "95/16":
                alSize = 95.4;
                stSize = 15.9;
                diameter = 13.5;
                massPerKm = 385.0;
                sigmaNb = 120.0;
                sigmaSe = 90.0;
                e = 8.25e4;
                alpha = 19.2e-6;
                break;
            case "120/19":
                alSize = 118;
                stSize = 18.8;
                diameter = 15.2;
                massPerKm = 471;
                sigmaNb = 135.0;
                sigmaSe = 90.0;
                e = 8.25e4;
                alpha = 19.2e-6;
                break;
            case "185/29":
                alSize = 181;
                stSize = 29;
                diameter = 18.8;
                massPerKm = 728;
                sigmaNb = 135.0;
                sigmaSe = 90.0;
                e = 8.25e4;
                alpha = 19.2e-6;
                break;
            case "240/39":
                alSize = 236;
                stSize = 38.6;
                diameter = 21.6;
                massPerKm = 952;
                sigmaNb = 135.0;
                sigmaSe = 90.0;
                e = 8.25e4;
                alpha = 19.2e-6;
                break;
            case "300/39":
                alSize = 301;
                stSize = 38.6;
                diameter = 24;
                massPerKm = 1132;
                sigmaNb = 126.0;
                sigmaSe = 84.0;
                e = 7.7e4;
                alpha = 19.8e-6;
                break;
            case "300/204":
                alSize = 298;
                stSize = 204;
                diameter = 29.2;
                massPerKm = 2428;
                sigmaNb = 254.0;
                sigmaSe = 169.0;
                e = 11.4e4;
                alpha = 15.5e-6;
                break;
            case "400/64":
                alSize = 390;
                stSize = 63.5;
                diameter = 27.7;
                massPerKm = 1572;
                sigmaNb = 135.0;
                sigmaSe = 90.0;
                e = 8.25e4;
                alpha = 19.2e-6;
                break;
        }
        sigmaMinus = sigmaNb;
    }

    public String getMarka() {
        return marka;
    }

    public double getAlSize() {
        return alSize;
    }

    public double getStSize() {
        return stSize;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getMassPerKm() {
        return massPerKm;
    }

    public double getSizeRelation() {
        return alSize / stSize;
    }

    public double getSigmaNb() {
        return sigmaNb;
    }

    public double getSigmaMinus() {
        return sigmaMinus;
    }

    public double getSigmaSe() {
        return sigmaSe;
    }

    public double getE() {
        return e;
    }

    public double getAlpha() {
        return alpha;
    }
}
