/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academia.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import es.manueldonoso.academia.main.main;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Stage_show {

    /**
     * Muestra la ventana de login en una nueva ventana (Stage).
     *
     * Este método crea una nueva instancia de Stage, carga el archivo FXML de
     * la ventana de login, crea una escena con el contenido de la ventana y la
     * asigna al stage. Luego, se configura el estilo de la ventana y se aplica
     * un efecto visual para el movimiento de la ventana. Si ocurre algún error
     * al cargar el archivo FXML, se captura la excepción y se muestra un
     * mensaje de error en el log.
     *
     */
    public static void Mostrar_login() {
        Stage primaryStage = new Stage();
        try {
            // Cargo la ventana inicial
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.class.getResource("/vistas/Login.fxml"));

            // Ventana a cargar
            VBox ventana = (VBox) loader.load();

            // Creo la escena
            Scene scene = new Scene(ventana);

            // Modifico el stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("Login");
            primaryStage.initStyle(StageStyle.UNDECORATED);

            Efectos_visuales.darMovimientoStage(primaryStage);
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        primaryStage.show();
    }

    
    public static void Mostrar_crearContrato(){
         try {
            Stage primaryStage = new Stage();
            // Cargo la ventana inicial
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.class.getResource("/vistas/Contrato_registro.fxml"));

            // Ventana a cargar
            GridPane ventana = (GridPane) loader.load();

            // Creo la escena
            Scene scene = new Scene(ventana);

            // Modifico el stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("Licencia de uso");

            //detectar cierre de ventana
            primaryStage.setOnHidden(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {

                    System.out.println("se cerro la ventana de configuración");
                    //comprobarInstalacion();

                }
            });

            //mostrar ventana
            primaryStage.show();

            // primaryStage.setMaximized(true);
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
}
