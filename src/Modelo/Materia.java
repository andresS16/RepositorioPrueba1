
package modelo;

import java.util.Date;


public class Materia {
    
    private String nombre_Materia;
    private Alumno alumno;
    private Date horario;
    private Aula aula;
    private Profesor profesor_responsable;
    private Profesor profesor_auxiliar;
   private int codigo_materia;

    public Materia() {
    }
   
   

    public Materia(String nombre_Materia, Alumno alumno, Date horario, Aula aula, Profesor profesor_responsable, Profesor profesor_auxiliar, int codigo_materia) {
        this.nombre_Materia = nombre_Materia;
        this.alumno = alumno;
        this.horario = horario;
        this.aula = aula;
        this.profesor_responsable = profesor_responsable;
        this.profesor_auxiliar = profesor_auxiliar;
        this.codigo_materia = codigo_materia;
    }

    public Materia(String nombre_Materia, int codigo_materia) {
        this.nombre_Materia = nombre_Materia;
        this.codigo_materia = codigo_materia;
    }

    public Materia(String nombre_Materia) {
        this.nombre_Materia = nombre_Materia;
    }
    

    public String getNombre_Materia() {
        return nombre_Materia;
    }

    public void setNombre_Materia(String nombre_Materia) {
        this.nombre_Materia = nombre_Materia;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public Profesor getProfesor_responsable() {
        return profesor_responsable;
    }

    public void setProfesor_responsable(Profesor profesor_responsable) {
        this.profesor_responsable = profesor_responsable;
    }

    public Profesor getProfesor_auxiliar() {
        return profesor_auxiliar;
    }

    public void setProfesor_auxiliar(Profesor profesor_auxiliar) {
        this.profesor_auxiliar = profesor_auxiliar;
    }

    public int getCodigo_materia() {
        return codigo_materia;
    }

    public void setCodigo_materia(int codigo_materia) {
        this.codigo_materia = codigo_materia;
    }

   
    
     @Override
    public String toString() {
        //return  nombre_Materia + "(" + codigo_materia + " )";
        return this.getNombre_Materia();
    }
     
    
}
