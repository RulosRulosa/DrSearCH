import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final String rutaP="Fonasa.txt";
    private final String rutaE="Especialistas.txt";
    //Billy
    @Test
    void vRuts() {
        String rutP = "20331763-8";
        assertTrue(Main.validarRut(rutP));
    }

    @Test
    void vRuts2() {
        String rutP = "20.331.763-8";
        assertTrue(Main.validarRut(rutP));
    }

    @Test
    void vRuts3() {
        String rutP = "203317638";
        assertTrue(Main.validarRut(rutP));
    }

    @Test
    void pacienteLogExist() {
        Path archivo = Paths.get(rutaP);
        assertEquals("Fonasa.txt", archivo.getFileName().toString());
    }

    @Test
    void especialistaLogExist() {
        Path archivo = Paths.get(rutaE);
        assertEquals("Especialistas.txt", archivo.getFileName().toString());
    }

    @Test
    void crearArchivo() {
        Path archivo = Paths.get(rutaE);
        assertTrue(archivo.toFile().canWrite());
    }

    @Test
    void crearArchivo2() {
        Path archivo = Paths.get(rutaP);
        assertTrue(archivo.toFile().canWrite());
    }
