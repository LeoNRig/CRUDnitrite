package br.androidapps.crudnitr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;

import br.androidapps.crudnitr.model.Cliente;
import br.androidapps.crudnitr.model.Veiculo;
import br.androidapps.crudnitr.persistencia.BancoDados;

public class MainActivity extends AppCompatActivity {

    private ListView listaVeiculo;

    private ListView listaCliente;
    private ArrayAdapter<Veiculo> veiculoAdpter;
    private ArrayAdapter<Cliente> clienteAdpter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BancoDados.iniciaBancoDados(this);
        
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BancoDados.closeDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaVeiculo = findViewById(R.id.listViewVeiculos);

        List<Veiculo> veiculos = BancoDados.listarVeiculo();

        veiculoAdpter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, veiculos);
        listaVeiculo.setAdapter(veiculoAdpter);

        listaCliente= findViewById(R.id.listViewClientes);

        List<Cliente> clientes = BancoDados.listarCliente();

        clienteAdpter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clientes);
        listaCliente.setAdapter(clienteAdpter);
    }
}