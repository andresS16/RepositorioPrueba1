/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FXML Controller class
 *
 * @author Silva
 */
public class UsuariosAltaControlador implements Initializable {// VIDEO 27 .Curso de JavaFX:Formulario de registro..conbinacion de combobox y tabla BD (Materia)

    @FXML
    private VBox panelFormUsuarioAlta;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private TextField txtCorreoUsuario;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtContraseña;
    @FXML
    private TextField txtConfirmaContraseña;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          txtCorreoUsuario.addEventFilter(KeyEvent.KEY_TYPED,new EventHandler<KeyEvent>(){//PARA NO GENERAR ESPACIOS EN BLANCO EN LOS CAMPOS
            @Override
            public void handle(KeyEvent t) {
                if(t.getCharacter().equals(" ")){
                     t.consume();   
                 }              
             }                
        });
          
          txtUsuario.addEventFilter(KeyEvent.KEY_TYPED,new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent t) {
                if(t.getCharacter().equals(" ")){
                     t.consume();   
                 }              
             }                
        });
          
          txtContraseña.addEventFilter(KeyEvent.KEY_TYPED,new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent t) {
                if(t.getCharacter().equals(" ")){
                     t.consume();   
                 }              
             }                
        });
          
          txtConfirmaContraseña.addEventFilter(KeyEvent.KEY_TYPED,new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent t) {
                if(t.getCharacter().equals(" ")){
                     t.consume();   
                 }              
             }                
        });
                    
    }    
    
     public Boolean validarCorreo(String email){
          Boolean exito=false;
        // Patrón para validar el email
         Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
 
        // El email a validar
         //email = "info@programacionextrema.com";
 
        Matcher mather = pattern.matcher(email);
 
        if (mather.find() == true) {
           // System.out.println("El email ingresado es válido.");
            exito=true;
        } else {
            JOptionPane.showMessageDialog(null,"El email ingresado es invalido " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);  
            exito=false;
        }
        
     return exito;
    }
    
    public void limpiarCampos(){
        txtCorreoUsuario.setText("");
        txtUsuario.setText("");
        txtContraseña.setText("");
        txtConfirmaContraseña.setText("");
    
    }
    @FXML
    private void actionEvent(ActionEvent e) throws SQLException {
             
         Object evento = e.getSource();// metodo p/ saber en que nodo se aplico el evento , donde esta posicionado
         Usuario usuario= new Usuario();
        
        if(evento.equals(btnRegistrar)){//se aplica condicional para saber que boton se acciono
             
                if(!txtCorreoUsuario.getText().isEmpty() && !txtUsuario.getText().isEmpty() 
                        && !txtContraseña.getText().isEmpty() && !txtConfirmaContraseña.getText().isEmpty() ){
                        usuario.setCorreo(txtCorreoUsuario.getText());
                    
                    if(validarCorreo(usuario.getCorreo()) ){
                        
                        if(txtUsuario.getText().length() >= 3){                                              
                    
                             if(txtContraseña.getText().equals(txtConfirmaContraseña.getText())){
                            
                                    usuario.setCorreo(txtCorreoUsuario.getText());
                                    usuario.setUsuario(txtUsuario.getText());
                                    usuario.setPassword(txtContraseña.getText());

                                    String query ="INSERT INTO `batman`.`usuario` ( `correo`, `usuario`, `pass`)"
                                            + "VALUES ( '"+usuario.getCorreo()+"', '"+usuario.getUsuario()+"' , AES_ENCRYPT( '" +usuario.getPassword()+"','AB12'))";

                                    TransaccionesBD trscns = new TransaccionesBD();                                     
                                    boolean exito = trscns.ejecutarQuery(query); 

                                    if(exito){

                                             JOptionPane.showMessageDialog(null,"El usuario fue creado con exito " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                                             limpiarCampos();

                                     }else{
                                             JOptionPane.showMessageDialog(null," No se pudo ingresar usuario, verificar conexion " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);

                                         }
                              } else{
                            
                                        JOptionPane.showMessageDialog(null,"Debe ingresar la misma contraseña " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);                             
                                 } 
                          }else{
                         JOptionPane.showMessageDialog(null,"El nombre de usuario debe contener al menos TRES caracteres" ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }else{
               // JOptionPane.showMessageDialog(null,"Falta llenar algun campo " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("ERROR");
                        alert.setContentText("falta agregar algun campo");
                        alert.showAndWait();
                 }
  
        }else if(evento.equals(btnLimpiar)){      
            
              limpiarCampos();
    
          }
                   
    }
    
    
}
