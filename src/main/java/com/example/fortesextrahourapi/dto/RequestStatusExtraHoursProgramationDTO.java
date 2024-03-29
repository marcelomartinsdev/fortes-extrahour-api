package com.example.fortesextrahourapi.dto;

import com.example.fortesextrahourapi.enums.ExtraHoursProgramationStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestStatusExtraHoursProgramationDTO {
    private String programationId;

    private ExtraHoursProgramationStatusEnum status;
}
