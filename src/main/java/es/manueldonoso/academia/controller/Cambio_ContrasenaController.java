/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import es.manueldonoso.academia.util.Efectos_visuales;
import es.manueldonoso.academia.util.Session;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Cambio_ContrasenaController implements Initializable {

    @FXML
    private JFXButton btn_aceptar;
    @FXML
    private JFXPasswordField pf_contrasena;
    @FXML
    private JFXPasswordField pf_contrasena_rep;
    @FXML
    private AnchorPane root;

    // Variable para indicar si se ha pulsado el botón aceptar
    private boolean botonAceptarPulsado = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Deshabilitar el botón aceptar al inicio
        btn_aceptar.setDisable(true);

        // Listener para verificar las contraseñas en ambos campos
        ChangeListener<String> passwordListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                verificarContrasenas();
            }
        };

        // Agregar el listener a ambos campos de contraseña
        pf_contrasena.textProperty().addListener(passwordListener);
        pf_contrasena_rep.textProperty().addListener(passwordListener);
    }

    @FXML
    private void btn_cancelar(ActionEvent event) {
        Efectos_visuales.closeEffectReducion(root);
    }

    @FXML
    private void btn_aceptar(ActionEvent event) {
        botonAceptarPulsado = true;  // Actualizar el estado al pulsar el botón
        Efectos_visuales.closeEffectReducion(root);
    }

    private void verificarContrasenas() {
        // Habilitar el botón si ambas contraseñas coinciden y no están vacías
        boolean sonIguales = !pf_contrasena.getText().isEmpty()
                && pf_contrasena.getText().equals(pf_contrasena_rep.getText());
        btn_aceptar.setDisable(!sonIguales);
    }

    // Método para consultar si se ha pulsado el botón aceptar
    public boolean isBotonAceptarPulsado() {
        Session.setPass(pf_contrasena.getText());
        return botonAceptarPulsado;
    }

    // Método opcional para reiniciar el estado del botón aceptar si es necesario
    public void resetBotonAceptar() {
        botonAceptarPulsado = false;
    }
}
