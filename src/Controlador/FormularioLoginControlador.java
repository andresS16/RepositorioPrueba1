
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               
        try {
            inisioSesion= cargarFormulario("/vista/Login.fxml");          
            usuarioAlta = cargarFormulario("/vista/UsuariosAlta.fxml");
            
            contenedorFormulario.getChildren().addAll(inisioSesion,usuarioAlta);
            inisioSesion.setVisible(true);
            usuarioAlta.setVisible(false);
      
        } catch (IOException ex) {
            Logger.getLogger(FormularioLoginControlador.class.getName()).log(Level.SEVERE, null, ex);
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
    private void actionEvent(ActionEvent e) {
        //System.out.println("ENTRO EN EL METODO DEL BOTON");      
         Object evento = e.getSource();// metodo p/ saber en que nodo se aplico el evento , donde esta posicionado
        
        if(evento.equals(btnInisiarSesion)){//se aplica condicional para saber que boton se acciono
            inisioSesion.setVisible(true);
            usuarioAlta.setVisible(false);
            //System.out.println("    ENTRO EN EL IF DE INISIO DE SESION");        
              
  
        }else if(evento.equals(btnRegistrarUsuario)){      
            usuarioAlta.setVisible(true);
             inisioSesion.setVisible(false);
              //System.out.println("    ENTRO EN EL IF DE alta user");
    
          }
    }



    
}
