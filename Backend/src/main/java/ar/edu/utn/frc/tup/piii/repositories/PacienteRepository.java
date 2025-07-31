package ar.edu.utn.frc.tup.piii.repositories;

import ar.edu.utn.frc.tup.piii.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
