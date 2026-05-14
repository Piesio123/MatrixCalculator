package pl.pwr.matrixcalculator;

public class Matrix {
    private final Complex[][] data;
    private final int rows;
    private final int cols;
    private String name;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new Complex[rows][cols];
        // Inicjalizuj zerami
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = new Complex(0, 0);
            }
        }
    }

    public void set(int row, int col, Complex value) {
        data[row][col] = value;
    }

    public Complex get(int row, int col) {
        return data[row][col];
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append("[").append(data[i][j]).append("] ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}