public class Main {
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
