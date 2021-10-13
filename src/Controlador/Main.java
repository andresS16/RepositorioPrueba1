
package Controlador;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
 
    @Override
    public void start(Stage primaryStage) {
        
        try{
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Vista/EjemploVista.fxml"));
            Pane ventana = (Pane) loader.load();
            Scene scene = new Scene (ventana);
            primaryStage.setScene(scene);
            primaryStage.show();
        
        } catch(IOException e){
            System.out.println("tratar error"+ e.getMessage());
        
         }
    }
    
       public static void main(String[] args) {
        // TODO code application logic here     
        System.out.println("hola mundo desde Main_controlador");
        launch(args);            
        }
}
