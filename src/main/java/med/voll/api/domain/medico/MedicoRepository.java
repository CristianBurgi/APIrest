package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository  extends JpaRepository<Medico,Long> {

    Page<Medico> findByActivoTrue(Pageable paginacion);


    //Primer cambio en Intellij
    @Query("""
            select * from Medico m
            Where m.activo = 1 and
            m.especialidad = :especialidad and
            m.id not in (
            select c.medico.id from Consulta c
            c.data=:fecha
            )
            order by rand()
            limit 1
            """)
    Medico seleccionarMedicoConEspecialidadEnFecha(Especialidad especalidad, LocalDateTime fecha);
}
