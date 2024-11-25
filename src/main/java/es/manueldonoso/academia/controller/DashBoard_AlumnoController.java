/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academia.controller;

import es.manueldonoso.academia.controller.DashBoard_ProfesorController;
import es.manueldonoso.academia.modelos.Pregunta;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class DashBoard_AlumnoController implements Initializable {
    
    @FXML
    private BorderPane root;
    @FXML
    private ImageView iv_avatar;
    @FXML
    private AnchorPane ac_center;
    
    private Connection conn;
    private Usuario user;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Efectos_visuales.setBackgroundImage(root, "/images/app/fondo_pastel.jpg");
        if (Session.getPass() != null) {
            Base_datos.CambioContraseña(Base_datos.conectarSqlite(), Session.getUsuario(), Session.getPass());
            Session.setPass(null);
        }
        
        Efectos_visuales.modificarTamañoImagen(iv_avatar, 50, 0.5);
        Platform.runLater(() -> root.requestFocus());
        
    }
    
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
    private void btn_Temario(ActionEvent event) {
        Stage_show.cargar_MaterialPanelAlumno(conn, ac_center, user);
    }
    
    @FXML
    private void btn_examenes(ActionEvent event) {
     Stage_show.cargar_Panel_Examen(ac_center, conn,user);
    }
    
    public void CargarUsuario() {
        String usuario = Session.getUsuario();
        
        try {
            user = Base_datos.BuscarUsuario_Usuario(conn, usuario);
            Acciones.imagenView_cambiarImage(this.getClass(), iv_avatar, user.getFoto());
            
        } catch (SQLException ex) {
            Logger.getLogger(DashBoard_ProfesorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
