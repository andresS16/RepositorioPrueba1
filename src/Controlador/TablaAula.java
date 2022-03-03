/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Alumno;
import modelo.Aula;
import modelo.Carrera;
import modelo.Materia;

/**
 * FXML Controller class
 *
 * @author Silva
 */
public class TablaAula implements Initializable {
     private ObservableList<Aula> aulas = FXCollections.observableArrayList(); 

    @FXML
    private TextField textBuscar;
    @FXML
    private ChoiceBox<String> chcBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private TextField lblResultado;
    @FXML
    private Button btnRefrescar;
    @FXML
    private Button btnNuevo;
    @FXML
    private TableColumn<Aula, Integer> colNumAula;
    @FXML
    private TableColumn<Aula,Integer> colCapacidad;
    @FXML
    private TableView<Aula> tblAula;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chcBuscar.getItems().addAll("capacidad","numeroAula");
        configurarVentana();
         rellenarTablaAula();  
        
      // TODO
    }  
    public void configurarVentana(){       
        colNumAula.setCellValueFactory(new PropertyValueFactory<>("numAula"));//cada col. se asigna setvalufactory
        colCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        tblAula.setItems(aulas);                
    }
    
    public void rellenarTablaAula(){
         
        aulas.clear();                   
        TablaAula tabla= new TablaAula();
        ObservableList<Aula> resultado=tabla.buscarTodos();            
        aulas.setAll(resultado);
        int resultados=resultado.size();       
        lblResultado.setText("resultado :" + resultados);                    
        //cuantos resultados hay en la lista                                                     
    }
    
    public ObservableList<Aula> buscarTodos(){
                         
        String query = "SELECT * FROM aula";
        TransaccionesBD trscns = new TransaccionesBD(); 
        ResultSet rs = trscns.realizarConsulta(query);
        Aula a =null;                 
        ObservableList<Aula>aulas = FXCollections.observableArrayList();
       
       try{
            while(rs.next()){  
                
                a=new Aula();
                a.setNumAula(rs.getInt("numAula"));
                a.setCapacidad(rs.getInt("capacidad"));                                                
                aulas.add(a);                                      
                }  
                rs.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error al buscar aula" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
       return aulas;
       }

    
    @FXML
    private void buscar(ActionEvent event) {
        
        Aula a =null;
                          
        String modoBusqueda= chcBuscar.getValue();
        TablaAula tabla = new TablaAula();
       // System.out.println(" el modo de busqueda es  : " +modoBusqueda);           
        ObservableList<Aula> aulaSeleccion = FXCollections.observableArrayList();
        
        if(modoBusqueda==null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar metodo de busca ", "Error",JOptionPane.WARNING_MESSAGE); 
              
        }else{
            
           switch(modoBusqueda){ // busqueda por apellido , materia o carrera
                                       
                case "capacidad":
                    
                    int aulaBusqueda = Integer.parseInt(textBuscar.getText());

                        if(textBuscar.getText().isEmpty()){
                             JOptionPane.showMessageDialog(null, "debe ingresar capacidad solicitada " , "Error",JOptionPane.WARNING_MESSAGE); 

                         }else if(!tabla.buscarAulaCapacidad(aulaBusqueda).isEmpty()){
                             aulas.clear();
                             aulaSeleccion.addAll(tabla.buscarAulaCapacidad(aulaBusqueda));  

                         }  else{
                         JOptionPane.showMessageDialog(null, "no se encontro aula " , "Error",JOptionPane.WARNING_MESSAGE);
                         }


                     break;
                     
                case "numeroAula":               
                    String string = textBuscar.getText() ;
                    boolean isNumero = string.chars().allMatch( Character::isDigit );                                                                                               
                    if(textBuscar.getText().isEmpty()){
                         JOptionPane.showMessageDialog(null, "debe ingresar dni ", "Error",JOptionPane.WARNING_MESSAGE); 

                         }else if(textBuscar.getText()!=null){

                                    if(isNumero){                        
                                        int numAula = Integer.parseInt(textBuscar.getText());                    
                                        a=tabla.buscarNumAula(numAula);

                                        if(a!=null){
                                             aulaSeleccion.addAll(tabla.buscarNumAula(numAula)); 
                                             aulas.clear();
                                        }else{
                                             JOptionPane.showMessageDialog(null, "no se encontro id ", "Error",JOptionPane.WARNING_MESSAGE);  
                                         }                                    
                                    }else{
                                        JOptionPane.showMessageDialog(null, "debe ingresar numeros ", "Error",JOptionPane.WARNING_MESSAGE);  
                                      }                                         
                                }                                                
                    break;       
                    
                default:
                     JOptionPane.showMessageDialog(null, "Modo de busqueda incorrecto ", "Error",JOptionPane.WARNING_MESSAGE);
        }
           
        aulas.addAll(aulaSeleccion);
        int resultado = aulaSeleccion.size();
        lblResultado.setText("Resultados " + resultado);  
        
        }   
        
        
        
    }
    
    public ObservableList<Aula> buscarAulaCapacidad(int capacidad){
        
        
        int limSupConsulta =2 + capacidad;
         //int limInfConsulta = capacidad ;
         System.out.println("limite superior :" + limSupConsulta );
         System.out.println("limite inferior : " + capacidad );
            
        String query = "select numAula ,capacidad from aula where capacidad between '"+capacidad+"' and '"+limSupConsulta+"' order by capacidad";
            
        TransaccionesBD trscns =new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
        Aula aula =null;            
        ObservableList<Aula> aulaCapacidad = FXCollections.observableArrayList();
           // JOptionPane.showMessageDialog(null, "entro en el metodo buscarMateria ", "Error",JOptionPane.WARNING_MESSAGE);          
        try{
            
            while(rs.next()){
                   // JOptionPane.showMessageDialog(null, "entro en el while ", "Error",JOptionPane.WARNING_MESSAGE);
                //long idBusqueda=rs.getLong("id");  
                int numAula = rs.getInt("numAula");
                int capacidadAula = rs.getInt("capacidad");

               aula = new Aula();
               aula.setNumAula(numAula);
               aula.setCapacidad(capacidadAula);
               aulaCapacidad.add(aula);                               
                }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar aula" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
          
            return aulaCapacidad;                        
    }
    
    public Aula buscarNumAula(int numBusqueda){
         
        String query ="SELECT * FROM aula WHERE numAula= " + numBusqueda;
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);           
        Aula aula =null;
          
        try{
            if(rs.next()){
                //idBusqueda=rs.getLong("id");
               int numAula = rs.getInt("numAula");
                int capacidadAula = rs.getInt("capacidad");

               aula = new Aula();
               aula.setNumAula(numAula);
               aula.setCapacidad(capacidadAula);
                             
                }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar objeto profesor" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
    // JOptionPane.showMessageDialog(null, "saliendo de metodo buscar por id ", "Error",JOptionPane.WARNING_MESSAGE);       
    return aula;   
    
    } 
       

    @FXML
    private void refrescar(ActionEvent event) {
         rellenarTablaAula();
        
        try {               
            DefaultTableModel modelo = new DefaultTableModel();
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al refrescar", "Error",JOptionPane.WARNING_MESSAGE);
            return; 
        } 
    }

    @FXML
    private void nuevo(ActionEvent event) {
        
         try {             
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/IngresoAula.fxml"));//carga una gerarqui DE OBJETOS       
            Parent root = loader.load();//carga el parent             
            IngresoAula controlador = loader.getController();//carga el controlador de esa vista           
            //controlador.initAttributes(personas);            
            Scene scene = new Scene(root);
            Stage stage = new Stage();                    
            stage.initModality(Modality.APPLICATION_MODAL);//modal : hasta que no termine el no me deje
            stage.setScene(scene);
            stage.showAndWait();
                                 
        } catch (IOException ex) {
            
             Alert alert=new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText(null);
             alert.setTitle("Error");
             alert.setContentText(ex.getMessage());
             alert.showAndWait();  
        } 
    }

    @FXML
    private void seleccionar(MouseEvent event) {
            
       Aula a = this.tblAula.getSelectionModel().getSelectedItem();
        
        if(a ==null){            
            JOptionPane.showMessageDialog(null, "seleccion nula ", "Error",JOptionPane.WARNING_MESSAGE);   
            
        }else{  
            
          try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/IngresoAula.fxml"));//carga una gerarqui DE OBJETOS        
            Parent root = loader.load();//carga el parent            
            IngresoAula controlador = loader.getController();//carga el controlador de esa vista                     
            //controlador.initAttributes(personas);
            controlador.traerAula(a);
            //controlador.seleCarrera();
            Scene scene = new Scene(root);
            Stage stage = new Stage();                                  
            stage.initModality(Modality.APPLICATION_MODAL);//modal : hasta que no termine el no me deje
            stage.setScene(scene);            
            stage.showAndWait();  
            
          }catch (IOException ex) {
              
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();  
          }   
        
        }
    }
    
    public Aula buscarAulaNumero(int numBusqueda){
         
        String query ="SELECT * FROM aula WHERE numAula = " + numBusqueda;
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);           
        Aula a =null;
          
        try{
            if(rs.next()){
                //idBusqueda=rs.getLong("id");
                a=new Aula();
                a.setNumAula(rs.getInt("numAula"));
                a.setCapacidad(rs.getInt("capacidad"));
                              
                }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar numero de aula " + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
    // JOptionPane.showMessageDialog(null, "saliendo de metodo buscar por id ", "Error",JOptionPane.WARNING_MESSAGE);       
    return a;                                  
    }
    
    
    public Aula buscarAulaID(Long id){
         
        String query ="SELECT * FROM aula WHERE id= " + id;
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);           
        Aula a =null;
          
        try{
            if(rs.next()){
                //idBusqueda=rs.getLong("id");
                a=new Aula();
                a.setId(rs.getInt("id"));
                a.setNumAula(rs.getInt("numAula"));
                a.setCapacidad(rs.getInt("capacidad"));
                              
                }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar numero de aula " + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
    // JOptionPane.showMessageDialog(null, "saliendo de metodo buscar por id ", "Error",JOptionPane.WARNING_MESSAGE);       
    return a;                                  
    }
    
    public void refrescada() {
         rellenarTablaAula();
        
        try {               
            DefaultTableModel modelo = new DefaultTableModel();
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al refrescar", "Error",JOptionPane.WARNING_MESSAGE);
            return; 
        } 
    }
    
 
   
}
