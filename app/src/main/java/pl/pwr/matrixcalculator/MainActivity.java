package pl.pwr.matrixcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Główny ekran aplikacji.
 * Dziedziczy po AppCompatActivity, która zapewnia kompatybilność wsteczną dla starszych Androidów.
 */
public class MainActivity extends AppCompatActivity {

    // Pole klasy - referencja do TextView z listą macierzy
    // Trzymamy je jako pole, żeby móc je odświeżać z innych metod (np. onResume)
    private TextView txtMatrixList;

    // onCreate - pierwsza metoda cyklu życia Activity, wywoływana raz przy tworzeniu ekranu
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   // wywołanie metody z nadklasy - WYMAGANE
        setContentView(R.layout.activity_main);   // ładuje layout XML do widoku

        // findViewById - łączy widget z XML z obiektem Java
        // R.id.btnAddMatrix to wygenerowany przez Androida int identyfikujący widget
        Button btnAddMatrix = findViewById(R.id.btnAddMatrix);
        txtMatrixList = findViewById(R.id.txtMatrixList);

        // Rejestracja listenera na kliknięcie przycisku
        // Lambda v -> ... to skrócony zapis anonimowego obiektu OnClickListener
        // 'this' = ta instancja MainActivity (przekazana jako Context dla Intentu)
        btnAddMatrix.setOnClickListener(v ->
                startActivity(new Intent(this, AddMatrixActivity.class))
        );
    }

    // onResume - wywoływane gdy Activity wraca na pierwszy plan
    // Tu wraca, gdy użytkownik kończy AddMatrixActivity (klika back lub finish())
    // Idealne miejsce żeby odświeżyć listę o nowo zapisane macierze
    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }

    // Buduje tekst z listą wszystkich macierzy i wyświetla go w TextView
    private void refreshList() {
        StringBuilder sb = new StringBuilder();

        // Pętla foreach - przechodzi przez wszystkie macierze z repozytorium
        // Singleton gwarantuje, że to TA SAMA lista co w AddMatrixActivity
        for (Matrix m : MatrixRepository.getInstance().getAll()) {
            sb.append("=== ").append(m.getName()).append(" ===\n");
            sb.append(m.toString()).append("\n");
        }

        if (sb.length() == 0) {
            sb.append("Brak zapisanych macierzy");
        }

        // setText() aktualizuje tekst widoczny na ekranie
        txtMatrixList.setText(sb.toString());
    }
}