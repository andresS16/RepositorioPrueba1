
package modelo;

public class Carrera {
  
    private String nombre;
    private Materia materia;
    private int año;
    private long id;

    public Carrera() {
    }

  
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    

    /*@Override
    public String toString() {
        //return "Carrera{" + "nombre=" + nombre + '}';
        return nombre ;
    }*/
 @Override
    public String toString() {
        return  nombre ;
    }
  
    
    
    
   
    
    
    
    
    
    
}
