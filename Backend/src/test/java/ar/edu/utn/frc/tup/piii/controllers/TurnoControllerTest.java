package ar.edu.utn.frc.tup.piii.controllers;

import ar.edu.utn.frc.tup.piii.dtos.turno.TurnoDto;
import ar.edu.utn.frc.tup.piii.dtos.turno.TurnoRequestDto;
import ar.edu.utn.frc.tup.piii.entities.Medico;
import ar.edu.utn.frc.tup.piii.entities.Paciente;
import ar.edu.utn.frc.tup.piii.entities.Turno;
import ar.edu.utn.frc.tup.piii.services.TurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TurnoController.class)
@AutoConfigureMockMvc
class TurnoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TurnoService turnoService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getAllTurnos() throws Exception {
        Paciente paciente = new Paciente(1L, "Paciente", "2020-04-04");
        Medico medico = new Medico(1L, "Medico", "Pediatra");
        List<TurnoDto> turnoDtos = List.of(
                new TurnoDto(1L, paciente, medico,LocalDate.of(2020,4,4)),
                new TurnoDto(2L, paciente, medico, LocalDate.now())
        );

        when(turnoService.getAllTurnos()).thenReturn(turnoDtos);

        mockMvc.perform(get("/api/v1/turnos")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[0].paciente.nombreCompleto", is("Paciente")))
                .andExpect(jsonPath("$[1].medico.nombreCompleto", is("Medico")));

        verify(turnoService, times(1)).getAllTurnos();

    }

