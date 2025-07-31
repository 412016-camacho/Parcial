package ar.edu.utn.frc.tup.piii.controllers;


import ar.edu.utn.frc.tup.piii.dtos.medico.MedicoDto;
import ar.edu.utn.frc.tup.piii.dtos.paciente.PacienteDto;
import ar.edu.utn.frc.tup.piii.services.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MedicoController {

    private final MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<MedicoDto>> getAllMedicos(){
        return ResponseEntity.ok(medicoService.getAllMedicos());
    }
}
