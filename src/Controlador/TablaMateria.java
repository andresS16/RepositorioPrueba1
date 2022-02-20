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
        chcBuscar.getItems().addAll("nombre","cuatrimestre","año","carrera");
        configurarVentana();
        rellenarTablaMateria();
        // TODO
    }  
    
    public void configurarVentana(){
     
        //colNumMateria.setCellValueFactory(new PropertyValueFactory<>("numMateria"));//cada col. se asigna setvalufactory
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));               
        colCuatrimestre.setCellValueFactory(new PropertyValueFactory<>("cuatrimestre"));
        colAño.setCellValueFactory(new PropertyValueFactory<>("año"));
        colCarrera.setCellValueFactory(new PropertyValueFactory<>("carrera"));        
        //colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tblMateria.setItems(materiasLista);   
    }
     
     
    public void rellenarTablaMateria(){
        materiasLista.clear();
        // RepoProfesor repoProf=new RepoProfesor();
        //ObservableList<Profesor> resultProfesores = repoProf.buscarTodos();
        TablaMateria tabla= new TablaMateria();
        ObservableList<Materia> resultMateria = tabla.buscarTodos();
        
        //System.out.println("contenido de lista "+ resultMateria.get(1));
        materiasLista.setAll(resultMateria);
        int resultados = resultMateria.size();//cuantos resultados hay en la lista
        lblResultado.setText("resultado :" +resultados);
    }
    
    @FXML
    private void seleccionar(MouseEvent event) {
         Materia m = this.tblMateria.getSelectionModel().getSelectedItem();
        
        if(m==null){            
            JOptionPane.showMessageDialog(null, "seleccion nula ", "Error",JOptionPane.WARNING_MESSAGE);     
            
        }else{                
          try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/IngresoMateria.fxml"));//carga una gerarqui DE OBJETOS        
            Parent root = loader.load();//carga el parent            
            IngresoMateria controlador = loader.getController();//carga el controlador de esa vista                     
            //controlador.initAttributes(personas);
            controlador.traerMateria(m);
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
    
   
    @FXML
    private void refrescar(ActionEvent event) {
        
        rellenarTablaMateria();      
        try {               
            DefaultTableModel modelo = new DefaultTableModel();
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al refrescar", "Error",JOptionPane.WARNING_MESSAGE);
            return; 
        }          
    }
         
    public Materia buscarMateriaID(long idBusqueda){
        
        String query ="SELECT * FROM materia WHERE id = " + idBusqueda;
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);            
        Materia m =null;
          
        try{            
           if(rs.next()){
               m.setId(rs.getLong("id"));
               m.setNombre(rs.getString("nombre"));
               m.setCuatrimestre(rs.getString("cuatrimestre"));
               m.setAño(rs.getInt("año"));
               Carrera carrera= new Carrera();
               carrera.setNombre(rs.getString("carrera"));
               m.setCarrera(carrera);                          
          }            
       }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar objeto profesor" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
     //JOptionPane.showMessageDialog(null, "saliendo de metodo buscar por id ", "Error",JOptionPane.WARNING_MESSAGE);       
    return m;                                  
        }
     
    public Materia buscarMateria(String nombreMateria){
         
        //JOptionPane.showMessageDialog(null, "Ingreso en metodo buscarprofeApellido", "Error",JOptionPane.WARNING_MESSAGE);            
        String query = "SELECT * FROM materia WHERE nombre LIKE '%" + nombreMateria + "%'";           
        TransaccionesBD trscns =new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
        Materia m =null; 
        Carrera c=null;  
            //JOptionPane.showMessageDialog(null, "entro en el metodo buscarApellido ", "Error",JOptionPane.WARNING_MESSAGE);           
        try{
            while(rs.next()){
                     //JOptionPane.showMessageDialog(null, "entro en el while ", "Error",JOptionPane.WARNING_MESSAGE);
                     m=new Materia();
                     c=new Carrera();
                     m.setId(rs.getLong("id"));
                     m.setNombre(rs.getString("nombre"));
                     m.setCuatrimestre(rs.getString("cuatrimestre"));
                     m.setAño(rs.getInt("año"));
                     c.setNombre(rs.getString("carrera"));                    
                     m.setCarrera(c);                                                             
                    // materiaNombre.add(m);  
                }           
           }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar materia" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
       
       return m ;                        
    }
     
     
    public ObservableList<Materia> buscarCuatrimestre(String cuatrimestre){
        
        //JOptionPane.showMessageDialog(null, "Ingreso en metodo buscarprofeApellido", "Error",JOptionPane.WARNING_MESSAGE);            
        String query = "SELECT * FROM materia WHERE cuatrimestre LIKE '%" + cuatrimestre + "%'";           
        TransaccionesBD trscns =new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
        Materia m =null; 
        Carrera c=null;
        ObservableList<Materia> cuatrimestreMateria = FXCollections.observableArrayList();
            //JOptionPane.showMessageDialog(null, "entro en el metodo buscarApellido ", "Error",JOptionPane.WARNING_MESSAGE);           
        try{
            while(rs.next()){
                     //JOptionPane.showMessageDialog(null, "entro en el while ", "Error",JOptionPane.WARNING_MESSAGE);
                m= new Materia();
                c = new Carrera();
                m.setNombre(rs.getString("nombre"));
                m.setCuatrimestre(rs.getString("cuatrimestre"));
                m.setAño(rs.getInt("año"));
                c.setNombre(rs.getString("carrera"));                    
                 m.setCarrera(c);                                                             
                 cuatrimestreMateria.add(m);  
            }           
        }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar objeto profesor" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return cuatrimestreMateria ;                        
    }
     
       
    public ObservableList<Materia> buscarCarrera(String carreraBusqueda){
            
        String query = "SELECT * FROM materia WHERE carrera LIKE '%" + carreraBusqueda + "%'";
            
        TransaccionesBD trscns =new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
        Materia m =null;
        Carrera c =null;            
        ObservableList<Materia> carreraMateria = FXCollections.observableArrayList();
            //JOptionPane.showMessageDialog(null, "entro en el metodo buscarApellido ", "Error",JOptionPane.WARNING_MESSAGE);         
        try{
            while(rs.next()){
                     //JOptionPane.showMessageDialog(null, "entro en el while ", "Error",JOptionPane.WARNING_MESSAGE);
                m=new Materia();
                c=new Carrera();
                m.setNombre(rs.getString("nombre"));
                m.setCuatrimestre(rs.getString("cuatrimestre"));
                m.setAño(rs.getInt("año"));
                c.setNombre(rs.getString("carrera"));                    
                m.setCarrera(c);                                                             
                carreraMateria.add(m);                              
            }           
        }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar objeto profesor" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
          
        return carreraMateria;                        
        }
        
    public Materia buscarMateriaAnio(int anio){
         
        String query ="SELECT * FROM materia WHERE año = " + anio;
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);           
        Materia m= null;
        Carrera c=null;
          
        try{
            if(rs.next()){
                //idBusqueda=rs.getLong("id");
               m=new Materia();
               c=new Carrera();
               m.setNombre(rs.getString("nombre"));
               m.setCuatrimestre(rs.getString("cuatrimestre"));
               m.setAño(rs.getInt("año"));
               c.setNombre(rs.getString("carrera"));                    
               m.setCarrera(c);                                                                                       
             }           
        }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar año " + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
    // JOptionPane.showMessageDialog(null, "saliendo de metodo buscar por id ", "Error",JOptionPane.WARNING_MESSAGE);       
    return m ;                                  
    }
   
    @FXML
    private void nuevo(ActionEvent event) {
        
         try {             
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/IngresoMateria.fxml"));//carga una gerarqui DE OBJETOS       
            Parent root = loader.load();//carga el parent   
            
            IngresoMateria controlador = loader.getController();//carga el controlador de esa vista           
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
                materia.setCuatrimestre(rs.getString("cuatrimestre")); 
                 materia.setAño(rs.getInt("año"));
                carrera.setNombre(rs.getString("carrera"));
                materia.setCarrera(carrera);                                                                                                                                                                                                                         ;                             
                materias.add(materia);                                     
                }  
                rs.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error al buscar objeto profesor" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        
       return materias;
       } 
    
     public Materia buscarNombre(String NombreBusqueda){
     
        //JOptionPane.showMessageDialog(null, "Ingreso en metodo buscarprofeApellido", "Error",JOptionPane.WARNING_MESSAGE);            
        String query = "SELECT * FROM materia WHERE nombre LIKE '%" +NombreBusqueda + "%'";           
        TransaccionesBD trscns =new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
        Materia materia =null;            
        ObservableList<Materia> materiasNombre = FXCollections.observableArrayList();
            //JOptionPane.showMessageDialog(null, "entro en el metodo buscarApellido ", "Error",JOptionPane.WARNING_MESSAGE);          
        try{
            while(rs.next()){
                     //JOptionPane.showMessageDialog(null, "entro en el while ", "Error",JOptionPane.WARNING_MESSAGE);
               materia= new Materia();
               materia.setId(rs.getLong("id"));
               materia.setNombre(rs.getString("nombre"));
               materia.setCuatrimestre(rs.getString("cuatrimestre"));
               materia.setAño(rs.getInt("año"));                   
               Carrera carrera = new Carrera();
               carrera.setNombre(rs.getString("carrera"));                                                               
              //materiasNombre.add(materia);                               
                }           
           }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error al buscar objeto profesor" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }        
          //System.out.println("el id encontrado es : "+ materia.getId());         
      return materia;                        
    }    

    @FXML
    private void buscarMateria(ActionEvent event) {
        
        Materia p =null;
        long id=0;                      
        String modoBusqueda= chcBuscar.getValue();
        TablaMateria tabla = new TablaMateria();
       // System.out.println(" el modo de busqueda es  : " +modoBusqueda);           
        ObservableList<Materia> materiaSeleccion = FXCollections.observableArrayList();
        
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
                            p=tabla.buscarMateriaID(id);

                            if(p!=null){
                                materiaSeleccion.addAll(tabla.buscarMateriaID(id)); 
                                 materiasLista.clear();
                            }else{
                                JOptionPane.showMessageDialog(null, "no se encontro id ", "Error",JOptionPane.WARNING_MESSAGE);  
                                             }                                   
                        }else{
                            JOptionPane.showMessageDialog(null, "debe ingresar numeros ", "Error",JOptionPane.WARNING_MESSAGE);  
                        }                                         
                    }                                                                
                    break;             
                case "nombre":              
                    String materiaBusqueda = textBuscar.getText();
                   // boolean estaVacia =tabla.buscarProfeApellido(apellidoBusqueda).isEmpty();               
                    if(textBuscar.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "debe ingresar nombre de materia " , "Error",JOptionPane.WARNING_MESSAGE); 

                    }else if(tabla.buscarMateria(materiaBusqueda)!= null){
                         materiasLista.clear();
                         materiaSeleccion.addAll(tabla.buscarMateria(materiaBusqueda));
                         
                    } else{
                    JOptionPane.showMessageDialog(null, "no se encontro materia " , "Error",JOptionPane.WARNING_MESSAGE);
                    }                                      

                    break; 
                    
                case "cuatrimestre":
                    String cuatrimestreBusqueda = textBuscar.getText();
                    
                    if(textBuscar.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "debe ingresar materia " , "Error",JOptionPane.WARNING_MESSAGE); 

                    }else if(!tabla.buscarCuatrimestre(cuatrimestreBusqueda).isEmpty()){
                        materiasLista.clear();
                        materiaSeleccion.addAll(tabla.buscarCuatrimestre(cuatrimestreBusqueda));                                      
                    } else{
                    JOptionPane.showMessageDialog(null, "no se encontro materia " , "Error",JOptionPane.WARNING_MESSAGE);
                    }

                    break;  
                    
                case "carrera":
                    String carreraBusqueda = textBuscar.getText();

                    if(textBuscar.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "debe ingresar carrera " , "Error",JOptionPane.WARNING_MESSAGE); 

                    }else if(!tabla.buscarCarrera(carreraBusqueda).isEmpty()){
                        materiasLista.clear();
                        materiaSeleccion.addAll(tabla.buscarCarrera(carreraBusqueda));                                    
                    }  else{
                    JOptionPane.showMessageDialog(null, "no se encontro carrera " , "Error",JOptionPane.WARNING_MESSAGE);
                    } 

                    break; 
                    
                case "año":
                    
                    String bandera = textBuscar.getText() ;
                    boolean isNumero = bandera.chars().allMatch( Character::isDigit ); 
                    
                    if(textBuscar.getText().isEmpty()){
                         JOptionPane.showMessageDialog(null, "debe ingresar dni ", "Error",JOptionPane.WARNING_MESSAGE); 

                    }else if(textBuscar.getText()!=null){

                        if(isNumero){                        
                            int anioBusqueda = Integer.parseInt(textBuscar.getText());                    
                            p=tabla.buscarMateriaAnio(anioBusqueda);

                            if(p!=null){
                                materiaSeleccion.addAll(tabla.buscarMateriaAnio(anioBusqueda)); 
                                materiasLista.clear();
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
           
        materiasLista.addAll(materiaSeleccion);
        int resultado = materiaSeleccion.size();
        lblResultado.setText("Resultados " + resultado);  
        
        }               
    }
    
}
