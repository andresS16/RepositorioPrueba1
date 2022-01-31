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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    private Button btnIngresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void agregarUsuario(ActionEvent event) {
        String usuario = txtUsuario.getText().trim();
        String contraseña=txtPass.getText().trim();
             System.out.println("datos ingresados "+ usuario + ","+ contraseña);   
             
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
        try {
            
            
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Usuarios.fxml"));//carga una gerarqui DE OBJETOS
            
            Parent root = loader.load();//carga el parent
            
            LoguinControlador controlador = loader.getController();//carga el controlador de esa vista
            
            //controlador.initAttributes(personas);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);//modal : hasta que no termine el no me deje
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(LoguinControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ingresar1(ActionEvent event) {
        JOptionPane.showMessageDialog(null,"Operacion ingresar1 " ,"aviso" , JOptionPane.INFORMATION_MESSAGE);
        try {
            
            
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Sistema.fxml"));//carga una gerarqui DE OBJETOS
            
            Parent root = loader.load();//carga el parent
            
          SistemaControlador controlador = loader.getController();//carga el controlador de esa vista
            
            //controlador.initAttributes(personas);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);//modal : hasta que no termine el no me deje
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(LoguinControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }

   
}
