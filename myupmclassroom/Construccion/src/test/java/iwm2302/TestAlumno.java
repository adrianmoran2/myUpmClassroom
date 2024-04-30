package iwm2302;

import iwm2302.models.Alumno;
import iwm2302.models.Aula;
import iwm2302.models.TAula;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class TestAlumno {
    private Alumno alumno;

    @Before
    public void setUp() {
        alumno = new Alumno("12345678L", "Alumno",
                "Apellido 1", "Apellido 2",
                "alumno@alumnos.upm.es", "contraseña",
                "ba1234");
    }

    @Test
    public void testGetDni() {
        assertEquals("Error: No se obtiene el DNI",
                "12345678L",
                alumno.getDNI());
    }

    @Test
    public void testSetDni() {
        alumno.setDNI("12345678O");
        assertEquals("Error: No se actualiza el DNI",
                "12345678O",
                alumno.getDNI());
    }

    @Test
    public void testGetNombre() {
        assertEquals("Error: No se obtiene el nombre",
                "Alumno",
                alumno.getNombre());
    }

    @Test
    public void testSetNombre() {
        alumno.setNombre("Nombre actualizado");
        assertEquals("Error: No se actualiza el nombre",
                "Nombre actualizado",
                alumno.getNombre());
    }

    @Test
    public void testGetPrimerApellido() {
        assertEquals("Error: No se obtiene el primer apellido",
                "Apellido 1",
                alumno.getPrimerApellido());
    }

    @Test
    public void testSetPrimerApellido() {
        alumno.setPrimerApellido("Apellido actualizado");
        assertEquals("Error: No se actualiza el primer apellido",
                "Apellido actualizado",
                alumno.getPrimerApellido());
    }

    @Test
    public void testGetSegundoApellido() {
        assertEquals("Error: No se obtiene el segundo apellido",
                "Apellido 2",
                alumno.getSegundoApellido());
    }

    @Test
    public void testSetSegundoApellido() {
        alumno.setSegundoApellido("Apellido 2 actualizado");
        assertEquals("Error: No se actualiza el segundo apellido",
                "Apellido 2 actualizado",
                alumno.getSegundoApellido());
    }

    @Test
    public void testGetCorreo() {
        assertEquals("Error: No se obtiene el correo",
                "alumno@alumnos.upm.es",
                alumno.getCorreo());
    }

    @Test
    public void testSetCorreo() {
        alumno.setCorreo("correo@alumnos.upm.es");
        assertEquals("Error: No se actualiza el correo",
                "correo@alumnos.upm.es",
                alumno.getCorreo());
    }

    @Test
    public void testGetContrasenia() {
        assertEquals("Error: No se obtiene la contraseña",
                "contraseña",
                alumno.getContrasenia());
    }

    @Test
    public void testSetContrasenia() {
        alumno.setContrasenia("password");
        assertEquals("Error: No se actualiza la contraseña",
                "password",
                alumno.getContrasenia());
    }

    @Test
    public void testGetMatricula() {
        assertEquals("Error: No se obtiene la matricula esperada",
                "ba1234",
                alumno.getNumMatricula());
    }

    @Test
    public void testSetMatricula() {
        alumno.setNumMatricula("ba0000");
        assertEquals("Error: No se actualiza la matricula",
                "ba0000",
                alumno.getNumMatricula());
    }

    @Test
    public void testGetAulas() {
        Set<Aula> aulas = alumno.getAulasSuscripciones();
        assertEquals("Error: No se obtiene el set de aulas",
                aulas.isEmpty(),
                true);
    }

    @Test
    public void testSetAulas() {
        Aula aula = new Aula("Aula", "ETSISI UPM", 40f, 20, TAula.Mixta);
        alumno.setAulasSuscripciones(aula);
        Set<Aula> aulas = alumno.getAulasSuscripciones();
        assertEquals("Error: No se actualizan las aulas",
                true,
                aulas.contains(aula));
    }

}
