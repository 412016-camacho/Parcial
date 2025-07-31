package ar.edu.utn.frc.tup.piii.dtos.turno;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TurnoRequestDto {

    @NotNull
    @JsonProperty("paciente_id")
    private Long pacienteId;

    @NotNull
    @JsonProperty("medico_id")
    private Long medicoId;

    @NotNull
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha;
}
