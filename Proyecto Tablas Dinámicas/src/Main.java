/*
 * Creado por: David Pérez S.
 * Matrícula: 163202
 * Materia: Base de Datos Distribuida
 * Universidad Politécnica de Chiapas
 * Fecha de Creación: 21/11/2018 
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author David Pérez S.
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        // Abrimos el fxml (la vista) de la "entrada de datos"
        Parent root = FXMLLoader.load(getClass().getResource("entrada_datos/FXMLEntradaDatos.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Proyecto Tablas Dinámicas");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}