package br.androidapps.crudnitr.model;

public class ClienteDAO {
    private String nome;
    private String cpf;

    public ClienteDAO() {
        this.nome = nome;
        this.cpf = cpf;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
