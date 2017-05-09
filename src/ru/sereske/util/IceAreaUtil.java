package ru.sereske.util;

import ru.sereske.enumeration.Ice;

/**
 * Created by Sergey on 07.05.2017.
 */
public class IceAreaUtil {
    public static double getB(Ice ice) {
        double b = 0.0;
        switch (ice) {
            case One:
                b = 10;
                break;
            case Two:
                b = 15;
                break;
            case Three:
                b = 20;
                break;
            case Four:
                b = 25;
                break;
            case Five:
                b = 30;
                break;
            case Six:
                b = 35;
                break;
            case Seven:
                b = 40;
                break;
        }
        return b;
    }
}
