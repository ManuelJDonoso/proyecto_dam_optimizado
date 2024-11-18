/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academia.util;

import es.manueldonoso.academia.controller.Cambio_ContrasenaController;
import es.manueldonoso.academia.controller.DashBoard_AdministradorController;
import es.manueldonoso.academia.controller.DashBoard_AlumnoController;
import es.manueldonoso.academia.controller.DashBoard_ProfesorController;
import es.manueldonoso.academia.controller.MisDatosController;
import es.manueldonoso.academia.util.utilidades;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import es.manueldonoso.academia.main.main;
import es.manueldonoso.academia.modelos.Usuario;
import static es.manueldonoso.academia.util.Efectos_visuales.darMovimientoStage;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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

    /**
     * Muestra una nueva ventana para la creación o registro de un contrato.
     * Este método crea un nuevo `Stage` y carga una interfaz gráfica desde el
     * archivo FXML ubicado en `/vistas/Contrato_registro.fxml`. Configura la
     * escena con un título específico y define el comportamiento al cerrar la
     * ventana.
     *
     * Al mostrar la ventana, se establece un título en el `Stage` y se define
     * un manejador de eventos que detecta el cierre de la ventana, registrando
     * un mensaje en consola. En caso de que ocurra una excepción al cargar el
     * archivo FXML, se registra el error en el log.
     *
     */
    public static void Mostrar_crearContrato() {
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

    public static void Mostrar_Dasboard_Administrador(Connection conn) {
        try {
            Stage primaryStage = new Stage();
            // Cargo la ventana inicial
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.class.getResource("/vistas/DashBoard_Administrador.fxml"));

            // Ventana a cargar
            BorderPane ventana = (BorderPane) loader.load();

            DashBoard_AdministradorController dashBoard_AdministradorController = new DashBoard_AdministradorController();
            dashBoard_AdministradorController.SetConn(conn);

            // Creo la escena
            Scene scene = new Scene(ventana);

            // Modifico el stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("DashBoard Administrador");

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

    public static void Mostrar_Dasboard_Profesor(Connection conn) {

       try {
        Stage primaryStage = new Stage();
        // Cargo la ventana inicial
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(main.class.getResource("/vistas/DashBoard_Profesor.fxml"));

        // Ventana a cargar
        BorderPane ventana = (BorderPane) loader.load();

        // Obtén la instancia del controlador desde el FXMLLoader
        DashBoard_ProfesorController dashBoard_ProfesorController = loader.getController();

        
        // Configura la conexión en la instancia correcta
        dashBoard_ProfesorController.SetConn(conn);

        // Carga el usuario
        dashBoard_ProfesorController.CargarUsuario();

        // Creo la escena
        Scene scene = new Scene(ventana);

        // Modifico el stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("DashBoard Profesor");

        // Detectar cierre de ventana
        primaryStage.setOnHidden(event -> {
            System.out.println("Se cerró la ventana de configuración");
        });

        // Mostrar ventana
        primaryStage.show();
    } catch (IOException ex) {
        Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
    }

    }

    public static void Mostrar_Dasboard_Alumno(Connection conn) {
        try {
            Stage primaryStage = new Stage();
            // Cargo la ventana inicial
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.class.getResource("/vistas/DashBoard_Alumno.fxml"));

            // Ventana a cargar
            BorderPane ventana = (BorderPane) loader.load();
            DashBoard_AlumnoController dashboard_AlumnoController = new DashBoard_AlumnoController();
            dashboard_AlumnoController.SetConn(conn);

            // Creo la escena
            Scene scene = new Scene(ventana);

            // Modifico el stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("DashBoard Alumno");

            Efectos_visuales.darMovimientoStage(primaryStage);

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

    public static Cambio_ContrasenaController MostrarCambioPass(Pane root) {
        try {

            // Cargo la ventana inicial
            FXMLLoader loader = new FXMLLoader();
            Stage stage = new Stage();
            loader.setLocation(main.class.getResource("/vistas/Cambio_Contrasena.fxml"));

            // Ventana a cargar
            AnchorPane ventana = (AnchorPane) loader.load();

            // Obtener el controlador asociado
            Cambio_ContrasenaController controller = loader.getController();

            // Creo la escena
            Scene scene = new Scene(ventana);

            // Modifico el stage
            stage.setScene(scene);
            stage.initOwner(root.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            stage.setIconified(false);
            stage.initStyle(StageStyle.UNDECORATED);
            Efectos_visuales.darMovimientoStage(stage);
            stage.showAndWait();
            // Retorna el controlador para que pueda ser usado fuera de este método
            return controller;
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;// Retorna null en caso de error

    }

    public static void Mostrar_Mis_Datos(Connection conn, Pane root, Usuario user) throws IOException {
        // Cargo la ventana inicial
        FXMLLoader loader = new FXMLLoader();
        Stage stage = new Stage();
        loader.setLocation(main.class.getResource("/vistas/MisDatos.fxml"));

        // Ventana a cargar
        AnchorPane ventana = (AnchorPane) loader.load();

        // Creo la escena
        Scene scene = new Scene(ventana);

        // Obtener el controlador asociado
        MisDatosController controller = loader.getController();
        controller.setConn(conn);
       

        try {
            Usuario u=Base_datos.BuscarUsuario_Usuario(conn, Session.getUsuario());
            controller.CargarDatos(user);
        } catch (SQLException ex) {
            Logger.getLogger(Stage_show.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Modifico el stage
        stage.setScene(scene);
        stage.initOwner(root.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.setIconified(false);
        stage.initStyle(StageStyle.UNDECORATED);
        darMovimientoStage(stage);

         // Detectar cierre de ventana
        stage.setOnHidden(event -> {
            System.out.println("Se cerró la ventana de Datos");
        });
        
        stage.showAndWait();

    }

}
