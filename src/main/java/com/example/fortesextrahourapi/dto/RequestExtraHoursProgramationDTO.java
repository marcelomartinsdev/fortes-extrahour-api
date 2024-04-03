package com.example.fortesextrahourapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class RequestExtraHoursProgramationDTO {
    private String id;

    @NotBlank(message = "A data nao pode estar vazia!")
    private LocalDate programationDate;

    @NotBlank(message = "O campo motivo nao pode estar vazio!")
    private String reason;

    @NotBlank(message = "O campo tecnico nao pode estar vazio!")
    private String tecnico;

    @NotBlank(message = "O campo gestor nao pode estar vazio!")
    private String gestor;

    @NotBlank(message = "O campo funcionario nao pode estar vazio!")
    private List<String> funcionarios;

    @NotBlank(message = "O horario de inicio nao pode estar vazio")
    private LocalTime startTime;

    @NotBlank(message = "O horario de termino nao pode estar vazio")
    private LocalTime endTime;
}

