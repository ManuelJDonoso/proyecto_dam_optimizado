/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academia.util;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Efectos_visuales {

    /**
     * Cierra la ventana aplicando un efecto de reducción de escala en el nodo
     * especificado.
     *
     * <p>
     * Este método aplica una transición de escala a un nodo, reduciéndolo a un
     * tamaño de 0 en los ejes X, Y y Z en el transcurso de 500 milisegundos.
     * Una vez completada la animación, la ventana asociada al nodo se cierra
     * automáticamente.</p>
     *
     * @param node el nodo en cuya ventana se aplicará el efecto de cierre
     */
    public static void closeEffectReducion(Node node) {
        final Stage stage = (Stage) node.getScene().getWindow();
        ScaleTransition st = new ScaleTransition(Duration.millis(500), node);
        st.setToX(0);
        st.setToY(0);
        st.setToZ(0);
        st.play();
        st.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                stage.close();
            }
        });
    }

    private static double xOffset = 0;
    private static double yOffset = 0;

    /**
     * Permite mover la ventana (Stage) haciendo clic y arrastrando el mouse
     * sobre la escena.
     *
     * <p>
     * Este método establece dos manejadores de eventos en la escena de la
     * ventana especificada: uno para registrar la posición inicial del clic del
     * mouse y otro para mover la ventana según el movimiento del mouse,
     * calculando el desplazamiento basado en la posición inicial.</p>
     *
     * @param stage la ventana (Stage) que se podrá mover con el mouse
     */
    public static void darMovimientoStage(Stage stage) {

        stage.getScene().setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        stage.getScene().setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }
}
