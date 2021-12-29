
package Controlador;

import java.io.IOException;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class FormularioLoginControlador implements Initializable {
    @FXML
    private Button btnInisiarSesion;
    @FXML
    private Button btnRegistrarUsuario;
    @FXML
    private StackPane contenedorFormulario;
    
    
    
    private VBox inisioSesion;
    private VBox usuarioAlta;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        try {
            inisioSesion= cargarFormulario("/vista/Login2.fxml");
            
            usuarioAlta = cargarFormulario("/vista/UsuariosAlta.fxml");
            
            contenedorFormulario.getChildren().addAll(inisioSesion,usuarioAlta);
            inisioSesion.setVisible(true);
            usuarioAlta.setVisible(false);
      
        } catch (IOException ex) {
            Logger.getLogger(FormularioLoginControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    public void iniciarSesion(ActionEvent e) {
        
        Object evento = e.getSource();
        
        if(evento.equals(btnInisiarSesion.onActionProperty())){
            inisioSesion.setVisible(true);
            usuarioAlta.setVisible(false);
            
    
    }else if(evento.equals(btnRegistrarUsuario)){
        
            usuarioAlta.setVisible(true);
            inisioSesion.setVisible(false);
    
    }
    }

    
    private VBox cargarFormulario(String url) throws IOException{
        
             FXMLLoader loader= new FXMLLoader();
      
            loader.setLocation(getClass().getResource(url));
            VBox ventana = (VBox) loader.load();
        
      //return(VBox)FXMLLoader.load(getClass().getResource(url));
       return ventana ;
    
    }

    @FXML
    private void registrarse(ActionEvent event) {
    }



    
}
