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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class IngresoMateria implements Initializable {
    private ObservableList<Profesor>materias= FXCollections.observableArrayList();

    @FXML
    private TextField txtNombre;
    
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnModificar;
    @FXML
    private ComboBox<String> comboCuatrimestre;
    @FXML
    private ComboBox<Integer> comboCursada;
    @FXML
    private ComboBox<Carrera> comboCarrera;
    
    ArrayList<Carrera> listaCarrera  = new ArrayList<>();
     ArrayList<Materia> listaCuatrimestre  = new ArrayList<>();
    IngresoMateria im ;
    //
    Long id = null; 
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        im = new IngresoMateria();                      
        listaCarrera= im.seleCarrera();      
        comboCarrera.getItems().addAll(listaCarrera); 
        //listaCuatrimestre=im.seleCuatrimestre();
        comboCuatrimestre.getItems().addAll("Primer_cuatrimestre","Segundo_cuatrimestre");
        comboCursada.getItems().addAll(1,2,3,4,5);
    }  
    
    
    public void rellenarTablaMateria(){
         materias.clear();
         RepoProfesor repoProf=new RepoProfesor();
         ObservableList<Profesor> resultProfesores = repoProf.buscarTodos();
         materias.setAll(resultProfesores);
         int resultados = resultProfesores.size();//cuantos resultados hay en la lista
        //lblResultado.setText("resultado :" +resultados);
    }
    public void vaciarCampos(){        
        //txtId.setText("");
        txtNombre.setText("");              
        comboCuatrimestre.setValue(null);
        comboCursada.setValue(null);   
        comboCarrera.setValue(null); 
    }
    
      public void traerMateria(Materia m){
                                  
        if(m !=null){                          
                 //JOptionPane.showMessageDialog(null,"entro al if del metodo traer" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);                      
            this.txtNombre.setText(m.getNombre());           
            this.comboCuatrimestre.getSelectionModel().select(m.getCuatrimestre());
            //this.comboCursada.getSelectionModel().select(m.getAño());
            this.comboCursada.setValue(m.getAño());
            this.comboCarrera.getSelectionModel().select(m.getCarrera());
        }
        
       String query = "select id from materia where nombre='"+ m.getNombre()+"'";//con el nombre seleccionado obtengo el id para trabajar
        
           TransaccionesBD trscns = new TransaccionesBD();
            ResultSet rs = trscns.realizarConsulta(query);
            try{
              if(rs.next()){                   
                     //JOptionPane.showMessageDialog(null, "entro en el if para asignar", "Error",JOptionPane.WARNING_MESSAGE);                                  
                 m.setId(rs.getLong("id"));
                 this.id=m.getId();
                                                                                                                                 }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleccion de materia" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }   
    }
          
    private void eliminarMateria() { 
                       
         String nombre = this.txtNombre.getText(); 
         TablaMateria tm = new TablaMateria();
         Materia m= new Materia();      
         Carrera carrera = new Carrera();                             
                                
        if( nombre.equals("") ||  this.comboCarrera.getValue()== null ||
                   this.comboCuatrimestre.getValue()==null || this.comboCursada.getValue()==null){
               
            JOptionPane.showMessageDialog(null,"falta seleccionar materia" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);            
            rellenarTablaMateria();
            return ;
                    
         } else{                                                                  
                int opcion = JOptionPane.showConfirmDialog(null, 
                             "Desea eliminar " + "el registro ? " , "confirmacion" ,JOptionPane.YES_NO_OPTION,2);

                if(opcion== JOptionPane.YES_OPTION  ){

                    String query ="DELETE FROM `materia` WHERE `materia`.`nombre` ='" + this.txtNombre.getText()+"' ";// traer() trae id
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
        }                                           
    }
      
    private void modificarMateria() {
                  
        // IngresoMateria im= new IngresoMateria();
         TablaMateria tm =new TablaMateria();                                                              
         String nombre = this.txtNombre.getText();          
         Materia m;        
         //System.out.println("nombre"+ " el id es = " + m.getId());
         
        if( nombre.equals("") || this.comboCuatrimestre.getValue()== null || 
                this.comboCursada.getValue()== null || this.comboCarrera.getValue()==null ){
               
            JOptionPane.showMessageDialog(null,"falta llenar los campos" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);            
            rellenarTablaMateria();
            return ;
                    
        } else{                                      
                    String query = "UPDATE materia SET nombre = '" 
                            + this.txtNombre.getText()+"',cuatrimestre = '"
                            + this.comboCuatrimestre.getValue()+"', año='" 
                            + this.comboCursada.getValue()+ "', carrera='"
                            + comboCarrera.getValue()+ "' WHERE id = '"+ id +"' " ;
                    
                                                                                                                                                                                                                                                                                                                                                                                    
                              /*String query= "UPDATE materia SET nombre='" + this.txtNombre.getText() + "',cuatrimestre ='"+ this.comboCuatrimestre.getValue()+"', año='"+this.comboCursada.getValue()+"',carrera='" + this.comboCarrera.getValue() +"' WHERE id='" + materia.getId()+"'";*/
 
                              TransaccionesBD trscns = new TransaccionesBD();
                              boolean exito = trscns.ejecutarQuery(query);
                              JOptionPane.showMessageDialog(null,"alumno modificado " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                             // vaciarCampos();
                              Stage stage =(Stage) this.btnGuardar.getScene().getWindow();
                              stage.close();                               
                                      
                    }/*else{
                         JOptionPane.showMessageDialog(null,"El nombre debe tener al menos 4 caracteres " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                         
                        }   */                                         
             
        
                                                                                                                           
    }
    
    @FXML
    private void actionEvent(ActionEvent e) {
        Object evento = e.getSource();// metodo p/ saber en que nodo se aplico el evento , donde esta posicionado
        
        if(evento.equals(btnEliminar)){
            
            eliminarMateria();      
            //JOptionPane.showMessageDialog(null,"llama al metodo eliminar"+ nombre,"aviso" , JOptionPane.INFORMATION_MESSAGE);  
        
        }else if(evento.equals(btnModificar)){               
            modificarMateria();                             
        }           
    }

    @FXML
    private void guardarMateria(ActionEvent event) {
      
         IngresoMateria im= new IngresoMateria();
         TablaMateria tm=new TablaMateria();                     
         long id ; //Long.parseLong(this.txtId.getText())   
         
         id=im.id_incrementable();                    
         String nombre = this.txtNombre.getText(); 
         
         Materia materia= new Materia();
         Carrera carrera = new Carrera();      
         //LocalDate fecha = this.dateFecha.getValue(); 
                      
        if( nombre.equals("") ||  this.comboCarrera.getValue()==null || this.comboCuatrimestre.getValue()== null ||
             this.comboCursada.getValue()==null ){
            
            JOptionPane.showMessageDialog(null,"falta llenar los campos" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);            
            rellenarTablaMateria();
            return ;
            
         }else {           
                materia.setNombre(nombre); 
                carrera.setNombre(comboCarrera.getValue().toString());
                materia.setCarrera(carrera);
                materia.setCuatrimestre(comboCuatrimestre.getValue());
                materia.setAño(comboCursada.getValue());
                                                            
                im.insertar(materia);
                JOptionPane.showMessageDialog(null,"Se guardo correctamente" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);   
                Stage stage =(Stage) this.btnGuardar.getScene().getWindow();
                stage.close();                               //return;                                        
            }                                                                                        
    }

    
     public long id_incrementable(){
           
           long id=0;       
           String query="SELECT MAX(id) FROM materia";              
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
    
      public boolean insertar(Materia materia){
         
        String query = "INSERT INTO materia(nombre,cuatrimestre,año,carrera)" 
                + "VALUES(' "
                
                +  materia.getNombre()                            
                + " ',' " 
                + materia.getCuatrimestre() 
                + " ',' " 
                + materia.getAño()
                + " ',' " 
                + materia.getCarrera()
              
                + "' )";
          
        TransaccionesBD trscns = new TransaccionesBD();
        boolean exito = trscns.ejecutarQuery(query);    
        
       // JOptionPane.showMessageDialog(null,"entro en metodo insertar REPO" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
        return exito;
    }
      
      public ArrayList<Carrera> seleCarrera(){
        
        Carrera carrera = new Carrera();
        ArrayList<Carrera> lista  = new ArrayList<>();        
        String query = "select nombre FROM carrera where 1 ORDER BY nombre ASC;";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
            
            try{
                while(rs.next()){
                   //JOptionPane.showMessageDialog(null, "entro en el while ", "Error",JOptionPane.WARNING_MESSAGE);
                  // long idBusqueda=rs.getLong("id");                                                                      
                    carrera = new Carrera();
                    //carrera.setId(rs.getLong("id"));
                    carrera.setNombre(rs.getString("nombre"));
                    //System.out.println("la carrera es " + carrera.getNombre_carrera());
                                       
                    lista.add(carrera);                     
                }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleCarrera" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                  
       // System.out.println("la carrera es " + carrera.getNombre_carrera());
       return lista;             
    }
      
      /*public ArrayList<Materia> seleCuatrimestre(){
        
        Materia materia = new Materia();
        ArrayList<Materia> listaMateria  = new ArrayList<>();        
        String query = "select cuatrimestre FROM materia where nombre ='"+this.txtNombre.getText()+"';";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
         
            
            try{
                while(rs.next()){
                   //JOptionPane.showMessageDialog(null, "entro en el while ", "Error",JOptionPane.WARNING_MESSAGE);
                  // long idBusqueda=rs.getLong("id");                                                                      
                    //carrera = new Carrera();
                    //carrera.setId(rs.getLong("id"));
                    materia.setCuatrimestre(rs.getString("cuatrimestre"));
                    //System.out.println("la carrera es " + carrera.getNombre_carrera());
                                       
                    listaMateria.add(materia);                     
                }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleCarrera" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                  
         System.out.println("el cuatrimestre es : " + materia.getCuatrimestre());
       return listaMateria;             
    }*/
    
    
    
    
}
