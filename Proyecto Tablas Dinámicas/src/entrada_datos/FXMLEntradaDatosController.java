/*
 * Creado por: David Pérez S.
 * Matrícula: 163202
 * Materia: Base de Datos Distribuida
 * Universidad Politécnica de Chiapas
 * Fecha de Creación: 21/11/2018 
 */
package entrada_datos;

import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import tabla.FXMLTablaDatosController;

/**
 * FXML Controller class
 *
 * @author David Pérez S.
 */
public class FXMLEntradaDatosController implements Initializable {
    
    @FXML private JFXTextArea entradaDatos;
    
    @FXML
    private void leerDatos() throws IOException {
        String datos = entradaDatos.getText();
        if(!datos.equals("")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tabla/FXMLTablaDatos.fxml"));
            Parent root = loader.load();
            // Traspasamos los datos (la consulta insertada) al controlador del fxml de la "tabla de datos"
            FXMLTablaDatosController controller = (FXMLTablaDatosController) loader.getController();
            controller.recibirDatos(datos);
            // Abrimos el fxml (la vista)
            Stage stage = new Stage();
            stage.setTitle("Proyecto Tablas Dinámicas");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            // Si algo sale mal, avisamos.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Proyecto Tablas Dinámicas");
            alert.setHeaderText("Error");
            alert.setContentText("Por favor introduce algo");
            alert.showAndWait();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
}