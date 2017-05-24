package ru.sereske;

import ru.sereske.enumeration.Area;
import ru.sereske.enumeration.Ice;
import ru.sereske.enumeration.Wind;
import ru.sereske.util.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.*;

/**
 * Created by stepanovsg on 18.04.2017.
 */
public class Computations {
    private double length;
    private double diffY;
    private double diffX;
    private double density;

    private double heightOpora;
    private int voltage;
    private int chainNumber;
    private Wire wire;
    private Area area;
    private Wind wind;
    private Ice ice;
    private int maxTemp;
    private int avgTemp;
    private int minTemp;
    private int iceTemp;

    private double sigmaOpr;
    private double yOpr;
    private double tempOpr;

    //private double sigmaR;

    private double u;

    private double a;
    private double c;

    private static double g = 9.8;
    private static double eps = 0.0000000001;

    public Computations(double length, double diffY, double diffX, double density) {
        this.length = length;
        this.diffY = diffY;
        this.diffX = diffX;
        this.density = density;
        this.u = getEquationSolutionByNewtonMethod();
        this.a = 2 * u / diffX;
        this.c = atanh(diffY / length) - a * diffX / 2;
    }

    public Computations(double diffY, double diffX, double heightOpora, Wire wire, Wind wind, Area area, Ice ice,
                        int minTemp, int avgTemp, int iceTemp, int maxTemp, int voltage) {
        this.diffY = diffY;
        this.diffX = diffX;
        this.heightOpora = heightOpora;
        this.wire = wire;
        this.wind = wind;
        this.area = area;
        this.ice = ice;

        this.length = diffX * 1.015;
        this.u = getEquationSolutionByNewtonMethod();
        this.a = 2 * u / diffX;
        this.c = atanh(diffY / length) - a * diffX / 2;

        this.minTemp = minTemp;
        this.avgTemp = avgTemp;
        this.iceTemp = iceTemp;
        this.maxTemp = maxTemp;

        this.voltage = 110;

        getCritLength2();
    }

    private double getEquationSolutionByNewtonMethod() {
        double u0 = 2.0;
        double u1 = u0 - func(u0) / deriv(u0);
        while (abs(u1 - u0) > eps) {
            u0 = u1;
            u1 = u0 - (func(u0) / deriv(u0));
            //System.out.println(u1);
        }
        return u1;
    }

    private double func(double u) {
        double b = sqrt(length * length - diffY * diffY) / diffX;
        return sinh(u) / u - b;
    }

    private double deriv(double u) {
        return (u * cosh(u) - sinh(u)) / pow(u, 2);
    }

    public double computeYCenterMass() {
        return (1 / (a * length)) * ((sinh(2 * (a * diffX + c)) - sinh(2 * c)) / (4 * a) + diffX / 2 - cosh(c) * (sinh(a * diffX + c) - sinh(c)) / a);
    }

    public double computeXCenterMass() {
        return (1 / (a * length)) * (diffX * sinh(a * diffX + c) - (cosh(a * diffX + c) - cosh(c)) / a);
    }

    public double computeX0() {
        return -c / a;
    }

    public double computeY0() {
        return (1 - cosh(c)) / a;
    }

    public double getF() {
        return wire.getAlSize() + wire.getStSize();
    }

    public double getY1() {
        return wire.getMassPerKm() * 1e-3 * g / getF();
    }

    public double getHCenterMass() {
        return heightOpora + computeYCenterMass();
    }

    public double getKbh() {
        return (getHCenterMass() <= 25.0) ? 1.0 :  pow(log10(sqrt(7 * getHCenterMass())), 2);
    }

    public double getKbd() {
        return (wire.getDiameter() <= 10) ? 1.0 : 0.83 / pow(wire.getDiameter() * 0.1, 0.25) + 0.17;
    }

    public double getB() {
        return IceAreaUtil.getB(ice) * getKbd() * getKbh();
    }

    public double getKng() {
        if (chainNumber == 1 && voltage <= 220) {
            return 1.0;
        } else {
            return 1.3;
        }
    }

    public double getPg() {
        return 1.0;
    }

    public double getKfg() {
        if (ice == Ice.One || ice == Ice.Two) {
            return 1.3;
        } else {
            return 1.6;
        }
    }

    public double getKpg() {
        return 1.0;
    }

    public double getKd() {
        return 0.5;
    }

    public double getKnw() {
        double knw;
        if (chainNumber == 1 && voltage <= 220) {
            knw = 1.0;
        } else {
            knw = 1.1;
        }
        return knw;
    }

