/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Carrera;
import modelo.Materia;
import modelo.Profesor;
//import modelo.Carrera;
//import modelo.Materia;
//import modelo.Profesor;

/**
 * FXML Controller class
 *
 * @author Silva
 */
public class TablaProfesor implements Initializable {

    private ObservableList<Profesor> profesores = FXCollections.observableArrayList();
   
     @FXML
    private TableColumn<Profesor,Long > colId;
    @FXML
    private TableColumn<Profesor,String> colNombre;
    @FXML
    private TableColumn<Profesor, String> colApellido;
    @FXML
    private TableColumn<Profesor, Materia> colMateria;
    @FXML
    private TableColumn<Profesor, Carrera> colCarrera;
    @FXML
    private TableColumn<Profesor, LocalDate> colFecha;
    @FXML
    private TableView<Profesor> tblProfesores;
    //pegado
    
    @FXML
    private TextField textBuscar;
    @FXML
    private ChoiceBox<String> chcBuscar;
    @FXML
    private TextField lblResultado;
    
   // private ComboBox<Materia> comboMateria;
    //private ComboBox<Carrera> comboCarrera;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnRefrescar;
    @FXML
    private Button bttNuevo;
    Profesor profesor;
       
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chcBuscar.getItems().addAll("id","apellido","materia","carrera"); 
        configurarVentana();
        rellenarTablaProfesor();                     
    }   
    
    public void configurarVentana(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));//cada col. se asigna setvalufactory
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colMateria.setCellValueFactory(new PropertyValueFactory<>("materia"));
        colCarrera.setCellValueFactory(new PropertyValueFactory<>("carrera"));
         colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tblProfesores.setItems(profesores);   
    }
    
    public void rellenarTablaProfesor(){
        profesores.clear();
         RepoProfesor repoProf=new RepoProfesor();
        ObservableList<Profesor> resultProfesores = repoProf.buscarTodos();
        profesores.setAll(resultProfesores);
        int resultados = resultProfesores.size();//cuantos resultados hay en la lista
        //lblResultado.setText("resultado :" +resultados);
    }

    @FXML
    private void seleccionar(MouseEvent event) {
        Profesor p = this.tblProfesores.getSelectionModel().getSelectedItem();
        Materia m= new Materia();
                                
         try {
             
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/IngresoProfesor.fxml"));//carga una gerarqui DE OBJETOS        
            Parent root = loader.load();//carga el parent            
            IngresoProfesor controlador = loader.getController();//carga el controlador de esa vista                     
            //controlador.initAttributes(personas);
            controlador.traer(p);
            controlador.seleCarrera();
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


    @FXML
    private void refrescar(ActionEvent event) {
        rellenarTablaProfesor();
        
        try {               
            DefaultTableModel modelo = new  DefaultTableModel();
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al refrescar", "Error",JOptionPane.WARNING_MESSAGE);
            return; 
        }                 
    }

    @FXML
    private void nuevo(ActionEvent event) {
        
        try {
             
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/IngresoProfesor.fxml"));//carga una gerarqui DE OBJETOS       
            Parent root = loader.load();//carga el parent             
            IngresoProfesor controlador = loader.getController();//carga el controlador de esa vista           
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

    public Profesor getProfesor() {
        return profesor;
    }

   
    
    public Profesor buscarProfesor(long idBusqueda){
            String query ="SELECT * FROM profesor4 WHERE id = " + idBusqueda;
            TransaccionesBD trscns = new TransaccionesBD();
            ResultSet rs = trscns.realizarConsulta(query);
            
            Profesor profesor=null;
          
            try{
                if(rs.next()){
                    idBusqueda=rs.getLong("id");            
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                   LocalDate fecha = rs.getDate("fecha").toLocalDate();
        
                    Materia materia= new Materia();
                    materia.setNombre_Materia(rs.getString("materia"));
                    Carrera carrera = new Carrera();
                    carrera.setNombre_carrera(rs.getString("carrera"));
                     
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
     JOptionPane.showMessageDialog(null, "saliendo de metodo buscar por id ", "Error",JOptionPane.WARNING_MESSAGE);       
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
                    materia.setNombre_Materia(rs.getString("materia"));
                    Carrera carrera = new Carrera();
                    carrera.setNombre_carrera(rs.getString("carrera"));
                     
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
            JOptionPane.showMessageDialog(null, "entro en el metodo buscarMateria ", "Error",JOptionPane.WARNING_MESSAGE);
           
          try{
                while(rs.next()){
                     JOptionPane.showMessageDialog(null, "entro en el while ", "Error",JOptionPane.WARNING_MESSAGE);
                   long idBusqueda=rs.getLong("id");            
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    LocalDate fecha = rs.getDate("fecha").toLocalDate();
        
                    Materia materia= new Materia();
                    materia.setNombre_Materia(rs.getString("materia"));
                    Carrera carrera = new Carrera();
                    carrera.setNombre_carrera(rs.getString("carrera"));
                     
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
                    materia.setNombre_Materia(rs.getString("materia"));
                    Carrera carrera = new Carrera();
                    carrera.setNombre_carrera(rs.getString("carrera"));
                     
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

    @FXML
    private void buscarProfesor(ActionEvent event) {
        
           profesores.clear();
        String modoBusqueda = chcBuscar.getValue();
        TablaProfesor tabla = new TablaProfesor();
        System.out.println(" el id ingresado es : " +modoBusqueda);
       
        if(modoBusqueda.equals("id")){    //busqueda por id  
           long id=0;       
                 
            try{             
               id = Long.parseLong(textBuscar.getText());             
             }catch(Exception e){
                       JOptionPane.showMessageDialog(null, "Ingrese un id valido", "Error",JOptionPane.WARNING_MESSAGE);
                         return;                 
             }
           
             //RepoProfesor profeRepo = new RepoProfesor();
             //Profesor p = profeRepo.buscarProfesor(id);    
             Profesor p = tabla.buscarProfesor(id);
             JOptionPane.showMessageDialog(null, "metod buscarProfesor asigana valos a p  ", "Error",JOptionPane.WARNING_MESSAGE);
                
            if(p != null){
               profesores.add(p);
               lblResultado.setText("Resultado 1");                                
             }else{
                lblResultado.setText("Resultado 0");                                             
             }
              return ;   
         }
       
        //RepoProfesor profeRepo = new RepoProfesor();
        ObservableList<Profesor> profeSeleccion = FXCollections.observableArrayList();
        
     switch(modoBusqueda){ // busqueda por apellido , materia o carrera
            
            case "apellido":              
                String apellidoBusqueda = textBuscar.getText();
                
                profeSeleccion.addAll(tabla.buscarProfeApellido(apellidoBusqueda));             
                 //JOptionPane.showMessageDialog(null, "entro en case 1 " , "Error",JOptionPane.WARNING_MESSAGE);
                break;                 
            case "materia":
                String materiaBusqueda = textBuscar.getText();
                profeSeleccion.addAll(tabla.buscarProfeMateria(materiaBusqueda));                        
                break;                                          
            case "carrera":
                String carreraBusqueda = textBuscar.getText();
                profeSeleccion.addAll(tabla.buscarProfeCarrera(carreraBusqueda));
                break;              
            default:
                 JOptionPane.showMessageDialog(null, "Modo de busqueda incorrecto ", "Error",JOptionPane.WARNING_MESSAGE);
        }
        
        profesores.addAll(profeSeleccion);
        int resultado = profeSeleccion.size();
        lblResultado.setText("Resultados " + resultado);  
        
        
        
    }
       
}
