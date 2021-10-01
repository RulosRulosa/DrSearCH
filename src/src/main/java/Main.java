import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    //Danko
    //Crea un archivo, en este caso crearemos txt
    public static void crearArchivo(String ruta, String contenido) {
        Path archivo = Paths.get(ruta);
        try {
            Files.write(archivo, contenido.getBytes());
            System.out.println("El archivo fue creado exitosamente");
        } catch (IOException e) {
            System.out.println("El archivo no pudo ser creado");
        }
    }

    //Retorna un String con el contenido del archivo
    public static String leerArchivo(String ruta) {
        Path archivo = Paths.get(ruta);
        String contenido = "";
        try {
            contenido = new String(Files.readAllBytes(archivo));
        } catch (IOException e) {
            System.out.println("El archivo no pudo ser leido");
        }
        return contenido;
    }

    //Valida los digitos de la fecha ingresada
    public static boolean fechaValida(String ingresoFecha) {
        //Guardamos la cadena en otro arreglo para no llamar la funcion tantas veces
        int[] Cadena = CadenaFecha(ingresoFecha);
        if (Cadena[0]<1||Cadena[0]>31){
            return false;
        }else if (Cadena[1]<1||Cadena[1]>12){
            return false;
        }else if (Cadena[2]<2021){
            return false;
        }else {
            return true;
        }
    }
}
