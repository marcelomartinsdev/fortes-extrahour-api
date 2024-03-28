package com.example.fortesextrahourapi.enums;

import java.util.ArrayList;
import java.util.List;

public enum UnitEnum {
    EDIFICIO_1("Edifício 1"),
    EDIFICIO_2("Edifício 2"),
    EDIFICIO_3("Edifício 3"),
    EDIFICIO_4("Edifício 4"),
    EDIFICIO_5("Edifício 5");

    public final String unit;

    UnitEnum(String unit) {
        this.unit = unit;
    }

    public String getDescription(){
        return this.unit;
    }

    public static List<String> getUnitsDescription() {
        List<String> descriptions = new ArrayList<>();
        for (UnitEnum unit : UnitEnum.values()) {
            descriptions.add(unit.getDescription());
        }
        return descriptions;
    }
}
