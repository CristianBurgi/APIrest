package med.voll.api.domain.consulta;

import aj.org.objectweb.asm.commons.Remapper;
import med.voll.api.domain.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta,Long> {


    Boolean existsByPacienteIdAndDataBetween(Long aLong, LocalDateTime primerHorario, LocalDateTime ultimoHorario);

    Boolean existsByMedicoIdAndData(Long aLong, LocalDateTime fecha);

    Page<Consulta> findByActivoTrue(Pageable paginacion);
}
