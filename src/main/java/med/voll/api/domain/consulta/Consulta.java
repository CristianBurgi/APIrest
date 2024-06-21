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

    public Consulta(Object o, Medico medico, Paciente paciente, LocalDateTime fecha) {
    }


//    public Consulta(final Long id, final Medico medico, final Paciente paciente, final LocalDateTime data) {
//        this.id = id;
//        this.medico = medico;
//        this.paciente = paciente;
//        this.data = data;
   // }
}
