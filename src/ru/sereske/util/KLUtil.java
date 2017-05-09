package ru.sereske.util;

import java.util.*;

/**
 * Created by Sergey on 07.05.2017.
 */
public class KLUtil {
    private static Map<Double, Double> values = new HashMap<>();

    private static void init() {
        values.put(50.0, 1.2);
        values.put(100.0, 1.1);
        values.put(150.0, 1.05);
        values.put(250.0, 1.0);
    }

    public static double getKL(double diffX) {
        init();
        List<Double> sortedList = new ArrayList(values.keySet());
        Collections.sort(sortedList);
        double kl = 0.0;
        double l1 = 0.0;
        double l2 = 0.0;
        double k1 = 0.0;
        double k2 = 0.0;
        for (int i = 0; i < sortedList.size() - 1; i++) {
            if (sortedList.get(0) >= diffX) {
                kl = values.get(sortedList.get(0));
                break;
            }
            if (sortedList.get(sortedList.size() - 1) <= diffX) {
                kl = values.get(sortedList.get(sortedList.size() - 1));
                break;
            }
            if (sortedList.get(i) <= diffX && sortedList.get(i + 1) >= diffX) {
                l1 = sortedList.get(i);
                l2 = sortedList.get(i + 1);
                k1 = values.get(l1);
                k2 = values.get(l2);
                kl = k1 + (k2 - k1) * (diffX - l1) / (l2 - l1);
                break;
            }
        }
        return kl;
    }

    public static void main(String[] args) {
        System.out.println(getKL(250));
    }
}
