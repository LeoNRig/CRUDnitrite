package br.androidapps.crudnitr.model;

public class VeiculoDAO {
    private String modelo;

    private String placa;

    private String renavam;


    public VeiculoDAO() {
        this.modelo = modelo;
        this.placa = placa;
        this.renavam = renavam;
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
}
