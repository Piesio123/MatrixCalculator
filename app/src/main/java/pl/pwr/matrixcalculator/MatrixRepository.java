package pl.pwr.matrixcalculator;

import java.util.ArrayList;
import java.util.List;

public class MatrixRepository {
    private static MatrixRepository instance;
    private final List<Matrix> matrices = new ArrayList<>();

    private MatrixRepository() {}

    public static MatrixRepository getInstance() {
        if (instance == null) instance = new MatrixRepository();
        return instance;
    }

    public void add(Matrix m) { matrices.add(m); }
    public List<Matrix> getAll() { return matrices; }
}