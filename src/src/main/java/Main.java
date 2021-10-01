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


}
