/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller;

import es.manueldonoso.academia.modelos.Material;
import es.manueldonoso.academia.modelos.Usuario;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class PanelMaterialController implements Initializable {

    private String nombreArchivo, rutaArchivo;
    private Connection conn;
    //---------------------------Tabla----------------------------
    @FXML
    private TableView<Material> tabla_Material;
    @FXML
    private TableColumn<Material, String> colum_Asignatura;
    @FXML
    private TableColumn<Material, String> colum_tema;
    @FXML
    private TableColumn<Material, String> column_archivo;
    @FXML
    private TableColumn<Material, Void> column_accion;

    private Usuario usuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

}
