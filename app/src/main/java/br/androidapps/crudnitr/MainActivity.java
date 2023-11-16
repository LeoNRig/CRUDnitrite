package br.androidapps.crudnitr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.List;

import br.androidapps.crudnitr.model.ClienteDAO;
import br.androidapps.crudnitr.model.VeiculoDAO;
import br.androidapps.crudnitr.persistencia.BancoDados;

public class MainActivity extends AppCompatActivity {

    private ListView listaVeiculo;

    private ListView listaCliente;
    private ArrayAdapter<VeiculoDAO> veiculoAdpter;
    private ArrayAdapter<ClienteDAO> clienteAdpter;
    private BancoDados databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findViewById(R.id.btnVeiculo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VeiculoActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnCliente).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ClienteActivity.class);
                startActivity(intent);
            }
        });

        listaVeiculo = findViewById(R.id.listViewVeiculos);

        databaseHelper = new BancoDados(this);

        List<VeiculoDAO> veiculos = databaseHelper.listarVeiculo();

        veiculoAdpter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, veiculos);
        listaVeiculo.setAdapter(veiculoAdpter);

        listaCliente= findViewById(R.id.listViewClientes);

        List<ClienteDAO> clientes = databaseHelper.listarCliente();

        clienteAdpter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clientes);
        listaCliente.setAdapter(clienteAdpter);

    }
}