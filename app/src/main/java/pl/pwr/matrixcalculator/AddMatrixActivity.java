package pl.pwr.matrixcalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Ekran dodawania nowej macierzy.
 * Użytkownik podaje nazwę, wymiary, generuje siatkę pól, wpisuje wartości i zapisuje.
 */
public class AddMatrixActivity extends AppCompatActivity {

    // Pola formularza - referencje do widgetów z layoutu XML
    private EditText edtName, edtRows, edtCols;
    private GridLayout gridMatrix;

    // Dwie tablice 2D pól EditText - jedna na część rzeczywistą, druga na urojoną
    // Każda komórka macierzy ma DWA pola input (re i im)
    private EditText[][] reFields, imFields;

    // Wymiary macierzy zapamiętywane po wygenerowaniu siatki
    private int rows, cols;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_matrix);

        // Łączymy wszystkie widgety z XML z polami Java
        edtName = findViewById(R.id.edtName);
        edtRows = findViewById(R.id.edtRows);
        edtCols = findViewById(R.id.edtCols);
        gridMatrix = findViewById(R.id.gridMatrix);
        Button btnGenerate = findViewById(R.id.btnGenerate);
        Button btnSave = findViewById(R.id.btnSave);

        // Dwa przyciski - dwa listenery
        // generateGrid() rysuje siatkę pól EditText na podstawie wymiarów
        // saveMatrix() zbiera wartości i tworzy obiekt Matrix
        btnGenerate.setOnClickListener(v -> generateGrid());
        btnSave.setOnClickListener(v -> saveMatrix());
    }

    // Tworzy dynamicznie siatkę pól EditText na podstawie podanych wymiarów
    private void generateGrid() {
        try {
            // Konwersja tekstu z pól na int
            // Integer.parseInt rzuca NumberFormatException jeśli tekst nie jest liczbą
            rows = Integer.parseInt(edtRows.getText().toString());
            cols = Integer.parseInt(edtCols.getText().toString());
        } catch (NumberFormatException e) {
            // Toast to małe powiadomienie na dole ekranu
            Toast.makeText(this, "Podaj wymiary!", Toast.LENGTH_SHORT).show();
            return;   // przerywamy - nie da się generować siatki bez wymiarów
        }

        // Usuwamy ewentualną poprzednią siatkę
        gridMatrix.removeAllViews();
        gridMatrix.setRowCount(rows);
        gridMatrix.setColumnCount(cols * 2);   // 2 pola na komórkę: re, im

        // Tworzymy tablice na referencje do nowo generowanych EditText
        reFields = new EditText[rows][cols];
        imFields = new EditText[rows][cols];

        // Dla każdej komórki macierzy generujemy 2 pola tekstowe
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                // Pole na część rzeczywistą
                // 'this' jako Context - EditText potrzebuje wiedzieć w jakim Activity działa
                EditText re = new EditText(this);
                re.setHint("re");
                re.setWidth(150);
                reFields[i][j] = re;
                gridMatrix.addView(re);   // dodanie widoku do siatki

                // Pole na część urojoną - analogicznie
                EditText im = new EditText(this);
                im.setHint("im");
                im.setWidth(150);
                imFields[i][j] = im;
                gridMatrix.addView(im);
            }
        }
    }

    // Zbiera wartości z pól, tworzy obiekt Matrix i zapisuje do repozytorium
    private void saveMatrix() {
        // Sprawdzenie - czy użytkownik najpierw wygenerował siatkę
        if (reFields == null) {
            Toast.makeText(this, "Najpierw wygeneruj siatkę!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tworzymy NOWĄ macierz o zadanych wymiarach
        Matrix m = new Matrix(rows, cols);
        m.setName(edtName.getText().toString());

        // Iterujemy przez wszystkie komórki i pobieramy wartości z pól
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double re = parseOrZero(reFields[i][j].getText().toString());
                double im = parseOrZero(imFields[i][j].getText().toString());
                m.set(i, j, new Complex(re, im));
            }
        }

        // Zapisujemy macierz do singletona - od teraz widzi ją cała apka
        MatrixRepository.getInstance().add(m);
        Toast.makeText(this, "Zapisano macierz " + m.getName(), Toast.LENGTH_SHORT).show();

        // finish() zamyka tę Activity i wraca do poprzedniej (MainActivity)
        // System Androida automatycznie zdejmuje nas ze stosu Activity
        finish();
    }

    // Pomocnicza metoda - próbuje sparsować string na double, zwraca 0 jeśli pusty/zły
    // Dzięki temu użytkownik nie musi wpisywać 0 ręcznie do każdej komórki
    private double parseOrZero(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}