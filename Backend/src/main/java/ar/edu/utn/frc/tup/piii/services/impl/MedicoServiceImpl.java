package ar.edu.utn.frc.tup.piii.services.impl;

import ar.edu.utn.frc.tup.piii.dtos.medico.MedicoDto;
import ar.edu.utn.frc.tup.piii.entities.Medico;
import ar.edu.utn.frc.tup.piii.repositories.MedicoRepository;
import ar.edu.utn.frc.tup.piii.services.MedicoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository medicoRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<MedicoDto> getAllMedicos() {
        List<Medico> medicos = medicoRepository.findAll();
        return modelMapper.map(medicos, new TypeToken<List<MedicoDto>>() {}.getType());
    }

    @Override
    public Medico findById(Long medicoId) {
        return medicoRepository.findById(medicoId).get();
    }
}
