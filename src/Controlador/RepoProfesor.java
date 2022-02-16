
package Controlador;

import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import static java.util.Collections.list;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import jdk.jfr.Timestamp;
import modelo.Profesor;
//import modelo.TransaccionesBD;
import modelo.Materia;
import modelo.Carrera;
//import modelo.Fecha;


public class RepoProfesor {
     
    public boolean insertar(Profesor profesor){
         
        String query = "INSERT INTO profesor4(id ,dni,nombre,apellido,materia,carrera,fecha)" 
                + "VALUES(' "
                + profesor.getId() 
                + " ',' " 
                 +  profesor.getDni()
                + " ',' " 
                +  profesor.getNombre()
                +"','"
                + profesor.getApellido()
                + " ',' " 
                + profesor.getMateria()              
                + " ',' " 
                + profesor.getCarrera() 
                + " ','" + profesor.getFecha()
                + "' )";
          
        TransaccionesBD trscns = new TransaccionesBD();
        boolean exito = trscns.ejecutarQuery(query);    
        
       // JOptionPane.showMessageDialog(null,"entro en metodo insertar REPO" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
        return exito;
    }
     
       public boolean modificar(Profesor profesor){
               
           String query="UPDATE `profesor4` SET "
                   + "`id`='"+ profesor.getId()+"',"
                   + "`nombre`='"+ profesor.getNombre()+"',"
                   + "`apellido`='"+profesor.getApellido()+"',"
                   + "`materia`='"+ profesor.getMateria() +"',"
                   + "`carrera`='"+ profesor.getCarrera()+"',"
                   + "`fecha`='"+profesor.getFecha()+"' "
                   + "WHERE `id`="+ profesor.getId();
           
            TransaccionesBD trscns = new TransaccionesBD();
            boolean exito = trscns.ejecutarQuery(query);
             //JOptionPane.showMessageDialog(null,"Se modifico objeto" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
        
        return exito;
       }
          
       public boolean eliminar(long id){
           
          //String query = "DELETE FROM Profesor4 id= " + id;
           
            String query ="DELETE FROM `profesor4` WHERE `profesor4`.`id` = " + id;
           TransaccionesBD trscns = new TransaccionesBD();
            boolean exito = trscns.ejecutarQuery(query);
            
        return exito;
       }
             
