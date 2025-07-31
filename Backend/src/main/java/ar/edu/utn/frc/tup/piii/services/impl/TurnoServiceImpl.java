package ar.edu.utn.frc.tup.piii.services.impl;

import ar.edu.utn.frc.tup.piii.dtos.turno.TurnoDto;
import ar.edu.utn.frc.tup.piii.dtos.turno.TurnoRequestDto;
import ar.edu.utn.frc.tup.piii.entities.Medico;
import ar.edu.utn.frc.tup.piii.entities.Paciente;
import ar.edu.utn.frc.tup.piii.entities.Turno;
import ar.edu.utn.frc.tup.piii.repositories.TurnoRepository;
import ar.edu.utn.frc.tup.piii.services.MedicoService;
import ar.edu.utn.frc.tup.piii.services.PacienteService;
import ar.edu.utn.frc.tup.piii.services.TurnoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TurnoServiceImpl implements TurnoService {

    private final TurnoRepository turnoRepository;
    private final MedicoService medicoService;
    private final PacienteService pacienteService;
    private final ModelMapper modelMapper;

    @Override
    public List<TurnoDto> getAllTurnosFiltrados(Optional<Long> medicoId, Optional<Long> pacienteId, Optional<LocalDate> fecha) {
        List<Turno> turnos = turnoRepository.findAllByMedicoIdAndPacienteIdAndFechaTurno(medicoId,pacienteId,fecha);
        List<TurnoDto> turnosFiltrados = new ArrayList<>();
        for(Turno t : turnos){
            turnosFiltrados.add(modelMapper.map(t, new TypeToken<TurnoDto>(){}.getType()));
        }
        return turnosFiltrados;
    }

    @Override
    public TurnoRequestDto createTurno(TurnoRequestDto turno) {
        Turno turnoEntity = new Turno();
        Medico medico = medicoService.findById(turno.getMedicoId());
        Paciente paciente = pacienteService.findById(turno.getPacienteId());
        turnoEntity.setMedico(medico);
        turnoEntity.setPaciente(paciente);
        turnoEntity.setFechaTurno(turno.getFecha());
        Turno turnoSaved = turnoRepository.save(turnoEntity);
        return modelMapper.map(turnoSaved, new TypeToken<TurnoRequestDto>(){}.getType());
    }

    @Override
    public List<TurnoDto> getTurnosByMedicoAndPaciente(Optional<Long> medicoId, Optional<Long> pacienteId) {
        List<Turno> turnos = turnoRepository.findAllByMedicoIdAndPacienteId(medicoId,pacienteId);
        List<TurnoDto> turnosFiltrados = new ArrayList<>();
        for(Turno t : turnos){
            turnosFiltrados.add(modelMapper.map(t, new TypeToken<TurnoDto>(){}.getType()));
        }
        return turnosFiltrados;
    }

    @Override
    public List<TurnoDto> getTurnosByMedicoAndFecha(Optional<Long> medicoId, Optional<LocalDate> fecha) {
        List<Turno> turnos = turnoRepository.findAllByMedicoIdAndFechaTurno(medicoId,fecha);
        List<TurnoDto> turnosFiltrados = new ArrayList<>();
        for(Turno t : turnos){
            turnosFiltrados.add(modelMapper.map(t, new TypeToken<TurnoDto>(){}.getType()));
        }
        return turnosFiltrados;
    }

    @Override
    public List<TurnoDto> getTurnosByMedico(Optional<Long> medicoId) {
        List<Turno> turnos = turnoRepository.findAllByMedicoId(medicoId);
        List<TurnoDto> turnosFiltrados = new ArrayList<>();
        for(Turno t : turnos){
            turnosFiltrados.add(modelMapper.map(t, new TypeToken<TurnoDto>(){}.getType()));
        }
        return turnosFiltrados;
    }

    @Override
    public List<TurnoDto> getTurnosByPacienteAndFecha(Optional<Long> pacienteId, Optional<LocalDate> fecha) {
        List<Turno> turnos = turnoRepository.findAllByPacienteIdAndFechaTurno(pacienteId,fecha);
        List<TurnoDto> turnosFiltrados = new ArrayList<>();
        for(Turno t : turnos){
            turnosFiltrados.add(modelMapper.map(t, new TypeToken<TurnoDto>(){}.getType()));
        }
        return turnosFiltrados;
    }

    @Override
    public List<TurnoDto> getTurnosByPaciente(Optional<Long> pacienteId) {
        List<Turno> turnos = turnoRepository.findAllByPacienteId(pacienteId);
        List<TurnoDto> turnosFiltrados = new ArrayList<>();
        for(Turno t : turnos){
            turnosFiltrados.add(modelMapper.map(t, new TypeToken<TurnoDto>(){}.getType()));
        }
        return turnosFiltrados;
    }

    @Override
    public List<TurnoDto> getTurnosByFecha(Optional<LocalDate> fecha) {
        List<Turno> turnos = turnoRepository.findAllByFechaTurno(fecha);
        List<TurnoDto> turnosFiltrados = new ArrayList<>();
        for(Turno t : turnos){
            turnosFiltrados.add(modelMapper.map(t, new TypeToken<TurnoDto>(){}.getType()));
        }
        return turnosFiltrados;
    }

    @Override
    public List<TurnoDto> getAllTurnos() {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoDto> turnosFiltrados = new ArrayList<>();
        for(Turno t : turnos){
            turnosFiltrados.add(modelMapper.map(t, new TypeToken<TurnoDto>(){}.getType()));
        }
        return turnosFiltrados;
    }


}
