package br.androidapps.crudnitr.model;

import androidx.annotation.NonNull;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

public class Veiculo {

    @Id
    NitriteId id;
    private String modelo;

    private String placa;

    private String renavam;

    private String cor;

    private Cliente cliente;


    public Veiculo() {
        this.modelo = modelo;
        this.placa = placa;
        this.renavam = renavam;
        this.cor = cor;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getRenavam() {
        return renavam;
    }
    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public String getCor() {
        return cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }
    @NonNull
    @Override
    public String toString() {
        String clienteInfo = (cliente != null) ? ", Cliente: " + cliente.getNome() : "";
        return "Modelo: " + modelo + ", Placa: " + placa + ""  + clienteInfo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setId(NitriteId id) {
        this.id = id;
    }

    public NitriteId getId() {
        return id;
    }
}
