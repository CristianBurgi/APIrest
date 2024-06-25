package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface MedicoRepository  extends JpaRepository<Medico,Long> {

    Page<Medico> findByActivoTrue(Pageable paginacion);


    //Primer cambio en Intellij
    @Query(value = """
            select * from Medico m
            Where m.activo = true 
            and
            m.especialidad = :especialidad 
            and
            m.id not in (
            select c.id_medico from Consulta c
            where c.data = :fecha
            )
            order by rand()
            limit 1
            """ , nativeQuery = true)
    Medico seleccionarMedicoConEspecialidadEnFecha(@Param("especialidad") Especialidad especialidad, @Param("fecha") LocalDateTime fecha);

    @Query("""
            select m.activo 
            from Medico m
            where m.id=:idMedico
            """)
    Boolean findActivoById(Long idMedico);


}
