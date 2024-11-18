/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import es.manueldonoso.academia.util.Base_datos;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Asignaturas_PanelController implements Initializable {

    @FXML
    private VBox root;
    @FXML
    private JFXTextField tf_Asignatura;

    Connection conn;
    @FXML
    private VBox panel_Inactiva;
    @FXML
    private VBox panel_Activa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btn_agregar(ActionEvent event) {

        if (!tf_Asignatura.getText().trim().isBlank()) {
            Base_datos.AltaAsignatura(conn, tf_Asignatura.getText());
            CargarAct();
            tf_Asignatura.setText("");
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void CargarAct(){
       panel_Activa.getChildren().clear();
    // Crear botones a partir de la lista de asignaturas activas
    for (String nombreBoton : Base_datos.obtenerAsignaturasActivas(conn)) {
        JFXButton boton = new JFXButton(nombreBoton);
        
        // Agregar el evento para desactivar la asignatura
        boton.setOnAction(e -> {
            Base_datos.desactivarAsignatura(conn, nombreBoton);
            CargarAct(); // Actualizar las asignaturas activas
            CargarDes(); // Actualizar las asignaturas inactivas
            System.out.println("Botón " + nombreBoton + " presionado");
        });

        // Añadir el botón al panel correspondiente
        panel_Activa.getChildren().add(boton);
    }
    }
    
     public void CargarDes(){
        panel_Inactiva.getChildren().clear();
    // Crear botones a partir de la lista de asignaturas inactivas
    for (String nombreBoton : Base_datos.obtenerAsignaturasInActivas(conn)) {
        JFXButton boton = new JFXButton(nombreBoton);
        
        // Agregar el evento para activar la asignatura
        boton.setOnAction(e -> {
            Base_datos.activarAsignatura(conn, nombreBoton);
            CargarAct(); // Actualizar las asignaturas activas
            CargarDes(); // Actualizar las asignaturas inactivas
            System.out.println("Botón " + nombreBoton + " presionado");
        });

        // Añadir el botón al panel correspondiente
        panel_Inactiva.getChildren().add(boton);
    }
    }
    
}
