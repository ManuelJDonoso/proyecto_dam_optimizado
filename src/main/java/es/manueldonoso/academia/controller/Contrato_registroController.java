/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import es.manueldonoso.academia.modelos.Usuario;
import es.manueldonoso.academia.util.Efectos_visuales;
import es.manueldonoso.academia.util.Base_datos;
import es.manueldonoso.academia.util.Contrato;
import static es.manueldonoso.academia.util.PDFGenerator.generatePDF;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Contrato_registroController implements Initializable {

    @FXML
    private GridPane root;
    @FXML
    private JFXTextArea TA_contrato;
    @FXML
    private JFXCheckBox ck_aceptar;
    @FXML
    private JFXButton btn_cancelar;
    @FXML
    private JFXButton btn_Finalizar;
    @FXML
    private JFXTextField tf_nombre;
    @FXML
    private JFXTextField tf_user;
    @FXML
    private JFXTextField tf_apellido;
    @FXML
    private JFXTextField tf_email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TA_contrato.setText(Contrato.getContrato());//cargar el Contrato en el text area
    }

    /**
     * Habilita o deshabilita el botón de finalización según las condiciones de
     * entrada.
     *
     * Este método verifica si se cumplen todos los requisitos para habilitar el
     * botón de finalización (`btn_Finalizar`). Las condiciones incluyen que el
     * checkbox de aceptación (`ck_aceptar`) esté seleccionado y que los campos
     * de texto de nombre, apellido, usuario y correo electrónico no estén
     * vacíos. Si se cumplen todas las condiciones, el botón se habilita; de lo
     * contrario, permanece deshabilitado.
     */
    @FXML
    private void instalar() {
        boolean condicionesCumplidas = ck_aceptar.isSelected()
                && !tf_nombre.getText().isEmpty()
                && !tf_apellido.getText().isEmpty()
                && !tf_user.getText().isEmpty()
                && !tf_email.getText().isEmpty();

        btn_Finalizar.setDisable(!condicionesCumplidas);
    }

    @FXML
    private void cerrarventana(ActionEvent event) {

        Efectos_visuales.closeEffectReducion(root);
    }

    @FXML
    private void crearContrato(ActionEvent event) {

        Usuario user = new Usuario();
        user.setNombre(tf_nombre.getText());
        user.setApellidos(tf_apellido.getText());
        user.setUsuario(tf_user.getText());
        user.setEmail(tf_email.getText());

        Base_datos.RegistrarProductoContrato(user);

        String nombreCompleto = Base_datos.nombreUsuarioContrato();
        String Fecha = Base_datos.fechaContrato();

        Contrato.setUsuarioFinal(nombreCompleto, Fecha);
        generatePDF(Contrato.getContrato(), "contrato.pdf");
        
        Efectos_visuales.closeEffectReducion(root);
    }

}
