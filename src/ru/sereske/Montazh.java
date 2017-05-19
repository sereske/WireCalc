package ru.sereske;

/**
 * Created by Sergey on 09.05.2017.
 */
public class Montazh {
    private double temp;
    private double Fa;
    private double Fb;

    public Montazh(double temp, double fa, double fb) {
        this.temp = temp;
        Fa = fa;
        Fb = fb;
    }

    public double getTemp() {
        return temp;
    }

    public double getFa() {
        return Fa;
    }

    public double getFb() {
        return Fb;
    }
}
