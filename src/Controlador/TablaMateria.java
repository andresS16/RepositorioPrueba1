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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
public class TablaMateria implements Initializable {
    
    private ObservableList<Materia> materiasLista = FXCollections.observableArrayList();

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
    private TableColumn<Materia, String> colNombre;   
    @FXML
    private TableColumn<Materia, Carrera> colCarrera;
    @FXML
    private TableColumn<Materia, Integer> colAño;
   
    @FXML
    private TableColumn<Materia, Integer> colCuatrimestre;  
   
    @FXML
    private TableView<Materia> tblMateria;
    
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarVentana();
        rellenarTablaMateria();
        // TODO
    }  
    
     public void configurarVentana(){
        
        //colNumMateria.setCellValueFactory(new PropertyValueFactory<>("numMateria"));//cada col. se asigna setvalufactory
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCarrera.setCellValueFactory(new PropertyValueFactory<>("carrera"));
        colAño.setCellValueFactory(new PropertyValueFactory<>("año"));
        colCuatrimestre.setCellValueFactory(new PropertyValueFactory<>("cuatrimestre"));        
        //colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tblMateria.setItems(materiasLista);   
    }
     
     
      public void rellenarTablaMateria(){
        materiasLista.clear();
        // RepoProfesor repoProf=new RepoProfesor();
        //ObservableList<Profesor> resultProfesores = repoProf.buscarTodos();
        TablaMateria tabla= new TablaMateria();
        ObservableList<Materia> resultMateria =tabla.buscarTodos();
        
        System.out.println("contenido de lista "+ resultMateria.get(0));
        materiasLista.setAll(resultMateria);
        int resultados = resultMateria.size();//cuantos resultados hay en la lista
        lblResultado.setText("resultado :" +resultados);
    }

    @FXML
    private void buscarProfesor(ActionEvent event) {
    }

    @FXML
    private void refrescar(ActionEvent event) {
    }

    @FXML
    private void nuevo(ActionEvent event) {
    }

    @FXML
    private void seleccionar(MouseEvent event) {
    }
    
    
     public ObservableList<Materia> buscarTodos(){
           
            String query = "SELECT * FROM materia";
            TransaccionesBD trscns = new TransaccionesBD(); 
            ResultSet rs = trscns.realizarConsulta(query);     
            ObservableList<Materia> materias = FXCollections.observableArrayList();
       
       try{
                while(rs.next()){
                    
                      Materia materia= new Materia();
                      Carrera carrera = new Carrera();
                      
                      
                      
                      materia.setNombre(rs.getString("nombre"));
                      carrera.setNombre(rs.getString("carrera"));
                      materia.setCarrera(carrera);
                      materia.setAño(rs.getInt("año"));
                      materia.setCuatrimestre(rs.getInt("cuatrimestre"));
                                                                                                                                                                                                           ;                             
                     materias.add(materia);     
                                 
                }  
                rs.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error al buscar objeto profesor" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
       return materias;
       } 
    
}
