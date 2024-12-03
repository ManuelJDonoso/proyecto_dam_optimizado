/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller;

import es.manueldonoso.academia.modelos.Usuario;
import es.manueldonoso.academia.util.Acciones;
import es.manueldonoso.academia.util.Base_datos;
import es.manueldonoso.academia.util.Efectos_visuales;
import es.manueldonoso.academia.util.Session;
import es.manueldonoso.academia.util.Stage_show;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private Usuario user;

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

        Efectos_visuales.modificarTamañoImagen(iv_avatar, 50, 0.5);
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
        try {
            if (Stage_show.Mostrar_Mis_Datos(conn, root, user)) {
                CargarUsuario();
            }
        } catch (IOException ex) {
            Logger.getLogger(DashBoard_ProfesorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btn_cerrar_Session(ActionEvent event) {
        Efectos_visuales.closeEffectReducion(root);
    }

    @FXML
    private void agregar_usuario(ActionEvent event) {
        Efectos_visuales.efectoGaussian(root, 7);
        Stage_show.Mostrar_Registrar_usuario_nuevo(conn, root);
        Efectos_visuales.eliminarEfecto(root);
    }

    @FXML
    private void anadir_asignatura(ActionEvent event) {
        Stage_show.cargar_asignaturaPanel(conn,ac_center);
    }


    @FXML
    private void BUSCAR_USUARIO(ActionEvent event) {
        Stage_show.cargar_BuscarUsuaroPanel(conn, ac_center);
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public void CargarUsuario() {

        try {
            user = Base_datos.BuscarUsuario_Usuario(conn, Session.getUsuario());
            Acciones.imagenView_cambiarImage(this.getClass(), iv_avatar, user.getFoto());

        } catch (SQLException ex) {
            Logger.getLogger(DashBoard_ProfesorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
