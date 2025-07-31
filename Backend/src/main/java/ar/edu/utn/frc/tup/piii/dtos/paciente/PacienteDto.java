package ar.edu.utn.frc.tup.piii.dtos.paciente;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDto {
    private Long id;

    @NotNull
    @NotBlank
    @JsonProperty("nombre_completo")
    private String nombreCompleto;

    @JsonProperty("fecha_nacimiento")
    private String fechaNacimiento;
}
