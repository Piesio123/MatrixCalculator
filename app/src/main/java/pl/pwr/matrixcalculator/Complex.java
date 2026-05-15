package pl.pwr.matrixcalculator;

/**
 * Klasa reprezentująca liczbę zespoloną w postaci a + bi.
 * Jest IMMUTABLE - po stworzeniu nie da się jej zmienić.
 * Każda operacja (add, multiply) zwraca NOWY obiekt.
 */
public class Complex {

    // Pola final - po inicjalizacji w konstruktorze nie da się ich zmienić
    // Dzięki temu liczba zespolona jest niezmienna (jak String w Javie)
    private final double re;   // część rzeczywista
    private final double im;   // część urojona

    // Konstruktor - tworzy nową liczbę zespoloną
    // this.re odnosi się do POLA klasy, samo re to PARAMETR
    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    // Gettery - jedyny sposób dostępu do prywatnych pól z zewnątrz
    public double getRe() { return re; }
    public double getIm() { return im; }

    // Moduł liczby zespolonej: |z| = sqrt(re^2 + im^2)
    // Geometrycznie - długość wektora od (0,0) do (re, im) na płaszczyźnie zespolonej
    public double abs() {
        return Math.sqrt(re * re + im * im);
    }

    // Argument - kąt jaki tworzy wektor liczby z osią rzeczywistą
    // atan2(im, re) daje pełen zakres (-π, π], inaczej niż zwykły atan
    public double arg() {
        return Math.atan2(im, re);
    }

    // Sprzężenie zespolone: z* = re - im*i (odbicie względem osi rzeczywistej)
    // Przyda się przy macierzach hermitowskich/unitarnych
    public Complex conjugate() {
        return new Complex(re, -im);
    }

    // Dodawanie zespolone: (a+bi) + (c+di) = (a+c) + (b+d)i
    // Zwraca NOWY obiekt - nie modyfikuje tego ani 'other'
    public Complex add(Complex other) {
        return new Complex(this.re + other.re, this.im + other.im);
    }

    // Odejmowanie analogicznie do dodawania
    public Complex subtract(Complex other) {
        return new Complex(this.re - other.re, this.im - other.im);
    }

    // Mnożenie zespolone: (a+bi)(c+di) = (ac - bd) + (ad + bc)i
    // Wynika z tego, że i^2 = -1
    public Complex multiply(Complex other) {
        double newRe = this.re * other.re - this.im * other.im;
        double newIm = this.re * other.im + this.im * other.re;
        return new Complex(newRe, newIm);
    }

    // Przeciążona metoda multiply - mnożenie przez skalar rzeczywisty
    // Java rozróżnia metody po liście parametrów (overloading)
    public Complex multiply(double scalar) {
        return new Complex(re * scalar, im * scalar);
    }

    // toString() jest dziedziczone z Object i wywoływane przy konwersji na String
    // @Override informuje kompilator, że nadpisujemy metodę nadklasy
    @Override
    public String toString() {
        if (im >= 0) {
            return re + " + " + im + "i";
        } else {
            return re + " - " + Math.abs(im) + "i";
        }
    }
}