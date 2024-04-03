package com.example.fortesextrahourapi.domain;

import com.example.fortesextrahourapi.enums.ExtraHoursProgramationStatusEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Document(collection = "extrahours-programation")
@Getter
@Setter
@NoArgsConstructor
public class ExtraHoursProgramations {
    @Id
    private String id;

    private ExtraHoursProgramationStatusEnum status;

    @NotBlank(message = "A data nao pode estar vazia!")
    private String programationDate;

    @NotBlank(message = "O campo motivo nao pode estar vazio!")
    private String reason;

    @NotBlank(message = "O campo tecnico nao pode estar vazio!")
    private String tecnico;

    @NotBlank(message = "O campo gestor nao pode estar vazio!")
    private String gestor;

    @NotBlank(message = "O campo funcionario nao pode estar vazio!")
    private List<String> funcionarios;

    @NotBlank(message = "O horario de inicio nao pode estar vazio")
    private String startTime;

    @NotBlank(message = "O horario de termino nao pode estar vazio")
    private String endTime;

    public ExtraHoursProgramations(LocalTime startTime, LocalTime endTime, LocalDate programationDate) {
        this.status = ExtraHoursProgramationStatusEnum.IN_PROGRESS;
        this.startTime = formatLocalTime(startTime);
        this.endTime = formatLocalTime(endTime);
        this.programationDate = formatLocalDate(programationDate);
    }

    private String formatLocalTime(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(formatter);
        //"11:00:00"
    }

    private String formatLocalDate(LocalDate time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return time.format(formatter);
    }
}
