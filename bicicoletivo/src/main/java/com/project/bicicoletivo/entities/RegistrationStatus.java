package com.project.bicicoletivo.entities;

public enum RegistrationStatus {
    ACTIVE("Ativo"),
    FINISHED("Finalizado");

    private final String description;

    RegistrationStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

