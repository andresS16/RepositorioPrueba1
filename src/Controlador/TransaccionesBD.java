
package Controlador;
//import com.mysql.cj.xdevapi.Statement;
//import com.sun.jdi.connect.spi.Connection;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class TransaccionesBD {
    
    public ResultSet realizarConsulta(String query){
        ConexionBD conexion = new ConexionBD();
        Connection conn = conexion.conectar();
        ResultSet rs = null;
        Statement stQuery;
        
        
        
        try {
            stQuery = conn.createStatement();
            rs= stQuery.executeQuery(query);
            
         } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al ejecutar : " + query + ":" + e);
        }
    
    return rs;
    }
    
    
    public Boolean ejecutarQuery(String query){ // sera usado para insertar , modificar y eliminar
        
        ConexionBD conexion = new ConexionBD();
        Connection conn = conexion.conectar();
        Boolean exito =false;
        Statement s;
        ResultSet rsid = null;
        
            try {
                PreparedStatement ps = conn.prepareStatement(query);
                int queryRecibido = ps.executeUpdate();

                    if(queryRecibido !=0)
                        exito = true;

                       else
                         exito=false;  
                //System.out.println(" consulta recibida " + queryRecibido);
                //JOptionPane.showMessageDialog(null,"Consulta recibida " + queryRecibido );

                } catch (Exception e) {
                 JOptionPane.showMessageDialog(null,"Error al ejecutar ejecutar consulta : " + query + ":" + e);
                 exito =false;

        }
        return exito; 
    }
      
    
}
