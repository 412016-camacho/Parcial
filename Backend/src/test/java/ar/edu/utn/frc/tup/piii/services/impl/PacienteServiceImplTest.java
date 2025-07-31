package ar.edu.utn.frc.tup.piii.services.impl;

import ar.edu.utn.frc.tup.piii.dtos.paciente.PacienteDto;
import ar.edu.utn.frc.tup.piii.entities.Medico;
import ar.edu.utn.frc.tup.piii.entities.Paciente;
import ar.edu.utn.frc.tup.piii.repositories.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PacienteServiceImplTest {

    @Mock
    private PacienteRepository pacienteRepository;

    private PacienteServiceImpl pacienteService;

    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        pacienteService = new PacienteServiceImpl(pacienteRepository, modelMapper);
    }

    @Test
    void getAllPacientes() {
        Paciente paciente1 = new Paciente(1L, "Juan Perez", "2013-05-03");
        Paciente paciente2 = new Paciente(2L, "Ramón Valdez", "1993-08-23");
        Paciente paciente3 = new Paciente(3L, "Karen Flores", "1994-06-16");
        List<Paciente> pacientes = List.of(paciente1, paciente2, paciente3);

        when(pacienteRepository.findAll()).thenReturn(pacientes);

        List<PacienteDto> result = pacienteService.getAllPacientes();

        assertNotNull(result);
        assertEquals(3, pacientes.size());
        assertEquals("Juan Perez", pacientes.get(0).getNombreCompleto());

    }

    @Test
    void findById() {
        Paciente paciente = new Paciente(1L, "Juan Perez", "2020-04-04");

        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));

        Optional<Paciente> result = Optional.ofNullable(pacienteService.findById(1L));

        assertNotNull(result);
        assertEquals("Juan Perez", result.get().getNombreCompleto());
        assertEquals(1L, result.get().getId());
    }

}