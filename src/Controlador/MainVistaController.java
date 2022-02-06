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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Silva
 */
public class MainVistaController implements Initializable {

    @FXML
    private Button btnEstudiante;
    @FXML
    private Button btnProfesor;
    @FXML
    private Button btnSistema;
    @FXML
    private TableView<?> tblTablaMenu;
    @FXML
    private Button btnBuscar;
    @FXML
    private TextField txtBuscar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
