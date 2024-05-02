package com.example.fortesextrahourapi.dto;

import com.example.fortesextrahourapi.enums.ExtraHoursProgramationStatusEnum;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ResponseListExtraHoursProgramationDTO {
    private String id;

    private ExtraHoursProgramationStatusEnum status;

    private String tecnico;

    private String gestor;

    private String programationDate;

    private String startTime;

    private String endTime;
}
