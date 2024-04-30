package iwm2302.views;

import iwm2302.models.Alumno;
import iwm2302.models.Aula;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class GUIAlumno {
    private Scanner sc;
    private HashMap<String,String> params;

    public GUIAlumno() {
        sc = new Scanner(System.in);
        params = new HashMap<>();
    }

    public void renderNew() {
        System.out.println("Introduce los datos en una linea distinta");
        System.out.printf("Introduce el nombre del centro" +
                " y el id del aula\n");
        params.put("nombreCentro", sc.nextLine());
        params.put("idAula", sc.nextLine());
    }

    public void renderDelete() {
        System.out.println("Introduce los datos en una linea distinta");
        System.out.printf("Introduce el nombre del centro" +
                " y el id del aula \n");
        params.put("nombreCentro", sc.nextLine());
        params.put("idAula", sc.nextLine());
    }

    public String renderShow(Alumno subscriptor) {
        Set<Aula> aulas = subscriptor.getAulasSuscripciones();
        String res = "\nSubscripciones = {";

        for (Aula aula: aulas) {
            res += "\n\tAula = {";
            res += "\n\t\t centro = " + aula.getNombreCentro();
            res += "\n\t\t id aula = " + aula.getIdInterno();
            res += "\n\t}\n";
        }

        res += "}\n";

        return res;
    }

    public String renderMessage(String mensaje) {
        return mensaje;
    }

    public HashMap<String,String> getParams() {
        return params;
    }
}
