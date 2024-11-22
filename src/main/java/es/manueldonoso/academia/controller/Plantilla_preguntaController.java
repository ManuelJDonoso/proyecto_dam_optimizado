/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller;

import com.jfoenix.controls.JFXButton;
import es.manueldonoso.academia.modelos.Pregunta;
import java.net.URL;
import java.util.ResourceBundle;
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

    private boolean selectA = false, selectB = false, selectC = false, selectD = false, acierto = false;

    private Pregunta p;

    @FXML
    private JFXButton btn_respuestaA;

    @FXML
    private JFXButton btn_b;

    @FXML
    private JFXButton btn_C;

    @FXML
    private JFXButton btn_D;

    /**
     * Inicializa el controlador de clase. Este método es llamado
     * automáticamente después de cargar los elementos FXML.
     *
     * @param url el recurso utilizado para resolver rutas relativas para el
     * objeto raíz o null si no se conoce.
     * @param rb el paquete de recursos utilizado para localizar el objeto raíz
     * o null si no se utiliza.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resetButtonStyles();
    }

    /**
     * Maneja el evento de selección del botón asociado a la respuesta A.
     *
     * @param event el evento que activa la acción.
     */
    @FXML
    private void btn_respuestaA(ActionEvent event) {
        selectA = !selectA;
        selectB = selectC = selectD = false;
        acierto = p.isSol_A();
        updateButtonStyles();
    }

    /**
     * Maneja el evento de selección del botón asociado a la respuesta B.
     *
     * @param event el evento que activa la acción.
     */
    @FXML
    private void btn_b(ActionEvent event) {
        selectB = !selectB;
        selectA = selectC = selectD = false;
        acierto = p.isSol_B();
        updateButtonStyles();
    }

    /**
     * Maneja el evento de selección del botón asociado a la respuesta C.
     *
     * @param event el evento que activa la acción.
     */
    @FXML
    private void btn_C(ActionEvent event) {
        selectC = !selectC;
        selectA = selectB = selectD = false;
        acierto = p.isSol_C();
        updateButtonStyles();
    }

    /**
     * Maneja el evento de selección del botón asociado a la respuesta D.
     *
     * @param event el evento que activa la acción.
     */
    @FXML
    private void btn_D(ActionEvent event) {
        selectD = !selectD;
        selectA = selectB = selectC = false;
        acierto = p.isSol_D();
        updateButtonStyles();
    }

    /**
     * Actualiza los estilos de los botones para reflejar el estado de
     * selección. Cambia el color de fondo del botón seleccionado a gris y los
     * demás a transparente.
     */
    private void updateButtonStyles() {
        btn_respuestaA.setStyle(selectA ? "-fx-background-color: gray;" : "-fx-background-color: transparent;");
        btn_b.setStyle(selectB ? "-fx-background-color: gray;" : "-fx-background-color: transparent;");
        btn_C.setStyle(selectC ? "-fx-background-color: gray;" : "-fx-background-color: transparent;");
        btn_D.setStyle(selectD ? "-fx-background-color: gray;" : "-fx-background-color: transparent;");
    }

    /**
     * Restablece los estilos de los botones a su estado inicial (fondo
     * transparente).
     */
    private void resetButtonStyles() {
        btn_respuestaA.setStyle("-fx-background-color: transparent;");
        btn_b.setStyle("-fx-background-color: transparent;");
        btn_C.setStyle("-fx-background-color: transparent;");
        btn_D.setStyle("-fx-background-color: transparent;");
    }

    /**
     * Carga una pregunta en la interfaz y establece el texto de cada respuesta.
     *
     * @param pregunta la pregunta a cargar, que contiene el texto de la
     * pregunta y las posibles respuestas.
     */
    public void CargarPregunta(Pregunta pregunta) {
        this.pregunta.setText(pregunta.getPregunta());
        this.respuestaA.setText(pregunta.getRespuestaA());
        this.respuestaB.setText(pregunta.getRespuestaB());
        this.respuestaC.setText(pregunta.getRespuestaC());
        this.respuestaD.setText(pregunta.getRespuestaD());
        p = pregunta;
    }

    public boolean getRespuesta() {
        return acierto;
    }
}
