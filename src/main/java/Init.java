import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Init {
    static Paciente activo;

    public static void main(String[] args) {
        ingreso();
    }

    public static void ingreso() {
        leer();
        String rut =IngresarRut();
        Paciente p=Paciente.buscarPorRut(rut);
        if(p !=null){
            System.out.println("Bienvenido "+p.getNombre());
            activo=p;
        }else {
            System.out.println("Quiere crear un perfil de paciente?");
            System.out.println("1)Si \n2)No ");
            if(get1o2()){
                crearNuevoPaciente(rut);
            }else{
                System.out.println("estos son los horarios disponibles *le muestra los horarios*");
            }
        }
        repeat();
        GuardadoGeneral();
    }

    private static void repeat() {
        int x;
        do{
            showMenu();
            System.out.println("desea realizar otra acción en el menu? (No (0) / Si (1))");
            x=validar(1);
        }while (x!=0);
    }

    private static void showMenu() {
        System.out.println("Menu");
        System.out.println("---------------");
        System.out.println("1)Agendar hora con un especialista");
        System.out.println("2)SALIR");
        menu1();
    }

    private static void menu1() {
        if (get1o2()) {
            System.out.println("Ingrese su Prevision");
            System.out.println("[1]Fonasa");
            System.out.println("[2]Isapre");
            menu2();
        }
    }

    private static void menu2() {
        if (get1o2()){
            int index=Especialista.mostrarEspecialistasFonasa();
                Especialista.consultarHoras(Especialista.atiendefonasa.get(index-1));
        }else{
            int index=Especialista.mostrarEspecialistasIsapre();
            if (index-1>=0){
                Especialista.consultarHoras(Especialista.atiendeisapre.get(index-1));
            }
            else if (index<=0){
                Especialista.consultarHoras(Especialista.especialistas.get(0));

            }
        }
    }

    private static void crearNuevoPaciente(String rut) {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Ingrese su Nombre");
        String nombre = sc.next();
        System.out.println("Ingrese su Apellido");
        String apellido = sc.next();
        System.out.println("Quiere proceder el registro con el siguiente rut?: "+ rut);
        System.out.println("1)Si \n2)No ");
        if(get1o2()){
            Paciente p =new Paciente(nombre,apellido,rut);
            Paciente.pacientes.add(p);
            activo =p;
        }else{
            String rut2=IngresarRut();
            Paciente p =new Paciente(nombre,apellido,rut2);
            Paciente.pacientes.add(p);
            activo =p;
        }
    }

    private static boolean get1o2() {
        return validar(2) == 1;
    }

    public static String IngresarRut() {
        Scanner teclado = new Scanner(System.in);
        String rut;
        do {
            System.out.println("Ingresar rut:");
            rut = teclado.next();

        }while (!Paciente.validarRut(rut));
        return rut.replace("-", "").replace(".","");
    }

    public static void leer(){
        if(ArchivosExisten()){
            try{
                csvReadP();
                csvReadE();
                csvReadA();
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }else if(!ArchivosExisten()){
            System.out.println("File not Found, Please contact a support");
            System.out.println("if you want to create the default files type \"yes\"");
            Scanner sc=new Scanner(System.in);
            String option= sc.nextLine();
            if(option.equalsIgnoreCase("yes")){
                GuardadoGeneral();
            }
            if(ArchivosExisten()){
                try{
                    csvReadP();
                    csvReadE();
                }catch(FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void csvReadE() throws FileNotFoundException {
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        var builder = new CSVReaderBuilder(new FileReader(rutaE));
        try (CSVReader reader = builder.withCSVParser(parser).withSkipLines(1).build()) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                int id = Integer.parseInt(nextLine[0]);
                String nombre = nextLine[1];
                String especialidad = nextLine[2];
                boolean fonasa = Boolean.parseBoolean(nextLine[3]);
                boolean isapre = Boolean.parseBoolean(nextLine[4]);
                Especialista.especialistas.add(new Especialista(id,nombre,especialidad,fonasa,isapre));
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    public static void csvReadP() throws FileNotFoundException {
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        var builder = new CSVReaderBuilder(new FileReader(rutaP));
        try (CSVReader reader = builder.withCSVParser(parser).withSkipLines(1).build()) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String nombre = nextLine[0];
                String apellido = nextLine[1];
                String rut =nextLine[2];
                String fecha2 =nextLine[3];
                Paciente.pacientes.add(new Paciente(nombre,apellido,rut,fecha2));
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }
    public static void csvReadA() throws FileNotFoundException {
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        var builder = new CSVReaderBuilder(new FileReader(rutaA));
        try (CSVReader reader = builder.withCSVParser(parser).withSkipLines(1).build()) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String fecha = nextLine[0];
                String nombre = nextLine[1];
                String especialista =nextLine[2];
                String hora =nextLine[3];
                Especialista.horasAgendadas.add(new Agenda(fecha,nombre,especialista,hora));
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    public static boolean ArchivosExisten(){
        return Gestor.PacienteLogExist()&&Gestor.EspecialistaLogExist();
    }

    private static void GuardadoGeneral() {
        Gestor.guardarEspecialistas();
        Gestor.guardarPacientes();
    }
    static final String rutaE="src/main/resources/Especialistas.CSV";
    static final String rutaP="src/main/resources/Pacientes.CSV";
    static final String rutaA="src/main/resources/Horas.CSV";
    public static int validar(int x) {
        int n = -1;
        do {
            //Scanner ponerlo dentro del DO, y dentro de una función
            Scanner teclado = new Scanner(System.in);
            //System.out.println("ingrese otro numero");
            try {
                n = teclado.nextInt();
            } catch (Exception e) {
                System.out.println("Error! Por favor, >Ingrese un número<");
            }
            if (n < 0 || n > x) {
                System.out.println("Ingrese un numero válido");
            }
        } while (n < 0 || n > x);
        return n;
    }
}
