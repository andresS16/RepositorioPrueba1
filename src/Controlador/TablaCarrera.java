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
import modelo.Carrera;
import modelo.Materia;

/**
 * FXML Controller class
 *
 * @author Silva
 */
public class TablaCarrera implements Initializable {
    private ObservableList<Carrera> carreraLista = FXCollections.observableArrayList();

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
    private Button bttNuevo;
    @FXML
    private TableColumn<Carrera, String> colNombre;
    @FXML
    private TableColumn<Carrera,Integer> colAño;
    private TableColumn<Carrera, Materia> colMateria;
    @FXML
    private TableView<Carrera> tblCarrera;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         configurarVentana();
        rellenarTablaCarrera();
          chcBuscar.getItems().addAll("nombre","cursada");
        // TODO
    }  
     public void configurarVentana(){
        
        //colNumMateria.setCellValueFactory(new PropertyValueFactory<>("numMateria"));//cada col. se asigna setvalufactory
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        //colNombre.setCellValueFactory(new PropertyValueFactory<>("carrera"));
        //colMateria.setCellValueFactory(new PropertyValueFactory<>("materia"));
        colAño.setCellValueFactory(new PropertyValueFactory<>("año"));
       // colCuatrimestre.setCellValueFactory(new PropertyValueFactory<>("cuatrimestre"));        
        //colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tblCarrera.setItems(carreraLista);   
    }
     
      public void rellenarTablaCarrera(){
        carreraLista.clear();
        // RepoProfesor repoProf=new RepoProfesor();
        //ObservableList<Profesor> resultProfesores = repoProf.buscarTodos();
        TablaCarrera tabla= new TablaCarrera();
        ObservableList<Carrera> resultCarrera =tabla.buscarTodos();
       
        System.out.println("contenido de lista "+ resultCarrera.get(0));
        carreraLista.setAll(resultCarrera);
        int resultados = resultCarrera.size();//cuantos resultados hay en la lista
       //lblResultado.setText("resultado :" + resultados);
    }
    
    
    @FXML
    private void refrescar(ActionEvent event) {
        
        rellenarTablaCarrera();      
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/IngresoCarrera.fxml"));//carga una gerarqui DE OBJETOS       
            Parent root = loader.load();//carga el parent              
            IngresoCarrera controlador = loader.getController();//carga el controlador de esa vista           
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
        
        Carrera c = this.tblCarrera.getSelectionModel().getSelectedItem();
        
        if(c ==null){            
            JOptionPane.showMessageDialog(null, "seleccion nula ", "Error",JOptionPane.WARNING_MESSAGE);   
            
        }else{  
            
          try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/IngresoCarrera.fxml"));//carga una gerarqui DE OBJETOS        
            Parent root = loader.load();//carga el parent            
            IngresoCarrera controlador = loader.getController();//carga el controlador de esa vista                     
            //controlador.initAttributes(personas);
            controlador.traerCarrera(c);
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
       
    public ObservableList<Carrera> buscarTodos(){
           
            String query = "SELECT * FROM carrera";
            TransaccionesBD trscns = new TransaccionesBD(); 
            ResultSet rs = trscns.realizarConsulta(query);     
            ObservableList<Carrera> carreras = FXCollections.observableArrayList();
       
       try{
                while(rs.next()){
                    
                    Materia materia= new Materia();
                    Carrera carrera = new Carrera();                     
                    carrera.setNombre(rs.getString("nombre"));
                   // materia.setNombre(rs.getString("materia"));
                    carrera.setMateria(materia);
                    carrera.setAño(rs.getInt("año"));                                                                                                                                                                                                                                                                                                               ;                             
                    carreras.add(carrera);                                  
                }  
                rs.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error al buscar objeto carrera" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
       return carreras;
       } 

    @FXML
    private void buscarCarrera(ActionEvent event) {
        
        Carrera p =null;
        long id=0;                      
        String modoBusqueda= chcBuscar.getValue();
        TablaCarrera tabla = new TablaCarrera();
       // System.out.println(" el modo de busqueda es  : " +modoBusqueda);           
        ObservableList<Carrera> carreraSeleccion = FXCollections.observableArrayList();
        
        if(modoBusqueda==null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar metodo de busca ", "Error",JOptionPane.WARNING_MESSAGE); 
              
        }else{
            
           switch(modoBusqueda){ // busqueda por apellido , materia o carrera
                                
                case "nombre":              
                    String carreraBusqueda = textBuscar.getText();
                   // boolean estaVacia =tabla.buscarProfeApellido(apellidoBusqueda).isEmpty();               
                    if(textBuscar.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "debe ingresar nombre de materia " , "Error",JOptionPane.WARNING_MESSAGE); 

                    }else if(tabla.buscarCarreraNombre(carreraBusqueda)!= null){
                         carreraLista.clear();
                         carreraSeleccion.addAll(tabla.buscarCarreraNombre(carreraBusqueda));
                         
                    } else{
                    JOptionPane.showMessageDialog(null, "no se encontro materia " , "Error",JOptionPane.WARNING_MESSAGE);
                    }                                      
                    break; 
                                 
                case "cursada":                   
                    String bandera = textBuscar.getText() ;
                    boolean isNumero = bandera.chars().allMatch( Character::isDigit ); 
                    
                    if(textBuscar.getText().isEmpty()){
                         JOptionPane.showMessageDialog(null, "debe ingresar cursada ", "Error",JOptionPane.WARNING_MESSAGE); 

                    }else if(textBuscar.getText()!=null){

                        if(isNumero){                        
                            int anioBusqueda = Integer.parseInt(textBuscar.getText());                    
                            tabla.buscarCarreraAnio(anioBusqueda);

                            if(!tabla.buscarCarreraAnio(anioBusqueda).isEmpty()){
                                carreraSeleccion.addAll(tabla.buscarCarreraAnio(anioBusqueda)); 
                                carreraLista.clear();
                            }else{
                                JOptionPane.showMessageDialog(null, "no se encontro año", "Error",JOptionPane.WARNING_MESSAGE);  
                                System.out.println("el año es :"+ anioBusqueda);
                            }                                    
                        }else{
                                JOptionPane.showMessageDialog(null, "debe ingresar numeros ", "Error",JOptionPane.WARNING_MESSAGE);  
                        }                                         
                    }                                                
                break;
                    
                default:
                     JOptionPane.showMessageDialog(null, "Modo de busqueda incorrecto ", "Error",JOptionPane.WARNING_MESSAGE);
        }          
        carreraLista.addAll(carreraSeleccion);
        int resultado = carreraSeleccion.size();
        lblResultado.setText("Resultados " + resultado);         
        }
        
    }
    
    
    public Carrera buscarCarreraNombre(String nombreCarrera){
         
        //JOptionPane.showMessageDialog(null, "Ingreso en metodo buscarprofeApellido", "Error",JOptionPane.WARNING_MESSAGE);            
        String query = "SELECT * FROM carrera WHERE nombre LIKE '%" + nombreCarrera + "%'";           
        TransaccionesBD trscns =new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);       
        Carrera c=null;  
            //JOptionPane.showMessageDialog(null, "entro en el metodo buscarApellido ", "Error",JOptionPane.WARNING_MESSAGE);           
        try{
            while(rs.next()){
                     //JOptionPane.showMessageDialog(null, "entro en el while ", "Error",JOptionPane.WARNING_MESSAGE);                   
                    c=new Carrera();
                    //c.setId(rs.getLong("id"));
                    c.setNombre(rs.getString("nombre"));                  
                    c.setAño(rs.getInt("año"));                                    
                  // materiaNombre.add(m);  
                }           
           }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar carrera " + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
       
        return c;                        
    }
    
    public ObservableList<Carrera>buscarCarreraAnio(int anio){
         
        String query ="SELECT * FROM carrera WHERE año = " + anio;
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);  
         ObservableList<Carrera> anioLista = FXCollections.observableArrayList();
        Carrera c=null;
          
        try{
           while(rs.next()){              
               c=new Carrera();
               c.setId(rs.getLong("id"));
               c.setNombre(rs.getString("nombre"));
               c.setAño(rs.getInt("año")); 
               anioLista.add(c);
            }           
        }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar año " + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
    // JOptionPane.showMessageDialog(null, "saliendo de metodo buscar por id ", "Error",JOptionPane.WARNING_MESSAGE);       
    return anioLista ;                                  
    }
    
}
