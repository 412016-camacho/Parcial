package ar.edu.utn.frc.tup.piii.repositories;

import ar.edu.utn.frc.tup.piii.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findAllByMedicoIdAndPacienteIdAndFechaTurno(Optional<Long> medico_id, Optional<Long> paciente_id, Optional<LocalDate> fechaTurno);

    List<Turno> findAllByMedicoIdAndPacienteId(Optional<Long> medicoId, Optional<Long> pacienteId);

    List<Turno> findAllByMedicoIdAndFechaTurno(Optional<Long> medicoId, Optional<LocalDate> fecha);

    List<Turno> findAllByMedicoId(Optional<Long> medicoId);

    List<Turno> findAllByPacienteIdAndFechaTurno(Optional<Long> pacienteId, Optional<LocalDate> fecha);

    List<Turno> findAllByPacienteId(Optional<Long> pacienteId);

    List<Turno> findAllByFechaTurno(Optional<LocalDate> fecha);
}
