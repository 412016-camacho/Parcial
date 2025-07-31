package ar.edu.utn.frc.tup.piii.services;

import ar.edu.utn.frc.tup.piii.dtos.paciente.PacienteDto;
import ar.edu.utn.frc.tup.piii.entities.Paciente;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PacienteService {
    List<PacienteDto> getAllPacientes();

    Paciente findById(@NotNull Long pacienteId);
}
