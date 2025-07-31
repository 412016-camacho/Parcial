package ar.edu.utn.frc.tup.piii.controllers;


import ar.edu.utn.frc.tup.piii.dtos.medico.MedicoDto;
import ar.edu.utn.frc.tup.piii.dtos.turno.TurnoDto;
import ar.edu.utn.frc.tup.piii.dtos.turno.TurnoRequestDto;
import ar.edu.utn.frc.tup.piii.services.TurnoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/turnos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TurnoController {

    private final TurnoService turnoService;

    @GetMapping
    public ResponseEntity<List<TurnoDto>> getAllTurnos(@RequestParam Optional<Long> medicoId,
                                                       @RequestParam Optional<Long> pacienteId,
                                                       @RequestParam Optional<LocalDate> fecha){
        if(medicoId.isPresent() && pacienteId.isPresent() && fecha.isPresent()){
            return ResponseEntity.ok(turnoService.getAllTurnosFiltrados(medicoId, pacienteId, fecha));
        };
        if(medicoId.isPresent()){
            if(pacienteId.isPresent()){
                return ResponseEntity.ok(turnoService.getTurnosByMedicoAndPaciente(medicoId, pacienteId));
            }
            if(fecha.isPresent()){
                return ResponseEntity.ok(turnoService.getTurnosByMedicoAndFecha(medicoId, fecha));
            }
            return ResponseEntity.ok(turnoService.getTurnosByMedico(medicoId));
        }
        if(pacienteId.isPresent()){
            if(fecha.isPresent()){
                return ResponseEntity.ok(turnoService.getTurnosByPacienteAndFecha(pacienteId, fecha));
            }
            return ResponseEntity.ok(turnoService.getTurnosByPaciente(pacienteId));
        }
        if(fecha.isPresent()){
            return ResponseEntity.ok(turnoService.getTurnosByFecha(fecha));
        }
        return ResponseEntity.ok(turnoService.getAllTurnos());
    }

    @PostMapping
    public ResponseEntity<TurnoRequestDto> createTurno(@RequestBody @Valid TurnoRequestDto turno){
        TurnoRequestDto turnoSaved = turnoService.createTurno(turno);
        if(Objects.isNull(turnoSaved)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Datos inválidos");
        }
        return ResponseEntity.ok(turnoSaved);
    }
}
