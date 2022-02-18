
package modelo;

import java.util.Date;


public class Materia {
    
    private String nombre;
    private int numMateria;
    private Carrera carrera;
    private int año;
    private String cuatrimestre;
    private long id;
    

    public Materia() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumMateria() {
        return numMateria;
    }

    public void setNumMateria(int numMateria) {
        this.numMateria = numMateria;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(String cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    

@Override
    public String toString() {
        return nombre ;
    }    
   
   

    
   
    
  
     
    
}
