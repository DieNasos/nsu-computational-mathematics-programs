package ru.nsu.ccfit.beloglazov.cubiceqcalc;

import java.util.Vector;

public class CubicEqCalc {
    public double arsh(double x) {
        // returns inverse hyperbolic sin
        return Math.log(x + Math.sqrt(x*x + 1));
    }

    public double arch(double x) {
        // returns inverse hyperbolic cos
        return Math.log(x + Math.sqrt(x*x - 1));
    }

    public Vector<Complex> countRoots(double a, double b, double c, double d) {
        // counts roots of cubic equation with coefficients a, b, c, d

        Vector<Complex> roots = new Vector<>();

        // dividing equation by a =>
        // => recounting coefficients
        double aa = b / a;
        double bb = c / a;
        double cc = d / a;

        double q = (aa*aa - 3*bb) / 9;
        double r = (2*aa*aa*aa - 9*aa*bb + 27*cc) / 54;
        double s = q*q*q - r*r;

        if (s > 0) {
            double phi = (Math.acos(r / Math.sqrt(q*q*q))) / 3;

            roots.add(new Complex(-2 * Math.sqrt(q) * Math.cos(phi) - aa/3, 0));
            roots.add(new Complex(-2 * Math.sqrt(q) * Math.cos(phi + (2 * Math.PI) / 3) - aa/3, 0));
            roots.add(new Complex(-2 * Math.sqrt(q) * Math.cos(phi - (2 * Math.PI) / 3) - aa/3, 0));
        } else if (s < 0) {
            if (q > 0) {
                double phi = arch(Math.abs(r) / Math.sqrt(q*q*q)) / 3;

                roots.add(new Complex(-2 * Math.signum(r) * Math.sqrt(q) * Math.cosh(phi) - aa / 3, 0));

                double real = Math.signum(r) * Math.sqrt(q) * Math.cosh(phi) - aa / 3;
                roots.add(new Complex(real, Math.sqrt(3) * Math.sqrt(q) * Math.sinh(phi)));
                roots.add(new Complex(real, -Math.sqrt(3) * Math.sqrt(q) * Math.sinh(phi)));
            } else {
                // q <= 0

                double phi = arsh(Math.abs(r) / Math.sqrt(Math.abs(q*q*q))) / 3;

                roots.add(new Complex(-2 * Math.signum(r) * Math.sqrt(Math.abs(q)) * Math.sinh(phi) - aa / 3, 0));

                double real = Math.signum(r) * Math.sqrt(Math.abs(q)) * Math.sinh(phi) - aa / 3;
                roots.add(new Complex(real, Math.sqrt(3) * Math.sqrt(Math.abs(q)) * Math.cosh(phi)));
                roots.add(new Complex(real, -Math.sqrt(3) * Math.sqrt(Math.abs(q)) * Math.cosh(phi)));
            }
        } else {
            // s == 0

            roots.add(new Complex(-2 * Math.signum(r) * Math.sqrt(q) - a/3, 0));
            roots.add(new Complex(Math.signum(r) * Math.sqrt(q) - a/3, 0));
        }

        return roots;
    }

    public void printRoots(double a, double b, double c, double d, int precision) {
        // counting roots of cubic equation with coefficients a, b, c, d
        Vector<Complex> roots = countRoots(a, b, c, d);

        // printing info in console

        System.out.println("EQUATION: " + a + "x^3 + " + b + "x^2 + " + c + "x + " + d + " = 0");
        System.out.println("ROOTS:");

        // current root's id
        int i = 1;

        // printing roots in console
        for (Complex root:
             roots) {
            System.out.print("x" + i + " = ");
            root.print(precision);
            System.out.println();   // new-line
            i++;
        }
    }
}