    public double getKpw() {
        return 1.0;
    }

    public double getKfw() {
        return 1.1;
    }

    public double getCxg() {
        return 1.2;
    }

    public double getY2() {
        double y2n = 27.75 * getB() * (wire.getDiameter() + getB()) * 1e-3 / getF();
        return y2n * getKng() * getKfg() * getKpg() * getKd();
    }

    public double getY3() {
        return getY1() + getY2();
    }

    public double getY4() {
        double cx;
        if (wire.getDiameter() >= 20) {
            cx = 1.1;
        } else {
            cx = 1.2;
        }
        double windPressure = WindAreaUtil.getW0(wind);
        double y4n = AlphaWUtil.getAlphaW(windPressure)
                * KLUtil.getKL(diffX)
                * AreaUtil.getKw(area, getHCenterMass())
                * cx
                * WindAreaUtil.getW0(wind)
                * wire.getDiameter()
                * 1e-3
                / getF();
        return y4n * getKnw() * getKpw() * getKfw();
    }

    public double getY5() {
        double windPressure = WindAreaUtil.getW0(wind);
        double windPressureNorm = windPressure * 0.25;
        double y5n = AlphaWUtil.getAlphaW(windPressureNorm)
                * KLUtil.getKL(diffX)
                * AreaUtil.getKw(area, getHCenterMass())
                * getCxg()
                * windPressureNorm * (wire.getDiameter() + 2 * getB()) * 1e-3 / getF();
        return y5n * getKnw() * getKpw() * getKfw();
    }

    public double getY6() {
        return sqrt(getY1() * getY1() + getY4() * getY4());
    }

    public double getY7() {
        return sqrt(getY3() * getY3() + getY5() * getY5());
    }

    public double getYnb() {
        return Math.max(getY6(), getY7());
    }

    public double getCritLength1() {
        double critLength1;
        double sigmaMinus = wire.getSigmaMinus();
        double sigmaSe = wire.getSigmaSe();
        double alpha = wire.getAlpha();
        double sigmaNb = wire.getSigmaNb();
        double e = wire.getE();
        double sigmaSe0 = sigmaMinus + alpha * e * (minTemp - avgTemp);
        if (sigmaSe0 <= sigmaSe) {
            critLength1 = 4.9 * (sigmaMinus / getY1())
                    * sqrt((sigmaMinus - sigmaSe + alpha * e * (minTemp - avgTemp))
                    / ((1 - pow(sigmaMinus / sigmaSe, 2)) * e));
        } else {
            critLength1 = -1;
        }
        return critLength1;
    }

    public double getCritLength3() {
        double critLength3;
        double sigmaMinus = wire.getSigmaMinus();
        double sigmaSe = wire.getSigmaSe();
        double alpha = wire.getAlpha();
        double sigmaNb = wire.getSigmaNb();
        double e = wire.getE();
        double ynb = getYnb();
        double y1 = getY1();
        double sigmaSe1 = sigmaNb * getY1() / getYnb();
        if (sigmaSe1 <= sigmaSe) {
            critLength3 = 4.9 * (sigmaNb / y1)
                    * sqrt(
                            (sigmaNb - sigmaSe + alpha * e * (iceTemp - avgTemp))
                                / ((pow((ynb / y1), 2) - pow(sigmaNb / sigmaSe, 2)) * e)
                        );
        } else {
           critLength3 = -1;
        }
        return critLength3;
    }

    public double getCritLength2() {

        double critLength2;
        double critLength1 = getCritLength1();
        double critLength3 = getCritLength3();

        double sigmaMinus = wire.getSigmaMinus();
        double sigmaSe = wire.getSigmaSe();
        double alpha = wire.getAlpha();
        double sigmaNb = wire.getSigmaNb();
        double e = wire.getE();
        double ynb = getYnb();
        double y1 = getY1();
        double sigmaSe1 = sigmaNb * getY1() / getYnb();

        if (critLength1 < critLength3) {
            if (diffX < critLength1) {
                yOpr = y1;
                tempOpr = minTemp;
                sigmaOpr = sigmaMinus;
            } else if (diffX >= critLength1 && diffX <= critLength3) {
                yOpr = y1;
                tempOpr = avgTemp;
                sigmaOpr = sigmaSe;
            } else if (diffX > critLength3) {
                yOpr = ynb;
                tempOpr = iceTemp;
                sigmaOpr = sigmaNb;
            }
            critLength2 = -1;
        } else {
            critLength2 = 4.9 * (sigmaNb / y1) * sqrt((alpha * (iceTemp - minTemp)) / (pow((ynb / y1), 2) - 1));
            if (diffX < critLength2) {
                yOpr = y1;
                tempOpr = minTemp;
                sigmaOpr = sigmaMinus;
            } else {
                yOpr = ynb;
                tempOpr = iceTemp;
                sigmaOpr = sigmaNb;
            }
        }
        return critLength2;
    }

