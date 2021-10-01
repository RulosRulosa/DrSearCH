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
}
