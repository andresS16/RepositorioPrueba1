
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConexionBD {
    
           String usuario= "root";
           String clave = "";
           String url = "jdbc:mysql://localhost:3306/batman";
       
      
       public Connection conectar(){
           Connection link = null;
           
             try{
                
                // Class.forName("com.mysql.cj.jdbc.Driver");
                  
           link= DriverManager.getConnection(this.url,this.usuario,this.clave);
                 //System.out.println("conexion exitosa");
                 //JOptionPane.showMessageDialog(null,"Conectado a base de datos"); 
           
               }catch(Exception ex) {
                   JOptionPane.showMessageDialog(null," ERROR 0 al conectar : " + ex 
                           + "\n Asegurese que el servidor este encendido","ERROR",JOptionPane.ERROR_MESSAGE);  
               }
           return link;          
       }
        
      
       
        
    
    
}
