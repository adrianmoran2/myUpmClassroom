package iwm2302.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.UniqueConstraint;

@MappedSuperclass
public abstract class Usuario implements IUsuario {

	@Column(name = "dni", unique = true)
	private String DNI;

	private String nombre;

	@Column(name = "primer_apellido")
	private String primerApellido;

	@Column(name = "segundo_apellido")
	private String segundoApellido;

	@Column(unique = true)
	private String correo;

	private String contrasenia;

	public Usuario() {

	}
	
	public Usuario(String DNI, String nombre,
				   String primerApellido, String segundoApellido,
				   String correo, String contrasenia) {
		this.DNI = DNI;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.correo = correo;
		this.contrasenia = contrasenia;
	}
	
	public boolean autenticar() {
		return false;
	}
	
	public String getDNI() {
		return DNI;
	}
	
	public void setDNI(String dni) {
		DNI = dni;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPrimerApellido() {
		return primerApellido;
	}
	
	public void setPrimerApellido(String primerAp) {
		primerApellido = primerAp;
	}
	
	public String getSegundoApellido() {
		return segundoApellido;
	}
	
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getContrasenia() {
		return contrasenia;
	}
	
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	public void pedirCorreo() {
		System.out.println("Introduzca su correo");
	}
	
	public void pedirContrasenia() {
		System.out.println("Introduzca su contaseña");
	}
	
	public boolean comprobarCorreo() {
		return false;
	}
	
	public boolean comprobarContrasenia() {
		return false;
	}
	
	public void solicitarNuevaContrasenia() {
	
	}
	
	public void mensajeError() {
	
	}
	
	public void mensajeExito() {
	
	}
	
	public void destroy() {
		
	}
}
