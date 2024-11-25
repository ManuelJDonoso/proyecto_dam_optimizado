/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller;

import com.jfoenix.controls.JFXButton;
import es.manueldonoso.academia.modelos.Pregunta;
import es.manueldonoso.academia.util.Session;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * Controlador para manejar las interacciones en la plantilla de preguntas.
 * Permite seleccionar una respuesta entre varias opciones y actualiza el color
 * del botón para indicar qué opción está seleccionada.
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Plantilla_preguntaController implements Initializable {

    @FXML
    private VBox root;

    @FXML
    private Text pregunta;

    @FXML
    private Text respuestaA;

    @FXML
    private Text respuestaB;

    @FXML
    private Text respuestaC;

    @FXML
    private Text respuestaD;

    private boolean selectA = false, selectB = false, selectC = false, selectD = false;

    private boolean acierto = false;

    private Pregunta p;

    @FXML
    private JFXButton btn_respuestaA;

    @FXML
    private JFXButton btn_b;

    @FXML
    private JFXButton btn_C;

    @FXML
    private JFXButton btn_D;

    @FXML
    private Text numero_pregunta;

    private BooleanProperty isAcierto = new SimpleBooleanProperty(false);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resetButtonStyles();
        puntuacion();
    }

    @FXML
    private void btn_respuestaA(ActionEvent event) {
        selectA = !selectA;
        selectB = selectC = selectD = false;
       
        setAcierto(p.isSol_A());
        updateButtonStyles();
    }

    @FXML
    private void btn_b(ActionEvent event) {
        selectB = !selectB;
        selectA = selectC = selectD = false;
        setAcierto(p.isSol_B());
        updateButtonStyles();
    }

    @FXML
    private void btn_C(ActionEvent event) {
        selectC = !selectC;
        selectA = selectB = selectD = false;
        setAcierto(p.isSol_C());
        updateButtonStyles();
    }

    @FXML
    private void btn_D(ActionEvent event) {
        selectD = !selectD;
        selectA = selectB = selectC = false;
        setAcierto(p.isSol_D());
        updateButtonStyles();
    }

    private void updateButtonStyles() {
        btn_respuestaA.setStyle(selectA ? "-fx-background-color: gray;" : "-fx-background-color: transparent;");
        btn_b.setStyle(selectB ? "-fx-background-color: gray;" : "-fx-background-color: transparent;");
        btn_C.setStyle(selectC ? "-fx-background-color: gray;" : "-fx-background-color: transparent;");
        btn_D.setStyle(selectD ? "-fx-background-color: gray;" : "-fx-background-color: transparent;");
    }

    private void resetButtonStyles() {
        btn_respuestaA.setStyle("-fx-background-color: transparent;");
        btn_b.setStyle("-fx-background-color: transparent;");
        btn_C.setStyle("-fx-background-color: transparent;");
        btn_D.setStyle("-fx-background-color: transparent;");
    }

    public void CargarPregunta(Pregunta pregunta) {
        this.numero_pregunta.setText(pregunta.getNumero() + "");
        this.pregunta.setText(pregunta.getPregunta());
        this.respuestaA.setText(pregunta.getRespuestaA());
        this.respuestaB.setText(pregunta.getRespuestaB());
        this.respuestaC.setText(pregunta.getRespuestaC());
        this.respuestaD.setText(pregunta.getRespuestaD());
        p = pregunta;
        isAcierto();
    }

    public boolean isAcierto() {
        return acierto;
    }

    public BooleanProperty isAciertoProperty() {
        return isAcierto;
    }

    private void setAcierto(boolean value) {
//            System.out.println("solucion resuesta A");
//            System.out.println(p.isSol_A());
//             System.out.println("solucion resuesta b");
//            System.out.println(p.isSol_B());
//             System.out.println("solucion resuesta c");
//            System.out.println(p.isSol_C());
//             System.out.println("solucion resuesta d");
//            System.out.println(p.isSol_D());
            System.out.println(p.toString());
        
        
        System.out.println(value);
        if (this.acierto != value) { // Solo actualizar si hay cambios
            this.acierto = value;
            isAcierto.set(value); // Actualizar la propiedad observable
        }
        
    }

    private void puntuacion() {
        isAcierto.addListener((observable, oldValue, newValue) -> {
            System.out.println("isAcierto cambió de " + oldValue + " a " + newValue);
            if (newValue) {
                Session.sumar_Punto(1);
              //  System.out.println("isAcierto es verdadero. Puntos sumados: +1");
            } else {
                Session.sumar_Punto(-1);
               // System.out.println("isAcierto es falso. Puntos restados: -1");
            }
        });
    }
}
