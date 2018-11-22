/*
 * Creado por: David Pérez S.
 * Matrícula: 163202
 * Materia: Base de Datos Distribuida
 * Universidad Politécnica de Chiapas
 * Fecha de Creación: 21/11/2018 
 */
package tabla;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 *
 * @author David Pérez S.
 */
public class FXMLTablaDatosController implements Initializable {
    
    private String datosEntrada;
    @FXML private TableView<ObservableList> tablaDatos;
    @FXML private JFXTextField textFieldBuscar;
    
    private void cargarDatosTabla(ResultSet rs) throws SQLException {
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        // Obtenemos el número de columnas de la tabla que recibimos
        int numeroColumnas = rs.getMetaData().getColumnCount();
        
        // Empezamos a crear los encabezados de cada columna con el nombre
        // de cada columna de la tabla recibida de la BD
        for(int i = 0; i < numeroColumnas; i++) {
            final int j = i;
            // Asignamos nombre
            TableColumn columna = new TableColumn(rs.getMetaData().getColumnName(i+1));
            columna.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });
            // Asignamos esta columna a la tabla
            tablaDatos.getColumns().addAll(columna);
        }
        // Empezamos a agregar los registros (filas) a la lista observable una por una
        while(rs.next()) {
            ObservableList<String> row = FXCollections.observableArrayList();
            for(int i = 1; i <= numeroColumnas; i++) {
                row.add(rs.getString(i) == null ? "" : rs.getString(i));
            }
            if(row != null)
                data.add(row);
        }
        // Agregamos finalmente la lista observable con todos los registros
        // obtenidos al table view
        tablaDatos.setItems(data);
    }
    
    private void obtenerDatosBD() {
        // Credenciales del servidor y de la BD
        ConexionSQL aux = new ConexionSQL("<nombre de servidor>", "<numero de puerto>", "<nombre de BD>", "<nombre de usuario>", "<contraseña de usuario>");
        Connection conn = aux.conectar();
        try {
            Statement statement = conn.createStatement();
            String consulta = datosEntrada;
            ResultSet rs = statement.executeQuery(consulta);
            if(rs != null)
                cargarDatosTabla(rs);
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e);
            // Si algo sale mal, avisamos.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Proyecto Tablas Dinámicas");
            alert.setHeaderText("Upps.. Algo Salió Mal");
            alert.setContentText("No se pudo conectar a la Base de Datos");
            alert.showAndWait();
        }
    }
    
    // Recibimos los datos del controlador del fxml de la "entrada de datos"
    public void recibirDatos(String datosEntrada) {
        this.datosEntrada = datosEntrada;
        obtenerDatosBD();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // XD
    }
}