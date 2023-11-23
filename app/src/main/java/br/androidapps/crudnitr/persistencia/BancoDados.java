package br.androidapps.crudnitr.persistencia;

import android.content.Context;
import android.util.Log;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.filters.Filters;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;

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
        if (veiculo.getId() != null) {
            veiculoRepository.update(veiculo);
            Log.d("BancoDados", "Veículo atualizado: ID - " + veiculo.getId());
        } else {
            veiculoRepository.insert(veiculo);
            Log.d("BancoDados", "Veículo salvo: Modelo - " + veiculo.getModelo() + ", Placa - " + veiculo.getPlaca()+", Renavam - " + veiculo.getRenavam());
        }
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

    public static void editarVeiculo(NitriteId veiculoId) {
        Veiculo veiculo = veiculoRepository.getById(veiculoId);
    }

    public static void editarCliente(NitriteId clienteId) {
        Cliente cliente = clienteRepository.getById(clienteId);
    }

    public static void excluirVeiculo(NitriteId veiculoId) {
        Veiculo veiculo = veiculoRepository.getById(veiculoId);
        veiculoRepository.remove(veiculo);
        Log.d("BancoDados", "Veículo excluído: ID - " + veiculoId);
    }

    public static void excluirCliente(NitriteId clienteId) {
        Cliente cliente = clienteRepository.getById(clienteId);
        clienteRepository.remove(cliente);
        Log.d("BancoDados", "Cliente excluído: ID - " + clienteId);
    }

    public static Veiculo getVeiculoPorId(String veiculoId) {
        veiculoId = veiculoId.replaceAll("[^\\d]", "");
        NitriteId nitriteId = NitriteId.createId(Long.valueOf(String.valueOf(veiculoId)));
        Cursor<Veiculo> cursor = veiculoRepository.find(ObjectFilters.eq("_id", nitriteId));
        List<Veiculo> veiculos = cursor.toList();

        if (!veiculos.isEmpty()) {
            return veiculos.get(0);
        } else {
            return null;
        }
    }


    public static void closeDatabase() {
        nitrite.close();
    }

}