    @Test
    void getAllTurnosFiltrados() throws Exception {
        Paciente paciente = new Paciente(1L, "Paciente", "2020-04-04");
        Medico medico = new Medico(1L, "Medico", "Pediatra");
        List<TurnoDto> turnoDtos = List.of(
                new TurnoDto(1L, paciente, medico,LocalDate.of(2020,4,4)),
                new TurnoDto(2L, paciente, medico, LocalDate.now())
        );

        when(turnoService.getAllTurnosFiltrados(
                Optional.ofNullable(medico.getId()),
                Optional.ofNullable(paciente.getId()),
                Optional.ofNullable(turnoDtos.get(0).getFecha())
        )).thenReturn(turnoDtos);

        mockMvc.perform(get("/api/v1/turnos")
                        .param("medicoId", medico.getId().toString())
                        .param("pacienteId", paciente.getId().toString())
                        .param("fecha", "2020-04-04")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[0].paciente.nombreCompleto", is("Paciente")))
                .andExpect(jsonPath("$[1].medico.nombreCompleto", is("Medico")));

        verify(turnoService, times(1)).getAllTurnosFiltrados(Optional.ofNullable(medico.getId()), Optional.ofNullable(paciente.getId()), Optional.ofNullable(turnoDtos.get(0).getFecha()));

    }

    @Test
    void getTurnosByPacienteAndFecha() throws Exception {
        Paciente paciente = new Paciente(1L, "Paciente", "2020-04-04");
        Medico medico = new Medico(1L, "Medico", "Pediatra");
        List<TurnoDto> turnoDtos = List.of(
                new TurnoDto(1L, paciente, medico,LocalDate.of(2020,4,4)),
                new TurnoDto(2L, paciente, medico, LocalDate.now())
        );

        when(turnoService.getTurnosByPacienteAndFecha(
                Optional.ofNullable(paciente.getId()),
                Optional.ofNullable(turnoDtos.get(0).getFecha())
        )).thenReturn(turnoDtos);

        mockMvc.perform(get("/api/v1/turnos")
                        .param("pacienteId", paciente.getId().toString())
                        .param("fecha", "2020-04-04")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[0].paciente.nombreCompleto", is("Paciente")))
                .andExpect(jsonPath("$[1].medico.nombreCompleto", is("Medico")));

        verify(turnoService, times(1)).getTurnosByPacienteAndFecha(Optional.ofNullable(paciente.getId()), Optional.ofNullable(turnoDtos.get(0).getFecha()));

    }

    @Test
    void getTurnosByPaciente() throws Exception {
        Paciente paciente = new Paciente(1L, "Paciente", "2020-04-04");
        Medico medico = new Medico(1L, "Medico", "Pediatra");
        List<TurnoDto> turnoDtos = List.of(
                new TurnoDto(1L, paciente, medico,LocalDate.of(2020,4,4)),
                new TurnoDto(2L, paciente, medico, LocalDate.now())
        );

        when(turnoService.getTurnosByPaciente(
                Optional.ofNullable(paciente.getId())
        )).thenReturn(turnoDtos);

        mockMvc.perform(get("/api/v1/turnos")
                        .param("pacienteId", paciente.getId().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[0].paciente.nombreCompleto", is("Paciente")))
                .andExpect(jsonPath("$[1].medico.nombreCompleto", is("Medico")));

        verify(turnoService, times(1)).getTurnosByPaciente(Optional.ofNullable(paciente.getId()));

    }

    @Test
    void getTurnosByFecha() throws Exception {
        Paciente paciente = new Paciente(1L, "Paciente", "2020-04-04");
        Medico medico = new Medico(1L, "Medico", "Pediatra");
        List<TurnoDto> turnoDtos = List.of(
                new TurnoDto(1L, paciente, medico,LocalDate.of(2020,4,4)),
                new TurnoDto(2L, paciente, medico, LocalDate.now())
        );

        when(turnoService.getTurnosByFecha(
                Optional.ofNullable(turnoDtos.get(0).getFecha())
        )).thenReturn(turnoDtos);

        mockMvc.perform(get("/api/v1/turnos")
                        .param("fecha", "2020-04-04")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[0].paciente.nombreCompleto", is("Paciente")))
                .andExpect(jsonPath("$[1].medico.nombreCompleto", is("Medico")));

        verify(turnoService, times(1)).getTurnosByFecha(Optional.ofNullable(turnoDtos.get(0).getFecha()));

    }

    @Test
    void getAllTurnosByMedicoAndPaciente() throws Exception {
        Paciente paciente = new Paciente(1L, "Paciente", "2020-04-04");
        Medico medico = new Medico(1L, "Medico", "Pediatra");
        List<TurnoDto> turnoDtos = List.of(
                new TurnoDto(1L, paciente, medico,LocalDate.of(2020,4,4)),
                new TurnoDto(2L, paciente, medico, LocalDate.now())
        );

        when(turnoService.getTurnosByMedicoAndPaciente(
                Optional.ofNullable(medico.getId()),
                Optional.ofNullable(paciente.getId())
        )).thenReturn(turnoDtos);

        mockMvc.perform(get("/api/v1/turnos")
                        .param("medicoId", medico.getId().toString())
                        .param("pacienteId", paciente.getId().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[0].paciente.nombreCompleto", is("Paciente")))
                .andExpect(jsonPath("$[1].medico.nombreCompleto", is("Medico")));

        verify(turnoService, times(1)).getTurnosByMedicoAndPaciente(
                Optional.ofNullable(medico.getId()),
                Optional.ofNullable(paciente.getId())
        );
    }


    @Test
    void getAllTurnosByMedicoAndFecha() throws Exception {
        Paciente paciente = new Paciente(1L, "Paciente", "2020-04-04");
        Medico medico = new Medico(1L, "Medico", "Pediatra");
        List<TurnoDto> turnoDtos = List.of(
                new TurnoDto(1L, paciente, medico,LocalDate.of(2020,4,4)),
                new TurnoDto(2L, paciente, medico, LocalDate.now())
        );

        when(turnoService.getTurnosByMedicoAndFecha(
                Optional.ofNullable(medico.getId()),
                Optional.ofNullable(turnoDtos.get(0).getFecha())
        )).thenReturn(turnoDtos);

        mockMvc.perform(get("/api/v1/turnos")
                        .param("medicoId", medico.getId().toString())
                        .param("fecha", "2020-04-04")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[0].paciente.nombreCompleto", is("Paciente")))
                .andExpect(jsonPath("$[1].medico.nombreCompleto", is("Medico")));

        verify(turnoService, times(1)).getTurnosByMedicoAndFecha(
                Optional.ofNullable(medico.getId()),
                Optional.ofNullable(turnoDtos.get(0).getFecha())
        );
    }


    @Test
    void getAllTurnosByMedico() throws Exception {
        Paciente paciente = new Paciente(1L, "Paciente", "2020-04-04");
        Medico medico = new Medico(1L, "Medico", "Pediatra");
        List<TurnoDto> turnoDtos = List.of(
                new TurnoDto(1L, paciente, medico,LocalDate.of(2020,4,4)),
                new TurnoDto(2L, paciente, medico, LocalDate.now())
        );

        when(turnoService.getTurnosByMedico(
                Optional.ofNullable(medico.getId())
        )).thenReturn(turnoDtos);

        mockMvc.perform(get("/api/v1/turnos")
                        .param("medicoId", medico.getId().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[0].paciente.nombreCompleto", is("Paciente")))
                .andExpect(jsonPath("$[1].medico.nombreCompleto", is("Medico")));

        verify(turnoService, times(1)).getTurnosByMedico(
                Optional.ofNullable(medico.getId())
        );
    }

    @Test
    void createTurno() throws Exception {
        Medico medico = new Medico(1L,"Test 1","Cardiología");
        Paciente paciente = new Paciente(1L, "Test 2", "2020-01-02");
        Turno turno = new Turno(1L, paciente, medico, LocalDate.now(), "");

        TurnoRequestDto turnoDto = new TurnoRequestDto(1L, 1L, turno.getFechaTurno());

        when(turnoService.createTurno(any())).thenReturn(turnoDto);

        mockMvc.perform(post("/api/v1/turnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(turnoDto)))
                .andExpect(status().isOk());
    }

    @Test
    void createTurno_isNull() throws Exception {
        TurnoRequestDto turnoRequest = TurnoRequestDto.builder()
                .medicoId(1L)
                .pacienteId(1L)
                .fecha(LocalDate.now())
                .build();

        when(turnoService.createTurno(any(TurnoRequestDto.class))).thenReturn(null);

        mockMvc.perform(post("/api/v1/turnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(turnoRequest)))
                .andExpect(status().isBadRequest());

        verify(turnoService, times(1)).createTurno(argThat(dto ->
                dto.getMedicoId().equals(1L) &&
                dto.getPacienteId().equals(1L)
        ));
    }
}