    /*
    public void setCritOpr() {
        double critLength2;
        double critLength1 = getCritLength1();
        double critLength3 = getCritLength3();

        double sigmaMinus = wire.getSigmaMinus();
        double sigmaSe = wire.getSigmaSe();
        double alpha = wire.getAlpha();
        double sigmaNb = wire.getSigmaNb();
        double e = wire.getE();
        double ynb = getYnb();
        double y1 = getY1();
        double sigmaSe1 = sigmaNb * getY1() / getYnb();

        if (critLength1 < critLength3) {
            if (length < critLength1) {
                yOpr = y1;
                tempOpr = minTemp;
                sigmaOpr = sigmaMinus;
            } else if (length >= critLength1 && length <= critLength3) {
                yOpr = y1;
                tempOpr = avgTemp;
                sigmaOpr = sigmaSe;
            } else if (length > critLength3) {
                yOpr = ynb;
                tempOpr = iceTemp;
                sigmaOpr = sigmaNb;
            }
        } else {
            critLength2 = 4.9 * (sigmaNb / y1) * sqrt((alpha * (iceTemp - minTemp)) / (pow((ynb / y1), 2) - 1));
            if (length < critLength2) {
                yOpr = y1;
                tempOpr = minTemp;
                sigmaOpr = sigmaMinus;
            } else {
                yOpr = y1;
                tempOpr = avgTemp;
                sigmaOpr = sigmaSe;
            }
        }
    }
    */

    public double getSigma0() {
        double sigma0 = wire.getSigmaNb();
        double sigmaNb = wire.getSigmaNb();
        double ynb = getYnb();
        int k = 0;
        while (true) {
            double Leq = diffX + 2 * (sigma0 / ynb) * asinh(ynb * diffY / (2 * sigma0 * sinh(diffX * ynb / (2 * sigma0))));
            double sigmaA = sigma0 * cosh(Leq * ynb / (2 * sigma0));
            double delta = abs(sigmaNb - sigmaA);
            sigma0 -= delta;
            if (delta < 0.1) {
                break;
            }
            if (k > 20) {
                sigma0 = sigmaOpr;
                break;
            }
            k++;
        }
        //sigmaR = sigma0;
        return sigma0;
    }

    public double getCritTemp() {
        double sigmaNb = wire.getSigmaNb();
        double y2 = getY2();
        double alpha = wire.getAlpha();
        double e = wire.getE();
        double y3 = getY3();
        return iceTemp - 3 + sigmaNb * y2 / (alpha * e * y3);
    }

    public double getSigmaMaxProvis() throws WireException {
        double alpha = wire.getAlpha();
        double e = wire.getE();

        double yMaxProvis = getYMaxProvis();
        double sigmaMaxProvis = getSigma0();

        double tempMaxProvis = getTempMaxProvis();

        double sigma0 = getSigma0();

        //getCritLength2();

        double A = sigma0 - yOpr * yOpr * diffX * diffX * e / (24 * sigma0 * sigma0) + alpha * e * (- tempMaxProvis + tempOpr);
        double B = yMaxProvis * yMaxProvis * diffX * diffX * e / 24;
        while (true) {
            double sigmaNext = (pow(sigmaMaxProvis, 2) * (2 * sigmaMaxProvis - A) + B)
                                / (sigmaMaxProvis * (3 * sigmaMaxProvis - 2 * A));
            double delta = abs(sigmaMaxProvis - sigmaNext);
            sigmaMaxProvis = sigmaNext;
            if (delta < 0.01) {
                break;
            }
        }
        /*
        if (sigmaMaxProvis > wire.getSigmaNb()) {
            throw new WireException("Неподходящий провод.\nВыберите другую марку!");
        }
        */
        return sigmaMaxProvis;

    }

    public double getYMaxProvis() {
        double yMaxProvis;
        if (getCritTemp() > maxTemp) {
            yMaxProvis = getY3();
        } else {
            yMaxProvis = getY1();
        }
        return yMaxProvis;
    }

