package iwm2302.views;

import java.util.Scanner;

public class GUISistema {
    private Scanner sc;

    public GUISistema() {
        this.sc = new Scanner(System.in);
    }

    public String iniciar() {
        System.out.println("Â¿Desea hacer login con su usuario o crear una cuenta nueva?\t" +
                "Para registrarse introduzca: nuevo usuario\t " +
                "Para hacer login introduzca: login");
        return sc.nextLine();
    }

    public void menuOpciones() {
        System.out.println("1.Subscribirse a un aula\t " +
                "2. cancelar subscripcion a un aula\t" +
                "3. Ver información del perfil");
        System.out.println("seleccione la opcion que desee:");
    }
    public String pedirIdentificacion() {
        renderMessage("Introduzca su DNI");
        return sc.nextLine();
    }
    public String pedirCorreo() {
        renderMessage("Introduzca su correo de la UPM");
        return sc.nextLine();
    }
    public String pedirContrasenia() {
        renderMessage("Introduzca su contasenia");
        return sc.nextLine();
    }
    public int altaBaja() {
        System.out.println("1.Alta de un aula\t " +
                "2. Baja de un aula\t" +
                "3. Ver información del perfil");
        System.out.println("seleccione la opcion que desee:");
        return sc.nextInt();
    }
    public String salir(){
        renderMessage("¿Desea salir? si : no");
        return sc.nextLine();
    }
    public void renderMessage(String mensaje) {
        System.out.println(mensaje);
    }


}
