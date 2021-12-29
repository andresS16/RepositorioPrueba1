
package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Silva
 */
public class Login2Controlador implements Initializable {
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void ingresar(ActionEvent event) {
    }

    @FXML
    private void limpiar(ActionEvent event) {
    }

    
}
