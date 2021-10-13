
package Controlador;

import Modelo.Suma;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class EjemploVistaControlador implements Initializable {

    @FXML
    private Button btnboton;
    @FXML
    private TextField textResultado;
    @FXML
    private TextField textOp2;
    @FXML
    private TextField textOp1;
    @FXML
    private Button btnSumar;

   
         @Override
        public void initialize(URL url, ResourceBundle rb) {
        
         }    

        private void click(ActionEvent event) {
             System.out.println("Hola mundo EjemploVistaControlador");
        }

        @FXML
         private void sumar(ActionEvent event) {
        try{
        
            System.out.println("evento de suma accionado");
            int op1= Integer.parseInt(this.textOp1.getText());
            int op2= Integer.parseInt(this.textOp2.getText());
         
            Suma s = new Suma(op1,op2);
            int resultado= s.suma();
            this.textResultado.setText(resultado + "");
        
        }catch (NumberFormatException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();            
                
        }     
        
    }
    
}
