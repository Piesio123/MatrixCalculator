package pl.pwr.matrixcalculator;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddMatrixActivity extends AppCompatActivity {

    private EditText edtName, edtRows, edtCols;
    private GridLayout gridMatrix;
    private EditText[][] reFields, imFields;
    private int rows, cols;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_matrix);

        edtName = findViewById(R.id.edtName);
        edtRows = findViewById(R.id.edtRows);
        edtCols = findViewById(R.id.edtCols);
        gridMatrix = findViewById(R.id.gridMatrix);
        Button btnGenerate = findViewById(R.id.btnGenerate);
        Button btnSave = findViewById(R.id.btnSave);

        btnGenerate.setOnClickListener(v -> generateGrid());
        btnSave.setOnClickListener(v -> saveMatrix());
    }

    private void generateGrid() {
        try {
            rows = Integer.parseInt(edtRows.getText().toString());
            cols = Integer.parseInt(edtCols.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Podaj wymiary!", Toast.LENGTH_SHORT).show();
            return;
        }

        gridMatrix.removeAllViews();
        gridMatrix.setRowCount(rows);
        gridMatrix.setColumnCount(cols * 2); // 2 pola na komórkę: re, im

        reFields = new EditText[rows][cols];
        imFields = new EditText[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                EditText re = new EditText(this);
                re.setHint("re");
                re.setWidth(150);
                reFields[i][j] = re;
                gridMatrix.addView(re);

                EditText im = new EditText(this);
                im.setHint("im");
                im.setWidth(150);
                imFields[i][j] = im;
                gridMatrix.addView(im);
            }
        }
    }

    private void saveMatrix() {
        if (reFields == null) {
            Toast.makeText(this, "Najpierw wygeneruj siatkę!", Toast.LENGTH_SHORT).show();
            return;
        }

        Matrix m = new Matrix(rows, cols);
        m.setName(edtName.getText().toString());

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double re = parseOrZero(reFields[i][j].getText().toString());
                double im = parseOrZero(imFields[i][j].getText().toString());
                m.set(i, j, new Complex(re, im));
            }
        }

        MatrixRepository.getInstance().add(m);
        Toast.makeText(this, "Zapisano macierz " + m.getName(), Toast.LENGTH_SHORT).show();
        finish();
    }

    private double parseOrZero(String s) {
        try { return Double.parseDouble(s); }
        catch (NumberFormatException e) { return 0; }
    }
}