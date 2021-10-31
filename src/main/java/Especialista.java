import org.apache.commons.lang3.text.WordUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Especialista {
    public static ArrayList<Especialista> especialistas=new ArrayList<>();
    public static ArrayList<Agenda> horasAgendadas=new ArrayList<>();
    public static ArrayList<LocalTime> horasAtencion =new ArrayList<>();
    public static ArrayList<Especialista> atiendeisapre =new ArrayList<>();
    public static ArrayList<Especialista> atiendefonasa =new ArrayList<>();
    public static DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final int id;
    private final String nombre;
    private final String especialidad;
    private final boolean fonasa;
    private final boolean isapre;

    public Especialista(int id, String nombre, String especialidad, boolean fonasa, boolean isapre) {
        this.id = id;
        this.nombre = WordUtils.capitalize(nombre);
        this.especialidad = especialidad;
        this.fonasa = fonasa;
        this.isapre = isapre;
    }

    public static int mostrarEspecialistasFonasa() {
        int counter = 0;
        System.out.println("Profesionales por indice");
        for (Especialista e:especialistas
        ) {
            if(e.isFonasa()){
                atiendefonasa.add(e);
                ++counter;
                System.out.println();
                System.out.println("Indice: "+counter);
                e.show();
            }
        }
        return Init.validar(counter);
    }

    public static int mostrarEspecialistasIsapre() {
        int counter = 0;
        System.out.println("Profesionales por indice");
        for (Especialista e:especialistas
        ) {
            if(e.isIsapre()){
                atiendeisapre.add(e);
                ++counter;
                System.out.println();
                System.out.println("Indice: "+counter);
                e.show();
            }
        }
        return Init.validar(counter);
    }
    public static void horasPosibles(){
        LocalTime time1 = LocalTime.of(11, 0, 0);
        LocalTime time2 = LocalTime.of(12, 0, 0);
        LocalTime time3 = LocalTime.of(14, 0, 0);
        horasAtencion.add(time1);
        horasAtencion.add(time2);
        horasAtencion.add(time3);
    }

    public static void mostrarHoras(){
        horasPosibles();
        System.out.println("1)\t"+ horasAtencion.get(0));
        System.out.println("2)\t"+horasAtencion.get(1));
        System.out.println("3)\t"+horasAtencion.get(2));

    }

    public static LocalTime EscogerHoras() {
        int eleccion= Init.validar(3);
        if (eleccion ==1){
            return horasAtencion.get(0);
        }else if(eleccion ==2){
            return horasAtencion.get(1);
        }else if(eleccion==3){
            return horasAtencion.get(2);
        }
        return null;
    }

    public static void consultarHoras(Especialista especialista) {
        LocalDate fecha;
        LocalTime hora;
        do {
            fecha =obtenerFecha();
            mostrarHoras();
            System.out.println("ingrese un el numero de la hora que escojió");
            hora = horaEscogida();
        }while(!verDisponible(fecha,hora,especialista));
        System.out.println("Hora agendada con éxito");
        Agenda a= new Agenda(fecha,Init.activo,especialista,hora);
        horasAgendadas.add(a);
        Gestor.guardarHoras();
    }

    public static boolean verDisponible(LocalDate fecha, LocalTime hora,Especialista especialista) {
        for (Agenda a:horasAgendadas
             ) {
            if (a.getDate().equals(fecha)&&a.getHour().equals(hora)&&a.getEspecialista().equals(especialista)){
                System.out.println("Horario no disponible");
                return false;
            }
        }
        return true;
    }

    public static LocalTime horaEscogida() {
        return EscogerHoras();
    }

    public static LocalDate obtenerFecha(){
        LocalDate fecha = null;
        do{
            System.out.println("Ingrese una fecha en el formato yyyy-MM-dd");
            Scanner sc = new Scanner(System.in);
            String string=sc.next();
            try {
                fecha= LocalDate.parse(string);
            }catch(Exception e){
                System.out.println("Ingrese la fecha correctamente");
            }
        }while(fecha==null);
        return fecha;
    }

    public void show() {
        System.out.println("-------------------");
        System.out.println("Nombre: "+this.nombre);
        System.out.println("Especialidad: "+this.especialidad);
        System.out.println("-------------------");
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public boolean isFonasa() {
        return fonasa;
    }

    public boolean isIsapre() {
        return isapre;
    }

    @Override
    public String toString() {
        return id +","+nombre +","+especialidad +","+fonasa+","+isapre;
    }
}
