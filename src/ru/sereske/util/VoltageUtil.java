package ru.sereske.util;

import ru.sereske.enumeration.Ice;

/**
 * Created by Sergey on 09.05.2017.
 */
public class VoltageUtil {
    public static int getG(int u) {
        int G = 0;
        switch (u) {
            case 35:
                G = 200;
                break;
            case 110:
                G = 400;
                break;
            case 150:
                G = 600;
                break;
            case 220:
                G = 800;
                break;
            case 330:
                G = 1700;
                break;
            case 500:
                G = 2800;
                break;
        }
        return G;
    }
}
