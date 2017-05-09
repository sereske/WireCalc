package ru.sereske.util;

import java.util.*;

/**
 * Created by Sergey on 07.05.2017.
 */
public class AlphaWUtil {

    private static Map<Double, Double> values = new HashMap<>();

    private static void init() {
        values.put(200.0, 1.0);
        values.put(240.0, 0.94);
        values.put(280.0, 0.88);
        values.put(300.0, 0.85);
        values.put(320.0, 0.83);
        values.put(360.0, 0.80);
        values.put(400.0, 0.76);
        values.put(500.0, 0.71);
        values.put(580.0, 0.70);
    }

    public static double getAlphaW(double windPressure) {
        init();
        List<Double> sortedWP = new ArrayList(values.keySet());
        Collections.sort(sortedWP);
        double w1 = 0.0;
        double w2 = 0.0;
        double a1 = 0.0;
        double a2 = 0.0;
        double a = 0.0;
        for (int i = 0; i < sortedWP.size() - 1; i++) {
            if (windPressure <= sortedWP.get(0)) {
                a = values.get(sortedWP.get(0));
                break;
            }
            if (windPressure >= sortedWP.get(sortedWP.size() - 1)) {
                a = values.get(sortedWP.get(sortedWP.size() - 1));
                break;
            }
            if (sortedWP.get(i) <= windPressure && sortedWP.get(i + 1) >= windPressure) {
                w1 = sortedWP.get(i);
                w2 = sortedWP.get(i + 1);
                a1 = values.get(w1);
                a2 = values.get(w2);
                a = a1 + (a2 - a1) * (windPressure - w1) / (w2 - w1);
                break;
            }
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(getAlphaW(500));
    }
}
