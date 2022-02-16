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
import modelo.Carrera;
import modelo.Materia;
import modelo.Alumno;
import modelo.Profesor;
//import modelo.Profesor;

/**
 * FXML Controller class
 *
 * @author Silva
 */
public class TablaAlumno implements Initializable {    
    
    private ObservableList<Alumno> alumnos = FXCollections.observableArrayList();   
    @FXML
    private TableColumn<Alumno,String> colNombre;
    @FXML
    private TableColumn<Alumno, String> colApellido;
    @FXML
    private TableColumn<Alumno, Materia> colMateria;
    @FXML
    private TableColumn<Alumno, Carrera> colCarrera;
    @FXML
    private TableColumn<Alumno, LocalDate> colFecha;         
    @FXML
    private TextField textBuscar;
    @FXML
    private ChoiceBox<String> chcBuscar;
    @FXML
    private TextField lblResultado;   
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnRefrescar;          
    @FXML
    private TableColumn<Alumno,Integer> colDNI;
    @FXML
    private Button bttNuevoAlumno;
    @FXML
    private TableView<Alumno> tblAlumnos;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chcBuscar.getItems().addAll("id","dni","apellido","materia","carrera"); 
        configurarVentana();
        rellenarTablaAlumno();       
    }  
     public void configurarVentana(){
        
        colDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));//cada col. se asigna setvalufactory
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colMateria.setCellValueFactory(new PropertyValueFactory<>("materia"));
        colCarrera.setCellValueFactory(new PropertyValueFactory<>("carrera"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tblAlumnos.setItems(alumnos);                
    }
     
     public void rellenarTablaAlumno(){
        alumnos.clear();                   
        TablaAlumno tabla= new TablaAlumno();
        ObservableList<Alumno> resultado=tabla.buscarTodos();
        alumnos.setAll(resultado);
        int resultados = resultado.size();//cuantos resultados hay en la lista
        lblResultado.setText("resultado :" +resultados);
        
    }
     
    @FXML
    private void seleccionar(MouseEvent event) {
        
        Alumno p = this.tblAlumnos.getSelectionModel().getSelectedItem();
        
        if(p==null){            
            JOptionPane.showMessageDialog(null, "seleccion nula ", "Error",JOptionPane.WARNING_MESSAGE);            
        }else{                
          try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/IngresoAlumno.fxml"));//carga una gerarqui DE OBJETOS        
            Parent root = loader.load();//carga el parent            
            IngresoAlumno controlador = loader.getController();//carga el controlador de esa vista                     
            //controlador.initAttributes(personas);
            controlador.traerAlumno(p);
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
                                  
    }
    
    public ObservableList<Alumno> buscarTodos(){
                         
        String query = "SELECT * FROM alumno";
        TransaccionesBD trscns = new TransaccionesBD(); 
        ResultSet rs = trscns.realizarConsulta(query);
        Alumno alumno =null;                 
        ObservableList<Alumno>alumnos = FXCollections.observableArrayList();
       
       try{
            while(rs.next()){                   
                Materia materia= new Materia();
                String m=rs.getString("materia");
                materia.setNombre(m);
                Carrera carrera = new Carrera();
                carrera.setNombre(rs.getString("carrera"));                     
                alumno= new Alumno();
                alumno.setDni(rs.getInt("dni"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setFecha(rs.getDate("fecha").toLocalDate());                      
                alumno.setMateria(materia);
                alumno.setCarrera(carrera);                                 
                alumnos.add(alumno);                                      
                }  
                rs.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error al buscar objeto profesor" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
       return alumnos;
       } 


    @FXML
    private void refrescar(ActionEvent event) {
        rellenarTablaAlumno();
        
        try {               
            DefaultTableModel modelo = new DefaultTableModel();
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al refrescar", "Error",JOptionPane.WARNING_MESSAGE);
            return; 
        } 
    }
    
    @FXML
    private void nuevoAlumno(ActionEvent event) {
        
         try {             
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/IngresoAlumno.fxml"));//carga una gerarqui DE OBJETOS       
            Parent root = loader.load();//carga el parent             
            IngresoAlumno controlador = loader.getController();//carga el controlador de esa vista           
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
    private void buscarAlumno(ActionEvent event) {
        Alumno p =null;
        long id=0;                      
        String modoBusqueda= chcBuscar.getValue();
        TablaAlumno tabla = new TablaAlumno();
       // System.out.println(" el modo de busqueda es  : " +modoBusqueda);           
        ObservableList<Alumno> alumnoSeleccion = FXCollections.observableArrayList();
        
        if(modoBusqueda==null){
            JOptionPane.showMessageDialog(null, "Debe seleccionar metodo de busca ", "Error",JOptionPane.WARNING_MESSAGE); 
              
        }else{
        
           switch(modoBusqueda){ // busqueda por apellido , materia o carrera
         
                case "id":   
                    String str = textBuscar.getText() ;
                    boolean isNumeric = str.chars().allMatch( Character::isDigit );                                                                                               
                    if(textBuscar.getText().isEmpty()){
                         JOptionPane.showMessageDialog(null, "debe ingresar id ", "Error",JOptionPane.WARNING_MESSAGE); 

                         }else if(textBuscar.getText()!=null){

                                if(isNumeric){                        
                                  id = Long.parseLong(textBuscar.getText());                    
                                  p=tabla.buscarAlumnoID(id);

                                    if(p!=null){
                                       alumnoSeleccion.addAll(tabla.buscarAlumnoID(id)); 
                                       alumnos.clear();
                                    }else{
                                       JOptionPane.showMessageDialog(null, "no se encontro id ", "Error",JOptionPane.WARNING_MESSAGE);  
                                         } 
                                   
                                }else{
                                 JOptionPane.showMessageDialog(null, "debe ingresar numeros ", "Error",JOptionPane.WARNING_MESSAGE);  
                                      }                                         
                                }                                                                
                      break;             
                case "apellido":              
                    String apellidoBusqueda = textBuscar.getText();
                   // boolean estaVacia =tabla.buscarProfeApellido(apellidoBusqueda).isEmpty();               
                    if(textBuscar.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "debe ingresar apellido " , "Error",JOptionPane.WARNING_MESSAGE); 

                    }else if(!tabla.buscarProfeApellido(apellidoBusqueda).isEmpty()){
                         alumnos.clear();
                         alumnoSeleccion.addAll(tabla.buscarProfeApellido(apellidoBusqueda));                                       
                    }  else{
                    JOptionPane.showMessageDialog(null, "no se encontro apellido " , "Error",JOptionPane.WARNING_MESSAGE);
                    }                                      

                    break;                 
                case "materia":
                    String materiaBusqueda = textBuscar.getText();
                    if(textBuscar.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "debe ingresar materia " , "Error",JOptionPane.WARNING_MESSAGE); 

                    }else if(!tabla.buscarProfeMateria(materiaBusqueda).isEmpty()){
                        alumnos.clear();
                        alumnoSeleccion.addAll(tabla.buscarProfeMateria(materiaBusqueda));                                      
                    }  else{
                    JOptionPane.showMessageDialog(null, "no se encontro materia " , "Error",JOptionPane.WARNING_MESSAGE);
                    }


                    break;                                          
                case "carrera":
                    String carreraBusqueda = textBuscar.getText();

                    if(textBuscar.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "debe ingresar carrera " , "Error",JOptionPane.WARNING_MESSAGE); 

                    }else if(!tabla.buscarAlumnoCarrera(carreraBusqueda).isEmpty()){
                         alumnos.clear();
                           alumnoSeleccion.addAll(tabla.buscarAlumnoCarrera(carreraBusqueda));                                    
                    }  else{
                    JOptionPane.showMessageDialog(null, "no se encontro carrera " , "Error",JOptionPane.WARNING_MESSAGE);
                    } 

                    break; 
                case "dni":               
                    String string = textBuscar.getText() ;
                    boolean isNumero = string.chars().allMatch( Character::isDigit );                                                                                               
                    if(textBuscar.getText().isEmpty()){
                         JOptionPane.showMessageDialog(null, "debe ingresar dni ", "Error",JOptionPane.WARNING_MESSAGE); 

                         }else if(textBuscar.getText()!=null){

                                    if(isNumero){                        
                                        int dniBusqueda = Integer.parseInt(textBuscar.getText());                    
                                        p=tabla.buscarAlumnoDNI(dniBusqueda);

                                        if(p!=null){
                                             alumnoSeleccion.addAll(tabla.buscarAlumnoDNI(dniBusqueda)); 
                                             alumnos.clear();
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
        
        alumnos.addAll(alumnoSeleccion);
        int resultado = alumnoSeleccion.size();
        lblResultado.setText("Resultados " + resultado);  
        
        }
    }
           
     public Alumno buscarAlumnoID(long idBusqueda){
        
        String query ="SELECT * FROM alumno WHERE id = " + idBusqueda;
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);            
        Alumno alumno =null;
          
        try{            
           if(rs.next()){
            //idBusqueda=rs.getLong("id");
            int dni = rs.getInt("dni");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            LocalDate fecha = rs.getDate("fecha").toLocalDate();        
            Materia materia= new Materia();
            materia.setNombre(rs.getString("materia"));
            Carrera carrera = new Carrera();
            carrera.setNombre(rs.getString("carrera"));                     
            alumno = new Alumno();
            alumno .setId(idBusqueda);
            alumno.setDni(dni);
            alumno.setNombre(nombre);
            alumno.setApellido(apellido);
            alumno.setMateria(materia);
            alumno.setCarrera(carrera);
            alumno.setFecha(LocalDate.EPOCH);                
          }   
           
       }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar objeto profesor" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
     //JOptionPane.showMessageDialog(null, "saliendo de metodo buscar por id ", "Error",JOptionPane.WARNING_MESSAGE);       
    return alumno;                                  
        }
     
     public ObservableList<Alumno> buscarProfeApellido(String apellidoBusqueda){
        
        //JOptionPane.showMessageDialog(null, "Ingreso en metodo buscarprofeApellido", "Error",JOptionPane.WARNING_MESSAGE);            
        String query = "SELECT * FROM alumno WHERE apellido LIKE '%" + apellidoBusqueda + "%'";           
        TransaccionesBD trscns =new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
        Alumno alumno=null;            
        ObservableList<Alumno> alumnoApellido = FXCollections.observableArrayList();
            //JOptionPane.showMessageDialog(null, "entro en el metodo buscarApellido ", "Error",JOptionPane.WARNING_MESSAGE);           
        try{
            while(rs.next()){
                     //JOptionPane.showMessageDialog(null, "entro en el while ", "Error",JOptionPane.WARNING_MESSAGE);
                long idBusqueda=rs.getLong("id"); 
                int dni = rs.getInt("dni");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();        
                Materia materia= new Materia();
                materia.setNombre(rs.getString("materia"));
                Carrera carrera = new Carrera();
                carrera.setNombre(rs.getString("carrera"));
                     
                alumno = new Alumno();
                alumno .setId(idBusqueda);
                alumno.setDni(dni);
                alumno.setNombre(nombre);
                alumno.setApellido(apellido);
                alumno.setMateria(materia);
                alumno.setCarrera(carrera);
                alumno.setFecha(LocalDate.EPOCH);
                alumnoApellido.add(alumno);  
                }           
           }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar objeto profesor" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
          
      return alumnoApellido ;                        
    }
     
      public ObservableList<Alumno> buscarProfeMateria(String materiaBusqueda){
            
        String query = "SELECT * FROM alumno WHERE materia LIKE '%" + materiaBusqueda + "%'";
            
        TransaccionesBD trscns =new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
        Alumno alumno =null;            
        ObservableList<Alumno> alumnoMateria = FXCollections.observableArrayList();
           // JOptionPane.showMessageDialog(null, "entro en el metodo buscarMateria ", "Error",JOptionPane.WARNING_MESSAGE);          
        try{
            
            while(rs.next()){
                   // JOptionPane.showMessageDialog(null, "entro en el while ", "Error",JOptionPane.WARNING_MESSAGE);
                long idBusqueda=rs.getLong("id");  
                int dni = rs.getInt("dni");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
        
                Materia materia= new Materia();
                materia.setNombre(rs.getString("materia"));
                Carrera carrera = new Carrera();
                carrera.setNombre(rs.getString("carrera"));
                     
                alumno = new Alumno();
                alumno .setId(idBusqueda);
                alumno.setDni(dni);
                alumno.setNombre(nombre);
                alumno.setApellido(apellido);
                alumno.setMateria(materia);
                alumno.setCarrera(carrera);
                alumno.setFecha(LocalDate.EPOCH); ;                              
                alumnoMateria.add(alumno);                               
                }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar objeto profesor" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
          
            return alumnoMateria;                        
        }
      
    public ObservableList<Alumno> buscarAlumnoCarrera(String apellidoBusqueda){
            
            String query = "SELECT * FROM alumno WHERE carrera LIKE '%" + apellidoBusqueda + "%'";
            
            TransaccionesBD trscns =new TransaccionesBD();
            ResultSet rs = trscns.realizarConsulta(query);
            Alumno alumno =null;
            
            ObservableList<Alumno> alumnoCarrera = FXCollections.observableArrayList();
            //JOptionPane.showMessageDialog(null, "entro en el metodo buscarApellido ", "Error",JOptionPane.WARNING_MESSAGE);
           
          try{
            while(rs.next()){
                     //JOptionPane.showMessageDialog(null, "entro en el while ", "Error",JOptionPane.WARNING_MESSAGE);
                long idBusqueda=rs.getLong("id"); 
                int dni = rs.getInt("dni");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();       
                Materia materia= new Materia();
                materia.setNombre(rs.getString("materia"));
                Carrera carrera = new Carrera();
                carrera.setNombre(rs.getString("carrera"));  
                
                alumno = new Alumno();
                alumno .setId(idBusqueda);
                alumno.setDni(dni);
                alumno.setNombre(nombre);
                alumno.setApellido(apellido);
                alumno.setMateria(materia);
                alumno.setCarrera(carrera);
                alumno.setFecha(LocalDate.EPOCH);                               
                alumnoCarrera.add(alumno);                              
                }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar objeto profesor" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
          
            return alumnoCarrera;                        
        }
    
     public Alumno buscarAlumnoDNI(int dniBusqueda){
         
        String query ="SELECT * FROM alumno WHERE dni = " + dniBusqueda;
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);           
        Alumno alumno =null;
          
        try{
            if(rs.next()){
                //idBusqueda=rs.getLong("id");
               int dni = rs.getInt("dni");
               String nombre = rs.getString("nombre");
               String apellido = rs.getString("apellido");
               LocalDate fecha = rs.getDate("fecha").toLocalDate();
        
                Materia materia= new Materia();
                materia.setNombre(rs.getString("materia"));
                Carrera carrera = new Carrera();
                carrera.setNombre(rs.getString("carrera"));
                     
                alumno = new Alumno();
                alumno .setId(dniBusqueda);
                alumno.setDni(dni);
                alumno.setNombre(nombre);
                alumno.setApellido(apellido);
                alumno.setMateria(materia);
                alumno.setCarrera(carrera);
                alumno.setFecha(LocalDate.EPOCH);                
                }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar objeto profesor" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
    // JOptionPane.showMessageDialog(null, "saliendo de metodo buscar por id ", "Error",JOptionPane.WARNING_MESSAGE);       
    return alumno;                                  
        }

    
    
}
