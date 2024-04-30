package iwm2302.controllers;

import iwm2302.models.PAS;
import iwm2302.models.TAula;
import iwm2302.models.Aula;
import iwm2302.persistance.DataAccesObject;
import iwm2302.views.GUIAula;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.Scanner;

public class CAula implements ICAula {
    private Session session;
    private GUIAula gui;

    public CAula(DataAccesObject dao) {
        session = dao.getSession();
        gui = new GUIAula();
    }

    @Override
    public String requestNew() {
        gui.renderNew();
        try {
            return create(gui.getParams());
        } catch (Exception e) {
            return gui.renderMessage("Error: Introduce los datos adecuados");
        }
    }

    @Override
    public String requestDelete() {
        gui.renderDelete();
        try {
            String id = gui.getParams().get("idAula");
            return remove(id);
        } catch (Exception e) {
            return gui.renderMessage("Error: Introduce un id correcto");
        }
    }

    @Override
    public String requestShow(String idAula) {
        try {
            Aula aula = session.createQuery("from Aula where idInterno = :id", Aula.class)
                    .setParameter("id", idAula)
                    .getSingleResultOrNull();
            return gui.renderShow(aula);
        } catch (Exception e) {
            return gui.renderMessage("Error: Introduce un id correcto");
        }
    }

    @Override
    public String create(HashMap<String, String> params) {
        String nombre = params.get("nombreCentro");
        String idAula = params.get("idAula");
        float superficie = Float.parseFloat(params.get("superficie"));
        int aforo = Integer.parseInt(params.get("aforo"));
        TAula tipo = Enum.valueOf(TAula.class, params.get("tipo"));
        String codigo = params.get("codigo");

        session.beginTransaction();
        Aula aula = new Aula(idAula, nombre, superficie, aforo, tipo);

        PAS pas = session.createQuery("from PAS where codigoPersonal = :id", PAS.class)
                .setParameter("id", codigo)
                .getSingleResultOrNull();

        if (pas != null) aula.setPas(pas);
        else return gui.renderMessage("Error: Introduce un codigo de trabajador correcto");
        session.persist(aula);
        session.getTransaction().commit();

        return gui.renderMessage("Aula creada con exito");
    }

    @Override
    public String remove(String idAula) {
        session.beginTransaction();

        Aula aula = session.createQuery("from Aula where idInterno = :id", Aula.class)
                .setParameter("id", idAula)
                .getSingleResultOrNull();

        session.remove(aula);
        session.getTransaction().commit();

        return gui.renderMessage("Se ha eliminado el aula con id = " + idAula);
    }
}
