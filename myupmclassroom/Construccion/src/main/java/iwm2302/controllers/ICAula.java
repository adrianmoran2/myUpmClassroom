package iwm2302.controllers;

import java.util.HashMap;

public interface ICAula {
	String requestNew();

	String requestDelete();

	String requestShow(String idAula);

	String create(HashMap<String,String> params);

	String remove(String idAula);
}
