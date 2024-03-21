package com.example.fortesextrahourapi.domain;

import lombok.Getter;

@Getter
public enum EmployeeRole {
    FUNCIONARIO("FUNCIONARIO"),
    ENCARREGADO("ENCARREGADO"),
    TECNICO("TECNICO"),
    GESTOR("GESTOR"),
    GERENTE("GERENTE"),
    ADMINISTRATIVO("ADMINISTRATIVO");

    private final String role;

    EmployeeRole(String role) {
        this.role = role;
    }
}