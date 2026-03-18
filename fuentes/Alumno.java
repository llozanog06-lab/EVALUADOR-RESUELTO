import java.util.*;

public class Alumno{
    private String nombre;
    private List<Double> calificaciones;

    public Alumno(String nombre){
        this.nombre = nombre;
        this.calificaciones = new ArrayList<>();
    }

    public void agregarCalificacion(Double calificacion){
        this.calificaciones.add(calificacion);
    }
    public List<Double> getCalificaciones(){
        return this.calificaciones;
    }
}