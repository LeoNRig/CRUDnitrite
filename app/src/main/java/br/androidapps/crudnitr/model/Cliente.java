package br.androidapps.crudnitr.model;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

public class Cliente {
    @Id
    NitriteId id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    public Cliente() {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setId(NitriteId id) {
        this.id = id;
    }

    public NitriteId getId() {
        return id;
    }

    @Override
    public String toString(){
        return nome;
    }

}
