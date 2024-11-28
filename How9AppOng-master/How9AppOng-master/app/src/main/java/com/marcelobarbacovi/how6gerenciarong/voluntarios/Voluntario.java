package com.marcelobarbacovi.how6gerenciarong.voluntarios;

public class Voluntario{
// atributos da classe voluntario
    private  int id;
    private String name;
    private String especializacao;
    private String telefone;
    private String função;


    // metodos de acesso aos atributos do voluntario para ler e escrever
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public void setEspecializacao(String especialização) {
        this.especializacao = especialização;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFuncao() {
        return função;
    }

    public void setFuncao(String função) {
        this.função = função;
    }
}
