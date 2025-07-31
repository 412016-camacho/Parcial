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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TurnoServiceImplTest {

    @Mock
    private TurnoRepository turnoRepository;

    @InjectMocks
    private TurnoServiceImpl turnoService;

    @Mock
    private MedicoService medicoService;

    @Mock
    private PacienteService pacienteService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        turnoService = new TurnoServiceImpl(turnoRepository, medicoService, pacienteService, modelMapper);
    }

    @Test
    void getAllTurnosFiltrados() {
        Paciente paciente = new Paciente(1L, "Juan Perez", "2013-05-03");
        Medico medico = new Medico(1L, "Karen Flores", "Cardiología");
        Turno turnoDto1 = new Turno(1L, paciente, medico, LocalDate.now(),"");
        Turno turnoDto2 = new Turno(2L, paciente, medico, LocalDate.now(), "");
        List<Turno> turnos = List.of(turnoDto1, turnoDto2);

        when(turnoRepository.findAllByMedicoIdAndPacienteIdAndFechaTurno(Optional.ofNullable(medico.getId()), Optional.ofNullable(paciente.getId()), Optional.ofNullable(turnoDto1.getFechaTurno()))).thenReturn(turnos);

        List<TurnoDto> result = turnoService.getAllTurnosFiltrados(Optional.ofNullable(medico.getId()), Optional.ofNullable(paciente.getId()), Optional.ofNullable(turnoDto1.getFechaTurno()));

        assertNotNull(result);
        assertEquals(turnos.size(), result.size());
        assertEquals("Juan Perez", turnos.get(0).getPaciente().getNombreCompleto());

    }

    @Test
    void createTurno() {
        Paciente paciente = new Paciente(1L, "Juan Perez", "2013-05-03");
        Medico medico = new Medico(1L, "Karen Flores", "Cardiología");
        Turno turno = new Turno(1L, paciente, medico, LocalDate.now(),"");
        TurnoRequestDto turnoRequestDto = new TurnoRequestDto(1L, 1L, turno.getFechaTurno());

        when(medicoService.findById(1L)).thenReturn(medico);
        when(pacienteService.findById(1L)).thenReturn(paciente);
        when(turnoRepository.save(any(Turno.class))).thenReturn(turno);

        TurnoRequestDto result = turnoService.createTurno(turnoRequestDto);

        assertNotNull(result);
        assertEquals(1L, result.getPacienteId());
        assertEquals(1L, result.getMedicoId());
        assertEquals(turno.getFechaTurno(), result.getFecha());

    }

    @Test
    void getTurnosByMedicoAndPaciente() {
        Paciente paciente = new Paciente(1L, "Juan Perez", "2013-05-03");
        Medico medico = new Medico(1L, "Karen Flores", "Cardiología");
        Turno turnoDto1 = new Turno(1L, paciente, medico, LocalDate.now(),"");
        Turno turnoDto2 = new Turno(2L, paciente, medico, LocalDate.now(), "");
        List<Turno> turnos = List.of(turnoDto1, turnoDto2);

        when(turnoRepository.findAllByMedicoIdAndPacienteId(Optional.ofNullable(medico.getId()), Optional.ofNullable(paciente.getId()))).thenReturn(turnos);

        List<TurnoDto> result = turnoService.getTurnosByMedicoAndPaciente(Optional.ofNullable(medico.getId()), Optional.ofNullable(paciente.getId()));

        assertNotNull(result);
        assertEquals(turnos.size(), result.size());
        assertEquals("Juan Perez", turnos.get(0).getPaciente().getNombreCompleto());
    }

    @Test
    void getTurnosByMedicoAndFecha() {
        Paciente paciente = new Paciente(1L, "Juan Perez", "2013-05-03");
        Medico medico = new Medico(1L, "Karen Flores", "Cardiología");
        Turno turnoDto1 = new Turno(1L, paciente, medico, LocalDate.now(),"");
        List<Turno> turnos = List.of(turnoDto1);

        when(turnoRepository.findAllByMedicoIdAndFechaTurno(Optional.ofNullable(medico.getId()), Optional.ofNullable(turnoDto1.getFechaTurno()))).thenReturn(turnos);

        List<TurnoDto> result = turnoService.getTurnosByMedicoAndFecha(Optional.ofNullable(medico.getId()), Optional.ofNullable(turnoDto1.getFechaTurno()));

        assertNotNull(result);
        assertEquals(turnos.size(), result.size());
        assertEquals("Juan Perez", turnos.get(0).getPaciente().getNombreCompleto());
    }

    @Test
    void getTurnosByMedico() {
        Paciente paciente = new Paciente(1L, "Juan Perez", "2013-05-03");
        Medico medico = new Medico(1L, "Karen Flores", "Cardiología");
        Turno turnoDto1 = new Turno(1L, paciente, medico, LocalDate.now(),"");
        Turno turnoDto2 = new Turno(2L, paciente, medico, LocalDate.now(), "");
        List<Turno> turnos = List.of(turnoDto1, turnoDto2);

        when(turnoRepository.findAllByMedicoId(Optional.ofNullable(medico.getId()))).thenReturn(turnos);

        List<TurnoDto> result = turnoService.getTurnosByMedico(Optional.ofNullable(medico.getId()));

        assertNotNull(result);
        assertEquals(turnos.size(), result.size());
        assertEquals("Juan Perez", turnos.get(0).getPaciente().getNombreCompleto());

    }

    @Test
    void getTurnosByPacienteAndFecha() {
        Paciente paciente = new Paciente(1L, "Juan Perez", "2013-05-03");
        Medico medico = new Medico(1L, "Karen Flores", "Cardiología");
        Turno turnoDto1 = new Turno(1L, paciente, medico, LocalDate.now(),"");
        Turno turnoDto2 = new Turno(2L, paciente, medico, LocalDate.now(), "");
        List<Turno> turnos = List.of(turnoDto1, turnoDto2);

        when(turnoRepository.findAllByPacienteIdAndFechaTurno(Optional.ofNullable(paciente.getId()), Optional.ofNullable(turnoDto1.getFechaTurno()))).thenReturn(turnos);

        List<TurnoDto> result = turnoService.getTurnosByPacienteAndFecha(Optional.ofNullable(paciente.getId()), Optional.ofNullable(turnoDto1.getFechaTurno()));

        assertNotNull(result);
        assertEquals(turnos.size(), result.size());
        assertEquals("Juan Perez", turnos.get(0).getPaciente().getNombreCompleto());

    }

    @Test
    void getTurnosByPaciente() {
        Paciente paciente = new Paciente(1L, "Juan Perez", "2013-05-03");
        Medico medico = new Medico(1L, "Karen Flores", "Cardiología");
        Turno turnoDto1 = new Turno(1L, paciente, medico, LocalDate.now(),"");
        Turno turnoDto2 = new Turno(2L, paciente, medico, LocalDate.now(), "");
        List<Turno> turnos = List.of(turnoDto1, turnoDto2);

        when(turnoRepository.findAllByPacienteId(Optional.ofNullable(paciente.getId()))).thenReturn(turnos);

        List<TurnoDto> result = turnoService.getTurnosByPaciente(Optional.ofNullable(paciente.getId()));

        assertNotNull(result);
        assertEquals(turnos.size(), result.size());
        assertEquals("Juan Perez", turnos.get(0).getPaciente().getNombreCompleto());

    }

    @Test
    void getTurnosByFecha() {
        Paciente paciente = new Paciente(1L, "Juan Perez", "2013-05-03");
        Medico medico = new Medico(1L, "Karen Flores", "Cardiología");
        Turno turnoDto1 = new Turno(1L, paciente, medico, LocalDate.now(),"");
        Turno turnoDto2 = new Turno(2L, paciente, medico, LocalDate.now(), "");
        List<Turno> turnos = List.of(turnoDto1, turnoDto2);

        when(turnoRepository.findAllByFechaTurno(Optional.ofNullable(turnoDto1.getFechaTurno()))).thenReturn(turnos);

        List<TurnoDto> result = turnoService.getTurnosByFecha(Optional.ofNullable(turnoDto1.getFechaTurno()));

        assertNotNull(result);
        assertEquals(turnos.size(), result.size());
        assertEquals("Juan Perez", turnos.get(0).getPaciente().getNombreCompleto());

    }

    @Test
    void getAllTurnos() {
        Paciente paciente = new Paciente(1L, "Juan Perez", "2013-05-03");
        Medico medico = new Medico(1L, "Karen Flores", "Cardiología");
        Turno turnoDto1 = new Turno(1L, paciente, medico, LocalDate.now(),"");
        Turno turnoDto2 = new Turno(2L, paciente, medico, LocalDate.now(), "");
        List<Turno> turnos = List.of(turnoDto1, turnoDto2);

        when(turnoRepository.findAll()).thenReturn(turnos);

        List<TurnoDto> result = turnoService.getAllTurnos();

        assertNotNull(result);
        assertEquals(turnos.size(), result.size());
        assertEquals("Juan Perez", turnos.get(0).getPaciente().getNombreCompleto());

    }
}