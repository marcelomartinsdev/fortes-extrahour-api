package com.example.fortesextrahourapi.enums;

import lombok.Getter;

@Getter
public enum ExtraHoursProgramationStatusEnum {
    IN_PROGRESS("IN_PROGRESS"),
    CANCELED("CANCELED"),
    APPROVED("APPROVED");

    private final String status;
    ExtraHoursProgramationStatusEnum(String status) {
        this.status = status;
    }
}
