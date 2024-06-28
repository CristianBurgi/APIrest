package med.voll.api.domain.consulta;


import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

@Entity(name = "consultas")
@Table(name = "consultas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime data;
    private Boolean activo;

    public Consulta(Object o, Medico medico, Paciente paciente, LocalDateTime fecha, Boolean activo) {
        this.activo= true;
    }


//    public Consulta(final Long id, final Medico medico, final Paciente paciente, final LocalDateTime data) {
//        this.id = id;
//        this.medico = medico;
//        this.paciente = paciente;
//        this.data = data;
   // }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Medico getMedico() {
        return this.medico;
    }

    public void setMedico(final Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return this.paciente;
    }

    public void setPaciente(final Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getData() {
        return this.data;
    }

    public void setData(final LocalDateTime data) {
        this.data = data;
    }
}
