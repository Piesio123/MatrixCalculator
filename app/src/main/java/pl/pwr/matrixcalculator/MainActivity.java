package pl.pwr.matrixcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView txtMatrixList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAddMatrix = findViewById(R.id.btnAddMatrix);
        txtMatrixList = findViewById(R.id.txtMatrixList);

        btnAddMatrix.setOnClickListener(v ->
                startActivity(new Intent(this, AddMatrixActivity.class))
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }

    private void refreshList() {
        StringBuilder sb = new StringBuilder();
        for (Matrix m : MatrixRepository.getInstance().getAll()) {
            sb.append("=== ").append(m.getName()).append(" ===\n");
            sb.append(m.toString()).append("\n");
        }
        if (sb.length() == 0) sb.append("Brak zapisanych macierzy");
        txtMatrixList.setText(sb.toString());
    }
}