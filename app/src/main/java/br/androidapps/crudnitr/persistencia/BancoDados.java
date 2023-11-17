package br.androidapps.crudnitr.persistencia;

import android.content.Context;
import android.util.Log;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.List;

import br.androidapps.crudnitr.model.Cliente;
import br.androidapps.crudnitr.model.Veiculo;

public class BancoDados {
    public static Nitrite nitrite;
    public static ObjectRepository<Veiculo> veiculoRepository;
    public static ObjectRepository<Cliente> clienteRepository;

    public static void iniciaBancoDados(Context context) {
        nitrite = Nitrite.builder()
                .filePath(context.getFilesDir() + "/mydatabase.db")
                .openOrCreate();
        veiculoRepository = nitrite.getRepository(Veiculo.class);
        clienteRepository = nitrite.getRepository(Cliente.class);
    }
    public static void salvarVeiculo(Veiculo veiculo) {
        veiculoRepository.insert(veiculo);
        Log.d("BancoDados", "Veículo salvo: Modelo - " + veiculo.getModelo() + ", Placa - " + veiculo.getPlaca()+", Renavam - " + veiculo.getRenavam());
    }
    public static void salvarCliente(Cliente cliente) {
        clienteRepository.insert(cliente);
        Log.d("BancoDados", "Cliente salvo: Modelo - " + cliente.getNome() + ", Placa - " + cliente.getCpf());

    }


    public static List<Veiculo> listarVeiculo(){
        Cursor<Veiculo> cursor = veiculoRepository.find();
        return cursor.toList();
    }

    public static List<Cliente> listarCliente(){
        Cursor<Cliente> cursor = clienteRepository.find();
        return cursor.toList();
    }



    public static void closeDatabase() {
        nitrite.close();
    }

}
