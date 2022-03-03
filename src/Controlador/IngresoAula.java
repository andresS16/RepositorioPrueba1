/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Alumno;
import modelo.Aula;
import modelo.Carrera;

/**
 * FXML Controller class
 *
 * @author Silva
 */
public class IngresoAula implements Initializable {

    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnModificar;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtCapacidad;
    private Long id =null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        
        
    } 
    
    public void vaciarCampos(){            
        txtNumero.setText("");                  
        txtCapacidad.setText("");         
    }
    
    public void traerAula(Aula a){
                           
        if(a!=null){                                            
            this.txtNumero.setText(a.getNumAula()+"");          
            this.txtCapacidad.setText(a.getCapacidad()+"");                                      
        }       
        String query = "select id_aula from aula where numAula='"+ a.getNumAula()+"'";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
        try{
            if(rs.next()){                   
                //JOptionPane.showMessageDialog(null, "entro en el if para asignar", "Error",JOptionPane.WARNING_MESSAGE);                 
                a.setId(rs.getLong("id_aula"));
                this.id = a.getId();
            //c.setNombre(rs.getString("nombre"));
            }           
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"error en metodo traer aula " + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
        }   
    }
    
    private void eliminarAula() {   
        
        TablaAula tc = new TablaAula();           
        Aula a = new Aula();  
                                    
        if( this.txtNumero.getText().isEmpty() || this.txtCapacidad.getText().isEmpty()){
               
            JOptionPane.showMessageDialog(null,"falta seleccionar campos " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);            
            //tc.rellenarTablaCarrera();
            return ;
                    
        }else if(id!=null){                                                                  
            int opcion = JOptionPane.showConfirmDialog(null, 
                    "Desea eliminar " + "el registro ? " , "confirmacion" ,JOptionPane.YES_NO_OPTION,2);

            if(opcion== JOptionPane.YES_OPTION  ){
               
                String query ="DELETE FROM `aula` WHERE `aula`.`numAula` ='" + this.txtNumero.getText() +"' ";// traer() trae id
                TransaccionesBD trscns = new TransaccionesBD();
                boolean exito = trscns.ejecutarQuery(query);

                JOptionPane.showMessageDialog(null,"Se elimino correctamente" ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
                vaciarCampos();
                
                Stage stage =(Stage) this.btnEliminar.getScene().getWindow();
                stage.close();                

            }else if(opcion== JOptionPane.NO_OPTION){
                vaciarCampos();
                Stage stage =(Stage) this.btnEliminar.getScene().getWindow();
                stage.close(); 
                JOptionPane.showMessageDialog(null,"Operacion cancelada" ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
            }                                                      
        }else{
            JOptionPane.showMessageDialog(null,"Haga un refresh luego seleccione aula" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
            Stage stage =(Stage) this.btnModificar.getScene().getWindow();
            stage.close();
            
            
        }                                       
    }
    
    private void modificarAula() {
                  
        IngresoAula ia= new IngresoAula();
        TablaAula ta=new TablaAula();                                                              
        //String nombre = this.txtNombre.getText();          
        Aula a =null;
        Aula e=null ;
        Aula a1= new Aula();
     
        if( this.txtNumero.getText().isEmpty() || this.txtCapacidad.getText().isEmpty()  ){
               
            JOptionPane.showMessageDialog(null,"falta llenar los campos" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);            
            //rellenarTablaMateria();
            return ;
                    
        }else if (id != null){
            
            a=ta.buscarAulaID(id);
            a1.setNumAula(Integer.parseInt(this.txtNumero.getText()));
            a1.setCapacidad(Integer.parseInt(this.txtCapacidad.getText()));
            
            if(a.getNumAula()== a1.getNumAula() && a.getCapacidad()==a1.getCapacidad()){
                JOptionPane.showMessageDialog(null,"Debe ingresar modificaciones " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                
            }else if(a.getNumAula()== a1.getNumAula() ){
                 String query = "UPDATE aula SET "
                    + "numAula = '"+ this.txtNumero.getText()+"',"
                    + " capacidad ='"  + this.txtCapacidad.getText()+ "' WHERE id = '" + id +"' " ;
                                                                                                                                                                                                                                                                                                                                                                                                           
                TransaccionesBD trscns = new TransaccionesBD();
                boolean exito = trscns.ejecutarQuery(query);
                JOptionPane.showMessageDialog(null,"aula modificada " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
             // vaciarCampos();
                Stage stage =(Stage) this.btnModificar.getScene().getWindow();
                stage.close(); 
            }                                                   
        }else{
            JOptionPane.showMessageDialog(null,"Haga un refrech luego seleccione aula" ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
            Stage stage =(Stage) this.btnModificar.getScene().getWindow();
            stage.close();
        }                                                                                                                                                                                    
    }
    
    @FXML
    private void  guardarAula(ActionEvent event) {
                  
        Aula a= new Aula();   
        Aula a1=null;
        IngresoAula ia = new IngresoAula();
        TablaAula ta=new TablaAula();                 
        long id ;                                       
        String bandera1 =this.txtCapacidad.getText();
                                                                      
        if(this.txtNumero.getText().isEmpty() || this.txtCapacidad.getText().isEmpty() ){
            
            JOptionPane.showMessageDialog(null,"falta llenar los campos" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);            
            //ta.rellenarTablaAula();
            return ;
            
        } else {            
            boolean isNumerico = this.txtNumero.getText().chars().allMatch( Character::isDigit );
            boolean isNumerico1 = this.txtCapacidad.getText().chars().allMatch( Character::isDigit );
            
            if(isNumerico != false && isNumerico1 != false) {              
                boolean isNumero = false;                                                                                                                                
                a1= ta.buscarAulaNumero(Integer.parseInt(this.txtNumero.getText()));  
                
                if(a1 !=null ){                                    
                    JOptionPane.showMessageDialog(null,"El aula ya existe" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);                                                                     
                }else {                                                                                                                                                                                                                                                 
                    a.setNumAula(Integer.parseInt(this.txtNumero.getText()));
                    a.setCapacidad(Integer.parseInt(this.txtCapacidad.getText()));
                    ia.insertar(a);
                    JOptionPane.showMessageDialog(null,"Se guardo correctamente" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);   
                    Stage stage =(Stage) this.btnGuardar.getScene().getWindow();
                    stage.close();                               //return; 
                }                                                                                                                 
        }else{                
            JOptionPane.showMessageDialog(null,"Debe ingresar numeros " ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
          }          
        }   
    }

    @FXML
    private void actionEvent(ActionEvent e) {
        Object evento = e.getSource(); 
                          
        if(evento.equals(btnEliminar)){            
          eliminarAula(); 
            //JOptionPane.showMessageDialog(null,"llama al metodo eliminar"+ nombre,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
                   
        }else if(evento.equals(btnModificar)){               
            modificarAula();                             
        }  
    }
    
    public boolean insertar(Aula a){
         
        String query = "INSERT INTO aula(numAula,capacidad)" 
                + "VALUES(' "
                + a.getNumAula()
                + " ',' " 
                +  a.getCapacidad()              
                + "' )";
          
        TransaccionesBD trscns = new TransaccionesBD();
        boolean exito = trscns.ejecutarQuery(query);    
        
       // JOptionPane.showMessageDialog(null,"entro en metodo insertar REPO" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
        return exito;
    }
    
    public void refrech(){
         TablaAula ta = new TablaAula();
         ta.rellenarTablaAula();
        
        try {               
            DefaultTableModel modelo = new DefaultTableModel();
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al refrescar", "Error",JOptionPane.WARNING_MESSAGE);
            return; 
        } 
         
        
    }
     
}
