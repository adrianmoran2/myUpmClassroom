package iwm2302.views;

import iwm2302.models.Alumno;
import iwm2302.models.PAS;
import iwm2302.models.PDI;
import iwm2302.models.Usuario;
import utilidades.Cifrado;

import java.util.HashMap;
import java.util.Scanner;

public class GUIUsuario {
    private Scanner sc;
    private HashMap<String, String> params;

    public GUIUsuario() {
        sc = new Scanner(System.in);
        params = new HashMap<>();
    }

    public void renderNew() {
        System.out.println("Porfavor introduce cada dato en una linea nueva");
        System.out.println("Introduce tu DNI, " +
                "nombre, "+
                "primer y segundo apellido, " +
                "correo, " +
                "contraseña ");
        params.put("DNI", sc.nextLine());
        params.put("nombre", sc.nextLine());
        params.put("primerApellido", sc.nextLine());
        params.put("segundoApellido", sc.nextLine());
        params.put("correo", sc.nextLine());
        String contrasenia = sc.nextLine();
        params.put("contrasenia", Cifrado.cifrar(contrasenia));
    }

    public void renderNewAlumno() {
        System.out.println("\ny tu numero de matricula");
        params.put("matricula", sc.nextLine());
    }

    public void renderNewPDI() {
        System.out.println("\ntu codigo de trabajador" +
                " y tu categoria");
        params.put("codigoTrabajador", sc.nextLine());
        params.put("categoria", sc.nextLine());
    }

    public void renderNewPAS() {
        System.out.printf("\ntu codigo de personal " +
                " y tu año de incorporacion");
        params.put("codigoPersonal", sc.nextLine());
        params.put("añoIncorporacion", sc.nextLine());
    }

    public void renderDelete() {
        System.out.println("Te echaremos de menos en MyUPMClassroom");
    }

    public String renderShow(Usuario usuario) {
        return "{" +
                "\n\tDNI = " + usuario.getDNI() +
                "\n\tnombre = " + usuario.getNombre() +
                "\n\tapellidos = " + usuario.getPrimerApellido() + usuario.getSegundoApellido() +
                "\n\tcorreo = " + usuario.getCorreo();
    }

    public String renderShowAlumno(Alumno alumno) {
        String res = renderShow(alumno);
        return "\nAlumno = " +
                res +
                "\n\tnumMatricula = " + alumno.getNumMatricula() +
                "\n}\n";
    }

    public String renderShowPDI(PDI pdi) {
        String res = renderShow(pdi);
        return "\nPDI = " +
                res +
                "\n\tcodigo de trabajador = " + pdi.getCodigoTrabajador() +
                "\n\tcategoria = " + pdi.getCategoria() +
                "\n}\n";
    }

    public String renderShowPAS(PAS pas) {
        String res = renderShow(pas);
        return "\nPAS = " +
                res +
                "\n\tcodigo de personal = " + pas.getCodigoPersonal() +
                "\n\taño de incorporacion = " + pas.getAnioIncorporacion() +
                "\n}\n";
    }

    public String renderMessage(String mensaje) {
        return mensaje;
    }

    public HashMap<String,String> getParams() {
        return params;
    }

}
