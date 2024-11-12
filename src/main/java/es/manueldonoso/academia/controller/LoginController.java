/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import es.manueldonoso.academia.util.Efectos_visuales;
import es.manueldonoso.academia.util.Stage_show;
import es.manueldonoso.academia.util.Acciones;
import es.manueldonoso.academia.util.Base_datos;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class LoginController implements Initializable {

    @FXML
    private VBox root;
    @FXML
    private JFXButton btn_whatsapp;
    @FXML
    private JFXButton btn_link;
    @FXML
    private JFXButton btn_olvideContraseña;
    @FXML
    private JFXComboBox<Label> cb_selectBBDD;
    @FXML
    private JFXCheckBox ck_recorgar;
    @FXML
    private JFXButton btn_iniciar;
    @FXML
    private JFXTextField txt_nombre;
    @FXML
    private JFXPasswordField txt_pass;
    @FXML
    private JFXButton btn_registrar;
    @FXML
    private ImageView iv_base_conect;
    @FXML
    private JFXButton btn_Contrato;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Label lb1 = new Label("EMBEDIDA");
        Label lb2 = new Label("REMOTA");
        Label lb3 = new Label("RED");
        Label lb4 = new Label("LOCAL");

        // Configurar el color de texto para cada Label
        String labelStyle = "-fx-text-fill: #C0C0C0;";
        String labelStyle2 = "-fx-prompt-fill: #C0C0C0;";
        lb1.setStyle(labelStyle);
        lb2.setStyle(labelStyle);
        lb3.setStyle(labelStyle);
        lb4.setStyle(labelStyle);
        lb1.setStyle(labelStyle2);
        lb2.setStyle(labelStyle2);
        lb3.setStyle(labelStyle2);
        lb4.setStyle(labelStyle2);

        // Añadir los Labels al ComboBox
        cb_selectBBDD.getItems().addAll(lb1, lb2, lb3, lb4);

        System.out.println("Mostrando pantalla login");

        if (Acciones.existeArchivo("Contrato.pfd")) {
            btn_Contrato.setDisable(true);
            btn_registrar.setDisable(false);
        } else {
            btn_Contrato.setDisable(false);
            btn_registrar.setDisable(true);
        }
    }

    @FXML
    private void abrirweb(ActionEvent event) {
        Acciones.abrirWeb("https://www.manueldonoso.es");
    }

    @FXML
    private void Iniciar_Sesion(ActionEvent event) {
    }

    @FXML
    private void cargarContrato(ActionEvent event) {
        Stage_show.Mostrar_crearContrato();
    }

    @FXML
    private void btn_salir(ActionEvent event) {
        Efectos_visuales.closeEffectReducion(root);
    }

    @FXML
    private void MostrarContrato(ActionEvent event) {
        Acciones.abrirPDF("contrato.pdf");
    }

    @FXML
    private void btn_ComprobarConexion(ActionEvent event) {
        comprobarConexion();
    }

    private void comprobarConexion() {
        boolean conexion;
        switch (cb_selectBBDD.getValue().getText()) {
            case "EMBEDIDA":
                conexion = Base_datos.isConnection(Base_datos.conectarSqlite());
                break;
            case "REMOTA":
                conexion = Base_datos.isConnection(Base_datos.connectMysqlRemota());
                break;

            case "RED":
                conexion = Base_datos.isConnection(Base_datos.connectMysqlRed());
                break;

            case "LOCAL":
                conexion = Base_datos.isConnection(Base_datos.connectMysqlLocal());
                break;
            default:
                conexion = false;
        }

        if (conexion) {
            Acciones.imagenView_cambiarImage(this.getClass(), iv_base_conect, "src/main/resources/images/app/bbdd_ok.png");
        } else {
            Acciones.imagenView_cambiarImage(this.getClass(), iv_base_conect, "src/main/resources/images/app/bbdd_down.png");

        }
    }

}
