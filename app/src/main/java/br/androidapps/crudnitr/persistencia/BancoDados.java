package br.androidapps.crudnitr.persistencia;

import android.content.Context;
import android.util.Log;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.List;

import br.androidapps.crudnitr.model.ClienteDAO;
import br.androidapps.crudnitr.model.VeiculoDAO;

public class BancoDados {
    private Nitrite nitrite;
    private ObjectRepository<VeiculoDAO> veiculoRepository;
    private ObjectRepository<ClienteDAO> clienteRepository;

    public BancoDados(Context context) {
        nitrite = Nitrite.builder()
                .filePath(context.getFilesDir() + "/mydatabase.db")
                .openOrCreate();
        veiculoRepository = nitrite.getRepository(VeiculoDAO.class);
        clienteRepository = nitrite.getRepository(ClienteDAO.class);
    }
    public void salvarVeiculo(VeiculoDAO veiculo) {
        veiculoRepository.insert(veiculo);
        Log.d("BancoDados", "Veículo salvo: Modelo - " + veiculo.getModelo() + ", Placa - " + veiculo.getPlaca()+", Renavam - " + veiculo.getRenavam());
    }
    public void salvarCliente(ClienteDAO cliente) {
        clienteRepository.insert(cliente);
        Log.d("BancoDados", "Cliente salvo: Modelo - " + cliente.getNome() + ", Placa - " + cliente.getCpf());

    }


    public List<VeiculoDAO> listarVeiculo(){
        Cursor<VeiculoDAO> cursor = veiculoRepository.find();
        return cursor.toList();
    }

    public List<ClienteDAO> listarCliente(){
        Cursor<ClienteDAO> cursor = clienteRepository.find();
        return cursor.toList();
    }



    public void closeDatabase() {
        nitrite.close();
    }

}
