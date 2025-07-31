package ar.edu.utn.frc.tup.piii.repositories;

import ar.edu.utn.frc.tup.piii.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
