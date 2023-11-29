package br.androidapps.crudnitr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import org.dizitart.no2.NitriteId;

import java.util.List;

import br.androidapps.crudnitr.model.Cliente;
import br.androidapps.crudnitr.model.Veiculo;
import br.androidapps.crudnitr.persistencia.BancoDados;

public class VeiculoActivity extends AppCompatActivity {
    private EditText modeloEditText;
    private EditText placaEditText;
    private EditText renavamEditText;
    private EditText corEditText;
    private EditText clienteEditText;
    private Button btnVincular;
    private Button salvarButton;
    private Button cancelarButton;
    private Veiculo veiculoEditar;
    private ArrayAdapter<Veiculo> veiculoAdpter;
    private ArrayAdapter<Cliente> clienteAdpter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.veiculo_activity);

        clienteAdpter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, BancoDados.listarCliente());
        veiculoAdpter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, BancoDados.listarVeiculo());


        modeloEditText = findViewById(R.id.modeloEditText);
        placaEditText = findViewById(R.id.placaEditText);
        salvarButton = findViewById(R.id.salvarButton);
        cancelarButton = findViewById(R.id.cancelarButton);
        btnVincular = findViewById(R.id.btnVincular);
        renavamEditText = findViewById(R.id.renavamEditText);
        corEditText = findViewById(R.id.corEditText);
        clienteEditText = findViewById(R.id.clienteEditText);

        Intent intent = getIntent();
        String veiculoEditarId = intent.getStringExtra("veiculoEditarId");

        if (veiculoEditarId != null) {

            veiculoEditar = BancoDados.getVeiculoPorId(veiculoEditarId);
            modeloEditText.setText(veiculoEditar.getModelo());
            placaEditText.setText(veiculoEditar.getPlaca());
            renavamEditText.setText(veiculoEditar.getRenavam());
            corEditText.setText(veiculoEditar.getCor());

        } else {
            veiculoEditar = null;
        }


        salvarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (veiculoEditar == null) {
                    veiculoEditar = new Veiculo();
                }

                veiculoEditar.setModelo(modeloEditText.getText().toString());
                veiculoEditar.setPlaca(placaEditText.getText().toString());
                veiculoEditar.setRenavam(renavamEditText.getText().toString());
                veiculoEditar.setCor(corEditText.getText().toString());


                BancoDados.salvarVeiculo(veiculoEditar);

                modeloEditText.setText("");
                placaEditText.setText("");
                renavamEditText.setText("");
                corEditText.setText("");
                clienteEditText.setText("");

                setResult(RESULT_OK);


                finish();
            }
        });
        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                modeloEditText.setText("");
                placaEditText.setText("");
                renavamEditText.setText("");
                corEditText.setText("");
                clienteEditText.setText("");

                finish();
            }
        });
        btnVincular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(btnVincular);
                openContextMenu(btnVincular);
                unregisterForContextMenu(btnVincular);
            }
        });


    }
    @Override
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v == btnVincular) {
            getMenuInflater().inflate(R.menu.vinculo_cliente, menu);

            List<Cliente> clientes = BancoDados.listarCliente();
            for (Cliente cliente : clientes) {
                long clienteId = cliente.getId().getIdValue();
                menu.add(Menu.NONE, Menu.NONE, Menu.NONE, clienteId + "]]" + cliente.getNome());
            }
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Long itemId = Long.parseLong(("" + item.getTitle()).split("]]")[0]);

            Cliente clienteSelecionado = BancoDados.clienteRepository.getById(NitriteId.createId(itemId));

            if (clienteSelecionado != null) {
                if (veiculoEditar != null) {

                    veiculoEditar.setCliente(clienteSelecionado);
                    Log.d("VincularCliente", "Cliente vinculado: " + veiculoEditar.getCliente().getNome());

                    clienteEditText.setText(clienteSelecionado.getNome());

                    BancoDados.salvarVeiculo(veiculoEditar);

                    veiculoAdpter.notifyDataSetChanged();
                    finish();
                    Log.e("VincularCliente", "Veículo vinculado " + veiculoEditar.getCliente());
                } else {
                    Log.e("VincularCliente", "Veículo a ser vinculado é nulo");
                }
            }   else {
                Log.e("VincularCliente", "Cliente selecionado é nulo");
            }

        return super.onContextItemSelected(item);
    }



}

