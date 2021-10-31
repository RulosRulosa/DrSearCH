import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public interface Gestor {
    static boolean PacienteLogExist(){
        Path archivo = Paths.get(Init.rutaP);
        return archivo.toFile().canWrite();
    }
    static boolean EspecialistaLogExist(){
        Path archivo = Paths.get(Init.rutaE);
        return archivo.toFile().canWrite();
    }
    static void crearArchivo(String ruta, String contenido) {
        Path archivo = Paths.get(ruta);
        try {
            Files.write(archivo, contenido.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static String leerArchivo(String ruta) {
        Path archivo = Paths.get(ruta);
        String contenido = "";
        try {
            contenido = new String(Files.readAllBytes(archivo));
        } catch (IOException e) {
            System.out.println("El archivo no pudo ser leído");
        }
        return contenido;
    }
    static void nuevaLinea(String ruta, String contenido) {
        String oldContenido = leerArchivo(ruta);
        crearArchivo(ruta, oldContenido + "\n" + contenido);
    }
    static void eliminarArchivo(String ruta) {
        Path archivo = Paths.get(ruta);
        try {
            Files.deleteIfExists(archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void guardarEspecialistas(){
        eliminarArchivo(Init.rutaE);
        crearArchivo(Init.rutaE,"ID,Nombre,Especialista,Fonasa,Isapres");
        for (Especialista e: Especialista.especialistas){
            nuevaLinea(Init.rutaE,e.toString());
        }
    }
    static void guardarPacientes(){
        eliminarArchivo(Init.rutaP);
        crearArchivo(Init.rutaP,"Nombre,Apellido,Rut,FechaRealizada");
        for (Paciente p: Paciente.pacientes) {
            nuevaLinea(Init.rutaP,p.toString());
        }
    }
    static void guardarHoras(){
        eliminarArchivo(Init.rutaA);
        crearArchivo(Init.rutaA,"Fecha,Nombre,Especialista,Hora");
        for (Agenda a: Especialista.horasAgendadas){
            nuevaLinea(Init.rutaA,a.toString());
        }
    }
}
