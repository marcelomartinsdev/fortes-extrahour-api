package com.example.fortesextrahourapi.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    FUNCIONARIO("FUNCIONARIO"),
    ENCARREGADO("ENCARREGADO"),
    TECNICO("TECNICO"),
    GESTOR("GESTOR"),
    GERENTE("GERENTE"),
    ADMINISTRATIVO("ADMINISTRATIVO");

    private final String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public static RoleEnum getRoleEnum(String role) {
        return RoleEnum.valueOf(role);
    }
}