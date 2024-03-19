package com.example.fortesextrahourapi.domain.employee;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "employees")
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    private String id;
    private String matricula;
    @NotBlank(message = "O nome nao pode estar vazio!")
    private String nome;
    private Cargo cargo;
    @NotBlank(message = "O email nao pode estar vazio!")
    private String email;
    @NotBlank(message = "A senha nao pode estar vazia!")
    @Length(min = 8, message = "A senha deve ter no minimo 8 caracteres!")
    private String senha; // lembrar de criptografar
    private String telefone;
    private boolean ativo;
    private LocalDate dataDeCadastro;
}
