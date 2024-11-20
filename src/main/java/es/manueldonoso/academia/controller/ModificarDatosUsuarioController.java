/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller;

import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import es.manueldonoso.academia.modelos.Usuario;
import es.manueldonoso.academia.util.Acciones;
import es.manueldonoso.academia.util.Base_datos;
import es.manueldonoso.academia.util.Efectos_visuales;
import es.manueldonoso.academia.util.utilidades;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class ModificarDatosUsuarioController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField tf_Usuario;
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
    private DatePicker dp_baja;
    @FXML
    private JFXButton btn_darBaja;
    @FXML
    private JFXComboBox<String> cb_asignatura;
    @FXML
    private VBox panel_lista_asignatura;
    @FXML
    private JFXButton btn_aceptar;
    @FXML
    private ImageView iv_foto;
    Connection conn;
    @FXML
    private DatePicker dp_alta;
    @FXML
    private HBox panel_Falta;
    @FXML
    private HBox panel_fechaBaja;

    private byte[] foto;

    List<String> listaSeleccionados = new ArrayList<>();
    @FXML
    private JFXRadioButton rb_administrador;
    @FXML
    private ToggleGroup tipo;
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
        System.out.println("Mostrando pantalla Modificar Datos");

        Platform.runLater(() -> root.requestFocus());

    }

    @FXML
    private void acep_btn_enable(KeyEvent event) {
    }

    @FXML
    private void btn_darBaja(ActionEvent event) {
        dp_baja.setValue(LocalDate.now());

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
        GuardarUsuario();
        Efectos_visuales.closeEffectReducion(root);
    }

    @FXML
    private void btn_cancelar(ActionEvent event) {
        Efectos_visuales.closeEffectReducion(root);
    }

    @FXML
    private void btn_cargar(ActionEvent event) {
        Stage stage = new Stage();
        byte[] fototomada = Acciones.ImageToByte(stage);

        if (fototomada.length == 0) {
            // Si no se seleccionó archivo, deshabilitar el botón

        } else {
            // Si se seleccionó un archivo, habilitar el botón y actualizar la imagen
            foto = fototomada;
            btn_aceptar.setDisable(false);
            Acciones.imagenView_cambiarImage(this.getClass(), iv_foto, foto);
        }
    }

    private BooleanProperty estadoCamara = new SimpleBooleanProperty(false);
    private AtomicReference<Webcam> selWebCam = new AtomicReference<>(null); // Usar AtomicReference para Webcam
    private AtomicReference<BufferedImage> bufferedImage = new AtomicReference<>(null); // Usar AtomicReference para BufferedImage
    private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();

    @FXML
    private void btn_eliminarFoto(ActionEvent event) {
        foto = null;
        Acciones.imagenView_cambiarImage(this.getClass(), iv_foto, "src/main/resources/images/app/incorgnito.png");
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void loadUserData(String user) {

        try {
            Usuario u = Base_datos.BuscarUsuario_Usuario(conn, user);
            tf_Usuario.setText(u.getUsuario());
            tf_nombre.setText(u.getNombre());
            tf_apellido.setText(u.getApellidos());
            tf_dir.setText(u.getDireccion());
            tf_email.setText(u.getEmail());
            tf_tel.setText(u.getTelefono());

            if (u.getFechaAlta() != null) {
                dp_alta.setValue(u.getFechaAlta().toLocalDate());
            }

            if (u.getFechaBaja() != null) {
                dp_baja.setValue(u.getFechaBaja().toLocalDate());
            }

            switch (u.getTipo().toString()) {
                case "ADMINISTRADOR":
                    rb_administrador.setSelected(true);
                    break;
                case "PROFESOR":
                    rb_profesor.setSelected(true);
                    break;
                case "ALUMNO":
                    rb_alumno.setSelected(true);
                    break;
                default:
                    throw new AssertionError();
            }
            foto = u.getFoto();
            if (foto != null) {
                Acciones.imagenView_cambiarImage(this.getClass(), iv_foto, foto);
            } else {
                Acciones.imagenView_cambiarImage(this.getClass(), iv_foto, "src/main/resources/images/app/incorgnito.png");
            }

            //Cargar asignaturas vista
            List<String> Asignaturas = u.getAsignaturas();
            
            

            System.out.println(Asignaturas);
            if (Asignaturas !=null) {
                listaSeleccionados.addAll(Asignaturas);
                for (String seleccion : Asignaturas) {
                // Crear un JFXTextField asociado
                JFXTextField textField = new JFXTextField(seleccion);
                textField.setEditable(false); // Para que no se pueda editar
                textField.setAlignment(Pos.CENTER);
                textField.setStyle("-fx-text-fill: white");

                // Agregar un evento para eliminar el elemento al hacer clic en el JFXTextField
                textField.setOnMouseClicked(e -> {
                    listaSeleccionados.remove(seleccion); // Eliminar de la lista
                    panel_lista_asignatura.getChildren().remove(textField); // Eliminar del panel
                    System.out.println("Elemento eliminado: " + seleccion);
                });

                // Agregar el JFXTextField al panel
                panel_lista_asignatura.getChildren().add(textField);
            }
                
            }

        } catch (SQLException ex) {
            //Logger.getLogger(ModificarDatosUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btn_foto(ActionEvent event) {
        foto = Acciones.CapturarFoto(iv_foto, estadoCamara, selWebCam, bufferedImage, imageProperty);
    }

    public void setCB_asignaturas() {
        cb_asignatura.getItems().addAll(Base_datos.obtenerAsignaturasActivas(conn));

    }

    private void GuardarUsuario() {
        Usuario user = new Usuario();

        user.setUsuario(tf_Usuario.getText());
        user.setNombre(tf_nombre.getText());
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
        user.setTelefono(tf_tel.getText());
        user.setAsignaturas(listaSeleccionados);

        user.setFoto(foto);
        System.out.println(user.toString());
        if (dp_alta.getValue() != null) {
            user.setFechaAlta(Date.valueOf(dp_alta.getValue()));
        }
        if (dp_baja.getValue() != null) {
            user.setFechaBaja(Date.valueOf(dp_baja.getValue()));
        }
        System.out.println(conn);
        Base_datos.ActualizarUsuario(conn, user);
    }
}
