package br.androidapps.crudnitr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

import br.androidapps.crudnitr.model.Veiculo;
import br.androidapps.crudnitr.persistencia.BancoDados;

public class VeiculoActivity extends AppCompatActivity {
    private EditText modeloEditText;
    private EditText placaEditText;
    private EditText renavamEditText;
    private Button salvarButton;

    private Veiculo veiculoEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.veiculo_activity);

        modeloEditText = findViewById(R.id.modeloEditText);
        placaEditText = findViewById(R.id.placaEditText);
        salvarButton = findViewById(R.id.salvarButton);
        renavamEditText = findViewById(R.id.renavamEditText);

        Intent intent = getIntent();
        String veiculoEditarId = intent.getStringExtra("veiculoEditarId");

        if (veiculoEditarId != null) {

            veiculoEditar = BancoDados.getVeiculoPorId(veiculoEditarId);
            modeloEditText.setText(veiculoEditar.getModelo());
            placaEditText.setText(veiculoEditar.getPlaca());
            renavamEditText.setText(veiculoEditar.getRenavam());
        } else {
            veiculoEditar = null;
        }

        salvarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(veiculoEditar == null){
                    veiculoEditar = new Veiculo();
                }

                veiculoEditar.setModelo(modeloEditText.getText().toString());
                veiculoEditar.setPlaca(placaEditText.getText().toString());
                veiculoEditar.setRenavam(renavamEditText.getText().toString());



                    BancoDados.salvarVeiculo(veiculoEditar);

                modeloEditText.setText("");
                placaEditText.setText("");
                renavamEditText.setText("");

                finish();
            }
        });
    }
}

