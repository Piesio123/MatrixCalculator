package pl.pwr.matrixcalculator;

import java.util.ArrayList;
import java.util.List;

/**
 * SINGLETON - wzorzec projektowy gwarantujący tylko jedną instancję klasy.
 * Trzyma wszystkie macierze stworzone przez użytkownika.
 * Dzięki singletonowi MainActivity i AddMatrixActivity widzą TĘ SAMĄ listę.
 */
public class MatrixRepository {

    // Statyczne pole 'instance' - należy do KLASY, nie do obiektu
    // Istnieje jedno na całą aplikację
    private static MatrixRepository instance;

    // Lista przechowująca wszystkie zapisane macierze
    private final List<Matrix> matrices = new ArrayList<>();

    // PRYWATNY konstruktor - nikt z zewnątrz nie może zrobić "new MatrixRepository()"
    // To wymusza użycie getInstance()
    private MatrixRepository() {}

    // Jedyna droga do obiektu - "lazy initialization"
    // Pierwszy wywołujący tworzy instancję, każdy kolejny dostaje tę samą
    public static MatrixRepository getInstance() {
        if (instance == null) {
            instance = new MatrixRepository();
        }
        return instance;
    }

    // Dodaje macierz do listy
    public void add(Matrix m) {
        matrices.add(m);
    }

    // Zwraca listę wszystkich zapisanych macierzy
    public List<Matrix> getAll() {
        return matrices;
    }
}