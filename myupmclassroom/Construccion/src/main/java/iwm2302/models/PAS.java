package iwm2302.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pas")
public class PAS extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_personal")
    private String codigoPersonal;

    @Column(name = "anio_incorporacion")
    private int anioIncorporacion;

    @OneToMany(mappedBy = "pas")
    Set<Aula> aulas;

    public PAS() {
        aulas = new HashSet<>();
    }

    public PAS(String DNI, String nombre,
               String primerApellido, String segundoApellido,
               String correo, String contrasenia,
               String codigoPersonal, int anioIncorporacion) {
        super(DNI, nombre, primerApellido, segundoApellido, correo, contrasenia);
        this.codigoPersonal = codigoPersonal;
        this.anioIncorporacion = anioIncorporacion;
    }


    public String getCodigoPersonal() {
        return codigoPersonal;
    }

    public void setCodigoPersonal(String codigoP) {
        codigoPersonal = codigoP;
    }

    public int getAnioIncorporacion() {
        return anioIncorporacion;
    }

    public void setAnioIncorporacion(int anioInc) {
        anioIncorporacion = anioInc;
    }
}
