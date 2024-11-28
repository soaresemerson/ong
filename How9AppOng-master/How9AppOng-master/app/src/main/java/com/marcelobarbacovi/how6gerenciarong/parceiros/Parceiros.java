package com.marcelobarbacovi.how6gerenciarong.parceiros;

public class Parceiros {
 // atributos da classe parceiro
    private  int id;
    private String nome;
    private String telefone;
    private String email;
    private String observacao;

    public int getId() {
        return id;
    }
    // metodos set e getter de acesso dos atributos

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
