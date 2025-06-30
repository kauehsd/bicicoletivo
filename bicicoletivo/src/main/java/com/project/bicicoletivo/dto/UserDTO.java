package com.project.bicicoletivo.dto;

import com.project.bicicoletivo.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class UserDTO {

    private Long id;
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "O número de ra é obrigatório")
    private Long ra;

    public UserDTO(Long id, String name, String email, Long ra) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.ra = ra;
    }

    public UserDTO(User entity){
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        ra = entity.getRa();

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Long getRa() {
        return ra;
    }
}
