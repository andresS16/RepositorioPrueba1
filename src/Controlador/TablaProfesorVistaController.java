/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.net.URL;
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
public class TablaProfesorVistaController implements Initializable {

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
            IngresoProfesorController controlador = loader.getController();//carga el controlador de esa vista                     
            //controlador.initAttributes(personas);
            controlador.traer(p);
            Scene scene = new Scene(root);
            Stage stage = new Stage(); 
            
            
            
            stage.initModality(Modality.APPLICATION_MODAL);//modal : hasta que no termine el no me deje
            stage.setScene(scene); 
            
            stage.showAndWait();  
            
            //Persona p = controlador.getPersona();
            /*if(p != null){
                this.personas.add(p);
                    if(p.getNombre().toLowerCase().contains(this.txtFiltrarNombre.getText().toLowerCase())){
                        this.filtroPersonas.add(p);
                    }
                this.tblPersona.refresh();     
            } */
            
        } catch (IOException ex) {
           Alert alert=new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText(null);
             alert.setTitle("Error");
             alert.setContentText(ex.getMessage());
             alert.showAndWait();  
        } 
         
         
         
         
     
        /*if(p !=null){         
             this.txtId.setText(p.getId()+ "");
             this.txtNombre.setText(p.getNombre());
             this.txtApellido.setText(p.getApellido());
             this.comboMateria.getSelectionModel().select(p.getMateria());
             this.comboCarrera.getSelectionModel().select(p.getCarrera());
             this.dateFecha.setValue(p.getFecha());                  
        } */
        
      
        
    }

    @FXML
    private void buscarFiltro(ActionEvent event) {
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
            
            IngresoProfesorController controlador = loader.getController();//carga el controlador de esa vista
            
            //controlador.initAttributes(personas);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();         
            
            stage.initModality(Modality.APPLICATION_MODAL);//modal : hasta que no termine el no me deje
            stage.setScene(scene);
            stage.showAndWait();
            
            //Persona p = controlador.getPersona();
            /*if(p != null){
                this.personas.add(p);
                    if(p.getNombre().toLowerCase().contains(this.txtFiltrarNombre.getText().toLowerCase())){
                        this.filtroPersonas.add(p);
                    }
                this.tblPersona.refresh();     
            } */
            
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



    
    
    
}
