
package modelo;

public class Alumno {
    private long id_matricula;
    private String nombre_alumno;
    private String apellido_alumno;;
    private Carrera carrera;
    private Materia materia;
    private long fecha_BD;
    private String fecha_usuario;

    public Alumno(long id_matricula, String nombre_alumno, String apellido_alumno, Carrera carrera, Materia materia, long fecha_BD, String fecha_usuario) {
        this.id_matricula = id_matricula;
        this.nombre_alumno = nombre_alumno;
        this.apellido_alumno = apellido_alumno;
        this.carrera = carrera;
        this.materia = materia;
        this.fecha_BD = fecha_BD;
        this.fecha_usuario = fecha_usuario;
    }

    public Alumno(String nombre_alumno, String apellido_alumno, Carrera carrera, Materia materia, long fecha_BD, String fecha_usuario) {
        this.nombre_alumno = nombre_alumno;
        this.apellido_alumno = apellido_alumno;
        this.carrera = carrera;
        this.materia = materia;
        this.fecha_BD = fecha_BD;
        this.fecha_usuario = fecha_usuario;
    }

    public long getId_matricula() {
        return id_matricula;
    }

    public void setId_matricula(long id_matricula) {
        this.id_matricula = id_matricula;
    }

    public String getNombre_alumno() {
        return nombre_alumno;
    }

    public void setNombre_alumno(String nombre_alumno) {
        this.nombre_alumno = nombre_alumno;
    }

    public String getApellido_alumno() {
        return apellido_alumno;
    }

    public void setApellido_alumno(String apellido_alumno) {
        this.apellido_alumno = apellido_alumno;
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

    public long getFecha_BD() {
        return fecha_BD;
    }

    public void setFecha_BD(long fecha_BD) {
        this.fecha_BD = fecha_BD;
    }

    public String getFecha_usuario() {
        return fecha_usuario;
    }

    public void setFecha_usuario(String fecha_usuario) {
        this.fecha_usuario = fecha_usuario;
    }

    @Override
    public String toString() {
        return "Alumno{" + "id_matricula=" + id_matricula + ", nombre_alumno=" + nombre_alumno + ", apellido_alumno=" + apellido_alumno + ", carrera=" + carrera  + '}';
    }

    
    
    
    
    

    
    
}
