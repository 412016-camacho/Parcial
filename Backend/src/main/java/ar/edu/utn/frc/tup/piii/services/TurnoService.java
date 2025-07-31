package ar.edu.utn.frc.tup.piii.services;

import ar.edu.utn.frc.tup.piii.dtos.turno.TurnoDto;
import ar.edu.utn.frc.tup.piii.dtos.turno.TurnoRequestDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface TurnoService {

    List<TurnoDto> getTurnosByMedicoAndPaciente(Optional<Long> medicoId, Optional<Long> pacienteId);

    List<TurnoDto> getTurnosByMedicoAndFecha(Optional<Long> medicoId, Optional<LocalDate> fecha);

    List<TurnoDto> getTurnosByMedico(Optional<Long> medicoId);

    List<TurnoDto> getTurnosByPacienteAndFecha(Optional<Long> pacienteId, Optional<LocalDate> fecha);

    List<TurnoDto> getTurnosByPaciente(Optional<Long> pacienteId);

    List<TurnoDto> getTurnosByFecha(Optional<LocalDate> fecha);

    List<TurnoDto> getAllTurnos();

    List<TurnoDto>  getAllTurnosFiltrados(Optional<Long> medicoId, Optional<Long> pacienteId, Optional<LocalDate> fecha);

    TurnoRequestDto createTurno(@Valid TurnoRequestDto turno);
}