/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Silva
 */
public class LoguinControlador implements Initializable {

    @FXML
    private TextField txtPass;
    @FXML
    private TextField txtUsuario;
    @FXML
    private Button btnAgregar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void agregarUsuario(ActionEvent event) {
        String usuario = txtUsuario.getText().trim();
        String contraseña=txtPass.getText().trim();
             System.out.println("datos ingresados "+ usuario +","+ contraseña);   
             
        if(usuario.equals("")|| contraseña.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("falta agregar algun campo");
            alert.showAndWait();
             
        }else{
                                
            String query ="INSERT INTO `batman`.`usuario` ( `nombre`, `pass`) VALUES ( '" + usuario+"', '"+contraseña+"');";
            TransaccionesBD trscns = new TransaccionesBD();
            boolean exito = trscns.ejecutarQuery(query);
            
             if(exito){
                JOptionPane.showMessageDialog(null,"Operacion exitosa " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);                  
              }                                
         }
    }

    private void ingresar(ActionEvent event) {
          JOptionPane.showMessageDialog(null,"Operacion ingresar " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);  
    }

   
}
