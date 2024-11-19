/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academia.util;

import es.manueldonoso.academia.controller.Asignaturas_PanelController;
import es.manueldonoso.academia.controller.BuscarUsuarioController;
import es.manueldonoso.academia.controller.Cambio_ContrasenaController;
import es.manueldonoso.academia.controller.DashBoard_AdministradorController;
import es.manueldonoso.academia.controller.DashBoard_AlumnoController;
import es.manueldonoso.academia.controller.DashBoard_ProfesorController;
import es.manueldonoso.academia.controller.MisDatosController;
import es.manueldonoso.academia.controller.Registrar_usuario_nuevoController;
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
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
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
            
            DashBoard_AdministradorController dashBoard_AdministradorController = loader.getController();
            dashBoard_AdministradorController.SetConn(conn);

            // Carga el usuario
            dashBoard_AdministradorController.CargarUsuario();

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
            
            darMovimientoStage(primaryStage);
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
    
    public static boolean Mostrar_Mis_Datos(Connection conn, Pane root, Usuario user) throws IOException {
        // Variable para el estado del cierre
        final boolean[] ventanaCerradaCorrectamente = {false};

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
            Usuario u = Base_datos.BuscarUsuario_Usuario(conn, Session.getUsuario());
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

        // Detectar cierre de ventana y actualizar el estado
        stage.setOnHidden(event -> {
            System.out.println("Se cerró la ventana de Datos");
            ventanaCerradaCorrectamente[0] = true; // Indicar que se cerró correctamente
        });
        
        stage.showAndWait();

        // Retornar el estado
        return ventanaCerradaCorrectamente[0];
        
    }
    
    public static void cargar_asignaturaPanel(Connection conn, Pane pane) {
        // Cargar el archivo FXML de la vista asignatura
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(main.class.getResource("/vistas/Asignaturas_Panel.fxml"));
        
        try {
            // Cargar el contenido desde el FXML
            Node contenido = loader.load();
            Asignaturas_PanelController controller = loader.getController();
            controller.setConn(conn);
            controller.CargarAct();
            controller.CargarDes();

            // Asegurar que el contenido cargado tenga el fondo transparente
            if (contenido instanceof Pane) {
                ((Pane) contenido).setStyle("-fx-background-color: transparent;");
            }

            // Verificar si el contenido cargado es una instancia de Region para ajustar el tamaño
            if (contenido instanceof Region) {
                Region region = (Region) contenido;
                region.prefWidthProperty().bind(pane.widthProperty());
                region.prefHeightProperty().bind(pane.heightProperty());
            }

            // Limpiar el contenido actual del pane y añadir el nuevo
            pane.getChildren().clear();
            pane.getChildren().add(contenido);
            
        } catch (IOException ex) {
            Logger.getLogger(Stage_show.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void cargar_BuscarUsuaroPanel(Connection conn, Pane pane) {
        // Cargar el archivo FXML de la vista asignatura
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(main.class.getResource("/vistas/BuscarUsuario.fxml"));
        
        try {
            // Cargar el contenido desde el FXML
            Node contenido = loader.load();
            BuscarUsuarioController controller = loader.getController();
            controller.setConn(conn);

            // Asegurar que el contenido cargado tenga el fondo transparente
            if (contenido instanceof Pane) {
                ((Pane) contenido).setStyle("-fx-background-color: transparent;");
            }

            // Verificar si el contenido cargado es una instancia de Region para ajustar el tamaño
            if (contenido instanceof Region) {
                Region region = (Region) contenido;
                region.prefWidthProperty().bind(pane.widthProperty());
                region.prefHeightProperty().bind(pane.heightProperty());
            }

            // Limpiar el contenido actual del pane y añadir el nuevo
            pane.getChildren().clear();
            pane.getChildren().add(contenido);
            
        } catch (IOException ex) {
            Logger.getLogger(Stage_show.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Registrar_usuario_nuevoController Mostrar_Registrar_usuario_nuevo(Connection conn, Pane root) {
        try {

            // Cargo la ventana inicial
            FXMLLoader loader = new FXMLLoader();
            Stage stage = new Stage();
            loader.setLocation(main.class.getResource("/vistas/Registrar_usuario_nuevo.fxml"));

            // Ventana a cargar
            AnchorPane ventana = (AnchorPane) loader.load();

            // Obtener el controlador asociado
            Registrar_usuario_nuevoController controller = loader.getController();
            controller.setConn(conn);
            controller.setCB_asignaturas();

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
}
