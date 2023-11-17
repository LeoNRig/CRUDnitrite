package br.androidapps.crudnitr;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.androidapps.crudnitr.model.Cliente;
import br.androidapps.crudnitr.persistencia.BancoDados;

public class ClienteActivity extends AppCompatActivity {
    private EditText nomeEditText;
    private EditText cpfEditText;
    private Button salvarButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente_activity);

        nomeEditText = findViewById(R.id.nomeEditText);
        cpfEditText = findViewById(R.id.cpfEditText);
        salvarButton = findViewById(R.id.salvarButton);

        salvarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = nomeEditText.getText().toString();
                String cpf = cpfEditText.getText().toString();

                Cliente cliente = new Cliente();
                cliente.setNome(nome);
                cliente.setCpf(cpf);

                BancoDados.salvarCliente(cliente);

                nomeEditText.setText("");
                cpfEditText.setText("");

                finish();
            }
        });
    }
}
