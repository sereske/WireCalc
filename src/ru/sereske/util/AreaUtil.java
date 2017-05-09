package ru.sereske.util;

import ru.sereske.enumeration.Area;

import java.util.*;

import static ru.sereske.enumeration.Area.*;

/**
 * Created by Sergey on 07.05.2017.
 */
public class AreaUtil {

    private static Map<Double, Double> aValues = new HashMap<>();
    private static Map<Double, Double> bValues = new HashMap<>();
    private static Map<Double, Double> cValues = new HashMap<>();
    private static Map<Area, Map<Double, Double>> fullValues = new HashMap<>();

    private static void init() {
        aValues.put(15.0, 1.0);
        aValues.put(20.0, 1.25);
        aValues.put(40.0, 1.50);
        aValues.put(60.0, 1.7);
        aValues.put(80.0, 1.85);
        aValues.put(100.0, 2.0);
        aValues.put(150.0, 2.25);
        aValues.put(200.0, 2.45);
        aValues.put(250.0, 2.65);
        aValues.put(300.0, 2.75);

        bValues.put(15.0, 0.65);
        bValues.put(20.0, 0.85);
        bValues.put(40.0, 1.10);
        bValues.put(60.0, 1.30);
        bValues.put(80.0, 1.45);
        bValues.put(100.0, 1.60);
        bValues.put(150.0, 1.90);
        bValues.put(200.0, 2.10);
        bValues.put(250.0, 2.30);
        bValues.put(300.0, 2.5);
        bValues.put(350.0, 2.75);

        cValues.put(15.0, 0.40);
        cValues.put(20.0, 0.55);
        cValues.put(40.0, 0.80);
        cValues.put(60.0, 1.00);
        cValues.put(80.0, 1.15);
        cValues.put(100.0, 1.25);
        cValues.put(150.0, 1.55);
        cValues.put(200.0, 1.80);
        cValues.put(250.0, 2.00);
        cValues.put(300.0, 2.20);
        cValues.put(350.0, 2.35);

        fullValues.put(A, aValues);
        fullValues.put(B, bValues);
        fullValues.put(C, cValues);
    }

    public static double getKw(Area area, double y) {
        init();
        Map<Double, Double> values = fullValues.get(area);
        List<Double> sortedHeights = new ArrayList<>(values.keySet());
        Collections.sort(sortedHeights);
        double y1 = 0.0;
        double y2 = 0.0;
        double k1 = 0.0;
        double k2 = 0.0;
        double kw = 0.0;
        for (int i = 0; i < sortedHeights.size(); i++) {
            if (y < sortedHeights.get(0)) {
                y1 = sortedHeights.get(0);
                k1 = values.get(y1);
                break;
            }
            if (y > sortedHeights.get(sortedHeights.size() - 1)) {
                y1 = sortedHeights.get(sortedHeights.size() - 1);
                k1 = values.get(y1);
                break;
            }
            if (sortedHeights.get(i) <= y && sortedHeights.get(i + 1) >= y) {
                y1 = sortedHeights.get(i);
                y2 = sortedHeights.get(i + 1);
                k1 = values.get(y1);
                k2 = values.get(y2);
            }
        }
        if (k2 != 0.0) {
            kw = k1 + (k2 - k1) * (y - y1) / (y2 - y1);
        } else {
            kw = k1;
        }
        return kw;
    }

    public static void main(String[] args) {
        System.out.println(getKw(A, 375));
    }
}
