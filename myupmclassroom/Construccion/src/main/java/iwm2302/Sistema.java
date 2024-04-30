package iwm2302;

import iwm2302.controllers.*;
import iwm2302.models.*;
import iwm2302.views.GUISistema;
import servidor.*;
import iwm2302.persistance.DataAccesObject;
import org.hibernate.Session;
import servidor.ObtencionDeRol;
import servidor.UPMUsers;
import utilidades.Cifrado;

import java.util.Scanner;

public class Sistema implements ISistema {
	Session session;
	private Scanner sc;
	private GUISistema gui;

	public Sistema(DataAccesObject dao) {
		session = dao.getSession();
		sc = new Scanner(System.in);
		gui = new GUISistema();
	}

	public static void main(String[] args) {
		DataAccesObject dataAccesObject = new DataAccesObject();
		Sistema sistema = new Sistema(dataAccesObject);
		while(true) {
			sistema.iniciar();
		}
	}
	
	public void iniciar() {
		DataAccesObject dataAccesObject = new DataAccesObject();
		String respuesta;
		CUsuario usuario = new CUsuario(dataAccesObject);
		do{
			respuesta = gui.iniciar();
			if (respuesta.equals("nuevo usuario")){
				usuario.requestNew();
				respuesta = "login";
			}
		}while(!respuesta.equals("login"));
		Autenticacion autenticacion = new Autenticacion();//login
		Usuario u;
		int errorContrasenia = 0;
		String correo;
		do{
			do{
				gui.renderMessage(autenticacion.getNombre());
				gui.renderMessage(autenticacion.getDescrip());
				correo = gui.pedirCorreo();
				if(autenticacion.existeCuentaUPM(correo)) gui.renderMessage("Cuenta Upm correcta");
				else gui.renderMessage("No existe la cuenta introducida");
			}while(!autenticacion.existeCuentaUPM(correo));
			String password = Cifrado.cifrar(gui.pedirContrasenia());
			u = session.createQuery("from Usuario where contrasenia = :contrasenia", Usuario.class)
					.setParameter("contrasenia", password)
					.getSingleResultOrNull();
			if(u == null){
				errorContrasenia++;
				gui.renderMessage("Contraseña incorrecta, le quedan " + (3-errorContrasenia)+ " intentos");
			}
		}while(errorContrasenia < 3 && u == null);
		if(u == null){//Ha fallado 3 veces la contraseña
			gui.renderMessage("¿Ha olvidado su contrasenia?");
			String password = gui.pedirContrasenia();
			gui.renderMessage("Confirme su contrasenia:");
			String pass2 = gui.pedirContrasenia();
			Correo email = new Correo();
			String user = gui.pedirIdentificacion();
			if(pass2.equals(password)&& email.enviarEmail(correo,user,password)){
				u = session.createQuery("from Usuario where dni = :dni", Usuario.class)
						.setParameter("dni", user)
						.getSingleResultOrNull();
				if(u != null)
					u.setContrasenia(password);
			}
		}else {
			gui.renderMessage("Usted ha iniciado sesion correctamente");
			UPMUsers rol = ObtencionDeRol.get_UPM_AccountRol(correo);
			int opcion;
			if (rol.compareTo(UPMUsers.ALUMNO) == 0 || rol.compareTo(UPMUsers.PDI) == 0) {//Usuario subscriptor
				do {
					gui.menuOpciones();
					opcion = sc.nextInt();
					if (rol.compareTo(UPMUsers.ALUMNO) == 0) {
						CSuscripcionAlumno alumno = new CSuscripcionAlumno(dataAccesObject);
						if (opcion == 1)
							alumno.requestNew(u.getDNI());
						if (opcion == 2)
							alumno.requestDelete(u.getDNI());
						if (opcion == 3)
							alumno.requestShow(u.getDNI());
					} else {
						CSuscripcionPdi pdi = new CSuscripcionPdi(dataAccesObject);
						if (opcion == 1)
							pdi.requestNew(u.getDNI());
						if (opcion == 2)
							pdi.requestDelete(u.getDNI());
						if (opcion == 3)
							pdi.requestShow(u.getDNI());
					}
					respuesta = gui.salir();
				} while (!respuesta.equals("si"));
			} else if (rol.compareTo(UPMUsers.PAS) == 0) {
				CAula aula = new CAula(dataAccesObject);
				do {
					opcion = gui.altaBaja();
					if (opcion == 1)
						aula.requestNew();
					if (opcion == 2)
						aula.requestDelete();
					if (opcion == 3)
						usuario.requestShow(u.getDNI());
					respuesta = gui.salir();
				} while (!respuesta.equals("si"));
			}
		}
	}
}


