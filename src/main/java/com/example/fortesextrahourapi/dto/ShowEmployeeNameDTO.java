package com.example.fortesextrahourapi.dto;

import com.example.fortesextrahourapi.domain.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShowEmployeeNameDTO {
    private String name;
    private String username;
    private RoleEnum role;
}
