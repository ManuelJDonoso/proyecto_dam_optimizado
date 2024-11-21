/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import es.manueldonoso.academia.modelos.Usuario;
import es.manueldonoso.academia.util.Acciones;
import es.manueldonoso.academia.util.Base_datos;
import es.manueldonoso.academia.util.Stage_show;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Panel_generar_ExamenController implements Initializable {

    @FXML
    private JFXComboBox<String> cb_asignatura;
    @FXML
    private JFXTextField tf_Nombre_Tema;
    @FXML
    private JFXComboBox<String> cb_cantidad;
    @FXML
    private ScrollPane root;
    @FXML
    private VBox panel_preguntas;

    Connection conn;
    Usuario user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btn_generar(ActionEvent event) {
        panel_preguntas.getChildren().clear();
        for (int i = 0; i < Integer.parseInt(cb_cantidad.getValue()); i++) {
             Stage_show.anadirPanelPreguntas(panel_preguntas,i+1);
        }
       
        
      
 
    }

    @FXML
    private void btn_guardar(ActionEvent event) {
        List<Map<String, String>> respuestas = Acciones.capturarContenidoTextAreas(panel_preguntas);
        
        Base_datos.guardarExamenGeneradoEnBaseDeDatos(conn,  cb_asignatura.getValue(), tf_Nombre_Tema.getText(), respuestas);
     
        
     }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void cargarCB(Usuario usuario) {

        cb_asignatura.getItems().addAll(usuario.getAsignaturas());
        user = usuario;
        for (int i = 0; i < 100; i++) {
            int preguntas = i + 1;
            cb_cantidad.getItems().add(preguntas + "");
        }
    }
    
    private void imprimirJerarquiaNodos(Node nodo, String prefijo) {
    System.out.println(prefijo + nodo.getClass().getSimpleName() + " (ID: " + nodo.getId() + ")");
    if (nodo instanceof Pane) {
        for (Node child : ((Pane) nodo).getChildren()) {
            imprimirJerarquiaNodos(child, prefijo + "  ");
        }
    }
}
}
