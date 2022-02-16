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
import javafx.scene.control.DatePicker;
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
public class IngresoAlumno implements Initializable {
    private ObservableList<Profesor> alumnos = FXCollections.observableArrayList();

    @FXML
    private TextField txtDNI;
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
    private Button btnModificar;
  
    Long id=null;
    Alumno alumno=null;

     ArrayList<Carrera> listaCarrera  = new ArrayList<>();
     ArrayList<Materia> listaMateria  = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IngresoAlumno ip = new IngresoAlumno();
        listaMateria = ip.seleMateria();
        listaCarrera= ip.seleCarrera();
        comboMateria.getItems().addAll(listaMateria);
        comboCarrera.getItems().addAll(listaCarrera);
      
    } 
    public void rellenarTablaAlumno(){
        alumnos.clear();
         RepoProfesor repoProf=new RepoProfesor();
        ObservableList<Profesor> resultProfesores = repoProf.buscarTodos();
        alumnos.setAll(resultProfesores);
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
    
     public void traerAlumno(Alumno a){
                           
        if(a !=null){         
                 //this.txtId.setText(p.getId()+ "");
           //JOptionPane.showMessageDialog(null,"entro al if del metodo traer" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);   
         
            this.txtDNI.setText(a.getDni()+ "");
            this.txtNombre.setText(a.getNombre());
            this.txtApellido.setText(a.getApellido());
            this.comboMateria.getSelectionModel().select(a.getMateria());
            this.comboCarrera.getSelectionModel().select(a.getCarrera());
            this.dateFecha.setValue(a.getFecha());                          
        }
        
        String query = "select id from alumno where dni='"+ a.getDni()+"'";
        
           TransaccionesBD trscns = new TransaccionesBD();
            ResultSet rs = trscns.realizarConsulta(query);
            try{
                if(rs.next()){                   
                     //JOptionPane.showMessageDialog(null, "entro en el if para asignar", "Error",JOptionPane.WARNING_MESSAGE);                 
                    a.setId(rs.getLong("id")); 
                       this.id = a.getId();
                                                                                                                                 }           
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleCarrera" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
    
    }
     
      private void eliminarAlumno() { 
        
         String bandera =this.txtDNI.getText();       
         String nombre = this.txtNombre.getText(); 
         String apellido =this.txtApellido.getText(); 
         Materia materia= new Materia();
         Carrera carrera = new Carrera();      
         LocalDate fecha = this.dateFecha.getValue();  
         
        if( bandera.equals("")|| nombre.equals("") || apellido.isEmpty() || this.comboMateria.getValue()== null ||
                   this.comboCarrera.getValue()==null || fecha== null ){
               
            JOptionPane.showMessageDialog(null,"falta seleccionar alumno" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);            
            rellenarTablaAlumno();
            return ;
                    
        } else if(id > 0){
            
                int opcion = JOptionPane.showConfirmDialog(null, 
                             "Desea eliminar " + "el registro ? " , "confirmacion" ,JOptionPane.YES_NO_OPTION,2);

                if(opcion== JOptionPane.YES_OPTION  ){

                    String query ="DELETE FROM `alumno` WHERE `alumno`.`id` = " + id;// traer() trae id
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
      
       private void modificarAlumno() {
           
         Alumno p= new Alumno();
         Alumno p1=new Alumno();
         IngresoAlumno ia= new IngresoAlumno();
         TablaAlumno ta =new TablaAlumno();
                                               
         String bandera =this.txtDNI.getText();       
         String nombre = this.txtNombre.getText(); 
         String apellido =this.txtApellido.getText(); 
         Materia materia= new Materia();
         Carrera carrera = new Carrera();      
         LocalDate fecha = this.dateFecha.getValue();
         boolean isNumerico = bandera.chars().allMatch( Character::isDigit ); 
         
        if( bandera.equals("")|| nombre.equals("") || apellido.isEmpty() || this.comboMateria.getValue()== null ||
                   this.comboCarrera.getValue()==null || fecha== null ){
               
            JOptionPane.showMessageDialog(null,"falta llenar los campos" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);            
            rellenarTablaAlumno();
            return ;
                    
        } else if(isNumerico) { 
             
            boolean isNumero = false;
            boolean isLetra = false;

            isNumero = !nombre.matches(".*\\d.*"); // if ternario :contiene un numero
             // no contiene un numero }
                 
            isLetra = !apellido.matches(".*\\d.*"); // contains a number
             // does not contain a number }
          
            if( isNumero != false && isLetra != false ){
                 if(bandera.length()==8){
                     int dni= Integer.parseInt(this.txtDNI.getText());
                        p1=ta.buscarAlumnoDNI(dni);
                        //p.setId(id);
                        if(p1 !=null ){                                    
                             JOptionPane.showMessageDialog(null,"El usuario ya existe" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);                                                                     

                         }else {                                                                                                                                                                                                                             
                             //int dni = Integer.parseInt(this.txtDNI.getText());
                              String query="UPDATE `alumno` SET "  
                              + "`dni`='"+ this.txtDNI.getText()+"',"
                              + "`nombre`='"+ this.txtNombre.getText()+"',"
                              + "`apellido`='"+ this.txtApellido.getText()+"',"
                              + "`materia`='"+ comboMateria.getValue() +"',"
                              + "`carrera`='"+comboCarrera.getValue()+"',"
                              + "`fecha`='"+dateFecha.getValue()+"' "
                              + "WHERE `id`="+ id ;

                              TransaccionesBD trscns = new TransaccionesBD();
                              boolean exito = trscns.ejecutarQuery(query);
                              JOptionPane.showMessageDialog(null,"alumno modificado " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                              vaciarCampos();
                              Stage stage =(Stage) this.btnGuardar.getScene().getWindow();
                              stage.close();                               
                           }            
                    }else{
                         JOptionPane.showMessageDialog(null,"Debe ingresar numero de 8 digitos " ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
                        }                                            
             }else{
                JOptionPane.showMessageDialog(null,"Debe ingresar letras " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);            
                }
                             
         }else{
           JOptionPane.showMessageDialog(null,"Debe ingresar numeros " ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
              }    /*else{
            
            String query="UPDATE `alumno` SET "  
            + "`dni`='"+ this.txtDNI.getText()+"',"
            + "`nombre`='"+ this.txtNombre.getText()+"',"
            + "`apellido`='"+ this.txtApellido.getText()+"',"
            + "`materia`='"+ comboMateria.getValue() +"',"
            + "`carrera`='"+comboCarrera.getValue()+"',"
            + "`fecha`='"+dateFecha.getValue()+"' "
            + "WHERE `id`="+ id ;

            TransaccionesBD trscns = new TransaccionesBD();
            boolean exito = trscns.ejecutarQuery(query);
            JOptionPane.showMessageDialog(null,"alumno modificado " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
            vaciarCampos();
            Stage stage =(Stage) this.btnGuardar.getScene().getWindow();
            stage.close();                                                    
          } */                                                                                                           
    }
    
     public ArrayList<Materia> seleMateria(){
        
        Carrera carrera = new Carrera();
        ArrayList<Materia> lista  = new ArrayList<>();       
        String query = "select id,nombre FROM materia where 1 ORDER BY nombre ASC;";       
        TransaccionesBD trscns = new TransaccionesBD();
        ResultSet rs = trscns.realizarConsulta(query);
        
        try{
            while(rs.next()){
             //JOptionPane.showMessageDialog(null, "entro en el while ", "Error",JOptionPane.WARNING_MESSAGE);
             // long idBusqueda=rs.getLong("id");                                                                      
                Materia materia = new Materia();
                materia.setNombre(rs.getString("nombre"));
                //System.out.println("la materia es " + materia.getNombre_Materia());                                      
                lista.add(materia);                     
                }           
        }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"error en metodo de seleMateria" + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
            }                                 
           // System.out.println("la carrera es " + carrera.getNombre_carrera());
     return lista;
               
    }
     
        public ArrayList<Carrera> seleCarrera(){
        
        Carrera carrera = new Carrera();
        ArrayList<Carrera> lista  = new ArrayList<>();        
        String query = "select id,nombre FROM carrera where 1 ORDER BY nombre ASC;";       
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
        
        
    public long id_incrementable(){
           
           long id=0;       
           String query="SELECT MAX(id) FROM alumno";              
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
         
          
    


    @FXML
    private void actionEvent(ActionEvent e) {
        
          Object evento = e.getSource();// metodo p/ saber en que nodo se aplico el evento , donde esta posicionado
        
        if(evento.equals(btnEliminar)){
            
            eliminarAlumno();      
            //JOptionPane.showMessageDialog(null,"llama al metodo eliminar"+ id,"aviso" , JOptionPane.INFORMATION_MESSAGE);  
        
        }else if(evento.equals(btnModificar)){   
            
            modificarAlumno();                             
        }   
        
        
        
    }

    @FXML
    private void guardarAlumno(ActionEvent event) {
       
         Alumno p= new Alumno();
         Alumno p1=new Alumno();
         IngresoAlumno ia= new IngresoAlumno();
         TablaAlumno ta =new TablaAlumno();
                      
         long id ; //Long.parseLong(this.txtId.getText())          
         id=ia.id_incrementable();
               
         String bandera =this.txtDNI.getText();   
         boolean isNumerico = bandera.chars().allMatch( Character::isDigit ); 
         String nombre = this.txtNombre.getText(); 
         String apellido =this.txtApellido.getText(); 
         Materia materia= new Materia();
         Carrera carrera = new Carrera();      
         LocalDate fecha = this.dateFecha.getValue();  
         
        if(bandera.equals("") || nombre.equals("") || apellido.isEmpty() || this.comboMateria.getValue()== null ||
                   this.comboCarrera.getValue()==null || fecha== null ){
               
            JOptionPane.showMessageDialog(null,"falta llenar los campos" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);            
            rellenarTablaAlumno();
            return ;
                    
        }else if(isNumerico) { 
             
            boolean isNumero = false;
            boolean isLetra = false;

            isNumero = !nombre.matches(".*\\d.*"); // if ternario :contiene un numero
             // no contiene un numero }
                 
            isLetra = !apellido.matches(".*\\d.*"); // contains a number
             // does not contain a number }
          
            if( isNumero != false && isLetra != false ){
                 if(bandera.length()==8){
                     int dni= Integer.parseInt(this.txtDNI.getText());
                        p1=ta.buscarAlumnoDNI(dni);
                        //p.setId(id);
                        if(p1 !=null ){                                    
                             JOptionPane.showMessageDialog(null,"El usuario ya existe" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);                                                                     

                         }else {                                                                                                                                                                                                                             
                             //int dni = Integer.parseInt(this.txtDNI.getText());
                              p.setId(id); 
                              p.setDni(dni);                          
                              p.setNombre(nombre);
                              p.setApellido(apellido);
                              materia.setNombre(comboMateria.getValue().toString());
                              p.setMateria(materia);
                              carrera.setNombre(comboCarrera.getValue().toString());
                              p.setCarrera(carrera);
                              p.setFecha(fecha);
                              //rep.insertar(p); 
                              ia.insertar(p);

                              JOptionPane.showMessageDialog(null,"Se guardo correctamente" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);   
                              Stage stage =(Stage) this.btnGuardar.getScene().getWindow();
                              stage.close();                               
                           }            
                    }else{
                         JOptionPane.showMessageDialog(null,"Debe ingresar numero de 8 digitos " ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
                        }                                            
             }else{
                JOptionPane.showMessageDialog(null,"Debe ingresar letras " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);            
                }
                             
         }else{
           JOptionPane.showMessageDialog(null,"Debe ingresar numeros " ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
              }                   
    }
    
    
    public boolean insertar(Alumno alumno){
         
        String query = "INSERT INTO alumno(id ,dni,nombre,apellido,materia,carrera,fecha)" 
                + "VALUES(' "
                + alumno.getId() 
                + " ',' " 
                 +  alumno.getDni()
                + " ',' " 
                +  alumno.getNombre()
                +"','"
                + alumno.getApellido()
                + " ',' " 
                + alumno.getMateria()              
                + " ',' " 
                + alumno.getCarrera() 
                + " ','" + alumno.getFecha()
                + "' )";
          
        TransaccionesBD trscns = new TransaccionesBD();
        boolean exito = trscns.ejecutarQuery(query);    
        
       // JOptionPane.showMessageDialog(null,"entro en metodo insertar REPO" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
        return exito;
    }
    
}
