import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PacienteTest {
    Paciente p;

    @BeforeEach
    void setUp() {
        p= new Paciente("Billy","Martinez","203317638","27/02/2010");
    }

    @AfterEach
    void tearDown() {
        p = null;
    }

    @Test
    void getFormato() {
        System.out.println(p.getFormato());
        System.out.println(p.getFechaDeCreacionUsuario().format(p.getFormato()));
    }

    @org.junit.jupiter.api.Test
    void getFechaConsulta() {
        System.out.println(p.getFechaCreacionStr());
    }

    @org.junit.jupiter.api.Test
    void getFechaDeCreacionUsuario() {
    }
}