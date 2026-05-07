package pl.pwr.matrixcalculator;

public class Complex {
    private final double re;
    private final double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    // Gettery
    public double getRe() { return re; }
    public double getIm() { return im; }

    // Moduł |z| = sqrt(re^2 + im^2)
    public double abs() {
        return Math.sqrt(re * re + im * im);
    }

    // Argument arg(z) — atan2 daje pełen zakres (-π, π]
    public double arg() {
        return Math.atan2(im, re);
    }

    // Sprzężenie z* = re - im*i
    public Complex conjugate() {
        return new Complex(re, -im);
    }

    // Dodawanie: (a+bi) + (c+di) = (a+c) + (b+d)i
    public Complex add(Complex other) {
        return new Complex(this.re + other.re, this.im + other.im);
    }

    // Odejmowanie: (a+bi) - (c+di) = (a-c) + (b-d)i
    public Complex subtract(Complex other) {
        return new Complex(this.re - other.re, this.im - other.im);
    }

    // Mnożenie: (a+bi)(c+di) = (ac-bd) + (ad+bc)i
    public Complex multiply(Complex other) {
        double newRe = this.re * other.re - this.im * other.im;
        double newIm = this.re * other.im + this.im * other.re;
        return new Complex(newRe, newIm);
    }

    // Mnożenie przez skalar rzeczywisty
    public Complex multiply(double scalar) {
        return new Complex(re * scalar, im * scalar);
    }

    // Reprezentacja tekstowa, np. "3.0 + 2.0i" albo "3.0 - 2.0i"
    @Override
    public String toString() {
        if (im >= 0) {
            return re + " + " + im + "i";
        } else {
            return re + " - " + Math.abs(im) + "i";
        }
    }
}