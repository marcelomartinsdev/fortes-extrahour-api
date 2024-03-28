package com.example.fortesextrahourapi.dto;

import com.example.fortesextrahourapi.enums.UnitEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class ResponseExtraHoursProgramationDTO {
    private List<ShowEmployeeNameDTO> tecnico;
    private List<ShowEmployeeNameDTO> gestor;
    private List<ShowEmployeeNameDTO> funcionarios;
    private List<String> units;
}