    public double getTempMaxProvis() {
        double tempMaxProvis;
        if (getCritTemp() > maxTemp) {
            tempMaxProvis = iceTemp;
        } else {
            tempMaxProvis = maxTemp;
        }
        return tempMaxProvis;
    }

    public double getFaMaxProvis() throws WireException {
        double yMaxProvis = getYMaxProvis();
        double sigmaMaxProvis = getSigmaMaxProvis();
        double distanceA =0.5 * (diffX - (2 * sigmaMaxProvis / yMaxProvis)
                * asinh(yMaxProvis * diffY
                        / (2 * sigmaMaxProvis * sinh(diffX * yMaxProvis / (2 * sigmaMaxProvis))))
        );
        double FaMaxProvis = (yMaxProvis * distanceA * (diffX - distanceA ) / (2 * sigmaMaxProvis)) - (diffY * distanceA / diffX);
        return FaMaxProvis;
    }

    public double getFbMaxProvis() throws WireException {
        return getFaMaxProvis() + diffY;
    }

    public double getLMaxProvis()  throws WireException {
        double yMaxProvis = getYMaxProvis();
        double sigmaMaxProvis = getSigmaMaxProvis();
        double value = pow(((2 * sigmaMaxProvis / yMaxProvis) * (sinh(yMaxProvis * diffX/ (2 * sigmaMaxProvis)))), 2) + diffY * diffY;
        return sqrt(value);
    }

    public double getD() {
        double m = wire.getSizeRelation();
        return 185 * 1e3 * (1 + 0.19 * m) / (1 + m);
    }

    public double getC() {
        double m = wire.getSizeRelation();
        return 185 * 1e3 * (1 + 0.29 * m) / (1 + m);
    }

    public double getV() {
        double m = wire.getSizeRelation();
        return 0.05 * m;
    }

    public double getCm() {
        double C = getC();
        double D = getD();
        double v = getV();
        return C / (1 + (C / D - 1) * v);
    }

    public List<Montazh> getMontazh() {
        List<Montazh> list = new ArrayList<>();
        List<Integer> temps = new ArrayList<>();
        for (int t = minTemp; t <= maxTemp;t+=10) {
            temps.add(t);
        }
        if (!temps.contains(maxTemp)) {
            temps.add(maxTemp);
        }
        double sigma0 = getSigma0();
        double Cm = getCm();
        double D = getD();
        double y1 = getY1();
        double alpha = wire.getAlpha();
        for (Integer temp : temps) {
            double A = sigma0 * Cm / D - pow(yOpr, 2) * pow(diffX, 2) * Cm / (24 * sigma0 * sigma0) + alpha * Cm * (tempOpr - temp);
            double B = y1 * y1 * diffX * diffX * Cm / 24;
            double s0;
            if (A < 0) {
                s0 = 1.035 * sqrt(B / (pow(B, 1.0/3.0)) - A);
            } else {
                s0 = 1.02 * sqrt(pow(B, 2.0/3.0) + A * A);
            }
            while (true) {
                double s1 = (s0 * s0 * (2 * s0 - A) + B) / (s0 * (3 * s0 - 2 * A));
                double delta = abs(s1 - s0);
                s0 = s1;
                if (delta <= 0.01) {
                    break;
                }
            }

            //double yMaxProvis = getYMaxProvis();
            //double sigmaMaxProvis = getSigmaMaxProvis();
            double distanceA = 0.5 * (diffX - (2 * s0 / y1)
                    * asinh(y1 * diffY
                    / (2 * s0 * sinh(diffX * y1 / (2 * s0))))
            );
            double Fa = (y1 * distanceA * (diffX - distanceA ) / (2 * s0)) - (diffY * distanceA / diffX);
            double Fb = Fa + diffY;
            Montazh montazh = new Montazh(temp, Fa, Fb);
            list.add(montazh);
        }
        return list;
    }

    public double ReLmech1() {
        double km1 = 2.5;
        double kd;
        double ynb = getYnb();
        double F = getF();
        int G = VoltageUtil.getG(voltage);
        double sigmaNb = wire.getSigmaNb();
        if (avgTemp <= -10 || minTemp <= -50) {
            kd = 1.4;
        } else {
            kd = 1.0;
        }
        double ReLmech1 = 0.5 * km1 * kd * sqrt(
                pow(ynb * diffX * F / 2 + G, 2) + pow(sigmaNb * F, 2)
        );
        return ReLmech1;
    }

