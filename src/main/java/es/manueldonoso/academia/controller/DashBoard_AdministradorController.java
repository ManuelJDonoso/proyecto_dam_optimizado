/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller;

import es.manueldonoso.academia.util.Base_datos;
import es.manueldonoso.academia.util.Efectos_visuales;
import es.manueldonoso.academia.util.Session;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class DashBoard_AdministradorController implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private ImageView iv_avatar;
    @FXML
    private AnchorPane ac_center;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Efectos_visuales.setBackgroundImage(root, "/images/app/fondo_pastel.jpg");
      if (Session.getPass() != null) {
            Base_datos.CambioContraseña(Base_datos.conectarSqlite(), Session.getUsuario(), Session.getPass());
            Session.setPass(null);
        }
      
        Efectos_visuales.modificarTamañoImagen(iv_avatar, 50,0.5);
Platform.runLater(() -> root.requestFocus());
    
    }

    Connection conn;

    public void SetConn(Connection conn) {
        this.conn = conn;
    }    

    @FXML
    private void btn_salir(ActionEvent event) {
        
        System.exit(0);
    }

    @FXML
    private void btn_MisDatos(ActionEvent event) {
    }

    @FXML
    private void btn_cerrar_Session(ActionEvent event) {
        Efectos_visuales.closeEffectReducion(root);
    }


    @FXML
    private void agregar_usuario(ActionEvent event) {
    }

    @FXML
    private void anadir_asignatura(ActionEvent event) {
    }

    @FXML
    private void mod_asignatura(ActionEvent event) {
    }

    @FXML
    private void BUSCAR_USUARIO(ActionEvent event) {
    }
}
