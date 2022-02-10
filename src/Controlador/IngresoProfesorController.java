/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import modelo.Carrera;
import modelo.Materia;
import modelo.Profesor;

/**
 * FXML Controller class
 *
 * @author Silva
 */
public class IngresoProfesorController implements Initializable {
    private ObservableList<Profesor> profesores = FXCollections.observableArrayList();

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private ComboBox<Materia> comboMateria;
    @FXML
    private ComboBox<Carrera> comboCarrera;
    @FXML
    private DatePicker dateFecha;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnCancelar;
    Profesor profesor;
    Long id=null;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<Materia> listaMateria =FXCollections.observableArrayList();
        listaMateria.addAll(new Materia("Ingles"), 
                              new Materia("calculo1"),
                              new Materia("algoritmos"));       
          //comboMateria.getItems().addAll(listaMateria);
         comboMateria.setItems(listaMateria);        
         ObservableList<Carrera> listaCarrera =FXCollections.observableArrayList();
         listaCarrera.addAll(new Carrera("Ingenieria"), 
                              new Carrera("Medicina"),
                              new Carrera("Enfermeria"));       
          //comboMateria.getItems().addAll(listaMateria);
           comboCarrera.setItems(listaCarrera);  
        // TODO
    }  
    
    
    public void rellenarTablaProfesor(){
        profesores.clear();
         RepoProfesor repoProf=new RepoProfesor();
        ObservableList<Profesor> resultProfesores = repoProf.buscarTodos();
        profesores.setAll(resultProfesores);
        int resultados = resultProfesores.size();//cuantos resultados hay en la lista
        //lblResultado.setText("resultado :" +resultados);
    }
    public void vaciarCampos(){
        
        //txtId.setText("");
         txtNombre.setText("");
        txtApellido.setText("");
        comboMateria.setValue(null);
        comboCarrera.setValue(null);
        dateFecha.setValue(null);
    }

    private void actionEvent(ActionEvent event)  {
      
           JOptionPane.showMessageDialog(null,"Evento sin uso" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);          
        
    }

    @FXML
    private void guardarProfesor(ActionEvent event) throws SQLException{
        
         Profesor p= new Profesor();
         Profesor p1=new Profesor();
         
         RepoProfesor rep = new RepoProfesor();     
         long id ; //Long.parseLong(this.txtId.getText())   
         id=rep.id_incrementable();
         String nombre = this.txtNombre.getText(); 
         String apellido =this.txtApellido.getText(); 
         Materia materia= new Materia();
         Carrera carrera = new Carrera();      
         LocalDate fecha = this.dateFecha.getValue();  
         
           if(nombre.equals("") || apellido.isEmpty() || this.comboMateria.getValue()== null ||
                   this.comboCarrera.getValue()==null || fecha== null ){
               
                JOptionPane.showMessageDialog(null,"falta llenar los campos" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
             
                rellenarTablaProfesor();
                                 return ;
                    //}else if(txtId.getText().trim().equals("")){ 
             } else if(id >0 ) { 
                  
                       // id=rep.id_incrementable();
                        p1=rep.buscarProfesor(id);                                                                                                    
                        p.setId(id);
                                
                       if(p1 !=null && p !=null){
                                     
                            JOptionPane.showMessageDialog(null,"El usuario ya existe" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);                                                                     
                                     
                         }else if(p1 ==null && p !=null){
                                                                                                                                                                                                
                              // id=Long.parseLong(txtId.getText());
                                p.setId(id);                             
                                p.setNombre(nombre);
                                p.setApellido(apellido);
                                materia.setNombre_Materia(comboMateria.getValue().toString());
                                p.setMateria(materia);
                                carrera.setNombre_carrera(comboCarrera.getValue().toString());
                                p.setCarrera(carrera);
                                p.setFecha(fecha);
                                rep.insertar(p); 
                                
                                JOptionPane.showMessageDialog(null,"Se guardo correctamente" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);   
                                Stage stage =(Stage) this.btnGuardar.getScene().getWindow();
                                stage.close();
                                //return; 
                            }                                                                                                                                                         
                  } 
                                                                                                                                                    
             //vaciarCampos();
             //habilitarCampos() 
                    
    }
           
    public void traer(Profesor p){
        
        JOptionPane.showMessageDialog(null,"el id seleccionado es  :"+p.getId() ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
        IngresoProfesorController nn = new IngresoProfesorController();
       
     if(p !=null){         
             //this.txtId.setText(p.getId()+ "");
             JOptionPane.showMessageDialog(null,"entro al if del metodo traer" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);   
             this.id = p.getId();
             this.txtNombre.setText(p.getNombre());
             this.txtApellido.setText(p.getApellido());
             this.comboMateria.getSelectionModel().select(p.getMateria());
             this.comboCarrera.getSelectionModel().select(p.getCarrera());
             this.dateFecha.setValue(p.getFecha());                          
        } 
   
    }
    @FXML
    private void eliminarProfesor(ActionEvent event) {
   
        RepoProfesor re =new RepoProfesor();
        IngresoProfesorController nn= new IngresoProfesorController();        
        //JOptionPane.showMessageDialog(null,"el id DEL METOD ELIMINAR  :" + id,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
         
         re.eliminar(id);          
        vaciarCampos();
        JOptionPane.showMessageDialog(null,"Se elimino correctamente" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);   
                                Stage stage =(Stage) this.btnGuardar.getScene().getWindow();
                                stage.close();
    }  
    
}
