package ar.edu.utn.frc.tup.piii.controllers;

import ar.edu.utn.frc.tup.piii.dtos.medico.MedicoDto;
import ar.edu.utn.frc.tup.piii.services.MedicoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(MedicoController.class)
@AutoConfigureMockMvc
class MedicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicoService medicoService;

    @Test
    void getAllMedicos() throws Exception {
        List<MedicoDto> medicos = new ArrayList<>();

        MedicoDto dto = new MedicoDto();
        dto.setId(1L);
        dto.setNombreCompleto("Test");
        dto.setEspecialidad("Cardiología");

        MedicoDto dto1 = new MedicoDto();
        dto1.setId(2L);
        dto1.setNombreCompleto("Test1");
        dto1.setEspecialidad("Cardiología");

        medicos.add(dto);
        medicos.add(dto1);

        when(medicoService.getAllMedicos()).thenReturn(medicos);

        mockMvc.perform(get("/api/v1/medicos")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[0].nombre_completo", is("Test")))
                .andExpect(jsonPath("$[1].nombre_completo", is("Test1")));

        verify(medicoService, times(1)).getAllMedicos();
    }
}