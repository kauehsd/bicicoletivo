package com.project.bicicoletivo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CreateUserDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    public Long getRa() {
        return ra;
    }

    public void setRa(Long ra) {
        this.ra = ra;
    }

    @NotBlank(message = "Número de matrícula é obrigatório")
    private Long ra;

    @NotBlank(message = "Marca da bicicleta é obrigatória")
    private String marcabike;

    @NotBlank(message = "Modelo da bicicleta é obrigatório")
    private String modelobike;

    @NotBlank(message = "Cor da bicicleta é obrigatória")
    private String corbike;

    public CreateUserDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMarcabike() {
        return marcabike;
    }

    public void setMarcabike(String marcabike) {
        this.marcabike = marcabike;
    }

    public String getModelobike() {
        return modelobike;
    }

    public void setModelobike(String modelobike) {
        this.modelobike = modelobike;
    }

    public String getCorbike() {
        return corbike;
    }

    public void setCorbike(String corbike) {
        this.corbike = corbike;
    }
}
