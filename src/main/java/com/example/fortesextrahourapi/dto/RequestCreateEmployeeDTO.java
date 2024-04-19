package com.example.fortesextrahourapi.dto;

import com.example.fortesextrahourapi.enums.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDate;

@Getter
@Setter
public class RequestCreateEmployeeDTO {

    @NotBlank(message = "O campo name nao pode estar vazio!")
    private String name;

    @NotBlank(message = "O campo username nao pode estar vazio!")
    private String username;

    private RoleEnum role;

    @NotBlank(message = "O campo email nao pode estar vazio!")
    private String email;

    @NotBlank(message = "A password nao pode estar vazia!")
    @Length(min = 8, message = "A password deve ter no minimo 8 caracteres!")
    private String password;

    private String cellphone;

    private boolean active;

    private String registrationDate;
}
