package iwm2302.controllers;

import servidor.UPMUsers;

import java.util.HashMap;

public interface ICUsuario {
	String requestNew();

	String requestDelete(String DNI);

	String requestShow(String DNI);

	String create(HashMap<String,String> params, UPMUsers rol);

	String remove(String DNI);
}
