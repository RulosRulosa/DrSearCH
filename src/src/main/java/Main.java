
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

   
public class Main {
    public static void main(String[] args) {
    Ingreso();
    }
    //Tania
    public static void Ingreso() {
        //Se guarda el rut que va a ser ingresado por el paciente
        String RutPaciente = IngresarRut();
        MostrarPrevisiones();
        AgendarFecha(IngresarPrevision(), RutPaciente);
    }

    //Se crea un archivo en la carpeta Pacientes, con el rut del paciente, datos del especialista y la fecha agendada
    public static void AgendarFecha(String ruta, String rut) {
        if (ruta.equals("Fonasa.txt")) {
            crearArchivo("Pacientes/" + rut + ".txt", EscogerEspecialista(ruta) + ";Fonasa");
        } else {
            crearArchivo("Pacientes/" + rut + ".txt", EscogerEspecialista(ruta) + ";Isapre");
        }
        System.out.println("Hora agendada con exito");
    }

    //Imprime por pantalla las opciones de previsiones
    public static void MostrarPrevisiones() {
        System.out.println("Ingresa tu prevision");
        System.out.println("[1]Fonasa");
        System.out.println("[2]Isapre");
    }

    //Ingresa la prevision, luego retorna la ruta de ésta
    public static String IngresarPrevision() {
        int Prevision = Validar(2);
        if (Prevision == 1) {  //Fonasa
            MostrarEspecialistasPrevision("Fonasa.txt");
            return "Fonasa.txt";
        } else {             //Isapre
            MostrarEspecialistasPrevision("Isapre.txt");
            return "Isapre.txt";
        }
    }

    //Billy
    //Validador de Rut
    public static boolean validarRut(String rut) {   // 12.345.678-k
        boolean valido= false;
        rut = rut.toUpperCase();            // 12.345.678-K
        rut = rut.replace("-", "");         // 12.345.678K
        rut = rut.replace(".", "");         // 12345678k
        int dv = rut.charAt(rut.length()-1);
        try{
            int numRut = Integer.parseInt(rut.substring(0,rut.length()-1));

            int m=0, s=1;
            for (;numRut !=0;numRut/=10) {
                s = (s+numRut%10 *(9-m++%6))%11;
            }

            if(dv==(char)(s!=0 ?s+47:75)){
                valido = true;
            }

        }catch(Exception e){
            System.out.println("rut invalido");
        }

        return valido;
    }

    //Valida numeros naturales en un cierto rango, se usa para entrada de opciones
    public static int Validar(int x) {
        int n = -1;
        do {
            //Scanner ponerlo dentro del DO, y dentro de una funcion
            Scanner teclado = new Scanner(System.in);
            //System.out.println("ingrese otro numero");
            try {
                n = teclado.nextInt();
            } catch (Exception e) {
                System.out.println("Error");
            }
            if (n < 0 || n > x) {
                System.out.println("ingrese un numero valido");
            }
        } while (n < 0 || n > x);
        return n;

    }
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
      
      //Rulos
    //Retorna un int[] con los digitos de la fecha ingresada
    public static int[] CadenaFecha(String ingresoFecha){
        String[] FechaEnCadena = ingresoFecha.split("/");
        int[] FechaIntEnCadena = new int[FechaEnCadena.length];
        for (int i=0;i< FechaEnCadena.length;i++){
            FechaIntEnCadena[i] = Integer.parseInt(FechaEnCadena[i]);
        }
        return FechaIntEnCadena;
    }

    //Retorna lo que se escribira en el txt del paciente, el especialista escogido y la fecha agendada
    public static String EscogerEspecialista(String ruta) {
        System.out.println("¿Con cual especialista desea agendar hora?");
        String[] Especialistas = leerArchivo(ruta).split("\n");
        return Especialistas[Validar(Especialistas.length)]+";"+EscogerFecha();
    }

    //Entrada de rut, si este pasa por el validador, se sigue con el codigo
    public static String IngresarRut() {
        Scanner teclado = new Scanner(System.in);
        String rut;
        do {
            System.out.println("Ingresar rut:");
            rut = teclado.next();

        }while (!validarRut(rut));
        return rut;
    }
}

