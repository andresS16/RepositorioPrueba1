
package modelo;

import java.util.Date;


public class Materia {
    
    private String nombre;
    private int numMateria;
    private Carrera carrera;
    private int año;
    private int cuatrimestre;
    

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

    public int getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(int cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

@Override
    public String toString() {
        return nombre ;
    }    
   
   

    
   
    
  
     
    
}
