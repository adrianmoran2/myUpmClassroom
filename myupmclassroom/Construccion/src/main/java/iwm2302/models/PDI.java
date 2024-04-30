package iwm2302.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "pdi")
public class PDI extends Usuario {

	@Id
	@Column(name = "codigo_trabajador")
	private String codigoTrabajador;

	@Enumerated(EnumType.STRING)
	private TProfesor categoria;

	@ManyToMany(mappedBy = "pdiSubscriptores", cascade = CascadeType.ALL)
	private Set<Aula> aulasSuscripciones;

	public PDI() {

	}
	
	public PDI (String DNI, String nombre,
			String primerApellido, String segundoApellido,
			String correo, String contrasenia,
			String codigoTrabajador, TProfesor categoria) {
		super(DNI, nombre, primerApellido, segundoApellido, correo, contrasenia);
		this.codigoTrabajador = codigoTrabajador;
		this.categoria = categoria;
	}
	
	public String getCodigoTrabajador() {
		return codigoTrabajador;
	}
	
	public void setCodigoTrabajador(String codigoT) {
		codigoTrabajador = codigoT;
	}
	
	public TProfesor getCategoria() {
		return categoria;
	}
	
	public void setCategoria(TProfesor categoria) {
		this.categoria = categoria;
	}

	public Set<Aula> getAulasSuscripciones() {
		return aulasSuscripciones;
	}
}
