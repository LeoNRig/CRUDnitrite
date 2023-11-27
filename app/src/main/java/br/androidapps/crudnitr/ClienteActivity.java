package br.androidapps.crudnitr;

import android.content.Intent;
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
    private EditText emailEditText;
    private EditText telefoneEditText;
    private Cliente clienteEditar;
    private Button salvarButton;
    private Button cancelarButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente_activity);

        nomeEditText = findViewById(R.id.nomeEditText);
        cpfEditText = findViewById(R.id.cpfEditText);
        emailEditText = findViewById(R.id.emailEditText);
        telefoneEditText = findViewById(R.id.telefoneEditText);
        salvarButton = findViewById(R.id.salvarButton);
        cancelarButton = findViewById(R.id.cancelarButton);

        Intent intent = getIntent();
        String clienteEditarId = intent.getStringExtra("clienteEditarId");

        if (clienteEditarId != null) {

            clienteEditar = BancoDados.getClientePorId(clienteEditarId);
            nomeEditText.setText(clienteEditar.getNome());
            cpfEditText.setText(clienteEditar.getCpf());
            emailEditText.setText(clienteEditar.getEmail());
            telefoneEditText.setText(clienteEditar.getTelefone());

        } else {
            clienteEditar = null;
        }

        salvarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clienteEditar == null){
                    clienteEditar = new Cliente();
                }

                clienteEditar.setNome(nomeEditText.getText().toString());
                clienteEditar.setCpf(cpfEditText.getText().toString());
                clienteEditar.setEmail(emailEditText.getText().toString());
                clienteEditar.setTelefone(telefoneEditText.getText().toString());


                BancoDados.salvarCliente(clienteEditar);

                nomeEditText.setText("");
                cpfEditText.setText("");
                emailEditText.setText("");
                clienteEditar.setTelefone("");

                finish();
            }
        });
        cancelarButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                nomeEditText.setText("");
                cpfEditText.setText("");
                emailEditText.setText("");
                telefoneEditText.setText("");

                finish();
            }
        });

    }
}
