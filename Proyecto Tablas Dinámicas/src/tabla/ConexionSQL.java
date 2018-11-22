/*
 * Creado por: David Pérez S.
 * Matrícula: 163202
 * Materia: Base de Datos Distribuida
 * Universidad Politécnica de Chiapas
 * Fecha de Creación: 21/11/2018 
 */

package tabla;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author David Pérez S.
 */
public class ConexionSQL {
    
    private final String nombreServidor;
    private final String numeroPuerto;
    private final String nombreBD;
    private final String nombreUsuario;
    private final String contraseñaUsuario;
    
    public ConexionSQL(String nombreServidor, String numeroPuerto, String nombreBD, String nombreUsuario, String contraseñaUsuario) {
        this.nombreServidor = nombreServidor;
        this.numeroPuerto = numeroPuerto;
        this.nombreBD = nombreBD;
        this.nombreUsuario = nombreUsuario;
        this.contraseñaUsuario = contraseñaUsuario;
    }
    
    public Connection conectar() {
//        try {
//            // Create a variable for the connection string.
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
//        } catch (ClassNotFoundException|InstantiationException|IllegalAccessException ex) {
//            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //String url = "jdbc:sqlserver://192.168.1.119;database=SAEBM_UPCHIAPAS";
        String connectionUrl = "jdbc:sqlserver://" + nombreServidor + ":" + numeroPuerto + ";" + nombreBD + "=AdventureWorks;user=" + nombreUsuario + ";password=" + contraseñaUsuario;
        
        try {
            Connection connection = DriverManager.getConnection(connectionUrl);
            return connection;
        } // Handle any errors that may have occurred.
        catch (SQLException e) {
            System.err.println("No se pudo hacer la conexión - " + e);
            return null;
        }
    }
}