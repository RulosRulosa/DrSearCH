import org.apache.commons.lang3.text.WordUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Paciente {
    public DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static ArrayList<Paciente> pacientes =new ArrayList<>();
    private final String nombre;
    private final String apellido;
    private final String rut;
    private final boolean rutValido;
    private LocalDate fechaDeCreacionUsuario;

    public Paciente(String nombre, String apellido, String rut) {//runtime constructor
        this.nombre = WordUtils.capitalize(nombre);
        this.apellido = WordUtils.capitalize(apellido);
        this.rut = rut;
        this.rutValido = validarRut(rut);
        this.fechaDeCreacionUsuario = LocalDate.parse(LocalDate.now().format(formato),formato);
    }
    public Paciente(String nombre, String apellido, String rut,String fecha2) {//escritura desde CSV
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.rutValido = validarRut(rut);
        try{
            this.fechaDeCreacionUsuario = LocalDate.parse(fecha2,formato);
        } catch (Exception e) {
            this.fechaDeCreacionUsuario = null;
        }
    }
    public static Paciente buscarPorRut(String rut){
        for (Paciente p:pacientes
        ) {
            if(rut.equals(p.rut)){
                if(p.nombre.equals("Billy")){
                    System.out.println(yamete());
                }
                return p;
            }
        }
        return null;
    }

    public static String yamete() {
        return "⠄⠄⠄⢰⣧⣼⣯⠄⣸⣠⣶⣶⣦⣾⠄⠄⠄⠄⡀⠄⢀⣿⣿⠄⠄⠄⢸⡇⠄⠄\n" + " ⠄⠄⠄⣾⣿⠿⠿⠶⠿⢿⣿⣿⣿⣿⣦⣤⣄⢀⡅⢠⣾⣛⡉⠄⠄⠄⠸⢀⣿⠄\n" + "⠄⠄⢀⡋⣡⣴⣶⣶⡀⠄⠄⠙⢿⣿⣿⣿⣿⣿⣴⣿⣿⣿⢃⣤⣄⣀⣥⣿⣿⠄\n" + "⠄⠄⢸⣇⠻⣿⣿⣿⣧⣀⢀⣠⡌⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿⠿⣿⣿⣿⠄\n" + "⠄⢀⢸⣿⣷⣤⣤⣤⣬⣙⣛⢿⣿⣿⣿⣿⣿⣿⡿⣿⣿⡍⠄⠄⢀⣤⣄⠉⠋⣰\n" + "⠄⣼⣖⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣿⣿⢇⣿⣿⡷⠶⠶⢿⣿⣿⠇⢀⣤\n" + "⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣽⣿⣿⣿⡇⣿⣿⣿⣿⣿⣿⣷⣶⣥⣴⣿⡗\n" + "⢀⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠄\n" + "⢸⣿⣦⣌⣛⣻⣿⣿⣧⠙⠛⠛⡭⠅⠒⠦⠭⣭⡻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠄\n" + "⠘⣿⣿⣿⣿⣿⣿⣿⣿⡆⠄⠄⠄⠄⠄⠄⠄⠄⠹⠈⢋⣽⣿⣿⣿⣿⣵⣾⠃⠄\n" + "⠄⠘⣿⣿⣿⣿⣿⣿⣿⣿⠄⣴⣿⣶⣄⠄⣴⣶⠄⢀⣾⣿⣿⣿⣿⣿⣿⠃⠄⠄\n" + "⠄⠄⠈⠻⣿⣿⣿⣿⣿⣿⡄⢻⣿⣿⣿⠄⣿⣿⡀⣾⣿⣿⣿⣿⣛⠛⠁⠄⠄⠄\n" + "⠄⠄⠄⠄⠈⠛⢿⣿⣿⣿⠁⠞⢿⣿⣿⡄⢿⣿⡇⣸⣿⣿⠿⠛⠁⠄⠄⠄⠄⠄\n" + "⠄⠄⠄⠄⠄⠄⠄⠉⠻⣿⣿⣾⣦⡙⠻⣷⣾⣿⠃⠿⠋⠁⠄⠄⠄⠄⠄⢀⣠⣴\n" + "⣿⣿⣿⣶⣶⣮⣥⣒⠲⢮⣝⡿⣿⣿⡆⣿⡿⠃⠄⠄⠄⠄⠄⠄⠄⣠⣴⣿⣿⣿";
    }

    public static void showAllPacients(){
        for (Paciente p:
                pacientes) {
            p.show();
        }
    }

    private void show() {
        System.out.println("------------------");
        System.out.println("Nombre: "+this.nombre);
        System.out.println("Apellido: "+this.apellido);
        System.out.println("Rut: "+this.rut);
        System.out.println("RutValido: "+this.rutValido);
        System.out.println("Fecha Creación: "+this.fechaDeCreacionUsuario);
        System.out.println("------------------");
    }

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

    public DateTimeFormatter getFormato() {
        return formato;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getRut() {
        return rut;
    }


    public boolean isRutValido() {
        return rutValido;
    }



    public LocalDate getFechaDeCreacionUsuario() {
        return fechaDeCreacionUsuario;
    }


    public String getFechaCreacionStr() {
        try {
            return fechaDeCreacionUsuario.format(formato);
        }catch(NullPointerException e) {
            return "";
        }
    }
    @Override
    public String toString() {
        return nombre+","+ apellido +","+rut+","+ getFechaCreacionStr();
    }
}