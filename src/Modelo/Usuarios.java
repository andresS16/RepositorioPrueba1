/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Silva
 */
public class Usuarios {
    private Long id ;
    private String correo;
    private String usuario;
    private int privilegio;
    private int activo;
    private String password;

    public Usuarios(Long id, String correo, String usuario, int privilegio, int activo, String password) {
        this.id = id;
        this.correo = correo;
        this.usuario = usuario;
        this.privilegio = privilegio;
        this.activo = activo;
        this.password = password;
    }

    public Usuarios(String correo, String usuario, String password) {
        this.correo = correo;
        this.usuario = usuario;
        this.password = password;
    }

    public Usuarios() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(int privilegio) {
        this.privilegio = privilegio;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    
    
}