        public Profesor buscarProfesor(long idBusqueda){
            String query ="SELECT * FROM profesor4 WHERE id = " + idBusqueda;
            TransaccionesBD trscns = new TransaccionesBD();
            ResultSet rs = trscns.realizarConsulta(query);
            
            Profesor profesor=null;
          
            try{
                if(rs.next()){
                    idBusqueda=rs.getLong("id");  
                    int dni=rs.getInt("dni");
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                   LocalDate fecha = rs.getDate("fecha").toLocalDate();
        
                    Materia materia= new Materia();
                    materia.setNombre(rs.getString("materia"));
                    Carrera carrera = new Carrera();
                    carrera.setNombre(rs.getString("carrera"));
                     
                   profesor = new Profesor();
                   profesor.setId(idBusqueda);
                   profesor.setNombre(nombre);
                   profesor.setApellido(apellido);
                   profesor.setMateria(materia);
                   profesor.setCarrera(carrera);
                   profesor.setFecha(LocalDate.EPOCH);
                 
                }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar objeto profesor" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
    return profesor;                                  
        }
             
        public ObservableList<Profesor> buscarProfeApellido(String apellidoBusqueda){
             JOptionPane.showMessageDialog(null, "Ingreso en metodo buscarprofeApellido", "Error",JOptionPane.WARNING_MESSAGE);
            
            String query = "SELECT * FROM profesor4 WHERE apellido LIKE '%" + apellidoBusqueda + "%'";
            
            TransaccionesBD trscns =new TransaccionesBD();
            ResultSet rs = trscns.realizarConsulta(query);
            Profesor profesor=null;
            
            ObservableList<Profesor> profesoresApellido = FXCollections.observableArrayList();
            //JOptionPane.showMessageDialog(null, "entro en el metodo buscarApellido ", "Error",JOptionPane.WARNING_MESSAGE);
           
          try{
                while(rs.next()){
                     //JOptionPane.showMessageDialog(null, "entro en el while ", "Error",JOptionPane.WARNING_MESSAGE);
                   long idBusqueda=rs.getLong("id");            
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    LocalDate fecha = rs.getDate("fecha").toLocalDate();
        
                    Materia materia= new Materia();
                    materia.setNombre(rs.getString("materia"));
                    Carrera carrera = new Carrera();
                    carrera.setNombre(rs.getString("carrera"));
                     
                    profesor = new Profesor();
                    profesor.setId(idBusqueda);
                    profesor.setNombre(nombre);
                    profesor.setApellido(apellido);
                    profesor.setMateria(materia);
                    profesor.setCarrera(carrera);
                    profesor.setFecha(LocalDate.EPOCH);
                    profesoresApellido.add(profesor);                               
                }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar objeto profesor" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
          
            return profesoresApellido;                        
        }
        
        
        public ObservableList<Profesor> buscarProfeMateria(String materiaBusqueda){
            
            String query = "SELECT * FROM profesor4 WHERE materia LIKE '%" + materiaBusqueda + "%'";
            
            TransaccionesBD trscns =new TransaccionesBD();
            ResultSet rs = trscns.realizarConsulta(query);
            Profesor profesor=null;
            
            ObservableList<Profesor> profesoresMateria = FXCollections.observableArrayList();
            //JOptionPane.showMessageDialog(null, "entro en el metodo buscarApellido ", "Error",JOptionPane.WARNING_MESSAGE);
           
          try{
                while(rs.next()){
                     //JOptionPane.showMessageDialog(null, "entro en el while ", "Error",JOptionPane.WARNING_MESSAGE);
                   long idBusqueda=rs.getLong("id");            
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    LocalDate fecha = rs.getDate("fecha").toLocalDate();
        
                    Materia materia= new Materia();
                    materia.setNombre(rs.getString("materia"));
                    Carrera carrera = new Carrera();
                    carrera.setNombre(rs.getString("carrera"));
                     
                    profesor = new Profesor();
                    profesor.setId(idBusqueda);
                    profesor.setNombre(nombre);
                    profesor.setApellido(apellido);
                    profesor.setMateria(materia);
                    profesor.setCarrera(carrera);
                    profesor.setFecha(LocalDate.EPOCH);
                    profesoresMateria.add(profesor);                               
                }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar objeto profesor" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
          
            return profesoresMateria;                        
        }
        
        public ObservableList<Profesor> buscarProfeCarrera(String apellidoBusqueda){
            
            String query = "SELECT * FROM profesor4 WHERE carrera LIKE '%" + apellidoBusqueda + "%'";
            
            TransaccionesBD trscns =new TransaccionesBD();
            ResultSet rs = trscns.realizarConsulta(query);
            Profesor profesor=null;
            
            ObservableList<Profesor> profesoresCarrera = FXCollections.observableArrayList();
            //JOptionPane.showMessageDialog(null, "entro en el metodo buscarApellido ", "Error",JOptionPane.WARNING_MESSAGE);
           
          try{
                while(rs.next()){
                     //JOptionPane.showMessageDialog(null, "entro en el while ", "Error",JOptionPane.WARNING_MESSAGE);
                   long idBusqueda=rs.getLong("id");            
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    LocalDate fecha = rs.getDate("fecha").toLocalDate();
        
                    Materia materia= new Materia();
                    materia.setNombre(rs.getString("materia"));
                    Carrera carrera = new Carrera();
                    carrera.setNombre(rs.getString("carrera"));
                     
                    profesor = new Profesor();
                    profesor.setId(idBusqueda);
                    profesor.setNombre(nombre);
                    profesor.setApellido(apellido);
                    profesor.setMateria(materia);
                    profesor.setCarrera(carrera);
                    profesor.setFecha(LocalDate.EPOCH);
                    profesoresCarrera.add(profesor);                               
                }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar objeto profesor" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
          
            return profesoresCarrera;                        
        }
        
        
       public ObservableList<Profesor> buscarTodos(){
           
            String query = "SELECT * FROM profesor4";
            TransaccionesBD trscns = new TransaccionesBD(); 
            ResultSet rs = trscns.realizarConsulta(query);
            
            Profesor profesor=null;
     
            ObservableList<Profesor> profesores = FXCollections.observableArrayList();
       
       try{
                while(rs.next()){
                    
                      Materia materia= new Materia();
                      String m=rs.getString("materia");
                      materia.setNombre(m);
                      Carrera carrera = new Carrera();
                      carrera.setNombre(rs.getString("carrera"));
                      
                      profesor=new Profesor();
                      
                     // profesor.setId(rs.getLong("id"));
                      profesor.setDni(rs.getInt("dni"));
                      profesor.setNombre(rs.getString("nombre"));
                      profesor.setApellido(rs.getString("apellido"));
                      profesor.setFecha(rs.getDate("fecha").toLocalDate());
                      
                      profesor.setMateria(materia);
                      profesor.setCarrera(carrera);
                   
               /* JOptionPane.showMessageDialog(null,"id :" + profesor.getId() + " nombre:"
                   + profesor.getNombre() + " apellido: " + profesor.getApellido() +
                        " materia:" + profesor.getMateria().getNombre_Materia() + " carrera:" +
                        profesor.getCarrera().getNombre_carrera() + " fecha: " + profesor.getFechaString());*/
                             
                   profesores.add(profesor);     
                                 
                }  
                rs.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error al buscar objeto profesor" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
       return profesores;
       } 
       
       public long id_incrementable(){
           
           long id=0;       
           String query="SELECT MAX(id) FROM profesor4";              
           TransaccionesBD trscns = new TransaccionesBD();
           ResultSet rs = trscns.realizarConsulta(query);
             try{
                 while(rs.next()){                                                        
                     id= rs.getLong(1)+1;
                 }                                             
             }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error al buscar id" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);                     
             }
             finally{//clase que cierra la coexion para evitar consumo de memoria
                 try{
                     rs.close();
                 }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error al buscar id" + ex , "ERROR", JOptionPane.ERROR_MESSAGE); 
                 }                     
             }       
        return id;
       }       
}
