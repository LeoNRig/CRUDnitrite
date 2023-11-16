package br.androidapps.crudnitr;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.androidapps.crudnitr.model.VeiculoDAO;
import br.androidapps.crudnitr.persistencia.BancoDados;

public class VeiculoActivity extends AppCompatActivity {
    private EditText modeloEditText;
    private EditText placaEditText;
    private EditText renavamEditText;
    private Button salvarButton;

    private BancoDados databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.veiculo_activity);

        modeloEditText = findViewById(R.id.modeloEditText);
        placaEditText = findViewById(R.id.placaEditText);
        salvarButton = findViewById(R.id.salvarButton);
        renavamEditText = findViewById(R.id.renavamEditText);

        databaseHelper = new BancoDados(this);

        salvarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String modelo = modeloEditText.getText().toString();
                String placa = placaEditText.getText().toString();
                String renavam = renavamEditText.getText().toString();

                VeiculoDAO veiculo = new VeiculoDAO();
                veiculo.setModelo(modelo);
                veiculo.setPlaca(placa);
                veiculo.setRenavam(renavam);

                databaseHelper.salvarVeiculo(veiculo);

                modeloEditText.setText("");
                placaEditText.setText("");
                renavamEditText.setText("");

                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.closeDatabase();
    }
}

