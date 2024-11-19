/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller;

import com.jfoenix.controls.JFXTextField;
import es.manueldonoso.academia.modelos.Usuario;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class BuscarUsuarioController implements Initializable {

    @FXML
    private VBox root;
    @FXML
    private JFXTextField tf_Nombre;
    @FXML
    private JFXTextField tf_Apellidos;
    @FXML
    private JFXTextField tf_Direccion;
    @FXML
    private JFXTextField tf_Email;
    @FXML
    private JFXTextField tf_Telefono;
    @FXML
    private JFXTextField tf_Usuario;
    @FXML
    private TableView<Usuario> tabla_admin;
    @FXML
    private TableColumn<Usuario, String> colum_usuario_admin;
    @FXML
    private TableColumn<Usuario, Void> columna_Accion_administrador;
    @FXML
    private TableView<Usuario> tabla_profesor;
    @FXML
    private TableColumn<Usuario, String> usuario_profesor;
    @FXML
    private TableColumn<Usuario, Void> accion_profesor;
    @FXML
    private TableView<Usuario> tabla_alumnoa;
    @FXML
    private TableColumn<Usuario, String> Usuario_alumno;
    @FXML
    private TableColumn<Usuario, Void> accion_alumno;
    
    private Connection conn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buscarUsuarios(KeyEvent event) {
    }

    @FXML
    private void btn_cerrar(ActionEvent event) {
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
}
