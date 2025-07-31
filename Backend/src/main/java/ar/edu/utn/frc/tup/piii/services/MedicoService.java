package ar.edu.utn.frc.tup.piii.services;

import ar.edu.utn.frc.tup.piii.dtos.medico.MedicoDto;
import ar.edu.utn.frc.tup.piii.entities.Medico;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicoService {
    List<MedicoDto> getAllMedicos();

    Medico findById(@NotNull Long medicoId);
}