    public double getSigmaGirlianda() {
        double e = wire.getE();
        double y1 = getY1();
        double alpha = wire.getAlpha();
        double sigmaNb = wire.getSigmaNb();
        double A = sigmaNb - pow(yOpr, 2) * pow(diffX, 2) * e / (24 * sigmaNb * sigmaNb) + alpha * e * (iceTemp - avgTemp);
        double B = y1 * y1 * diffX * diffX * e / 24;
        double s0 = sigmaNb;
        while (true) {
            double s1 = (s0 * s0 * (2 * s0 - A) + B) / (s0 * (3 * s0 - 2 * A));
            double delta = abs(s1 - s0);
            s0 = s1;
            if (delta <= 0.01) {
                break;
            }
        }
        return s0;
    }

    public double ReLmech2() {
        double km1 = 6.0;
        double kd;
        double y1 = getY1();
        double F = getF();
        double sigma0 = getSigma0();
        int G = VoltageUtil.getG(voltage);
        if (avgTemp <= -10 || minTemp <= -50) {
            kd = 1.4;
        } else {
            kd = 1.0;
        }
        double distanceB = 0.5 * (diffX + (2 * sigma0 / y1)
                * asinh(y1 * diffY
                / (2 * sigma0 * sinh(diffX * y1 / (2 * sigma0))))
        );
        double ReLmech2 = 0.5 * km1 * kd * sqrt(
                pow(y1 * distanceB * F / 2 + 2 * G, 2) + pow(getSigmaGirlianda() * F, 2)
        );
        return ReLmech2;
    }

    public double getRMax() {
        return max(ReLmech1(), ReLmech2());
    }

    private static double atanh(double x) {
        return log(sqrt((1 + x) / (1 - x)));
    }

    private static double asinh(double x) {
        return log(x + sqrt(x * x + 1));
    }

    public Map<Double, Double> getDots() {
        Map<Double, Double> dots = new HashMap<>();
        for (double x = 0.0; x <= diffX + 1; x++) {
            double y = (cosh(a * x + c) - cosh(c)) / a;
            dots.put(x, y);
        }
        return dots;
    }

    public static void main(String[] args)  throws WireException {
        Computations computations = new Computations(104.0, 1700.0, 140.0, Wire.getWire6(), Wind.Two, Area.A, Ice.One,
                -55, -5, -10, 35, 110);
        Computations computations1 = new Computations(0, 246.0, 22.6, Wire.getWire1(), Wind.Two, Area.A, Ice.One,
                -55, -5, -10, 35, 110);
        //System.out.println("Sigma0: " + computations.getSigma0());
        //System.out.println("ynb: " + computations.getYnb());
        //System.out.println("CritLength1: " + computations.getCritLength1());
        //System.out.println("CritLength3: " + computations.getCritLength3());
        //System.out.println("CritLength2: " + computations.getCritLength2());
        System.out.println("y1: " + computations.getY1());
        System.out.println("y2: " + computations.getY2());
        System.out.println("y3: " + computations.getY3());
        System.out.println("y4: " + computations.getY4());
        System.out.println("y5: " + computations.getY5());
        System.out.println("y6: " + computations.getY6());
        System.out.println("y7: " + computations.getY7());
        System.out.println("ynb: " + computations.getYnb());
        System.out.println("CritLenght1: " + computations.getCritLength1());
        System.out.println("CritLenght2: " + computations.getCritLength2());
        System.out.println("CritLenght3: " + computations.getCritLength3());
        System.out.println("SigmaOpr: " + computations.sigmaOpr);
        System.out.println("SigmaMaxProvis: " + computations.getSigmaMaxProvis());
        System.out.println("YMaxProvis: " + computations.getYMaxProvis());
        //System.out.println("SigmaMaxProvis: " + computations.getSigmaMaxProvis());
        //System.out.println("Sigma0: " + computations.getSigma0());
        System.out.println("Fa: " + computations.getFaMaxProvis());
        System.out.println("Fb: " + computations.getFbMaxProvis());
        System.out.println("L: " + computations.getLMaxProvis());
        computations.getMontazh().forEach(a -> System.out.println("temp: " + a.getTemp() + ", Fa: " + a.getFa() + ", Fb: " + a.getFb()));
        System.out.println("RelMech2: " + computations.ReLmech2());
        System.out.println("RelMech1: " + computations.ReLmech1());
        System.out.println("RelMech1: " + computations.ReLmech1());
        System.out.println("Temp max provis: " + computations.getTempMaxProvis());
    }

    public double getSigmaOpr() {
        return sigmaOpr;
    }

    public double getyOpr() {
        return yOpr;
    }

    public double getTempOpr() {
        return tempOpr;
    }
}
