/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller;

import com.jfoenix.controls.JFXComboBox;
import es.manueldonoso.academia.modelos.Pregunta;
import es.manueldonoso.academia.modelos.Usuario;
import es.manueldonoso.academia.util.Base_datos;
import es.manueldonoso.academia.util.Session;
import es.manueldonoso.academia.util.Stage_show;
import java.net.URL;
import java.sql.Connection;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Panel_ExamenMenuController implements Initializable {

    @FXML
    private VBox root;
    @FXML
    private JFXComboBox<String> cb_Asignatura;
    @FXML
    private JFXComboBox<String> cb_Tema;
    @FXML
    private VBox Panel_Preguntas;
    private Connection conn;
    private Usuario user;
    private int acierto = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btn_generar_examen(ActionEvent event) {
        Session.setPuntuacion(0);
        Pregunta p;
        String pregunta, respuestaV, respuesta1, respuesta2, respuesta3;
        Panel_Preguntas.getChildren().clear();

        if (cb_Tema.getValue() != null) {
            Map<Integer, Map<String, String>> examen = Base_datos.Examen(conn, cb_Asignatura.getValue(), cb_Tema.getValue());

            for (int i = 0; i < examen.size(); i++) {
                Map<String, String> preguntaselec = examen.get(i + 1);
                pregunta = preguntaselec.get("Pregunta");
                respuestaV = preguntaselec.get("correcta");
                respuesta1 = preguntaselec.get("incorrecta1");
                respuesta2 = preguntaselec.get("incorrecta2");
                respuesta3 = preguntaselec.get("incorrecta3");

//                System.out.println(i+1);
//                System.out.println(pregunta);
//                System.out.println(respuestaV);
//                System.out.println(respuesta1);
//                System.out.println(respuesta2);
//                 System.out.println(respuesta3);
                 
                // Crea una nueva pregunta
              
                
              //  p = new Pregunta(i + 1, pregunta, respuestaV, respuesta1, respuesta2, respuesta3);
               p = new Pregunta(i + 1, "pre", "verdad", "false", "falsa", "falsa");

             //    Carga la plantilla de la pregunta
                Stage_show.cargar_Pregunta(Panel_Preguntas, p);
                System.out.println(p.toString());

            }
        }
    }

    @FXML
    private void btn_Corregir(ActionEvent event) {
        // Muestra el total de aciertos al usuario
        Alert corregir = new Alert(Alert.AlertType.CONFIRMATION);
        corregir.setTitle("Corregir el examen");
        corregir.setHeaderText(null);
        corregir.setContentText("¿Desea Finalizar el examen?");
        corregir.showAndWait();

        if (corregir.getResult() == ButtonType.OK) {
            String Asignatura=cb_Asignatura.getValue();
            String Nota=Session.getPuntuacion()+"";
            String Tema=cb_Tema.getValue();
            String usuario=user.getUsuario();
           
            Base_datos.GuardarExamenRealizado(conn, Asignatura, Tema, Nota, usuario);
            // Muestra el total de aciertos al usuario
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resultados del Examen");
            alert.setHeaderText(null);
            alert.setContentText("Has tenido " + Session.getPuntuacion() + " acierto(s).");
            alert.showAndWait();
        }

    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public void loadDatos() {

        ObservableList<String> asignaturas = FXCollections.observableList(user.getAsignaturas());
        cb_Asignatura.setItems(asignaturas);

        // Agregar un Listener al valueProperty()
        cb_Asignatura.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                ObservableList<String> temas = FXCollections.observableList(Base_datos.BuscarExamenes(conn, newValue));
                System.out.println("Elemento seleccionado: " + newValue);
                cb_Tema.setItems(temas);
            }
        });
    }

}
