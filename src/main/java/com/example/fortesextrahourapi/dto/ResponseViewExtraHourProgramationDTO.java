package com.example.fortesextrahourapi.dto;

import com.example.fortesextrahourapi.enums.ExtraHoursProgramationStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseViewExtraHourProgramationDTO {
    private String id;
    private ExtraHoursProgramationStatusEnum status;
    private String programationDate;
    private String reason;
    private String tecnico;
    private String gestor;
    private List<String> funcionarios;
    private String startTime;
    private String endTime;
}
