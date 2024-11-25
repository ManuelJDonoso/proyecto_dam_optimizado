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
import es.manueldonoso.academia.controller.ModificarDatosUsuarioController;
import es.manueldonoso.academia.controller.PanelMaterialController;
import es.manueldonoso.academia.controller.Panel_ExamenMenuController;
import es.manueldonoso.academia.controller.Panel_SubirMaterialController;
import es.manueldonoso.academia.controller.Panel_generar_ExamenController;
import es.manueldonoso.academia.controller.Panel_preguntaController;
import es.manueldonoso.academia.controller.Plantilla_preguntaController;
import es.manueldonoso.academia.controller.Registrar_usuario_nuevoController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import es.manueldonoso.academia.main.main;
import es.manueldonoso.academia.modelos.Pregunta;
import es.manueldonoso.academia.modelos.Usuario;
import static es.manueldonoso.academia.util.Efectos_visuales.darMovimientoStage;
import java.sql.Connection;
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
 * La clase `Stage_show` se encarga de gestionar la visualización de diferentes
 * ventanas (Stages) en la aplicación, incluyendo ventanas de login, dashboards,
 * y paneles de gestión de datos. Utiliza archivos FXML para cargar las
 * interfaces de usuario y configurar el comportamiento de las ventanas.
 *
 * La clase también maneja la creación de escenas, el estilo visual de las
 * ventanas y la gestión de efectos visuales. Cada método corresponde a una
 * ventana o panel específico de la aplicación.
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Stage_show {

    //-----------------------------------------Comun-----------------------------------------------------------
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
            loader.setLocation(main.class.getResource("/vistas/main/Login.fxml"));

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
            loader.setLocation(main.class.getResource("/vistas/comun/Contrato_registro.fxml"));

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

    /**
     * Muestra una ventana de cambio de contraseña en una nueva ventana (Stage).
     *
     * Este método crea un `Stage` para mostrar la ventana de cambio de
     * contraseña utilizando un archivo FXML. El controlador asociado es
     * retornado para que pueda ser utilizado fuera del método.
     *
     * @param root El `Pane` de la ventana actual.
     * @return El controlador del cambio de contraseña.
     */
    public static Cambio_ContrasenaController MostrarCambioPass(Pane root) {
        try {

            // Cargo la ventana inicial
            FXMLLoader loader = new FXMLLoader();
            Stage stage = new Stage();
            loader.setLocation(main.class.getResource("/vistas/comun/Cambio_Contrasena.fxml"));

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

    /**
     * Muestra una ventana para modificar los datos del usuario.
     *
     * Este método carga el archivo FXML correspondiente a la ventana de
     * modificación de datos del usuario. Al cerrar la ventana, se actualiza el
     * estado del cierre y se retorna un valor indicando si la ventana se cerró
     * correctamente.
     *
     * @param conn Conexión a la base de datos.
     * @param root El `Pane` de la ventana actual.
     * @param user El objeto de tipo `Usuario` que contiene los datos del
     * usuario.
     * @return Un valor booleano indicando si la ventana se cerró correctamente.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    public static boolean Mostrar_Mis_Datos(Connection conn, Pane root, Usuario user) throws IOException {
        // Variable para el estado del cierre
        final boolean[] ventanaCerradaCorrectamente = {false};

        // Cargo la ventana inicial
        FXMLLoader loader = new FXMLLoader();
        Stage stage = new Stage();
        loader.setLocation(main.class.getResource("/vistas/comun/MisDatos.fxml"));

        // Ventana a cargar
        AnchorPane ventana = (AnchorPane) loader.load();

        // Creo la escena
        Scene scene = new Scene(ventana);

        // Obtener el controlador asociado
        MisDatosController controller = loader.getController();
        controller.setConn(conn);

        controller.CargarDatos(user);

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

    //------------------------------administrador--------------------------------------
    /**
     * Muestra el dashboard del administrador en una nueva ventana.
     *
     * Este método crea un `Stage` para mostrar la interfaz correspondiente al
     * dashboard del administrador. En caso de error, se captura y se registra
     * la excepción.
     *
     * @param conn Conexión a la base de datos.
     */
    public static void Mostrar_Dasboard_Administrador(Connection conn) {
        try {
            Stage primaryStage = new Stage();
            // Cargo la ventana inicial
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.class.getResource("/vistas/administrador/DashBoard_Administrador.fxml"));

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
            primaryStage.setMaximized(true);

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

    /**
     * Carga el panel de asignaturas en el `Pane` especificado.
     *
     * Este método carga el archivo FXML correspondiente al panel de asignaturas
     * y lo inserta en el `Pane`. Si el contenido cargado es una instancia de
     * `Region`, ajusta el tamaño al tamaño del `Pane`.
     *
     * @param conn Conexión a la base de datos.
     * @param pane El `Pane` donde se cargará el contenido.
     */
    public static void cargar_asignaturaPanel(Connection conn, Pane pane) {
        // Cargar el archivo FXML de la vista asignatura
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(main.class.getResource("/vistas/administrador/Asignaturas_Panel.fxml"));

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

    /**
     * Carga el panel de la vista "BuscarUsuario" y lo inserta en el pane
     * proporcionado.
     *
     * @param conn La conexión a la base de datos.
     * @param pane El pane en el que se insertará el contenido cargado.
     */
    public static void cargar_BuscarUsuaroPanel(Connection conn, Pane pane) {
        // Cargar el archivo FXML de la vista asignatura
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(main.class.getResource("/vistas/administrador/BuscarUsuario.fxml"));

        try {
            // Cargar el contenido desde el FXML
            Node contenido = loader.load();
            BuscarUsuarioController controller = loader.getController();
            controller.setConn(conn);
            controller.iniciar();

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

    /**
     * Muestra una ventana para registrar un nuevo usuario y retorna el
     * controlador asociado a la vista.
     *
     * @param conn La conexión a la base de datos.
     * @param root El pane raíz desde el cual se abre la ventana.
     * @return El controlador asociado a la ventana de registro de usuario, o
     * null si ocurre un error.
     */
    public static Registrar_usuario_nuevoController Mostrar_Registrar_usuario_nuevo(Connection conn, Pane root) {
        try {

            // Cargo la ventana inicial
            FXMLLoader loader = new FXMLLoader();
            Stage stage = new Stage();
            loader.setLocation(main.class.getResource("/vistas/administrador/Registrar_usuario_nuevo.fxml"));

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

    /**
     * Muestra una ventana para modificar los datos de un usuario específico.
     *
     * @param conn La conexión a la base de datos.
     * @param root El pane raíz desde el cual se abre la ventana.
     * @param user El nombre de usuario cuyos datos se van a modificar.
     */
    public static void Mostrar_ModificarDastos(Connection conn, Pane root, String user) {
        // Variable para el estado del cierre
        final boolean[] ventanaCerradaCorrectamente = {false};

        // Cargo la ventana inicial
        FXMLLoader loader = new FXMLLoader();
        Stage stage = new Stage();
        loader.setLocation(main.class.getResource("/vistas/administrador/ModificarDatosUsuario.fxml"));

        // Ventana a cargar
        AnchorPane ventana = null;
        try {
            ventana = (AnchorPane) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(Stage_show.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Creo la escena
        Scene scene = new Scene(ventana);

        // Obtener el controlador asociado
        ModificarDatosUsuarioController controller = loader.getController();
        controller.setConn(conn);
        controller.loadUserData(user);
        controller.setCB_asignaturas();

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
            System.out.println("Se cerró la ventana de modificar Datos");
            ventanaCerradaCorrectamente[0] = true; // Indicar que se cerró correctamente
        });

        stage.showAndWait();

    }

    //-------------------------profesor---------------------------------
    /**
     * Muestra el dashboard del profesor en una nueva ventana.
     *
     * Este método crea un `Stage` para mostrar la interfaz correspondiente al
     * dashboard del profesor. Si ocurre un error durante el proceso, se captura
     * y se registra en el log.
     *
     * @param conn Conexión a la base de datos.
     */
    public static void Mostrar_Dasboard_Profesor(Connection conn) {

        try {
            Stage primaryStage = new Stage();
            // Cargo la ventana inicial
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.class.getResource("/vistas/profesor/DashBoard_Profesor.fxml"));

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
            primaryStage.setMaximized(true);

            // Detectar cierre de ventana
            primaryStage.setOnHidden(event -> {
                System.out.println("Se cerró la ventana de configuración");
            });

            darMovimientoStage(primaryStage);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            // Mostrar ventana
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Carga el panel para subir materiales y lo inserta en el pane
     * proporcionado.
     *
     * @param conn La conexión a la base de datos.
     * @param pane El pane en el que se insertará el contenido cargado.
     * @param user El usuario para cargar las asignaturas relacionadas.
     */
    public static void cargar_MaterialPanel(Connection conn, Pane pane, Usuario user) {
        // Cargar el archivo FXML de la vista asignatura
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(main.class.getResource("/vistas/profesor/Panel_SubirMaterial.fxml"));

        try {
            // Cargar el contenido desde el FXML
            Node contenido = loader.load();
            Panel_SubirMaterialController controller = loader.getController();
            controller.setConn(conn);
            controller.loadCbAsignaturas(user);
            controller.cargarDatosDesdeBaseDeDatos();

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

    /**
     * Carga el panel para generar un examen y lo inserta en el pane
     * proporcionado.
     *
     * @param conn La conexión a la base de datos.
     * @param pane El pane en el que se insertará el contenido cargado.
     * @param user El usuario para cargar las asignaturas relacionadas.
     */
    public static void cargar_Generar_ExamenPanel(Connection conn, Pane pane, Usuario user) {
        // Cargar el archivo FXML de la vista asignatura
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(main.class.getResource("/vistas/profesor/Panel_generar_Examen.fxml"));

        try {
            // Cargar el contenido desde el FXML
            Node contenido = loader.load();
            Panel_generar_ExamenController controller = loader.getController();
            controller.setConn(conn);
            controller.cargarCB(user);

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

    /**
     * Añade un nuevo panel de pregunta al VBox proporcionado.
     *
     * @param preguntas El VBox en el que se añadirá el panel de pregunta.
     * @param numeroPregunta El número de la pregunta que se cargará en el
     * panel.
     */
    public static void anadirPanelPreguntas(VBox preguntas, int numeroPregunta) {
        // Cargar el archivo FXML de la vista asignatura
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(main.class.getResource("/vistas/profesor/Panel_pregunta.fxml"));

        try {
            // Cargar el contenido desde el FXML
            Node contenido = loader.load();
            Panel_preguntaController controller = loader.getController();
            controller.cargarpregunta(numeroPregunta);

            // Asegurar que el contenido cargado tenga el fondo transparente
            if (contenido instanceof Pane) {
                ((Pane) contenido).setStyle("-fx-background-color: transparent;");
            }

            // Verificar si el contenido cargado es una instancia de Region para ajustar el tamaño
            if (contenido instanceof Region) {
                Region region = (Region) contenido;
                region.prefWidthProperty().bind(preguntas.widthProperty());
            }

            // Añadir el contenido al VBox
            preguntas.getChildren().add(contenido);
            preguntas.setSpacing(30); //esoacui ebtre paneles

        } catch (IOException ex) {
            Logger.getLogger(Stage_show.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //------------------------Alumno-----------------------------------------
    /**
     * Muestra el dashboard del alumno en una nueva ventana.
     *
     * Este método crea un `Stage` para mostrar la interfaz correspondiente al
     * dashboard del alumno.
     *
     * @param conn Conexión a la base de datos.
     */
    public static void Mostrar_Dasboard_Alumno(Connection conn) {
        try {
            Stage primaryStage = new Stage();
            // Cargo la ventana inicial
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.class.getResource("/vistas/alumno/DashBoard_Alumno.fxml"));

            // Ventana a cargar
            BorderPane ventana = (BorderPane) loader.load();
            DashBoard_AlumnoController dashboard_AlumnoController = loader.getController();
            dashboard_AlumnoController.SetConn(conn);
            dashboard_AlumnoController.CargarUsuario();

            // Creo la escena
            Scene scene = new Scene(ventana);

            // Modifico el stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("DashBoard Alumno");

            //Efectos_visuales.darMovimientoStage(primaryStage);
            //detectar cierre de ventana
            primaryStage.setOnHidden(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {

                    System.out.println("se cerro la ventana de configuración");
                    //comprobarInstalacion();

                }
            });

            primaryStage.setMaximized(true);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            //mostrar ventana
            primaryStage.show();

            // primaryStage.setMaximized(true);
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Carga el panel de materiales para el alumno y lo inserta en el pane
     * proporcionado.
     *
     * @param conn La conexión a la base de datos.
     * @param pane El pane en el que se insertará el contenido cargado.
     * @param user El usuario para cargar los materiales relacionados.
     */
    public static void cargar_MaterialPanelAlumno(Connection conn, Pane pane, Usuario user) {
        // Cargar el archivo FXML de la vista asignatura
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(main.class.getResource("/vistas/alumno/PanelMaterial.fxml"));

        try {
            // Cargar el contenido desde el FXML
            Node contenido = loader.load();
            PanelMaterialController controller = loader.getController();
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

    /**
     * Carga el panel del menú de exámenes y lo inserta en el pane
     * proporcionado.
     *
     * @param pane El pane en el que se insertará el contenido cargado.
     * @param conn La conexión a la base de datos.
     */
    public static void cargar_Panel_Examen(Pane pane, Connection conn, Usuario user) {
        // Cargar el archivo FXML de la vista asignatura
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(main.class.getResource("/vistas/alumno/Panel_ExamenMenu.fxml"));

        try {
            // Cargar el contenido desde el FXML
            Node contenido = loader.load();
            Panel_ExamenMenuController controller = loader.getController();
            controller.setConn(conn);
            controller.setUser(user);
            controller.loadDatos();

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

    /**
     * Carga la vista de una pregunta específica y la inserta en el pane
     * proporcionado.
     *
     * @param pane El pane en el que se insertará el contenido cargado.
     * @param p El objeto Pregunta que contiene los datos de la pregunta a
     * cargar.
     */
    public static void cargar_Pregunta(Pane pane, Pregunta p) {
        // Cargar el archivo FXML de la vista asignatura
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(main.class.getResource("/vistas/alumno/Plantilla_pregunta.fxml"));
        Plantilla_preguntaController controller = null;
        try {
            // Cargar el contenido desde el FXML
            Node contenido = loader.load();
            controller = loader.getController();
            controller.CargarPregunta(p);

            contenido.getProperties().put("controller", loader);
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
            // pane.getChildren().clear();
            pane.getChildren().add(contenido);

        } catch (IOException ex) {
            Logger.getLogger(Stage_show.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
