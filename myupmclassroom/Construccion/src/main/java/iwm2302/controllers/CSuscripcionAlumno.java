package iwm2302.controllers;

import iwm2302.models.Alumno;
import iwm2302.models.Aula;
import iwm2302.persistance.DataAccesObject;
import iwm2302.views.GUIAlumno;
import org.hibernate.Session;

import java.util.HashMap;

public class CSuscripcionAlumno implements ICAlumno{
    private Session session;
    private GUIAlumno gui;

    public CSuscripcionAlumno(DataAccesObject dao) {
        session = dao.getSession();
        gui = new GUIAlumno();
    }

    @Override
    public String requestNew(String idUsuario) {
        gui.renderNew();
        try {
            return create(gui.getParams(), idUsuario);
        } catch (Exception e) {
            e.printStackTrace();
            return gui.renderMessage("Error: Introduce los datos correctos");
        }
    }

    @Override
    public String requestDelete(String idUsuario) {
        gui.renderDelete();
        try {
            return remove(gui.getParams(), idUsuario);
        } catch (Exception e) {
            return gui.renderMessage("Error: Introduce los datos correctos");
        }
    }

    @Override
    public String requestShow(String idUsuario) {
        session.beginTransaction();

        Alumno alumno = session.createQuery("from Alumno where numMatricula = :id", Alumno.class)
                .setParameter("id", idUsuario)
                .getSingleResultOrNull();

        if (alumno != null) return gui.renderShow(alumno);
        else return gui.renderMessage("Error: Ha ocurrido un error");
    }

    @Override
    public String create(HashMap<String, String> params, String idUsuario) {
        String nombreCentro = params.get("nombreCentro");
        String idAula = params.get("idAula");

        session.beginTransaction();

        Aula aula = session.createQuery("from Aula where " +
                        "nombreCentro = :nombre " +
                        "and idInterno = :id",Aula.class)
                .setParameter("nombre", nombreCentro)
                .setParameter("id", idAula)
                .getSingleResultOrNull();

        if (aula == null) {
            session.getTransaction().commit();
            return gui.renderMessage("Error: Aula no encontrada");
        }

        Alumno alumno = session.createQuery("from Alumno where numMatricula = :id", Alumno.class)
                .setParameter("id",idUsuario)
                .getSingleResultOrNull();

        if (alumno != null) {
            alumno.getAulasSuscripciones().add(aula);
            aula.getAlumnosSubscriptores().add(alumno);
            session.merge(alumno);
            session.merge(aula);
        }

        session.getTransaction().commit();

        return gui.renderMessage("Te has suscrito al aula " + aula.getIdInterno());
    }

    @Override
    public String remove(HashMap<String,String> params, String idUsuario) {
        String centro = params.get("nombreCentro");
        String idAula = params.get("idAula");

        session.beginTransaction();

        Aula aula = session.createQuery("from Aula where " +
                        "nombreCentro = :centro " +
                        "and idInterno = :id", Aula.class)
                .setParameter("centro", centro)
                .setParameter("id", idAula)
                .getSingleResultOrNull();

        if (aula == null) {
            gui.renderMessage("Error: aula no encontrada");
            session.getTransaction().commit();
        }

        Alumno alumno = session.createQuery("from Alumno where numMatricula = :id", Alumno.class)
                .setParameter("id", idUsuario)
                .getSingleResultOrNull();

        if (alumno != null) {
            alumno.getAulasSuscripciones().remove(aula);
            aula.getAlumnosSubscriptores().remove(alumno);
            session.merge(alumno);
            session.merge(aula);
        }

        session.getTransaction().commit();

        return gui.renderMessage("Te has desuscrito del aula " + aula.getIdInterno());
    }
}
