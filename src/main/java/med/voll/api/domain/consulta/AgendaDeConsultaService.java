package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.validaciones.ValidadorDeConsultas;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    List<ValidadorDeConsultas> validadores;
    public DatosDetalleConsulta agendar(DatosAgendarConsulta datos){

        if (!pacienteRepository.findById(datos.idPaciente()).isPresent()){
            throw new ValidacionDeIntegridad("este Id de paciente no fue encontrado !");
        }

//        if (!medicoRepository.findById(datos.idMedico()).isPresent()){
//            throw new ValidacionDeIntegridad("este Id de Medico no fue encontrado");
//        }
        if (datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionDeIntegridad("este Id de Medico no fue encontrado");
        }
        System.out.println("Validando todo");

        validadores.forEach(v -> v.validar(datos));

        var paciente = pacienteRepository.findById(datos.idPaciente()).get();
        var medico = seleccionarMedico(datos);

        if (medico == null){
            throw new ValidacionDeIntegridad(" No existe medico disponible para este horario u especialidad");
        }



        var consulta = new Consulta(null, medico , paciente, datos.fecha(),true);
        consultaRepository.save(consulta);

        return new DatosDetalleConsulta(consulta);

    }

    private Medico seleccionarMedico(DatosAgendarConsulta datos) {

        if (datos.idMedico() != null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if (datos.especalidad() == null){
            throw new ValidacionDeIntegridad("Debe seleccionar una especialidad para el Médico");
        }


        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.especalidad(),datos.fecha());
    }
}
