package iwm2302.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "aula")
public class Aula implements IAula {

    @Id
    @Column(name = "id_interno")
    private String idInterno;

    @Column(name = "nombre_centro")
    private String nombreCentro;

    private float superficie;

    private int aforo;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_aula")
    private TAula tipoAula;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "subscripciones_alumnos",
            joinColumns = @JoinColumn(name = "id_aula"),
            inverseJoinColumns = @JoinColumn(name = "num_matricula"))
    private Set<Alumno> alumnosSubscriptores;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "subscripciones_pdi",
            joinColumns = @JoinColumn(name = "id_aula"),
            inverseJoinColumns = @JoinColumn(name = "codigo_trabajador")
    )
    private Set<PDI> pdiSubscriptores;

    @ManyToOne
    @JoinColumn(name = "id_pas", nullable = false)
    private PAS pas;

    public Aula() {
        alumnosSubscriptores = new HashSet<>();
        pdiSubscriptores = new HashSet<>();
    }

    public Aula(String idInterno, String nombreCentro, float superficie, int aforo, TAula tipoAula) {
        this.idInterno = idInterno;
        this.nombreCentro = nombreCentro;
        this.superficie = superficie;
        this.aforo = aforo;
        this.tipoAula = tipoAula;
    }

    public String getIdInterno() {
        return idInterno;
    }

    public void setIdInterno(String idInt) {
        idInterno = idInt;
    }

    public String getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    public float getSuperficie() {
        return superficie;
    }

    public void setSuperficie(float superficie) {
        this.superficie = superficie;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public TAula getTipoAula() {
        return tipoAula;
    }

    public void setTipoAula(TAula tipo) {
        tipoAula = tipo;
    }

    public Set<Alumno> getAlumnosSubscriptores() {
        return alumnosSubscriptores;
    }

    public void setAlumnosSubscriptores(Set<Alumno> alumnosSubscriptores) {
        this.alumnosSubscriptores = alumnosSubscriptores;
    }

    public Set<PDI> getPdiSubscriptores() {
        return pdiSubscriptores;
    }

    public void setPdiSubscriptores(Set<PDI> pdiSubscriptores) {
        this.pdiSubscriptores = pdiSubscriptores;
    }

    public PAS getPas() {
        return pas;
    }

    public void setPas(PAS pas) {
        this.pas = pas;
    }
}
