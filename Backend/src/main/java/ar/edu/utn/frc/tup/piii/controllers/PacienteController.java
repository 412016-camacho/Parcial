package ar.edu.utn.frc.tup.piii.controllers;


import ar.edu.utn.frc.tup.piii.dtos.paciente.PacienteDto;
import ar.edu.utn.frc.tup.piii.services.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<PacienteDto>> getAllPacientes(){
        return ResponseEntity.ok(pacienteService.getAllPacientes());
    }
}
