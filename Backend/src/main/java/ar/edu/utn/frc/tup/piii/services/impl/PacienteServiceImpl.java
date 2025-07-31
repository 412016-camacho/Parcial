package ar.edu.utn.frc.tup.piii.services.impl;

import ar.edu.utn.frc.tup.piii.dtos.paciente.PacienteDto;
import ar.edu.utn.frc.tup.piii.entities.Paciente;
import ar.edu.utn.frc.tup.piii.repositories.PacienteRepository;
import ar.edu.utn.frc.tup.piii.services.PacienteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<PacienteDto> getAllPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return modelMapper.map(pacientes, new TypeToken<List<PacienteDto>>() {}.getType());
    }

    @Override
    public Paciente findById(Long pacienteId) {
        return pacienteRepository.findById(pacienteId).orElse(null);
    }
}
