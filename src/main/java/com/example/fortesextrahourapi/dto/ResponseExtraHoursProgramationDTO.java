package com.example.fortesextrahourapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseExtraHoursProgramationDTO {
    private List<ShowEmployeeNameDTO> tecnico;
    private List<ShowEmployeeNameDTO> gestor;
    private List<ShowEmployeeNameDTO> funcionarios;
    private List<String> units;
}
