package ar.edu.utn.frc.tup.piii.dtos.medico;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoDto {

    private Long id;

    @NotNull
    @NotBlank
    @JsonProperty("nombre_completo")
    private String nombreCompleto;

    @NotNull
    @NotBlank
    private String especialidad;
}
