/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Silva
 */
public class UsuariosAltaControlador implements Initializable {

    @FXML
    private VBox panelFormUsuarioAlta;
    @FXML
    private TextField txtCorreoAltaUsuario;
    @FXML
    private TextField txtUsuarioFormAlta;
    @FXML
    private TextField txtNuevaContraseña;
    @FXML
    private TextField txtConfirmaContraseña;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnLimpiar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
