/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private ChoiceBox<?> chcBuscar;
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
                      carrera.setAño(rs.getInt("año"));
                                                                                                                                                                                                                                                                                                                 ;                             
                     carreras.add(carrera);     
                                 
                }  
                rs.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error al buscar objeto carrera" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
       return carreras;
       } 
    
}
