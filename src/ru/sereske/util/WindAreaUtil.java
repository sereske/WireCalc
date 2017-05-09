package ru.sereske.util;

import ru.sereske.enumeration.Wind;

/**
 * Created by Sergey on 07.05.2017.
 */
public class WindAreaUtil {
    public static double getW0(Wind wind) {
        double w0 = 0.0;
        switch (wind) {
            case One:
                w0 = 400;
                break;
            case Two:
                w0 = 500;
                break;
            case Three:
                w0 = 650;
                break;
            case Four:
                w0 = 800;
                break;
            case Five:
                w0 = 1000;
                break;
            case Six:
                w0 = 1250;
                break;
            case Seven:
                w0 = 1500;
                break;
        }
        return w0;
    }
}
