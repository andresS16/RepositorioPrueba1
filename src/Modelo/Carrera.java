
package modelo;

public class Carrera {
    private int num_carrera;
    private String nombre_carrera;
    private String cantidad_materia;
    private Profesor director;

    public Carrera() {
    }

    public Carrera(String nombre_carrera) {
        this.nombre_carrera = nombre_carrera;
    }
    

    public Carrera(String nombre_carrera, String cantidad_materia, Profesor director) {
        this.nombre_carrera = nombre_carrera;
        this.cantidad_materia = cantidad_materia;
        this.director = director;
    }

    public String getNombre_carrera() {
        return nombre_carrera;
    }

    public void setNombre_carrera(String nombre_carrera) {
        this.nombre_carrera = nombre_carrera;
    }

    public String getCantidad_materia() {
        return cantidad_materia;
    }

    public void setCantidad_materia(String cantidad_materia) {
        this.cantidad_materia = cantidad_materia;
    }

    public Profesor getDirector() {
        return director;
    }

    public void setDirector(Profesor director) {
        this.director = director;
    }

    /*@Override
    public String toString() {
        return "Carrera{" + "num_carrera=" + num_carrera + ", nombre_carrera=" + nombre_carrera + ", cantidad_materia=" + cantidad_materia + ", director=" + director + '}';
    }*/
     @Override
    public String toString() {
        //return  nombre_carrera + "("+ num_carrera + ")" ;
        return this.getNombre_carrera();
    }
    
    
    
    
    
    
}
