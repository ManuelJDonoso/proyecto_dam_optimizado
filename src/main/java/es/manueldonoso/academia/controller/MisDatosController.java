/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller;

import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import es.manueldonoso.academia.util.Acciones;
import es.manueldonoso.academia.util.Alerta;
import es.manueldonoso.academia.util.Efectos_visuales;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import es.manueldonoso.academia.util.validaciones;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class MisDatosController implements Initializable {
    
    @FXML
    private AnchorPane root;
    @FXML
    private ImageView iv_foto;
    @FXML
    private JFXButton btn_aceptar;
    
    private byte[] foto;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Efectos_visuales.setBackgroundImage(root, "/images/app/fondo2.jpg");
        btn_aceptar.setDisable(true);
        
        Platform.runLater(() -> root.requestFocus());
    }
    
    @FXML
    private void acep_btn_enable(KeyEvent event) {
        btn_aceptar.setDisable(false);
        
    }
    
    @FXML
    private void btn_aceptar(ActionEvent event) {
        if (validacionesDatos()) {
            
            if(Alerta.Confirmacion("Advertencia", "Modificacion de datos", "¿Desea Continuar?"))
            Efectos_visuales.closeEffectReducion(root);
        }
        
    }
    
    @FXML
    private void tbn_cancelar(ActionEvent event) {
        Efectos_visuales.closeEffectReducion(root);
    }
    
    @FXML
    private void btn_cargar(ActionEvent event) {
        Stage stage = new Stage();
        foto = Acciones.ImageToByte(stage);
        Acciones.imagenView_cambiarImage(this.getClass(), iv_foto, foto);
    }
    
    @FXML
    private void btn_eliminar(ActionEvent event) {
        foto = null;
        Acciones.imagenView_cambiarImage(this.getClass(), iv_foto, "src/main/resources/images/app/incorgnito.png");
    }
    
    private BooleanProperty estadoCamara = new SimpleBooleanProperty(false);
    private AtomicReference<Webcam> selWebCam = new AtomicReference<>(null); // Usar AtomicReference para Webcam
    private AtomicReference<BufferedImage> bufferedImage = new AtomicReference<>(null); // Usar AtomicReference para BufferedImage
    private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();
    
    @FXML
    private void btn_Tomar(ActionEvent event) {
        foto = Acciones.CapturarFoto(iv_foto, estadoCamara, selWebCam, bufferedImage, imageProperty);
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
