/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.Panel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Silva
 */
public class Principal implements Initializable {

    
    @FXML
    private BorderPane panel;
    Stage stage;
    @FXML
    private MenuItem miProfesor;
    @FXML
    private MenuItem miAlumno;
    @FXML
    private MenuItem miMateria;
    @FXML
    private MenuItem miCarrera;
    @FXML
    private MenuButton btnEditarDatos;
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      
    @FXML
    private void evento(ActionEvent event) {
         Object evento = event.getSource();// metodo p/ saber en que nodo se aplico el evento , donde esta posicionado
        
        if(evento.equals(miProfesor)){//se aplica condicional para saber que boton se acciono     
                      
            try{
                FXMLLoader loader= new FXMLLoader();
                loader.setLocation(Main.class.getResource("/vista/TablaProfesorVista.fxml"));//FormularioLU.fxml
            
                AnchorPane ventana = (AnchorPane) loader.load();
                panel.setCenter(ventana);
                TablaProfesor tblc = loader.getController();                                                             
               //principal.setScene(scene);
                   
             } catch(IOException e){
                 System.out.println("tratar error"+ e.getMessage());        
                }
               
        }else if(evento.equals(miAlumno)){   
            
            try{
                  
                FXMLLoader loader= new FXMLLoader();
                loader.setLocation(Main.class.getResource("/vista/TablaAlumno.fxml"));//FormularioLU.fxml

                AnchorPane ventana = (AnchorPane) loader.load();
                panel.setCenter(ventana);
                //TablaProfesorVistaController tblc = loader.getController();
                TablaAlumno tblc = loader.getController();
                                                                  
            } catch(IOException e){
                 System.out.println("tratar error"+ e.getMessage());            
                  }
                                          
          }if(evento.equals(miMateria)){//se aplica condicional para saber que boton se acciono                                      
             try{
                FXMLLoader loader= new FXMLLoader();
                loader.setLocation(Main.class.getResource("/vista/TablaMateria.fxml"));//FormularioLU.fxml
            
                AnchorPane ventana = (AnchorPane) loader.load();
                panel.setCenter(ventana);
                TablaMateria tblc = loader.getController();                                                             
               //principal.setScene(scene);
                   
             } catch(IOException e){
                 System.out.println("tratar error"+ e.getMessage());       
         }
               
        }else if(evento.equals(miCarrera)){                                            
            try{
                  
                FXMLLoader loader= new FXMLLoader();
                loader.setLocation(Main.class.getResource("/vista/TablaCarrera.fxml"));//FormularioLU.fxml

                AnchorPane ventana = (AnchorPane) loader.load();
                panel.setCenter(ventana);
                //TablaProfesorVistaController tblc = loader.getController();
                TablaCarrera tblc = loader.getController();
                                                                  
             }catch(IOException e){
                    System.out.println("tratar error"+ e.getMessage());           
               }                                          
          }             
    }
   
}
