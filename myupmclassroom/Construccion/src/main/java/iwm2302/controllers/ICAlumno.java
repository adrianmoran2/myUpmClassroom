package iwm2302.controllers;

import java.util.HashMap;

public interface ICAlumno {
    String requestNew(String idUsuario);

    String requestDelete(String idUsuario);

    String requestShow(String idUsuario);

    String create(HashMap<String, String> params, String idUsuario);

    String remove(HashMap<String,String> params, String idUsuario);
}
