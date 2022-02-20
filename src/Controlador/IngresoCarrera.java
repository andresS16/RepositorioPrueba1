/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import modelo.Alumno;
import modelo.Carrera;
import modelo.Materia;
import modelo.Profesor;

/**
 * FXML Controller class
 *
 * @author Silva
 */
public class IngresoCarrera implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private ComboBox<Integer> comboCursada;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnModificar;
    private long id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboCursada.getItems().addAll(1,2,3,4,5);                  
    }  
    
    public void vaciarCampos(){            
        txtNombre.setText("");                  
        comboCursada.setValue(null);          
    }
    
    public void traerCarrera(Carrera c){
                           
        if(c !=null){                                            
            this.txtNombre.setText(c.getNombre());          
            this.comboCursada.setValue(c.getAño());                                    
        }       
        String query = "select id from carrera where nombre='"+ c.getNombre()+"'";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
        try{
            if(rs.next()){                   
                //JOptionPane.showMessageDialog(null, "entro en el if para asignar", "Error",JOptionPane.WARNING_MESSAGE);                 
                c.setId(rs.getLong("id")); 
                this.id = c.getId(); 
            //c.setNombre(rs.getString("nombre"));
            }           
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"error en metodo traer carrera" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
        }   
    }
     
    private void eliminarCarrera() { 
                       
        String nombre = this.txtNombre.getText(); 
        TablaCarrera tc = new TablaCarrera();           
        Carrera c = new Carrera();                             
                                
        if( nombre.equals("") || this.comboCursada.getValue()==null){
               
            JOptionPane.showMessageDialog(null,"falta seleccionar campos " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);            
            //tc.rellenarTablaCarrera();
            return ;
                    
        }else if(id!=0){                                                                  
            int opcion = JOptionPane.showConfirmDialog(null, 
                             "Desea eliminar " + "el registro ? " , "confirmacion" ,JOptionPane.YES_NO_OPTION,2);

            if(opcion== JOptionPane.YES_OPTION  ){
               
                String query ="DELETE FROM `carrera` WHERE `carrera`.`nombre` ='" + this.txtNombre.getText() +"' ";// traer() trae id
                TransaccionesBD trscns = new TransaccionesBD();
                boolean exito = trscns.ejecutarQuery(query);

                JOptionPane.showMessageDialog(null,"Se elimino correctamente" ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
                vaciarCampos();
                Stage stage =(Stage) this.btnGuardar.getScene().getWindow();
                stage.close();                   

            }else if(opcion== JOptionPane.NO_OPTION){
                vaciarCampos();
                Stage stage =(Stage) this.btnGuardar.getScene().getWindow();
                stage.close(); 
                JOptionPane.showMessageDialog(null,"Operacion cancelada" ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
            }                                                      
        }else{
            JOptionPane.showMessageDialog(null,"Debe seleccionar carrera" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
        }                                       
    }
    
    private void modificarCarrera() {
                  
        IngresoCarrera im= new IngresoCarrera();
        TablaCarrera tm =new TablaCarrera();                                                              
        String nombre = this.txtNombre.getText();          
        Carrera c ;     
     
        if( nombre.equals("") || this.comboCursada.getValue()== null  ){
               
            JOptionPane.showMessageDialog(null,"falta llenar los campos" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);            
            //rellenarTablaMateria();
            return ;
                    
        }else if (id != 0){                                      
            String query = "UPDATE carrera SET "
                    + "nombre = '"+ this.txtNombre.getText()+"',"
                    + " año='"  + this.comboCursada.getValue()+ "' WHERE id = '" + id +"' " ;
                                                                                                                                                                                                                                                                                                                                                                                                           
            TransaccionesBD trscns = new TransaccionesBD();
            boolean exito = trscns.ejecutarQuery(query);
            JOptionPane.showMessageDialog(null,"carrera modificado " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
         // vaciarCampos();
            Stage stage =(Stage) this.btnGuardar.getScene().getWindow();
            stage.close();                               
                                      
        }else{
            JOptionPane.showMessageDialog(null,"debe seleccionar materia" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);                    
        }                                                                                                                                                                                    
    }
    

    @FXML
    private void guardarCarrera(ActionEvent event) {
        
        Carrera c= new Carrera();   
        Carrera c1=null;
        IngresoCarrera ic= new IngresoCarrera();
        TablaCarrera tc=new TablaCarrera();                 
        long id ;         
        id=ic.id_incrementable();               
        String bandera =this.txtNombre.getText(); 
        boolean isNumerico = bandera.chars().allMatch( Character::isDigit );                   
        String nombre = this.txtNombre.getText(); 
                                                                      
        if(bandera.equals("") || nombre.equals("") ||  this.comboCursada.getValue()== null){
            
            JOptionPane.showMessageDialog(null,"falta llenar los campos" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);            
            tc.rellenarTablaCarrera();
            return ;
            
        }else if(isNumerico != true) {              
            boolean isNumero = false;            
            isNumero = !nombre.matches(".*\\d.*"); // if ternario :contiene un numero
             // no contiene un numero }                                             
            if( isNumero != false){                                                     
                c1= tc.buscarCarreraNombre(this.txtNombre.getText());  
                
                if(c1 !=null ){                                    
                    JOptionPane.showMessageDialog(null,"El usuario ya existe" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);                                                                     
                }else {                                                                                                                                                                                                                                                 
                    c.setId(id);
                    c.setNombre(this.txtNombre.getText());                                                          
                    c.setAño(this.comboCursada.getValue());                             
                    ic.insertar(c);
                    JOptionPane.showMessageDialog(null,"Se guardo correctamente" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);   
                    Stage stage =(Stage) this.btnGuardar.getScene().getWindow();
                    stage.close();                               //return; 
                }                                                                         
            }else{
                JOptionPane.showMessageDialog(null,"Debe ingresar letras ADENTRO" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);            
              }                            
        }else{                
            JOptionPane.showMessageDialog(null,"Debe ingresar letras AFUERA" ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
          }             
    }

    @FXML
    private void actionEvent(ActionEvent e) {
        
        Object evento = e.getSource();
        
        if(evento.equals(btnEliminar)){            
            eliminarCarrera();      
            //JOptionPane.showMessageDialog(null,"llama al metodo eliminar"+ nombre,"aviso" , JOptionPane.INFORMATION_MESSAGE);         
        }else if(evento.equals(btnModificar)){               
            modificarCarrera();                             
        }                                       
    }
    
    public long id_incrementable(){
           
        long id=0;       
        String query="SELECT MAX(id) FROM carrera";              
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
    
    public boolean insertar(Carrera c){
         
        String query = "INSERT INTO carrera(id,nombre,año)" 
                + "VALUES(' "
                
                +  c.getId()
                + " ',' " 
                + c.getNombre()
                + " ',' " 
                + c.getAño()                                           
                + "' )";
          
        TransaccionesBD trscns = new TransaccionesBD();
        boolean exito = trscns.ejecutarQuery(query);    
        
       // JOptionPane.showMessageDialog(null,"entro en metodo insertar REPO" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
        return exito;
    }
    
}
