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

    private static final Wire WIRE_1 = new Wire("95/16", 95.4, 15.9, 13.5, 385.0, 120.0, 90.0, 8.25e4, 19.2e-6);
    private static final Wire WIRE_2 = new Wire("120/19", 118, 18.8, 15.2, 471, 135.0, 90.0, 8.25e4, 19.2e-6);
    private static final Wire WIRE_3 = new Wire("185/29", 181, 29, 18.8, 728, 135.0, 90.0, 8.25e4, 19.2e-6);
    private static final Wire WIRE_4 = new Wire("240/39", 236, 38.6, 21.6, 952, 135.0, 90.0, 8.25e4, 19.2e-6);
    private static final Wire WIRE_5 = new Wire("300/39", 301, 38.6, 24, 1132, 126.0, 84.0, 7.7e4, 19.8e-6);
    private static final Wire WIRE_6 = new Wire("300/204", 298, 204, 29.2, 2428, 254.0, 169.0, 11.4e4, 15.5e-6);
    private static final Wire WIRE_7 = new Wire("400/64", 390, 63.5, 27.7, 1572, 135.0, 90.0, 8.25e4, 19.2e-6);

    public static Wire get(String marka) {
        /*
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
        */
        Wire wire = null;
        for (Wire w : getAll()) {
            if (w.marka.equals(marka)) {
                wire = w;
                break;
            }
        }
        return wire;
    }

    public static List<Wire> getAll() {
        List<Wire> wires = new ArrayList<>();
        wires.add(WIRE_1);
        wires.add(WIRE_2);
        wires.add(WIRE_3);
        wires.add(WIRE_4);
        wires.add(WIRE_5);
        wires.add(WIRE_6);
        wires.add(WIRE_7);
        return wires;
    }

    private Wire(String marka, double alSize, double stSize, double diameter, double massPerKm, double sigmaNb, double sigmaSe, double e, double alpha) {
        this.marka = marka;
        this.alSize = alSize;
        this.stSize = stSize;
        this.diameter = diameter;
        this.massPerKm = massPerKm;
        this.sigmaNb = sigmaNb;
        this.sigmaSe = sigmaSe;
        this.e = e;
        this.alpha = alpha;
        this.sigmaMinus = sigmaNb;
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

    @Override
    public String toString() {
        return marka;
    }

    public static Wire getWire1() {
        return WIRE_1;
    }

    public static Wire getWire2() {
        return WIRE_2;
    }

    public static Wire getWire3() {
        return WIRE_3;
    }

    public static Wire getWire4() {
        return WIRE_4;
    }

    public static Wire getWire5() {
        return WIRE_5;
    }

    public static Wire getWire6() {
        return WIRE_6;
    }

    public static Wire getWire7() {
        return WIRE_7;
    }
}
