package med.voll.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultaService;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.consulta.DatosDetalleConsulta;
import med.voll.api.domain.medico.DatosListadoMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Consultas", description = "Operaciones relacionadas con Consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService service;
    @Autowired
    private ConsultaRepository consultaRepository;

    @PostMapping
    @Transactional
    @Operation(summary = "Agendar Consulta", description = "Agenda una consulta")
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos) {


        var response = service.agendar(datos);


        return ResponseEntity.ok(response);
    }


    @GetMapping
    @Operation(summary = "Obtener lista de consultas", description = "Devuelve una lista de todos las consultas registrados")
    public ResponseEntity<Page<DatosDetalleConsulta>> listadoConsultas(@PageableDefault(size = 15) Pageable paginacion) {

        return ResponseEntity.ok(consultaRepository.findByActivoTrue(paginacion).map(DatosDetalleConsulta::new));
    }
}
