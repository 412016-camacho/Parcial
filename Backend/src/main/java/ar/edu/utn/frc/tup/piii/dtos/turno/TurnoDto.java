package ar.edu.utn.frc.tup.piii.dtos.turno;

import ar.edu.utn.frc.tup.piii.entities.Medico;
import ar.edu.utn.frc.tup.piii.entities.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurnoDto {

    private Long id;

    @NotNull
    private Paciente paciente;

    @NotNull
    private Medico medico;

    @NotNull
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha;
}
