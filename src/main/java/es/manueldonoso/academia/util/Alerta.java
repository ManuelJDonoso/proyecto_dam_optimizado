/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academia.util;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * Clase utilitaria para mostrar diferentes tipos de alertas en la aplicación.
 * Proporciona métodos para mostrar alertas de error y confirmación con un
 * estilo personalizado.
 *
 * @author "Manuel Jesus Donoso Perez";
 */
public class Alerta {

    /**
     * Muestra una alerta de error con un mensaje y un diseño personalizado.
     *
     * @param titulo El título de la ventana de alerta.
     * @param barra El texto de la barra de encabezado.
     * @param mensaje El contenido del mensaje a mostrar.
     *
     * <p>
     * Este método utiliza la clase {@link Alert} de JavaFX con el tipo
     * {@code AlertType.ERROR}. Personaliza la ventana eliminando la barra de
     * título y ajustando la opacidad.</p>
     *
     * <p>
     * <strong>Ejemplo de uso:</strong></p>
     * <pre>
     *     Alerta.Error("Error crítico", "Fallo de conexión", "No se pudo conectar al servidor.");
     * </pre>
     */
    public static void Error(String titulo, String barra, String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(barra);
        alerta.setContentText(mensaje);

        // Obtener el Stage de la alerta
        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();

        // Eliminar la barra de título
        stage.initStyle(javafx.stage.StageStyle.UTILITY);  // Elimina la barra de título
        stage.setOpacity(0.95);  // Opcional: ajuste de opacidad para un estilo personalizado

        alerta.showAndWait();

    }

    /**
     * Muestra una alerta de confirmación y devuelve la respuesta del usuario.
     *
     * @param titulo El título de la ventana de alerta.
     * @param barra El texto de la barra de encabezado.
     * @param mensaje El contenido del mensaje a mostrar.
     * @return {@code true} si el usuario selecciona "OK", {@code false} en caso
     * contrario.
     *
     * <p>
     * Este método utiliza la clase {@link Alert} de JavaFX con el tipo
     * {@code AlertType.CONFIRMATION}. Personaliza la ventana eliminando la
     * barra de título y ajustando la opacidad.</p>
     *
     * <p>
     * <strong>Ejemplo de uso:</strong></p>
     * <pre>
     *     boolean confirmado = Alerta.Confirmacion("Salir", "Confirmación requerida", "¿Está seguro de salir?");
     *     if (confirmado) {
     *         System.exit(0);
     *     }
     * </pre>
     */
    public static boolean Confirmacion(String titulo, String barra, String mensaje) {
        Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(barra);
        alerta.setContentText(mensaje);

        // Obtener el Stage de la alerta
        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();

        // Eliminar la barra de título
        stage.initStyle(javafx.stage.StageStyle.UTILITY);  // Elimina la barra de título
        stage.setOpacity(0.95);  // Opcional: ajuste de opacidad para un estilo personalizado

        Optional<ButtonType> result = alerta.showAndWait();

        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
