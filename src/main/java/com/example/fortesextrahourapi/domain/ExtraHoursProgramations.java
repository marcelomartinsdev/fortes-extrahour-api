package com.example.fortesextrahourapi.domain;

import com.example.fortesextrahourapi.enums.ExtraHoursProgramationStatusEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Document(collection = "extrahours-programation")
@Getter
@Setter
public class ExtraHoursProgramations {
    @Id
    private String id;

    private ExtraHoursProgramationStatusEnum status;

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

    public ExtraHoursProgramations() {
        this.status = ExtraHoursProgramationStatusEnum.IN_PROGRESS;
    }
}
