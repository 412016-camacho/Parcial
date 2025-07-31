package ar.edu.utn.frc.tup.piii.services.impl;

import ar.edu.utn.frc.tup.piii.dtos.medico.MedicoDto;
import ar.edu.utn.frc.tup.piii.entities.Medico;
import ar.edu.utn.frc.tup.piii.repositories.MedicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MedicoServiceImplTest {

    @Mock
    private MedicoRepository medicoRepository;

    private MedicoServiceImpl medicoService;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        medicoService = new MedicoServiceImpl(medicoRepository, modelMapper);
    }

    @Test
    void getAllMedicos() {
        Medico medico1 = new Medico(1L, "Juan Perez", "Cardiología");
        Medico medico2 = new Medico(2L, "Ramón Valdez", "Clínico");
        Medico medico3 = new Medico(3L, "Karen Flores", "Traumatología");
        List<Medico> medicos = List.of(medico1, medico2, medico3);

        when(medicoRepository.findAll()).thenReturn(medicos);

        List<MedicoDto> result = medicoService.getAllMedicos();

        assertNotNull(result);
        assertEquals(3, medicos.size());
        assertEquals("Juan Perez", medicos.get(0).getNombreCompleto());
    }

    @Test
    void findById() {
        Medico medico1 = new Medico(1L, "Juan Perez", "Cardiología");

        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medico1));

        Medico result = medicoService.findById(1L);

        assertNotNull(result);
        assertEquals("Juan Perez", result.getNombreCompleto());
        assertEquals(1L, result.getId());
    }
}