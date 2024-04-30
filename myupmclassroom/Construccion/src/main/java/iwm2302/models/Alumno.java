package iwm2302.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "alumno")
public class Alumno extends Usuario {
	@Id
	@Column(name = "num_matricula")
	private String numMatricula;

	@ManyToMany(mappedBy = "alumnosSubscriptores", cascade = CascadeType.ALL)
	private Set<Aula> aulasSuscripciones;

	public Alumno() {
		aulasSuscripciones = new HashSet<>();
	}
	
	public Alumno(String DNI, String nombre, String primerApellido, String segundoApellido,String correo, String contrasenia, String numMatricula) {
		super(DNI,nombre, primerApellido, segundoApellido, correo, contrasenia);
		this.numMatricula = numMatricula;
		aulasSuscripciones = new HashSet<>();
	}
	
	public String getNumMatricula() {
		return numMatricula;
	}
	
	public void setNumMatricula(String matricula) {
		numMatricula = matricula;
	}

	public Set<Aula> getAulasSuscripciones() {
		return aulasSuscripciones;
	}

	public void setAulasSuscripciones(Aula aula) {
		aulasSuscripciones.add(aula);
	}
}
