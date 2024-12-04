/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.glass.ui.GlassRobot;
import es.manueldonoso.academia.modelos.Usuario;
import es.manueldonoso.academia.util.Acciones;
import es.manueldonoso.academia.util.Alerta;
import es.manueldonoso.academia.util.Base_datos;
import es.manueldonoso.academia.util.Efectos_visuales;
import es.manueldonoso.academia.util.validaciones;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Registrar_usuario_nuevoController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField tf_nombre;
    @FXML
    private JFXTextField tf_apellido;
    @FXML
    private JFXTextField tf_dir;
    @FXML
    private JFXTextField tf_tel;
    @FXML
    private JFXTextField tf_email;
    @FXML
    private ToggleGroup tipo;
    @FXML
    private JFXComboBox<String> cb_asignatura;
    @FXML
    private VBox panel_lista_asignatura;

    private Connection conn;
    @FXML
    private JFXTextField tf_Usuario;

    List<String> listaSeleccionados = new ArrayList<>();
    @FXML
    private JFXRadioButton rb_administrador;
    @FXML
    private JFXRadioButton rb_profesor;
    @FXML
    private JFXRadioButton rb_alumno;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Efectos_visuales.setBackgroundImage(root, "/images/app/fondo_papiro.jpg");
        Platform.runLater(() -> root.requestFocus());
        add_listener_focusoff_TF();

    }

    @FXML
    private void acep_btn_enable(KeyEvent event) {
    }

    @FXML
    private void btn_agregar_Asignatura(ActionEvent event) {
        String seleccion = cb_asignatura.getSelectionModel().getSelectedItem();

        if (seleccion != null && !listaSeleccionados.contains(seleccion)) {
            // Agregar el elemento a la lista
            listaSeleccionados.add(seleccion);
            System.out.println("Elemento agregado: " + seleccion);

            // Crear un JFXTextField asociado
            JFXTextField textField = new JFXTextField(seleccion);
            textField.setEditable(false); // Para que no se pueda editar
            //textField.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: #cccccc; -fx-border-radius: 5; -fx-background-radius: 5;");

            // Agregar un evento para eliminar el elemento al hacer clic en el JFXTextField
            textField.setOnMouseClicked(e -> {
                listaSeleccionados.remove(seleccion); // Eliminar de la lista
                panel_lista_asignatura.getChildren().remove(textField); // Eliminar del panel
                System.out.println("Elemento eliminado: " + seleccion);
            });

            // Agregar el JFXTextField al panel
            panel_lista_asignatura.getChildren().add(textField);
        } else if (seleccion == null) {
            System.out.println("No se ha seleccionado ningún elemento.");
        } else {
            System.out.println("El elemento ya está en la lista.");
        }
        cb_asignatura.getSelectionModel().clearSelection();
    }

    @FXML
    private void btn_aceptar(ActionEvent event) {

        if (!tf_Usuario.getText().isBlank()) {
            GuardarUsuario();
            Efectos_visuales.closeEffectReducion(root);

        } else {
            validacionesDatos();
        }
    }

    @FXML
    private void btn_cancelar(ActionEvent event) {
        Efectos_visuales.closeEffectReducion(root);
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void generarUsuario() {
        tf_Usuario.setText(Acciones.generaUsuario(conn, tf_nombre.getText(), tf_apellido.getText()));
    }

    public void add_listener_focusoff_TF() {

        JFXTextField nombre = tf_nombre;
        JFXTextField apellido = tf_apellido;

// Agregar un listener para detectar cuando pierde el foco
        nombre.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                // Acción cuando pierde el foco
                System.out.println("El campo perdió el foco. Valor actual: " + nombre.getText());

                if (nombre.getLength() > 2 && apellido.getLength() > 2) {
                    generarUsuario();
                }

                // Ejemplo de validación
                if (nombre.getText().isEmpty()) {
                    System.out.println("El campo está vacío");
                }
            }
        });

// Agregar un listener para detectar cuando pierde el foco
        apellido.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                // Acción cuando pierde el foco
                System.out.println("El campo perdió el foco. Valor actual: " + apellido.getText());

                if (nombre.getLength() > 2 && apellido.getLength() > 2) {
                    generarUsuario();
                }
                // Ejemplo de validación
                if (apellido.getText().isEmpty()) {
                    System.out.println("El campo está vacío");
                }
            }
        });

    }

    public void setCB_asignaturas() {
        cb_asignatura.getItems().addAll(Base_datos.obtenerAsignaturasActivas(conn));

    }

    private void GuardarUsuario() {
        Usuario user = new Usuario();

        user.setUsuario(tf_Usuario.getText());
        user.setNombre(tf_Usuario.getText());
        user.setApellidos(tf_apellido.getText());
        user.setEmail(tf_email.getText());
        user.setDireccion(tf_dir.getText());
        user.setPass("cambiame");
        String tipo = "";
        if (rb_administrador.isSelected()) {
            tipo = "ADMINISTRADOR";
        }
        if (rb_profesor.isSelected()) {
            tipo = "PROFESOR";
        }
        if (rb_alumno.isSelected()) {
            tipo = "ALUMNO";
        }

        user.setTelefono(tf_tel.getText());
        switch (tipo) {
            case "ADMINISTRADOR":
                user.setTipo(Usuario.TipoUsuario.ADMINISTRADOR);
                break;
            case "PROFESOR":
                user.setTipo(Usuario.TipoUsuario.PROFESOR);
                break;
            case "ALUMNO":
                user.setTipo(Usuario.TipoUsuario.ALUMNO);
                break;
            default:
                throw new AssertionError();
        }

        user.setAsignaturas(listaSeleccionados);
        try {
            Base_datos.GuardarUsuarioNuevo(conn, user);
        } catch (SQLException ex) {
            Logger.getLogger(Registrar_usuario_nuevoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(user.toString());
    }

    private boolean validacionesDatos() {
        String mensaje = "";
        String nombre = tf_nombre.getText();
        String Apellidos = tf_apellido.getText();
        String Email = tf_email.getText();
        String telefono = tf_tel.getText();
        System.out.println(validaciones.min3letras(nombre));
        if (!validaciones.min3letras(nombre)) {
            mensaje += " Error en los datos Nombre";
        }

        if (!validaciones.min3letras(Apellidos)) {
            if (!mensaje.isEmpty()) {
                mensaje += "\n ";
            }
            mensaje += "Error en los datos Apellidos";
        }
        if (!tf_tel.getText().isEmpty() && !validaciones.telefono(telefono)) {
            if (!mensaje.isEmpty()) {
                mensaje += "\n ";
            }
            mensaje += "Error en la Telefono";
        }

        if (tf_dir.getText().isEmpty()) {
            if (!mensaje.isEmpty()) {
                mensaje += "\n ";
            }
            mensaje += "Error en la Dirección";
        }

        if (!validaciones.Email(Email)) {
            if (!mensaje.isEmpty()) {
                mensaje += "\n";
            }
            mensaje += " Error en los datos Email";
        }

        if (!mensaje.isBlank()) {
            Alerta.Error("Error", "Error en captura de datos", mensaje);
        }
        return mensaje.isEmpty();
    }

}
