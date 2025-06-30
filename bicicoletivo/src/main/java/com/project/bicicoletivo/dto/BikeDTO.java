package com.project.bicicoletivo.dto;



public class BikeDTO {
    private String nome;
    private String ra;
    private String modelo;
    private String dataHora;
    private String codigo;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRa() {
        return ra;
    }
    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDataHora() {
        return dataHora;
    }
    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
