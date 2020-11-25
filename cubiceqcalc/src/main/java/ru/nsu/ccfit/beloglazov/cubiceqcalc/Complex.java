package ru.nsu.ccfit.beloglazov.cubiceqcalc;

public class Complex {
    public double real;
    public double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public void print(int precision) {
        if (imaginary == 0) {
            System.out.print(String.format("%." + precision + "f", real));
        } else {
            System.out.print(String.format("%." + precision + "f", real) + " + i * " + String.format("%." + precision + "f", imaginary));
        }
    }
}