
package Controlador;

import Modelo.Usuario;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Silva
 */
public class LoginControlador implements Initializable {
    @FXML
    private VBox PanelInicioSesion;
    @FXML
    private TextField txtUsuarioInSesion;
    @FXML
    private PasswordField txtPasswordIniSesion;
    @FXML
    private TextField txtPasswordIniSesionMask;
    @FXML
    private CheckBox chcMostrarContrseña;
    @FXML
    private Button btnIngresar;
    @FXML
    private Button btnLimpiar;
    Main main =new Main();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
         maskPass(txtPasswordIniSesion,txtPasswordIniSesionMask,chcMostrarContrseña);
        
         txtUsuarioInSesion.addEventFilter(KeyEvent.KEY_TYPED,new EventHandler<KeyEvent>(){// no permite espacios en blanco
            @Override
            public void handle(KeyEvent t) {
                if(t.getCharacter().equals(" ")){
                     t.consume();   //se detiene el evento
                 }              
             }                
        });    
       
         txtPasswordIniSesion.addEventFilter(KeyEvent.KEY_TYPED,new EventHandler<KeyEvent>(){
         @Override
         public void handle(KeyEvent t) {
             if(t.getCharacter().equals(" ")){
                     t.consume();   //se detiene el evento
               }              
           }                
        }); 
    }    

    
    public void maskPass(PasswordField pass,TextField texto,CheckBox check){// 26.Curso de javafx formulario de registro e inicio de sesion
        
        texto.setVisible(false);
        texto.setManaged(false);
        
        texto.managedProperty().bind(check.selectedProperty());
        texto.visibleProperty().bind(check.selectedProperty());       
        texto.textProperty().bindBidirectional(pass.textProperty());// se conectan los elementos ..el chex con el campo de texto   
    }

   /* @FXML
    private void eventKey(KeyEvent e) {      
        String c = e.getCharacter();
        
        if(c.equalsIgnoreCase(" ")){
            e.consume();   
        }                    
    }*/
    public void limpiarCampos(){
        txtUsuarioInSesion.setText("");
        txtPasswordIniSesion.setText("");
        chcMostrarContrseña.setAccessibleHelp("");
          
    }
  

    @FXML
    private void actionEvent(ActionEvent e) {
        
         Object evento = e.getSource();// metodo p/ saber en que nodo se aplico el evento , donde esta posicionado
        Usuario usuario= new Usuario();
         
         if(btnIngresar.equals(evento)){//String query ="SELECT * FROM profesor4 WHERE id = " + idBusqueda;...String query = "SELECT * FROM profesor4 WHERE apellido LIKE '%" + apellidoBusqueda + "%'";
             
             if(!txtUsuarioInSesion.getText().isEmpty() && !txtPasswordIniSesion.getText().isEmpty()){
                                 
                 String nomUser=txtUsuarioInSesion.getText();
                 String passContr=txtPasswordIniSesion.getText();//SELECT usuario,cast(aes_decrypt(pass,'key')AS CHAR) AS pass FROM usuario ;
                 //String query = "SELECT pass,cast(aes_decrypt(pass,'A12') as char) from usuario WHERE usuario='"+nomUser+"'";
                
                 
                  
                 String query ="SELECT usuario,cast(aes_decrypt(pass,'AB12')AS CHAR) AS pass FROM usuario WHERE usuario='"+ nomUser +"' ";
                 TransaccionesBD trscns = new TransaccionesBD();  
             
                 ResultSet rs = trscns.realizarConsulta(query);                    
                   //JOptionPane.showMessageDialog(null,"paso la consulta " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);  
                  
                    try{
                            
                        if(rs.next()){
                                
                            //JOptionPane.showMessageDialog(null,"entro al IF" ,"aviso " , JOptionPane.INFORMATION_MESSAGE);                          
                            
                             usuario.setUsuario(rs.getString("usuario"));                                
                             usuario.setPassword(rs.getString("pass"));                                 
                              //String contraseña= rs.getString("pass");
                             System.out.println(" usuario "+ usuario.getUsuario());
                             System.out.println(" password "+ usuario.getPassword());
                                  
                                 if(usuario.getPassword().equals(passContr)){
                                    JOptionPane.showMessageDialog(null,"Bienvenido " ,"aviso" , JOptionPane.INFORMATION_MESSAGE); 
                                    limpiarCampos();
                                    
                                     try {
                                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MainVista.fxml"));//carga una gerarqui DE OBJETOS

                                            Pane root = (Pane) loader.load();//carga el parent
            
                                            MainVistaController  controlador = loader.getController();//carga el controlador de esa vista                                                                                  

                                            Scene escene= new Scene(root);
                                           
                                            Stage stage=new Stage();
                                            
                                            stage.setScene(escene);
                                            stage.show();
                                             

                                            //lo nuevo
                                           // stage.setOnCloseRequest(e -> controlador.closeWindows());
                                            //Stage myStage= (Stage)this.btnAgregarProfesor.getScene().getWindow();
                                            Stage myStage= (Stage)this.btnIngresar.getScene().getWindow();
                                            myStage.close();

                                        } catch (IOException ex) {
                                            Logger.getLogger(LoginControlador.class.getName()).log(Level.SEVERE, null, ex);
                                        }  

                                  }else{
                                      JOptionPane.showMessageDialog(null,"Verifique contraseña" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                                    }                                                                                      
                         } else{
                        
                         JOptionPane.showMessageDialog(null,"Verificar datos ingresados" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                        
                               }          
                       }catch(Exception ex){
                                JOptionPane.showMessageDialog(null,"error al buscar usuario " + ex , "ERROR", JOptionPane.ERROR_MESSAGE);
                         }
                                                     
               }
         
         
         }else if(btnLimpiar.equals(evento)){
           limpiarCampos();
         }
        
    }

    
}
