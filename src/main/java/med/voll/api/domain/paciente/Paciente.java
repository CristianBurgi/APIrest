package med.voll.api.domain.paciente;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import med.voll.api.domain.direccion.Direccion;
import med.voll.api.domain.medico.Especialidad;

@Entity
@Table(name = "paciente")
@Getter
@Setter

@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    private String email;

    private String telefono;

    private String documento;

    @Embedded
    private Direccion direccion;

    private boolean activo;


    public Paciente(DatosRegistroPaciente datosRegistroPaciente){

        this.nombre = datosRegistroPaciente.nombre();
        this.email = datosRegistroPaciente.email();
        this.telefono = datosRegistroPaciente.telefono();
        this.documento = datosRegistroPaciente.documento();
        this.direccion = new Direccion(datosRegistroPaciente.direccion());
        this.activo = true;

    }

    public Paciente() {

    }


    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(final String telefono) {
        this.telefono = telefono;
    }

    public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(final String documento) {
        this.documento = documento;
    }

    public Direccion getDireccion() {
        return this.direccion;
    }

    public void setDireccion(final Direccion direccion) {
        this.direccion = direccion;
    }

    public boolean isActivo() {
        return this.activo;
    }

    public void setActivo(final boolean activo) {
        this.activo = activo;
    }

    //Método para actualizar Paciente
    public void actualizarDatos(DatosActualizarPaciente datosActualizarPaciente) {

        if(datosActualizarPaciente.nombre() != null) {
            this.nombre = datosActualizarPaciente.nombre();
        }
        if (datosActualizarPaciente.documento() != null) {
            this.documento = datosActualizarPaciente.documento();
        }
        if (datosActualizarPaciente.direccion() != null) {
            this.direccion = direccion.actualizarDatos(datosActualizarPaciente.direccion());
        }

    }
}
