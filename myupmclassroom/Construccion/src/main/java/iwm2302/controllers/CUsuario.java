package iwm2302.controllers;

import iwm2302.models.*;
import iwm2302.persistance.DataAccesObject;
import iwm2302.views.GUIUsuario;
import org.hibernate.Session;
import org.hibernate.query.Query;
import servidor.ObtencionDeRol;
import servidor.UPMUsers;

import java.util.HashMap;

public class CUsuario implements ICUsuario {
	private Session session;
	private GUIUsuario gui;

	public CUsuario(DataAccesObject dao) {
		session = dao.getSession();
		gui = new GUIUsuario();
	}

	@Override
	public String requestNew() {
		gui.renderNew();
		String correo = gui.getParams().get("correo");
		UPMUsers rol = ObtencionDeRol.get_UPM_AccountRol(correo);

		try {
			if (rol == UPMUsers.ALUMNO) {
				gui.renderNewAlumno();
				return create(gui.getParams(), rol);
			}

			if (rol == UPMUsers.PDI) {
				gui.renderNewPDI();
				return create(gui.getParams(), rol);
			}

			if (rol == UPMUsers.PAS) {
				gui.renderNewPAS();
				return create(gui.getParams(), rol);
			}
		} catch (Exception e) {
			return gui.renderMessage("Error: Introduce los datos correctos");
		}
		return gui.renderMessage("Error: No formas parte de la UPM");
	}

	@Override
	public String requestDelete(String DNI) {
		gui.renderDelete();
		try {
			return remove(DNI);
		} catch (Exception e) {
			return gui.renderMessage("Error: Introduce un DNI valido");
		}
	}

	@Override
	public String requestShow(String DNI) {
		session.beginTransaction();
		Alumno alumno = session.createQuery("from Alumno where DNI = :dni", Alumno.class)
				.setParameter("dni",DNI)
				.getSingleResultOrNull();

		PDI pdi = session.createQuery("from PDI where DNI = :dni", PDI.class)
				.setParameter("dni", DNI)
				.getSingleResultOrNull();

		PAS pas = session.createQuery("from PAS where DNI = :dni", PAS.class)
				.setParameter("dni", DNI)
				.getSingleResultOrNull();

		session.getTransaction().commit();

		if (alumno != null) {
			return gui.renderShowAlumno(alumno);
		}

		if (pdi != null) {
			return gui.renderShowPDI(pdi);
		}

		if (pas != null) {
			return gui.renderShowPAS(pas);
		}
		return gui.renderMessage("Error: Ha ocurrido un error");
	}

	@Override
	public String create(HashMap<String,String> params, UPMUsers rol) {
		String DNI = params.get("DNI");
		String nombre = params.get("nombre");
		String primerApellido = params.get("primerApellido");
		String segundoApellido = params.get("segundoApellido");
		String correo = params.get("correo");
		String contrasenia = params.get("contrasenia");

		session.beginTransaction();

		if (rol == UPMUsers.ALUMNO) {
			String numMat = params.get("matricula");
			Alumno alumno = new Alumno(DNI,nombre,primerApellido,segundoApellido,correo,contrasenia,numMat);
			session.persist(alumno);
		}

		if (rol == UPMUsers.PDI) {
			String codigoTrabajador = params.get("codigoTrabajador");
			TProfesor categoria = TProfesor.valueOf(params.get("categoria"));
			PDI pdi = new PDI(DNI,nombre,primerApellido,segundoApellido,correo,contrasenia,codigoTrabajador,categoria);
			session.persist(pdi);
		}

		if (rol == UPMUsers.PAS) {
			String codigoPersonal = params.get("codigoPersonal");
			int anio = Integer.parseInt(params.get("añoIncorporacion"));
			PAS pas = new PAS(DNI,nombre,primerApellido,segundoApellido,correo,contrasenia,codigoPersonal,anio);
			session.persist(pas);
		}
		session.getTransaction().commit();
		return gui.renderMessage("Te has registrado en MyUPMClassroom");
	}

	@Override
	public String remove(String DNI) {
		session.beginTransaction();

		Alumno alumno = session.createQuery("from Alumno where DNI = :dni", Alumno.class)
				.setParameter("dni", DNI)
				.getSingleResultOrNull();

		PDI pdi = session.createQuery("from PDI where DNI = :dni", PDI.class)
				.setParameter("dni", DNI)
				.getSingleResultOrNull();

		PAS pas = session.createQuery("from PAS where DNI = :dni", PAS.class)
				.setParameter("dni", DNI)
				.getSingleResultOrNull();

		if (alumno != null) session.remove(alumno);
		if (pdi != null) session.remove(pdi);
		if (pas != null) session.remove(pas);

		session.getTransaction().commit();

		return gui.renderMessage("Tu cuenta ha sido eliminada ");
	}

}
