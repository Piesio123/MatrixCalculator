package pl.pwr.matrixcalculator;

/**
 * Macierz o elementach zespolonych - tablica 2D obiektów Complex.
 * Wymiary są ustalane w konstruktorze i nie zmieniają się.
 */
public class Matrix {

    // Tablica 2D liczb zespolonych - rzeczywiste dane macierzy
    private final Complex[][] data;
    private final int rows;     // liczba wierszy
    private final int cols;     // liczba kolumn
    private String name;        // nazwa nadawana przez użytkownika (np. "A", "B")

    // Konstruktor tworzy macierz o zadanych wymiarach i wypełnia ją zerami
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        // 'new Complex[rows][cols]' tworzy SAMĄ TABLICĘ, ale komórki są null
        this.data = new Complex[rows][cols];

        // Pętla wypełnia każdą komórkę obiektem Complex(0, 0)
        // Bez tego pierwsze użycie data[i][j] dałoby NullPointerException
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = new Complex(0, 0);
            }
        }
    }

    // Setter - ustawia wartość w konkretnej komórce macierzy
    public void set(int row, int col, Complex value) {
        data[row][col] = value;
    }

    // Getter - zwraca wartość z konkretnej komórki
    public Complex get(int row, int col) {
        return data[row][col];
    }

    // Standardowe gettery i settery dla pozostałych pól
    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // Reprezentacja tekstowa - przyda się do wyświetlenia macierzy w UI
    // StringBuilder jest wydajniejszy niż konkatenacja String + String w pętli
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append("[").append(data[i][j]).append("] ");
            }
            sb.append("\n");  // przejście do nowego wiersza po każdym rzędzie
        }
        return sb.toString();
    }
}