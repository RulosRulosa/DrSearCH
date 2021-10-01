public class Main {
    //Tomás
    //Imprime por pantalla los especialistas disponibles por prevision escogida
    public static void MostrarEspecialistasPrevision(String prevision) {
        System.out.println(leerArchivo(prevision));
    }

    //Si el ingreso de fecha es válido, lo retorna para guardarlo en su txt
    public static String EscogerFecha() {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        String ingresoFecha;
        do {
            ingresoFecha =IngresarFecha();
            try {                           //Valida formato dd/MM/yyyy
                fecha = format.parse(ingresoFecha);
            } catch (Exception e) {
                MostrarFechaIncorrecto();
            }
            if (fechaValida(ingresoFecha)){ //Valida digitos
                MostrarFechaInvalido();
            }
        }while (fecha==null||!fechaValida(ingresoFecha)); //Si el formato o los digitos son invalidos, se sigue en el bucle
        return ingresoFecha;
    }

    //Entrada de fecha a agendar
    public static String IngresarFecha() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingresar fecha en formato DD/MM/YYYY");
        return teclado.next();
    }

    public static void MostrarFechaInvalido() {
        System.out.println("Digitos de fecha invalidos");
    }

    public static void MostrarFechaIncorrecto() {
        System.out.println("Formato de fecha incorrecto");
    }
}
