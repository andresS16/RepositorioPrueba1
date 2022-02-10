
package modelo;

import java.time.LocalDate;
import java.util.Date;


public class Profesor {
    private long id;
    private String nombre;
    private String apellido;
    private Carrera carrera;
    private Materia materia;
    private LocalDate fecha;
    private long fechaLong;
    private String fechaString;
    private long getId;
    

    public Profesor(String nombre, String apellido, Carrera carrera, Materia materia, LocalDate fecha) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
        this.materia = materia;
        this.fecha = fecha;
    }
    
    

    public Profesor(long id, String nombre, String apellido, Carrera carrera, Materia materia ,LocalDate fecha) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
        this.materia = materia;
        this.fecha = fecha;
    }
   
    
    

    public Profesor(long id, String nombre, String apellido, Carrera carrera, Materia materia, long fechaLong, String fechaString) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
        this.materia = materia;
        this.fechaLong = fechaLong;
        this.fechaString = fechaString;
    }
    

    public Profesor(long id, String nombre, String apellido, Carrera carrera, Materia materia,long fechaLong) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
        this.materia = materia;
        this.fechaLong = fechaLong;
    }

    public Profesor() {
    }
    

    public Profesor(long id, String nombre, String apellido, Carrera carrera, Materia materia,String fechaString) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = new Carrera();
        this.materia = new Materia();
        this.fechaString = fechaString;
    }

     

    public Profesor(String nombre, String apellido, Carrera carrera, Materia materia, String fechaString) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
        this.materia = materia;
        this.fechaString = fechaString;
    }

    public Profesor(Long id, String nombre, String apellido, Materia materia, Carrera carrera, String fechaLong) {
       //To change body of generated methods, choose Tools | Templates.
    }

    public Profesor(long id, String nombre, String apellido, Materia materia, Carrera carrera, LocalDate fecha) {
        
    }

    public Profesor(long id, String nombre, String apellido) {
       
    }

    

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public long getFechaLong() {
        return fechaLong;
    }

    public void setFechaLong(long fechaLong) {
        this.fechaLong = fechaLong;
    }

    public String getFechaString() {
        return fechaString;
    }

    public void setFechaString(String fechaString) {
        this.fechaString = fechaString;
    }

    @Override
    public String toString() {
        return "Profesor{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", carrera=" + carrera + ", materia=" + materia + ", fechaLong=" + fechaLong + ", fechaString=" + fechaString + '}';
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public long set(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
 

@Override
public boolean equals(Object o) {
     if(super.equals(o)) {
         return true;
     }
    if(!(o instanceof Profesor)) {
        return false;
    }
    Profesor other = (Profesor) o;
    return 
        id == other.getId;

}  
    /*@Override
public boolean equals(Object obj) {
        Profesor p = (Profesor) obj;
        if (id == p.getId())
            return true;
        else
            return false;
    }*/
    
    

   

    

    

   

   

   

    
}
