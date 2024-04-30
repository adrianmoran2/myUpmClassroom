package iwm2302.controllers;

import iwm2302.models.Aula;
import iwm2302.models.PDI;
import iwm2302.persistance.DataAccesObject;
import iwm2302.views.GUIPdi;
import org.hibernate.Session;

import java.util.HashMap;

public class CSuscripcionPdi implements ICPdi {
    private Session session;
    private GUIPdi gui;

    public CSuscripcionPdi(DataAccesObject dao) {
        session = dao.getSession();
        gui = new GUIPdi();
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

        PDI pdi = session.createQuery("from PDI where codigoTrabajador = :id", PDI.class)
                .setParameter("id", idUsuario)
                .getSingleResultOrNull();

        return gui.renderShow(pdi);
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

        PDI pdi = session.createQuery("from PDI where codigoTrabajador = :id", PDI.class)
                .setParameter("id", idUsuario)
                .getSingleResultOrNull();

        if (pdi != null) {
            pdi.getAulasSuscripciones().add(aula);
            aula.getPdiSubscriptores().add(pdi);
            session.merge(pdi);
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

        PDI pdi = session.createQuery("from PDI where codigoTrabajador = :id", PDI.class)
                .setParameter("id", idUsuario)
                .getSingleResultOrNull();

        if (pdi != null) {
            pdi.getAulasSuscripciones().remove(aula);
            aula.getPdiSubscriptores().remove(pdi);
            session.merge(pdi);
            session.merge(aula);
        }

        session.getTransaction().commit();

        return gui.renderMessage("Te has desuscrito del aula " + aula.getIdInterno());
    }
}
