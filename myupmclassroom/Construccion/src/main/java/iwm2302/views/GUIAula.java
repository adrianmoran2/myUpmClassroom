package iwm2302.views;

import iwm2302.models.Aula;

import java.util.HashMap;
import java.util.Scanner;

public class GUIAula {
    private Scanner sc;
    private HashMap<String, String> params;

    public GUIAula() {
        sc = new Scanner(System.in);
        params = new HashMap<>();
    }

    public void renderNew() {
        System.out.println("Introduzca cada dato en una nueva linea");
        System.out.printf("Introduce el nombre del centro, "+
                "el id del aula, "+
                "su superficie, " +
                "su aforo, " +
                "el tipo de aula, " +
                "y tu codigo de personal\n");
        params.clear();
        params.put("nombreCentro", sc.nextLine());
        params.put("idAula", sc.nextLine());
        params.put("superficie", sc.nextLine());
        params.put("aforo", sc.nextLine());
        params.put("tipo", sc.nextLine());
        params.put("codigo", sc.nextLine());
    }

    public void renderDelete() {
        System.out.println("Introduce el id del aula");
        params.clear();
        params.put("idAula", sc.nextLine());
    }

    public String renderShow(Aula aula) {
        return "\nAula = {" +
                "\n\tcentro = " + aula.getNombreCentro() +
                "\n\tid = " + aula.getIdInterno() +
                "\n\tsuperficie = " + aula.getSuperficie() +
                "\n\taforo = " + aula.getAforo() +
                "\n\ttipo aula = " + aula.getTipoAula() +
                "\n}\n";
    }

    public String renderMessage(String mensaje) {
        return mensaje;
    }

    public HashMap<String,String> getParams() {
        return params;
    }
}
